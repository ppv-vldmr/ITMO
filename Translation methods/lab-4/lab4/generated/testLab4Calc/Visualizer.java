package testLab4Calc;

import java.io.File;
import java.io.IOException;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableNode;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class Visualizer {
    private final Tree tree;
    private int nodeId;

    public Visualizer(Tree tree) {
        this.tree = tree;
        nodeId = 0;
    }

    private String nextId() {
        return String.valueOf(nodeId++);
    }

    public void visualize(File outputSvgFile) throws IOException {
        var graph = mutGraph().setDirected(true)
                .graphAttrs().add(Rank.dir(Rank.RankDir.TOP_TO_BOTTOM))
                .nodeAttrs().add(Shape.BOX);
        var root = dfs(tree);
        graph.add(root);
        Graphviz.fromGraph(graph)
                .render(Format.SVG_STANDALONE)
                .toFile(outputSvgFile);
    }

    private MutableNode dfs(Tree tree) {
//        MutableNode node = mutNode(nextId()).add(Label.of(tree.getGrammarElement().humanReadable()));
        MutableNode node = mutNode(nextId()).add(Label.of(tree.toString()));
        for (Tree child : tree.getChildren()) {
            node.addLink(dfs(child));
        }
        return node;
    }

    public static void visualize(Tree tree, String svgOutputFilePath) throws IOException {
        new Visualizer(tree).visualize(new File(svgOutputFilePath));
    }
}
