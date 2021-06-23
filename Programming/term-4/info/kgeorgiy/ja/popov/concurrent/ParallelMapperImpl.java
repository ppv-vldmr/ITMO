package info.kgeorgiy.ja.popov.concurrent;

import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class implementing {@link ParallelMapper}.
 *
 * @author Vladimir Popov
 */
public class ParallelMapperImpl implements ParallelMapper {
    private final int threads;
    private final Thread[] threadsArray;
    private final ArrayDeque<Runnable> queue;

    private static class Counter {
        private int counter = 0;

        private int getCounter() {
            return counter;
        }

        private void increment() {
            counter++;
        }
    }

    /**
     * Thread-count constructor.
     * Creates a ParallelMapperImpl instance operating with maximum of {@code threads}
     * threads of type {@link Thread}.
     *
     * @param threads maximum count of operable threads
     */
    public ParallelMapperImpl(int threads) {
        this.threads = threads;
        threadsArray = new Thread[threads];
        queue = new ArrayDeque<>();
        // :NOTE: only one instnace of Runnable
        for (int i = 0; i < threads; i++) {
            threadsArray[i] = new Thread(() -> {
                Runnable runnable;
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                return;
                            }
                        }
                        runnable = queue.removeFirst();
                    }
                    runnable.run();
                }
            });
            threadsArray[i].start();
        }
    }

    /**
     * Maps function {@code f} over specified {@code args}.
     * Mapping for each element performs in parallel.
     *
     * @param f    function
     * @param args elements
     * @throws InterruptedException if calling thread was interrupted
     */
    @Override
    public <T, R> List<R> map(Function<? super T, ? extends R> f, List<? extends T> args) throws InterruptedException {
        List<R> answer = new ArrayList<>(args.size());
        final Counter counter = new Counter();
        for (int i = 0; i < args.size(); i++) {
            answer.add(null);
        }
        for (int i = 0; i < args.size(); i++) {
            int currentI = i;
            Runnable runnable = () -> {
                answer.set(currentI, f.apply(args.get(currentI)));
                synchronized (counter) {
                    counter.increment();
                    if (counter.getCounter() == args.size()) {
                        counter.notify();
                    }
                }
            };
            synchronized (queue) {
                queue.add(runnable);
                queue.notify();
            }
        }
        synchronized (counter) {
            while (counter.getCounter() < args.size()) {
                counter.wait();
            }
        }
        return answer;
    }

    /**
     * Stops all threads. All unfinished mappings leave in undefined state.
     */
    @Override
    public void close() {
        for (int i = 0; i < threads; i++) {
            threadsArray[i].interrupt();
        }
        for (int i = 0; i < threads; i++) {
            try {
                threadsArray[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}