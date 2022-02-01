package linked_list_set;

import kotlinx.atomicfu.AtomicRef;

public class SetImpl implements Set {
    private static interface Node {
        public boolean removed();
    }

    private static class Active implements Node {
        int k;
        AtomicRef<Node> next;

        Active(int k, Node next) {
            this.k = k;
            this.next = new AtomicRef<>(next);
        }

        @Override
        public boolean removed() {
            return false;
        }
    }

    private static class Inactive implements Node {
        Active link;

        Inactive(Active link) {
            this.link = link;
        }

        @Override
        public boolean removed() {
            return true;
        }
    }


    private static class ElsPair {
        Active left;
        Active right;
    }

    private final AtomicRef<Active> top = new AtomicRef<>(new Active(Integer.MIN_VALUE, new Active(Integer.MAX_VALUE, null)));

    private ElsPair findPair(int key) {
        while (true) {
            ElsPair pair = new ElsPair();
            pair.left = top.getValue();
            pair.right = (Active) pair.left.next.getValue();

            boolean failure = false;

            while (true) {
                if (pair.right.k >= key) {
                    break;
                }
                Node nextValue = pair.right.next.getValue();
                if (!nextValue.removed()) {
                    pair.left = pair.right;
                    pair.right = (Active) nextValue;
                } else {
                    if (!pair.left.next.compareAndSet(pair.right, ((Inactive) nextValue).link)) {
                        failure = true;
                        break;
                    }
                    pair.right = ((Inactive) nextValue).link;
                }
            }

            if (failure) {
                continue;
            }
            return pair;
        }
    }

    @Override
    public boolean add(int key) {
        while (true) {
            ElsPair p = findPair(key);
            if (p.right.next.getValue().removed() || p.right.k != key) {
                if (p.left.next.compareAndSet(p.right, new Active(key, p.right))) {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean remove(int key) {
        while (true) {
            ElsPair elsPair = findPair(key);
            if (elsPair.right.k == key) {
                Node next = elsPair.right.next.getValue();
                if (!next.removed()) {
                    if (elsPair.right.next.compareAndSet(next, new Inactive((Active) next))) {
                        elsPair.left.next.compareAndSet(elsPair.right, next);
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean contains(int key) {
        ElsPair pair = findPair(key);
        return !pair.right.next.getValue().removed() && pair.right.k == key;
    }
}