package stack;

import kotlinx.atomicfu.AtomicRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StackImpl implements Stack {
    private static class Node {
        final AtomicRef<Node> next;
        final int x;

        Node(int x, Node next) {
            this.next = new AtomicRef<>(next);
            this.x = x;
        }
    }

    public StackImpl() {
        atomicRefs = new ArrayList<>();
        for (int i = 0; i < SIZE_OF_ARRAY; i++) {
            atomicRefs.add(new AtomicRef<Node>(null));
        }
    }

    private final AtomicRef<Node> onTop = new AtomicRef<>(null);
    private final List<AtomicRef<Node>> atomicRefs;
    private final Random random = new Random();

    private final static int SIZE_OF_ARRAY = 15;
    private final static int CNT_PASS = 10;

    @Override
    public void push(int x) {
        int tempRand = random.nextInt(SIZE_OF_ARRAY);
        int right = Math.min(tempRand + 1, SIZE_OF_ARRAY - 1);
        int left = Math.max(tempRand - 1, 0);

        Node nodeToPush = new Node(x, null);
        for (int num = left; num <= right; num++) {
            if (!atomicRefs.get(num).compareAndSet(null, nodeToPush)) {
                continue;
            }

            int ptr = 0;
            while (ptr < CNT_PASS) {
                Node curNode = atomicRefs.get(num).getValue();
                if (curNode.x == Integer.MAX_VALUE) {

                    atomicRefs.get(num).setValue(null);
                    return;

                }
                ptr++;
            }

            if (!atomicRefs.get(num).compareAndSet(nodeToPush, null)) {
                atomicRefs.get(num).setValue(null);
                return;
            } else {
                break;
            }
        }
        while (true) {
            Node current = onTop.getValue();
            Node updated = new Node(x, current);
            if (onTop.compareAndSet(current, updated)) {
                return;
            }
        }
    }

    @Override
    public int pop() {
        int tempRand = random.nextInt(SIZE_OF_ARRAY);
        int right = Math.min(tempRand + 1, SIZE_OF_ARRAY - 1);
        int left = Math.max(tempRand - 1, 0);

        Node newElem = new Node(Integer.MAX_VALUE, null);
        for (int num = left; num <= right; num++) {
            Node elem = atomicRefs.get(num).getValue();
            if (elem != null && elem.x != Integer.MAX_VALUE) {
                if (atomicRefs.get(num).compareAndSet(elem, newElem)) {
                    return elem.x;
                }
            }
        }

        while (true) {
            Node current = onTop.getValue();
            if (current != null) {
                if (onTop.compareAndSet(current, current.next.getValue())) {
                    return current.x;
                }
            } else {
                return Integer.MIN_VALUE;
            }
        }
    }
}