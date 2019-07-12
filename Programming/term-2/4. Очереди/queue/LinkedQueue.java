package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

// PRE:  n > 0
// POST: R = a[1],...,a[n] ∧ n' = n ∧ ∀i=1..n : a'[i] = a[i]
public class LinkedQueue extends AbstractQueue {
    private Node head, tail;

    public LinkedQueue() {
        size = 0;
        head = tail = null;
    }

    public class Node {
        private Object value;
        private Node next;

        public Node(Object value) {
            assert value != null : "value == null";
            this.value = value;
            this.next = null;
        }
    }

    // PRE:  element != null
    // POST: a'[n + 1] = element ∧ ∀i=1..n : a'[i] = a[i] ∧ n' = n + 1
    public void enqueueFull(Object element) {
        if (tail == null) {
            head = tail = new Node(element);
        } else {
            tail.next = new Node(element);
            tail = tail.next;
        }
    }

    // PRE:  n > 0
    // POST: ∀i=1..n : a'[i] = a[i] ∧ n' = n ∧ R = a[1]
    public Object getElements() {
        return head.value;
    }

    // PRE:  n > 0
    // POST: R = a[1] ∧ n' = n - 1 ∧ ∀i=1..n-1 ∧ a'[i] = a[i+1]
    public void dequeueFull() {
        head = head.next;
        if (size - 1 == 0) tail = null;
    }

    // POST: n' = 0
    protected void clearFull() {
        head = tail = null;
    }

    /*protected Object[] newElements(int capacity) {
        Node pos = head;
        Object[] mas = new Object[capacity];
        int i = 0;
        while (pos != null) {
            mas[i++] = pos.value;
            pos = pos.next;
        }
        return mas;
    }*/
}
