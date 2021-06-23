package info.kgeorgiy.ja.popov.concurrent;

import info.kgeorgiy.java.advanced.concurrent.ListIP;
import info.kgeorgiy.java.advanced.mapper.ParallelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class implementing {@link ListIP}. Processes lists in multiple threads.
 *
 * @author Vladimir Popov
 */
public class IterativeParallelism implements ListIP {
    private final ParallelMapper parallelMapper;

    /**
     * Constructor with given {@link ParallelMapper}.
     * @param parallelMapper given {@link ParallelMapper}.
     */
    public IterativeParallelism(ParallelMapper parallelMapper) {
        this.parallelMapper = parallelMapper;
    }

    /**
     * Constructor without arguments.
     */
    public IterativeParallelism() {
        parallelMapper = null;
    }

    private <T>List<List<? extends T>> listToThreads(int threadCount, List<? extends T> list) {
        int elementsInThread = list.size() / threadCount;
        int mod = list.size() % threadCount;
        List<List<? extends T>> lists = new ArrayList<>();
        int prevPointer = 0;
        for (int i = 0; i < threadCount; i++) {
            int size = elementsInThread + (mod > 0 ? 1 : 0);
            lists.add(list.subList(prevPointer, prevPointer + size));
            prevPointer += size;
            mod--;
        }
        return lists;
    }

    private <T, R> List<R> doConcurrent(int threadCount, List<? extends T> values, Function<List<? extends T>, R> function) throws InterruptedException {

        threadCount = Math.min(threadCount, values.size());
        List<List<? extends T>> lists = listToThreads(threadCount, values);

        if (parallelMapper != null) {
            return parallelMapper.map(function, lists);
        } else {

            Thread[] threadArray = new Thread[threadCount];
            List<R> res = new ArrayList<>(threadCount);
            for (int i = 0; i < threadCount; i++) {
                res.add(null);
            }
            for (int i = 0; i < threadCount; i++) {
                List<? extends T> curList = lists.get(i);
                int finalI = i;
                threadArray[i] = new Thread(() -> res.set(finalI, function.apply(curList)));
                threadArray[i].start();
            }
            for (int i = 0; i < threadCount; i++) {
                threadArray[i].join();
            }
            return res;

        }
    }

    /**
     * Returns maximum value.
     *
     * @param threadCount    number or concurrent threads.
     * @param values     values to get maximum of.
     * @param comparator value comparator.
     * @return maximum of given values
     * @throws InterruptedException   if executing thread was interrupted.
     */
    @Override
    public <T> T maximum(int threadCount, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        return doConcurrent(threadCount, values, ts -> ts.stream().max(comparator).orElse(null))
                .stream().max(comparator).orElse(null);
    }

    /**
     * Returns minimum value.
     *
     * @param threadCount    number or concurrent threads.
     * @param values     values to get minimum of.
     * @param comparator value comparator.
     * @return minimum of given values
     * @throws InterruptedException   if executing thread was interrupted.
     */
    @Override
    public <T> T minimum(int threadCount, List<? extends T> values, Comparator<? super T> comparator) throws InterruptedException {
        return maximum(threadCount, values, comparator.reversed());
    }

    /**
     * Returns whether all values satisfies predicate.
     *
     * @param threadCount   number or concurrent threads.
     * @param values    values to test.
     * @param predicate test predicate.
     * @return whether all values satisfies predicate or {@code true}, if no values are given.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean all(int threadCount, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return doConcurrent(threadCount, values, ts -> ts.stream().allMatch(predicate))
                .stream().allMatch(bool -> bool);
    }

    /**
     * Returns whether any of values satisfies predicate.
     *
     * @param threadCount   number or concurrent threads.
     * @param values    values to test.
     * @param predicate test predicate.
     * @return whether any value satisfies predicate or {@code false}, if no values are given.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> boolean any(int threadCount, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return !all(threadCount, values, predicate.negate());
    }

    /**
     * Join values to string.
     *
     * @param threadCount number of concurrent threads.
     * @param values  values to join.
     * @return list of joined result of {@link #toString()} call on each value.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public String join(int threadCount, List<?> values) throws InterruptedException {
        return doConcurrent(threadCount, values,
                ts -> ts.stream().map(Object::toString).collect(Collectors.joining()))
                .stream().map(Object::toString).collect(Collectors.joining());
    }

    /**
     * Filters values by predicate.
     *
     * @param threadCount   number of concurrent threads.
     * @param values    values to filter.
     * @param predicate filter predicate.
     * @return list of values satisfying given predicated. Order of values is preserved.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T> List<T> filter(int threadCount, List<? extends T> values, Predicate<? super T> predicate) throws InterruptedException {
        return doConcurrent(threadCount, values,
                ts -> ts.stream().filter(predicate).collect(Collectors.toList()))
                .stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Maps values.
     *
     * @param threadCount number of concurrent threads.
     * @param values  values to filter.
     * @param f       mapper function.
     * @return list of values mapped by given function.
     * @throws InterruptedException if executing thread was interrupted.
     */
    @Override
    public <T, U> List<U> map(int threadCount, List<? extends T> values, Function<? super T, ? extends U> f) throws InterruptedException {
        return doConcurrent(threadCount, values,
                ts -> ts.stream().map(f).collect(Collectors.toList()))
                .stream().flatMap(Collection::stream).collect(Collectors.toList());
    }
}