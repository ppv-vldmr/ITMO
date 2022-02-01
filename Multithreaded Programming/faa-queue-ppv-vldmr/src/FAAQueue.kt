import kotlinx.atomicfu.*

class FAAQueue<T> {
    private val head: AtomicRef<Segment>
    private val tail: AtomicRef<Segment>

    init {
        val firstNode = Segment()
        head = atomic(firstNode)
        tail = atomic(firstNode)
    }

    fun enqueue(x: T) {
        while (true) {
            val tail: Segment = tail.value
            val enqIdx: Int = tail.enqIdx.getAndIncrement()
            if (enqIdx >= SEGMENT_SIZE) {
                val newTail = Segment(x)
                if (tail.next.compareAndSet(null, newTail)) {
                    this.tail.compareAndSet(tail, newTail)
                    return
                }
                this.tail.compareAndSet(tail, tail.next.value!!)
            } else if (tail.elements[enqIdx].compareAndSet(null, x)) {
                return
            }
        }
    }

    fun dequeue(): T? {
        while (true) {
            val curHead = head.value
            val deqIdx = curHead.deqIdx.getAndIncrement()
            if (deqIdx >= SEGMENT_SIZE) {
                val nextHead = curHead.next.value ?: return null
                head.compareAndSet(curHead, nextHead)
                continue
            }
            val res = curHead.elements[deqIdx].getAndSet(DONE) ?: continue
            return res as T?
        }
    }

    val isEmpty: Boolean = false
}

private class Segment {
    val next: AtomicRef<Segment?> = atomic(null)
    val deqIdx: AtomicInt
    val enqIdx: AtomicInt
    val elements: AtomicArray<Any?> = atomicArrayOfNulls(SEGMENT_SIZE)

    constructor() { // for the first segment creation
        enqIdx = atomic(0)
        deqIdx = atomic(0)
    }

    constructor(x: Any?) {
        enqIdx = atomic(1)
        deqIdx = atomic(0)
        elements[0].getAndSet(x)
    }

}

private val DONE = Any()
const val SEGMENT_SIZE = 2