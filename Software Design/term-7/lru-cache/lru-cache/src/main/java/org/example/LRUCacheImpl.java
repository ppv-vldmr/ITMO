package org.example;

import java.util.HashMap;

public class LRUCacheImpl<K, V> implements LRUCache<K, V>{
    private final HashMap<K, Node<K, V>> cache;
    private final int capacity;

    private int size;
    private Node<K, V> head;
    private Node<K, V> tail;

    public LRUCacheImpl(int capacity) {
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
        this.capacity = capacity;
        this.size = 0;
        assert (capacity >= 1);
    }

    @Override
    public int size() {
        assert (cache.size() <= capacity);
        return cache.size();
    }

    @Override
    public void put(K key, V value) {
        assert (key != null);
        assert (value != null);

        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.setValue(value);
            moveToTail(node);
        } else {
            Node<K, V> node = new Node<>(key, value);
            cache.put(key, node);
            System.out.println(toPrettyString());
            addToTail(node);
            System.out.println(toPrettyString());
            size++;
        }

        if (size > capacity) {
            assert (head != null);

            cache.remove(head.getKey());
            head = head.getNext();
            size--;

            assert (size > -1);
        }

        assert (size > -1 && size <= capacity);
    }

    private void addToTail(Node<K, V> node) {
        if (head == null) {
            head = node;
            assert (tail == null);
            tail = node;
            return;
        }

        tail.setNext(node);
        node.setPrev(tail);
        assert (node.getNext() == null);
        tail = node;
    }

    private void moveToTail(Node<K, V> node) {
        if (node == tail) {
            return;
        }

        if (node == head) {
            head = node.getNext();
        }

        Node<K, V> prev = node.getPrev();
        Node<K, V> next = node.getNext();

        if (prev != null) {
            prev.setNext(next);
        }

        if (next != null) {
            next.setPrev(prev);
        }

        node.setNext(null);
        addToTail(node);
    }

    @Override
    public V get(K key) {
        assert (key != null);
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            assert (node != null);
            moveToTail(node);
            return node.getValue();
        }
        return null;
    }

    @Override
    public Node<K, V> getHead() {
        return head;
    }

    @Override
    public Node<K, V> getTail() {
        return tail;
    }

    @Override
    public String toPrettyString() {
        Node<K, V> node = head;
        StringBuilder stringBuilder = new StringBuilder();
        while (node != null) {
            stringBuilder.append(node).append(", ");
            node = node.getNext();
        }
        return stringBuilder.toString();
    }
}
