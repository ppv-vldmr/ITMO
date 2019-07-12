package queue;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class QueueTest<T extends ArrayQueueTest.Queue> extends ArrayQueueTest<T> {
    public QueueTest(final Class<T> type, final Function<Stream<Object>, T> reference) {
        super(type, reference);
    }

    public static void main(final String[] args) {
        new QueueTest<>(Queue.class, ReferenceQueue::new).test();
    }

    public void test() {
        test("LinkedQueue", 2, Mode.CLASS);
        test("ArrayQueue", 2, Mode.CLASS);
    }

    private static boolean implementsQueue(final Class<?> type) {
        return type != Object.class
                && (Stream.of(type.getInterfaces()).map(Class::getName).anyMatch("queue.Queue"::equals)
                    || implementsQueue(type.getSuperclass()));
    }

    @Override
    protected void checkImplementation(final Class<?> type) {
        assertTrue(type + " should extend AbstractQueue", "queue.AbstractQueue".equals(type.getSuperclass().getName()));
        assertTrue(type + " should implement interface Queue", implementsQueue(type));
    }
}
