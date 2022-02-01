package msqueue;

import kotlinx.atomicfu.AtomicRef;

public class MSQueue implements Queue {
    private static class Node {
        final int value;
        AtomicRef<Node> next;

        Node(int value) {
            this.value = value;
            next = new AtomicRef<>(null);
        }
    }

    private final AtomicRef<Node> first;
    private final AtomicRef<Node> last;

    public MSQueue() {
        Node temp = new Node(0);
        this.first = new AtomicRef<>(temp);
        this.last = new AtomicRef<>(temp);
    }

    @Override
    public void enqueue(int x) {
        Node updateLast = new Node(x);
        while (true) {
            Node onTail = this.last.getValue();
            if (!onTail.next.compareAndSet(null, updateLast)) {
                this.last.compareAndSet(onTail, onTail.next.getValue());
            } else {
                this.last.compareAndSet(onTail, updateLast);
                break;
            }
        }
    }

    @Override
    public int dequeue() {
        int ans;
        while (true) {
            Node fromFirst = first.getValue();
            Node second = fromFirst.next.getValue();
            if (second == null) {
                ans = Integer.MIN_VALUE;
                break;
            }
            if (!first.compareAndSet(fromFirst, second)) {
                continue;
            }
            ans = second.value;
            break;
        }
        return ans;
    }

    @Override
    public int peek() {
        Node curUpdated = first.getValue().next.getValue();
        if (curUpdated == null) {
            return Integer.MIN_VALUE;
        }
        return curUpdated.value;
    }
}