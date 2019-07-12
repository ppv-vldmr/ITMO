package queue;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */

public class QueueTest {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        fill(queue);
        dump(queue);
    }

    private static void fill(ArrayQueue queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
    }

    private static void dump(ArrayQueue queue) {
        while(!queue.isEmpty()) {
            System.out.println(queue.size() + " " +
                    queue.element() + " " + queue.dequeue());
        }
    }
}
