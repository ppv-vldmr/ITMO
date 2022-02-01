import kotlinx.atomicfu.*
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

class DynamicArrayImpl<T> : DynamicArray<T> {
    private val head: AtomicRef<Core<T>> = atomic(Core(INITIAL_CAPACITY, 0, null))

    override fun get(index: Int): T {
        val curHead = head.value
        if (index >= curHead.len.value) {
            throw IllegalArgumentException()
        }
        val x = curHead.array[index].value
        when {
            x != null -> return x
            else -> throw RuntimeException("unexpected case")
        }
    }

    override fun put(index: Int, element: T) {
        var curHead = head.value
        if (index >= curHead.len.value) {
            throw IllegalArgumentException()
        }
        curHead.array[index].getAndSet(element)
        while (true) {
            val nextNode = curHead.next.value
            when {
                nextNode != null -> {
                    val y = curHead.array[index].value
                    if (y != null) {
                        nextNode.array[index].getAndSet(y)
                    }
                    curHead = nextNode
                }
                else -> return
            }
        }
    }

    override fun pushBack(element: T) {
        while (true) {
            val curHead = head.value
            val curSize = curHead.len.value
            if (curSize < curHead.getCapacity()) {
                if (curHead.array[curSize].compareAndSet(null, element)) {
                    curHead.len.compareAndSet(curSize, curSize + 1)
                    return
                } else {
                    curHead.len.compareAndSet(curSize, curSize + 1)
                }
            } else {
                val newNode =
                    Core<T>(2 * curHead.getCapacity(), curHead.getCapacity(), null)
                if (curHead.next.compareAndSet(null, newNode)) {
                    make(curHead, newNode)
                } else {
                    val nextNode = curHead.next.value
                    if (nextNode != null) {
                        make(curHead, nextNode)
                    }
                }
            }
        }
    }

    private fun make(curHead: Core<T>, nextNode: Core<T>) {
        for (i in 1..curHead.getCapacity()) {
            val y = curHead.array[i - 1].value
            if (y != null) {
                nextNode.array[i - 1].compareAndSet(null, y)
            }
        }
        head.compareAndSet(curHead, nextNode)
    }

    override val size: Int
        get() {
            return head.value.len.value
        }
}

private const val INITIAL_CAPACITY = 1 // DO NOT CHANGE ME

class Core<T>(private val capacity: Int, length: Int, next: Core<T>?) {
    val array: AtomicArray<T?> = atomicArrayOfNulls<T>(capacity)
    val len: AtomicInt = atomic(length)
    val next: AtomicRef<Core<T>?> = atomic(next)

    fun getCapacity(): Int {
        return capacity
    }
}