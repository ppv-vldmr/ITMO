package dijkstra

import java.util.*
import java.util.concurrent.Phaser
import kotlin.Comparator
import kotlin.concurrent.thread
import kotlinx.atomicfu.atomic

private val COMPARATOR_OF_DIST = Comparator<Node> { o1, o2 -> o1!!.distance.compareTo(o2!!.distance) }

fun shortestPathParallel(start: Node) {
    val workers = Runtime.getRuntime().availableProcessors()
    start.distance = 0
    val queue = PriorityMultiQueue(workers, COMPARATOR_OF_DIST)
    queue.push(start)
    val ifFinished = Phaser(workers + 1)
    repeat(workers) {
        thread {
            while (true) {
                val cur: Node = queue.myGet() ?: if (queue.isEmpty()) break else continue
                for (e in cur.outgoingEdges) {
                    do {
                        val old = e.to.distance
                        val updated = cur.distance + e.weight
                        if (old <= updated || !e.to.casDistance(old, updated)) continue
                        queue.push(e.to)
                        break
                    } while (old > updated)
                }
                queue.decrement()
            }
            ifFinished.arrive()
        }
    }
    ifFinished.arriveAndAwaitAdvance()
}

private class PriorityMultiQueue(val workers: Int, comparator: Comparator<Node>) {
    val length = atomic(0)
    val random = Random(0)
    val localQueue: MutableList<PriorityQueue<Node>> = Collections.nCopies(workers, PriorityQueue(comparator))

    fun myGet(): Node? {
        val from = random.nextInt(workers)
        val to = (from + 1) % workers
        synchronized(localQueue[from]) {
            synchronized(localQueue[to]) {
                if (localQueue[from].peek() != null) {
                    if (localQueue[to].peek() != null) {
                        return if (localQueue[from].peek().distance < localQueue[to].peek().distance) localQueue[from].poll() else localQueue[to].poll()
                    }
                    return localQueue[from].peek()
                }
                return localQueue[to].peek()
            }
        }
    }

    fun push(element: Node) {
        val randomizedIndex = random.nextInt(workers)
        synchronized(localQueue[randomizedIndex]) {
            localQueue[randomizedIndex].add(element)
        }
        length.incrementAndGet()
    }

    fun isEmpty(): Boolean {
        return length.compareAndSet(0, 0)
    }

    fun decrement() {
        length.decrementAndGet()
    }
}