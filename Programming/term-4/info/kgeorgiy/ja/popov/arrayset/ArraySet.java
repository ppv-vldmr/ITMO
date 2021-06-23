package info.kgeorgiy.ja.popov.arrayset;

import java.util.*;

public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {

    private final Comparator<? super E> comparator;
    private final List<E> data;

    public ArraySet() {
        comparator = null;
        data = Collections.emptyList();
    }

    public ArraySet(Collection<? extends E> data) {
        this(data, null);
    }

    public ArraySet(Collection<? extends E> data, Comparator<? super E> cmp) {
        comparator = cmp;
        TreeSet<E> tmp = new TreeSet<>(cmp );
        tmp.addAll(data);
        this.data = new ArrayList<>(tmp);
    }

    private ArraySet(List<E> data, Comparator<? super E> cmp) {
        this.data = data;
        this.comparator = cmp;
    }

    public Iterator<E> iterator() {
        return Collections.unmodifiableList(data).iterator();
    }

    boolean checkBorders(int l, int r) {
        return (l < 0 || r < 0 || l > r || l >= size() || r >= size());
    }

    public SortedSet<E> subSet(E from, boolean fromInclusive, E to, boolean toInclusive) {
        if (compare(from, to) > 0) {
            throw new IllegalArgumentException("fromElement > toElement");
        }
        // :NOTE: naming
        int ind1 = fromInclusive ? getIndex(from, 0, 0) : getIndex(from, 1, 0);
        int ind2 = toInclusive ? getIndex(to, 0, -1) : getIndex(to, -1, -1);

        if (checkBorders(ind1, ind2)) {
            return new ArraySet<>(Collections.emptyList(), comparator);
        }

        return new ArraySet<>(data.subList(ind1, ind2 + 1), comparator);
    }

    // :NOTE: copypaste
    public SortedSet<E> headSet(E e, boolean isInclusive) {
        if (size() == 0 || compare(first(), e) > 0) {
            return new ArraySet<>(Collections.emptyList(), comparator);
        }
        return subSet(first(), true, e, isInclusive);
    }

    public SortedSet<E> tailSet(E e, boolean isInclusive) {
        if (size() == 0 || compare(e, last()) > 0) {
            return new ArraySet<>(Collections.emptyList(), comparator);
        }
        return subSet(e, isInclusive, last(), true);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object obj) {
        return Collections.binarySearch(data, (E) obj, comparator) >= 0;
    }

    int getIndex(E e, int found, int notFound) {
        int index =  Collections.binarySearch(data, e, comparator);

        if (index >= 0) {
            return index + found;
        } else {
            index = index * (-1) + notFound - 1;
        }

        if (index < 0 || index >= size()) {
            index = -1;
        }

        return index;
    }

    // :NOTE: unchecked cast
    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        return comparator() == null ? ((Comparable<? super E>) e1).compareTo(e2) : comparator().compare(e1, e2);
    }

    @Override
    public SortedSet<E> subSet(E e, E e1) {
        return subSet(e, true, e1, false);
    }

    @Override
    public SortedSet<E> headSet(E e) {
        return headSet(e, false);
    }

    @Override
    public SortedSet<E> tailSet(E e) {
        return tailSet(e, true);
    }

    @Override
    public E first() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return data.get(0);
    }

    @Override
    public E last() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return data.get(data.size() - 1);
    }
}