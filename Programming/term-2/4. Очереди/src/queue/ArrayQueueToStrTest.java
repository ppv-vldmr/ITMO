package queue;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ArrayQueueToStrTest extends ArrayQueueTest<ArrayQueueToStrTest.ToStrQueue> {
    public ArrayQueueToStrTest() {
        super(ToStrQueue.class, ReferenceToStrQueue::new);
    }

    public static void main(final String[] args) {
        new ArrayQueueToStrTest().test();
    }

    @Override
    protected List<ToStrQueue> linearTest(final ToStrQueue queue) {
        queue.toStr();
        return List.of();
    }

    protected interface ToStrQueue extends Queue {
        String toStr();
    }

    protected static class ReferenceToStrQueue extends ReferenceQueue implements ToStrQueue {
        public ReferenceToStrQueue(final Stream<Object> elements) {
            super(elements);
        }

        @Override
        public String toStr() {
            return deque.toString();
        }
    }
}