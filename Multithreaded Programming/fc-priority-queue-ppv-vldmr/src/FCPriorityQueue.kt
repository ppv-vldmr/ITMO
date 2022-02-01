import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.atomicArrayOfNulls
import java.util.*

class FCPriorityQueue<E : Comparable<E>> {
    private val q = PriorityQueue<E>()
    private val size = 10 * Runtime.getRuntime().availableProcessors()
    private val combinerArray = atomicArrayOfNulls<Operation<E>>(size)
    private val lock = atomic<Boolean>(false)
    private val DEBUG = false

    private fun fortify(operation: Operation<E>): Operation<E> {
        when (operation.operation) {
            "POLL" -> operation.element = q.poll()
            "ADD" -> q.add(operation.element)
        }
        return Operation(operation.element, "DONE")
    }

    private fun combine() {
        if (!lock.compareAndSet(expect = false, update = true)) return
        for (i in 0 until size) {
            val operation = combinerArray[i]
            val real = operation.value ?: continue
            if (real.operation == "POLL" || real.operation == "ADD") {
                if (combinerArray[i].compareAndSet(real, Operation(real.element, "PROGRESS"))) {
                    val result = fortify(real)
                    combinerArray[i].getAndSet(result)
                }
            }
        }
        lock.compareAndSet(expect = true, update = false)
    }

    private fun addOp(operation: Operation<E>): E? {
        var realPosition = -1
        while (true) {
            if (realPosition == -1) {
                for (position in 0 until size) {
                    if (combinerArray[position].compareAndSet(null, operation)) {
                        realPosition = position
                        break
                    }
                }
            } else {
                if (!lock.value) {
                    combine()
                }
                val result = combinerArray[realPosition].value!!
                if (result.operation == "DONE") {
                    combinerArray[realPosition].compareAndSet(result, null)
                    return result.element
                }
            }
        }
    }

    /**
     * Retrieves the element with the highest priority
     * and returns it as the result of this function;
     * returns `null` if the queue is empty.
     */
    fun poll(): E? {
        return addOp(Operation<E>(null, "POLL"))
    }

    /**
     * Returns the element with the highest priority
     * or `null` if the queue is empty.
     */
    fun peek(): E? {
        return q.peek()
    }

    /**
     * Adds the specified element to the queue.
     */
    fun add(element: E) {
        addOp(Operation(element, "ADD"))
    }
}

class Operation<E>(var element: E?, val operation: String)