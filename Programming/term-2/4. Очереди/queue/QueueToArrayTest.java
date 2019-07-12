package queue;

import queue.ArrayQueueToArrayTest.ReferenceToArrayQueue;
import queue.ArrayQueueToArrayTest.ToArrayQueue;

import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class QueueToArrayTest extends QueueTest<ToArrayQueue> {
    public QueueToArrayTest() {
        super(ToArrayQueue.class, ReferenceToArrayQueue::new);
    }

    @Override
    protected List<ToArrayQueue> linearTest(final ToArrayQueue queue) {
        queue.toArray();
        return List.of();
    }

    public static void main(final String[] args) {
        new QueueToArrayTest().test();
    }
}