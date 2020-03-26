package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

// INV: n ≥ 0 ∧ ∀i=1..n: a[i] ≠ null
//       n - size    a[1]..a[n] - queue
public class ArrayQueueADT {
    private int size, head;
    private Object[] elements = new Object[10];

    private static int last_peace(ArrayQueueADT queue) {
        return (queue.size + queue.head) % queue.elements.length;
    }

    private static Object[] newElements(ArrayQueueADT queue, int capacity) {
        Object[] newElements = new Object[capacity];
        if (queue.head + queue.size <= queue.elements.length) {
            System.arraycopy(queue.elements, queue.head, newElements, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.head, newElements, 0, queue.size - last_peace(queue));
            System.arraycopy(queue.elements, 0, newElements, queue.size - last_peace(queue), last_peace(queue));
        }
        return newElements;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        queue.elements = newElements(queue, 2 * capacity);
        queue.head = 0;
    }

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert queue != null : "queue == null";
        assert element != null : "element == null";
        ensureCapacity(queue, queue.size + 1);
        queue.elements[last_peace(queue)] = element;
        queue.size++;
    }

    // PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    public static Object element(ArrayQueueADT queue) {
        assert queue != null : "queue == null";
        assert queue.elements.length > 0 : "length == 0";
        return queue.elements[queue.head];
    }

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null : "queue == null";
        assert queue.elements.length > 0 : "length == 0";
        Object tmp = queue.elements[queue.head];
        queue.elements[queue.head] = null; //memory leak
        queue.head++;
        if (queue.head == queue.elements.length) queue.head = 0;
        queue.size--;
        return tmp;
    }

    // POST: ℝ = n ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static int size(ArrayQueueADT queue) {
        assert queue != null : "queue == null";
        return queue.size;
    }

    // POST: ℝ = n > 0 ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null : "queue == null";
        return queue.size == 0;
    }

    // POST: n' = 0
    public static void clear(ArrayQueueADT queue) {
        assert queue != null : "queue == null";
        queue.head = 0;
        queue.size = 0;
        queue.elements = new Object[10];
    }

    // PRE:  n > 0
    // POST: R = a[1],...,a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
    public static Object[] toArray(ArrayQueueADT queue) {
        assert queue != null : "queue == null";
        return newElements(queue, queue.size);
    }
}
