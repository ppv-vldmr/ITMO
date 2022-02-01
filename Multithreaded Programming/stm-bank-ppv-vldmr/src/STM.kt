import kotlinx.atomicfu.*

fun <T> atomic(block: TxScope.() -> T): T {
    while (true) {
        val transaction = Transaction()
        try {
            val result = block(transaction)
            if (transaction.commit()) return result
            transaction.abort()
        } catch (e: AbortException) {
            transaction.abort()
        }
    }
}

abstract class TxScope {
    abstract fun <T> TxVar<T>.read(): T
    abstract fun <T> TxVar<T>.write(x: T): T
}

class TxVar<T>(initial: T) {
    private val loc = atomic(Loc(initial, initial, rootTx))

    fun openIn(tx: Transaction, update: (T) -> T): T {
        while (true) {
            val curLoc = loc.value
            val curValue = curLoc.valueIn(tx) { it.abort()  }
            if (curValue === TxStatus.ACTIVE) continue
            val updValue = update(curValue as T)
            val updLoc = Loc(curValue, updValue, tx)
            if (loc.compareAndSet(curLoc, updLoc)) {
                if (tx.status == TxStatus.ABORTED) throw AbortException
                return updValue
            }
        }
    }

    private fun contention(tx: Transaction, owner: Transaction) {
        owner.abort()
    }
}

private class Loc<T>(val oldValue: T, val newValue: T, val owner: Transaction) {
    fun valueIn(tx: Transaction, onActive: (Transaction) -> Unit): Any? {
        return if (owner === tx) {
            newValue
        } else {
            when (owner.status) {
                TxStatus.ABORTED -> oldValue
                TxStatus.COMMITTED -> newValue
                TxStatus.ACTIVE -> {
                    onActive(owner)
                    TxStatus.ACTIVE
                }
            }
        }
    }
}

private val rootTx = Transaction().apply { commit() }

enum class TxStatus { ACTIVE, COMMITTED, ABORTED }

class Transaction : TxScope() {
    private val _status = atomic(TxStatus.ACTIVE)
    val status: TxStatus get() = _status.value

    fun commit(): Boolean =
        _status.compareAndSet(TxStatus.ACTIVE, TxStatus.COMMITTED)

    fun abort() {
        _status.compareAndSet(TxStatus.ACTIVE, TxStatus.ABORTED)
    }

    override fun <T> TxVar<T>.read(): T = openIn(this@Transaction) { it }
    override fun <T> TxVar<T>.write(x: T) = openIn(this@Transaction) { x }
}

private object AbortException : Exception() {
    override fun fillInStackTrace(): Throwable = this
}