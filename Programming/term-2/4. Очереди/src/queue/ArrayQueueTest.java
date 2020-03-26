package queue;

import base.TestCounter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ArrayQueueTest<T extends ArrayQueueTest.Queue> extends ReflectionTest {
    private static final int OPERATIONS = 100_000;

    private final Class<T> type;
    private final TestCounter counter = new TestCounter();

    private static final Object[] ELEMENTS = new Object[]{
            "Hello",
            "world",
            1, 2, 3,
            List.of("a"),
            List.of("a"),
            List.of("b"),
            Map.of()
    };

    protected final Random random = new Random(2474258720358724587L);
    private final Supplier<T> reference;

    protected ArrayQueueTest(final Class<T> type, final Function<Stream<Object>, T> reference) {
        this.type = type;
        this.reference = () -> reference.apply(Stream.of());
    }

    public static void main(final String[] args) {
        new ArrayQueueTest<>(Queue.class, ReferenceQueue::new).test();
    }

    protected void test() {
        test("ArrayQueue", 2, Mode.values());
    }

    protected void test(final String className, final int step, final Mode... modes) {
        for (final Mode mode : modes) {
            System.err.printf("Running %s for %s in %s mode%n", getClass().getName(), className, mode);
            test(className, mode, step);
        }
        counter.printStatus(getClass());
    }

    private void test(final String className, final Mode mode, final int step) {
        final Supplier<T> factory = factory(className, mode);
        testEmpty(factory.get());
        testSingleton(factory.get());
        testClear(factory.get());
        for (int i = 0; i <= 10; i += step) {
            testRandom(factory.get(), (double) i / 10);
        }
    }

    protected Supplier<T> factory(final String name, final Mode mode) {
        final ProxyFactory<T> factory = new ProxyFactory<>(type, mode, "queue." + name);
        checkImplementation(factory.implementation);
        return () -> checking(type, reference.get(), factory.create());
    }

    protected void checkImplementation(final Class<?> implementation) {
        // Do nothing by default
    }

    protected void testEmpty(final T queue) {
        nextTest("testEmpty");
        assertSize(0, queue);
        counter.passed();
    }

    protected void testSingleton(final T queue) {
        nextTest("testSingleton");
        assertSize(0, queue);
        final String value = "value";
        queue.enqueue(value);
        assertSize(1, queue);
        assertEquals("element()", value, queue.element());
        assertEquals("dequeue()", value, queue.dequeue());
        assertSize(0, queue);
        counter.passed();
    }

    private void nextTest(final String name) {
        System.err.println("\t=== " + name);
        counter.nextTest();
    }

    protected void testClear(final T queue) {
        nextTest("testClear");
        assertSize(0, queue);

        final String value = "value";
        queue.enqueue(value);
        queue.enqueue(value);
        queue.clear();
        assertSize(0, queue);

        final String value1 = "value1";
        queue.enqueue(value1);
        assertEquals("deque()", value1, queue.dequeue());
        counter.passed();
    }

    protected void testRandom(final T initial, final double addFreq) {
        nextTest("testRandom, add frequency = " + addFreq);
        final List<T> queues = new ArrayList<>();
        queues.add(initial);
        int ops = 0;
        for (int i = 0; i < OPERATIONS; i++) {
            counter.nextTest();
            final T queue = queues.get(random.nextInt(queues.size()));
            if (queue.isEmpty() || random.nextDouble() < addFreq) {
                add(queue, randomElement());
            } else {
                remove(queue);
            }

            final int size = checkAndSize(queue);
            counter.passed();

            if (ops++ >= size && random.nextInt(4) == 0) {
                ops -= size;

                counter.nextTest();
                queues.addAll(linearTest(queue));
                checkAndSize(queue);
                counter.passed();
            }
        }

        for (final T queue : queues) {
            counter.nextTest();
            linearTest(queue);
            for (int i = queue.size(); i > 0; i--) {
                remove(queue);
                checkAndSize(queue);
            }
            counter.passed();
        }

        counter.passed();
    }

    private int checkAndSize(final T queue) {
        final int size = queue.size();
        if (!queue.isEmpty() && random.nextBoolean()) {
            check(queue);
        }
        return size;
    }

    protected void remove(final T queue) {
        queue.dequeue();
    }

    protected void check(final T queue) {
        queue.element();
    }

    protected void add(final T queue, final Object element) {
        queue.enqueue(element);
    }

    protected List<T> linearTest(final T queue) {
        // Do nothing by default
        return List.of();
    }

    protected Object randomElement() {
        return ELEMENTS[random.nextInt(ELEMENTS.length)];
    }

    protected void assertSize(final int size, final T queue) {
        counter.nextTest();
        assertEquals("size()", size, queue.size());
        assert queue.size() == size : "Expected size() " + size + ", found " + queue.size();
        assert (size == 0) == queue.isEmpty() : "Expected isEmpty() " + (size == 0) + ", found " + queue.isEmpty();
        counter.passed();
    }

    @Override
    protected void checkResult(final String call, final Object expected, final Object actual) {
        if (expected instanceof Queue) {
            super.checkResult(call, toList((Queue) expected), toList((Queue) actual));
        } else {
            super.checkResult(call, expected, actual);
        }
    }

    private static List<Object> toList(final Queue queue) {
        final List<Object> list = Stream.generate(queue::dequeue).limit(queue.size()).collect(Collectors.toUnmodifiableList());
        list.forEach(queue::enqueue);
        return list;
    }

    /**
     * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
     */
    protected interface Queue {
        void enqueue(Object element);
        Object element();
        Object dequeue();
        int size();
        boolean isEmpty();
        void clear();
    }

    protected static class ReferenceQueue implements Queue {
        protected final Deque<Object> deque;

        public ReferenceQueue(final Stream<Object> elements) {
            deque = elements.collect(Collectors.toCollection(ArrayDeque::new));
        }

        @Override
        public void enqueue(final Object element) {
            deque.addLast(element);
        }

        @Override
        public Object element() {
            return deque.getFirst();
        }

        @Override
        public Object dequeue() {
            return deque.removeFirst();
        }

        @Override
        public int size() {
            return deque.size();
        }

        @Override
        public boolean isEmpty() {
            return deque.isEmpty();
        }

        @Override
        public void clear() {
            deque.clear();
        }

        @Override
        public String toString() {
            return deque.toString();
        }
    }
}
