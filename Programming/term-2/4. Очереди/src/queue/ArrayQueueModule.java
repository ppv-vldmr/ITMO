package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

// INV: n ≥ 0 ∧ ∀i=1..n: a[i] ≠ null
//       n - size    a[1]..a[n] - queue
public class ArrayQueueModule {
    private static int size, head;
    private static Object[] elements = new Object[10];

    private static int last_peace() {
        return (size + head) % elements.length;
    }

    private static Object[] newElements(int capacity) {
        Object[] newElements = new Object[capacity];
        if (head + size <= elements.length) {
            System.arraycopy(elements, head, newElements, 0, size);
        } else {
            System.arraycopy(elements, head, newElements, 0, size - last_peace());
            System.arraycopy(elements, 0, newElements, size - last_peace(), last_peace());
        }
        return newElements;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = newElements(2 * capacity);
        head = 0;
    }

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    public static void enqueue(Object element) {
        assert element != null : "element == null";
        ensureCapacity(size + 1);
        elements[last_peace()] = element;
        size++;
    }

    /// PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    public static Object element() {
        assert elements.length > 0 : "length == 0";
        return elements[head];
    }

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
  /*  public static Object dequeue() {
        assert elements.length > 0 : "length == 0";
        Object tmp = elements[head];
        elements[head] = null; //memory leak
        head++;
        if (head == elements.length) head = 0;
        size--;
        return tmp;
    }*/

    // POST: ℝ = n ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static int size() {
        return size;
    }

    // POST: ℝ = n > 0 ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static boolean isEmpty() {
        return size == 0;
    }

    // POST: n' = 0
    public static void clear() {
        head = 0;
        size = 0;
        elements = new Object[10];
    }

    // PRE:  n > 0
    // POST: R = a[1],...,a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static Object[] toArray() {
        return newElements(size);
    }

}
