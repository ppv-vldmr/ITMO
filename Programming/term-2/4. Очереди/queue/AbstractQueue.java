package queue;

import java.util.Arrays;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

// INV: n ≥ 0 ∧ ∀i=1..n: a[i] ≠ null
//       n - size    a[1]..a[n] - queue
public abstract class AbstractQueue implements Queue {
    protected int size;

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    public void enqueue(Object element) {
        assert element != null : "element == null";
        enqueueFull(element);
        ++size;
    }

    // PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    public Object element() {
        assert size > 0 : "size == 0";
        return getElements();
    }

    // POST: ℝ = n ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public int size() {
        return size;
    }

    // POST: ℝ = n > 0 ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public boolean isEmpty() {
        return size == 0;
    }

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
    public Object dequeue() {
        assert size > 0;
        Object el = element();
        dequeueFull();
        --size;
        return el;
    }

    // POST: n' = 0
    public void clear() {
        size = 0;
        clearFull();
    }

    // PRE:  n > 0
    // POST: R = a[1],...,a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public Object[] toArray() {
        return newElements(size);
    }

    // PRE:  n > 0
    // POST: R = [a[1], ..., a[n]] -> string ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public String toStr() {
//        StringBuilder sb = new StringBuilder().append("[");
//        Object[] elements = newElements(size);
//        for (int i = 0; i < size; i++) {
//            sb.append(elements[i]);
//            if (i < size - 1) {
//                sb.append(", ");
//            }
//        }
//        sb.append("]");
//        return sb.toString();
        return Arrays.toString(newElements(size));
    }

    protected Object[] newElements(int capacity) {
        Object[] result = new Object[capacity];
        for (int i = 0; i < size; ++i) {
            result[i] = element();
            enqueue(dequeue());
        }
        return result;
    }

    protected abstract void clearFull();

    protected abstract void dequeueFull();

    protected abstract Object getElements();

    protected abstract void enqueueFull(Object element);
}
