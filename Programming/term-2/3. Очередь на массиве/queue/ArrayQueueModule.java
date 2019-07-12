package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */


// INV: n ≥ 0 ∧ ∀i=1..n: a[i] ≠ null
//       n - size    a[1]..a[n] - queue
public class ArrayQueueModule {
    private static int head = 0;
    private static int tail = 0;
    private static Object[] elements = new Object[16];

    private static void ensureCapacity(int size) {
        if (size == elements.length || (size > 3 && size == elements.length / 4)) {
            Object[] newElements;
            if (size == elements.length) {
                newElements = new Object[elements.length * 2];
            } else {
                newElements = new Object[elements.length / 2];
            }
            if (head <= tail) {
                System.arraycopy(elements, head, newElements, 0, size());
            } else {
                System.arraycopy(elements, head, newElements, 0, elements.length - head);
                System.arraycopy(elements, 0, newElements, elements.length - head, tail);
            }
            tail = size();
            head = 0;
            elements = newElements;
        }
    }

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    public static void enqueue(Object element) {
        ensureCapacity(size() + 1);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
    }

    // PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    public static Object element() {
        assert !isEmpty();
        return elements[head];
    }

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
    public static Object dequeue() {
        assert !isEmpty();
        ensureCapacity(size() - 1);
        Object r = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        return r;
    }

    // POST: ℝ = n ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static int size() {
        return tail - head + (head > tail ? elements.length : 0);
    }

    // POST: ℝ = n > 0 ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static boolean isEmpty() {
        return size() == 0;
    }

    // POST: n' = 0
    public static void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    // PRE:  n > 0
    // POST: R = a[1],...,a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static Object[] toArray() {
        Object array[] = new Object[size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = elements[(head + i) % elements.length];
        }
        return array;
    }

    // PRE:  n > 0
    // POST: R = [a[1], ..., a[n]] -> string ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static String toStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(elements[(head + i) % elements.length]);
        }
        sb.append("]");
        return sb.toString();
    }

    // PRE: element ≠ null
    // POST: n' = n + 1 ∧ ∀i=1..n : a'[i] = a[i] ∧ a'[n+1] = element
    public static void push(Object element) {
        ensureCapacity(size() + 1);
        head = head == 0 ? elements.length - 1 : head - 1;
        elements[head] = element;
    }

    // PRE: n > 0
    // POST: ℝ = a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static Object peek() {
        assert !isEmpty();
        return elements[tail == 0 ? elements.length - 1 : tail - 1];
    }

    // PRE:  n > 0
    // POST: ℝ = a[n] ∧ n' = n - 1 ∧ ∀i=1..n-1 : a'[i] = a[i]
    public static Object remove() {
        assert !isEmpty();
        ensureCapacity(size() - 1);
        tail = tail == 0 ? elements.length - 1 : tail - 1;
        Object r = elements[tail];
        elements[tail] = null;
        return r;
    }
}