package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AppTest {
    @Test
    public void commonTest() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(3);
        assertEquals(cache.size(), 0);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        assertEquals(cache.get(1), "one");
        assertEquals(cache.get(2), "two");
        assertEquals(cache.get(3), "three");
        assertEquals(cache.size(), 3);
        assertNull(cache.get(0));
    }

    @Test
    public void overflowByOneTest() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(3);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");

        assertNull(cache.get(1));
        assertEquals(cache.get(2), "two");
        assertEquals(cache.get(3), "three");
        assertEquals(cache.get(4), "four");
        assertEquals(cache.size(), 3);
        assertNull(cache.get(0));
    }

    @Test
    public void overflowByCapacityTest() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(2);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.put(4, "four");

        assertNull(cache.get(1));
        assertNull(cache.get(2));
        assertEquals(cache.get(3), "three");
        assertEquals(cache.get(4), "four");
        assertEquals(cache.size(), 2);
    }

    @Test
    public void cacheSizeIsOneTest() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(1);

        cache.put(1, "one");
        System.out.println(cache.get(1));
        assertEquals(cache.get(1), "one");
        assertEquals(cache.size(), 1);

        cache.put(2, "two");
        assertNull(cache.get(1));
        assertEquals(cache.get(2), "two");
        assertEquals(cache.size(), 1);
    }

    @Test
    public void moveNodeToTailWhenNodeIsAlreadyTailTest() {
        LRUCache<Integer, String> cache = new LRUCacheImpl<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        assertEquals(cache.getTail().getValue(), "two");
        cache.get(2);
        assertEquals(cache.getTail().getValue(), "two");
        cache.put(2, "two");
        assertEquals(cache.getTail().getValue(), "two");
    }

    @Test
    public void modeHeadTest() {
        LRUCache<Integer, Integer> cache = new LRUCacheImpl<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(cache.get(1), 1);

        cache.put(3, 3);
        assertNull(cache.get(2));

        cache.put(4, 4);

        assertNull(cache.get(1));
        assertEquals(cache.get(3), 3);
        assertEquals(cache.get(4), 4);
    }
}
