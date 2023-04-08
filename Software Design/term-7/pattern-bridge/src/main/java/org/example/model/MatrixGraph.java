package org.example.model;

import lombok.RequiredArgsConstructor;
import one.util.streamex.EntryStream;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MatrixGraph implements Graph {
    private final boolean[][] adjacencyMatrix;

    public MatrixGraph(List<List<Boolean>> adjacencyMatrix) {
        final int maxWidth = StreamEx.of(adjacencyMatrix).mapToInt(List::size).max().orElse(0);
        final int size = Math.max(adjacencyMatrix.size(), maxWidth);
        this.adjacencyMatrix = new boolean[size][size];
        EntryStream.of(adjacencyMatrix).forKeyValue((i, line) -> {
            EntryStream.of(line).forKeyValue((j, item) -> this.adjacencyMatrix[i][j] = item);
        });
    }

    @Override
    public int size() {
        return adjacencyMatrix.length;
    }

    @Override
    public List<Pair<Integer, Integer>> getEdges() {
        final List<Pair<Integer, Integer>> edges = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j]) {
                    edges.add(Pair.of(i, j));
                }
            }
        }
        return edges;
    }
}
