package org.example.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface Graph {
    int size();
    List<Pair<Integer, Integer>> getEdges();
}
