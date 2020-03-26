package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

public class ArrayQueue extends AbstractQueue {
    private int head;
    private Object[] elements = new Object[10];

    private int last_peace() {
        return (size + head) % elements.length;
    }

   /* protected Object[] newElements(int capacity) {
        Object[] newElements = new Object[capacity];
        if (head + size <= elements.length) {
            System.arraycopy(elements, head, newElements, 0, size);
        } else {
            System.arraycopy(elements, head, newElements, 0, size - last_peace());
            System.arraycopy(elements, 0, newElements, size - last_peace(), last_peace());
        }
        return newElements;
    }*/

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = newElements(2 * capacity);
        head = 0;
    }

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    public void enqueueFull(Object element) {
        ensureCapacity(size + 1);
        elements[last_peace()] = element;
    }

    // PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    public Object getElements() {
        return elements[head];
    }

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
    public void dequeueFull() {
        elements[head] = null;
        ++head;
        if (head == elements.length) head = 0;
    }

    // POST: n' = 0
    protected void clearFull() {
        size = 0;
        elements = new Object[10];
    }
}
