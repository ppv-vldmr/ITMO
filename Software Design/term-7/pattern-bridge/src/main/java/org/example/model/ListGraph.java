package org.example.model;

import one.util.streamex.StreamEx;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class ListGraph implements Graph {
    private final int size;
    private final List<Pair<Integer, Integer>> edges;

    public ListGraph(int size, List<Pair<Integer, Integer>> edges) {
        this.size = size;
        this.edges = StreamEx.of(edges).map(e -> Pair.of(e.getLeft() % size, e.getRight() % size)).toList();
    }

    public ListGraph(int size, List<Integer> firstVerteces, List<Integer> secondVerteces) {
        this(size, StreamEx.zip(firstVerteces, secondVerteces, Pair::of).toList());
    }

    public ListGraph(int size, int[] firstVerteces, int[] secondVerteces) {
        this(size, stream(firstVerteces).boxed().collect(toList()), stream(secondVerteces).boxed().collect(toList()));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<Pair<Integer, Integer>> getEdges() {
        return List.copyOf(edges);
    }
}
