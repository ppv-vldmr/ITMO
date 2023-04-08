package org.example;

public interface LRUCache<K, V> {
    int size();

    void put(K key, V value);

    V get(K key);

    Node<K, V> getHead();

    Node<K, V> getTail();

    String toPrettyString();
}
