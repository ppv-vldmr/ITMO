import java.util.concurrent.locks.ReentrantLock


/**
 * Bank implementation.
 *
 *
 * :
 *
 * @author : Popov Vladimir
 */
/**
 * Creates new bank instance.
 *
 * @param n the number of accounts (numbered from 0 to n-1).
 */
class BankImpl (n: Int) : Bank {
    /**
     * An array of accounts by index.
     */
    private val accounts: Array<Account> = Array(n) { Account() }

    override val numberOfAccounts: Int
        get() = accounts.size

    /**
     *
     * :TODO: DONE
     */
    override val totalAmount: Long
        get() {
            try {
                var total: Long = 0
                for (acc in accounts) {
                    acc.lock.lock()
                    total += acc.amount
                }
                return total
            } finally {
                for (acc in accounts) {
                    acc.lock.unlock()
                }
            }
        }

    /**
     *
     * :TODO: DONE
     */
    override fun getAmount(index: Int): Long {
        accounts[index].lock.lock()
        try {
            return accounts[index].amount
        } finally {
            accounts[index].lock.unlock()
        }
    }

    /**
     *
     * :TODO: DONE
     */
    override fun deposit(index: Int, amount: Long): Long {
        require(amount > 0) { "Invalid amount: $amount" }
        val acc = accounts[index]
        acc.lock.lock()
        try {
            check(!(amount > Bank.MAX_AMOUNT || acc.amount + amount > Bank.MAX_AMOUNT)) { "Overflow" }
            acc.amount += amount
            return acc.amount
        } finally {
            acc.lock.unlock()
        }
    }

    /**
     *
     * :TODO: DONE
     */
    override fun withdraw(index: Int, amount: Long): Long {
        require(amount > 0) { "Invalid amount: $amount" }
        val acc = accounts[index]
        acc.lock.lock()
        try {
            check(acc.amount - amount >= 0) { "Underflow" }
            acc.amount -= amount
            return acc.amount
        } finally {
            acc.lock.unlock()
        }
    }

    /**
     *
     * :TODO: DONE.
     */
    override fun transfer(fromIndex: Int, toIndex: Int, amount: Long) {
        require(amount > 0) { "Invalid amount: $amount" }
        require(fromIndex != toIndex) { "fromIndex == toIndex" }
        val fromAcc = accounts[fromIndex]
        val toAcc = accounts[toIndex]
        if (fromIndex < toIndex) {
            fromAcc.lock.lock()
            toAcc.lock.lock()
        } else {
            toAcc.lock.lock()
            fromAcc.lock.lock()
        }
        try {
            check(amount <= fromAcc.amount) { "Underflow" }
            check(!(amount > Bank.MAX_AMOUNT || toAcc.amount + amount > Bank.MAX_AMOUNT)) { "Overflow" }
            fromAcc.amount -= amount
            toAcc.amount += amount
        } finally {
            if (fromIndex < toIndex) {
                toAcc.lock.unlock()
                fromAcc.lock.unlock()
            } else {
                fromAcc.lock.unlock()
                toAcc.lock.unlock()
            }
        }
    }

    /**
     * Private account data structure.
     */
    internal class Account {
        /**
         * Amount of funds in this account.
         */
        var lock = ReentrantLock()
        var amount: Long = 0
    }
}