import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Tree {
    private static final Set<String> TERMINALS = Set.of("(", ");", ",", "*", "type", "name");
    private final String node;
    private final List<Tree> children;
    private final int vertexNumber;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
        this.vertexNumber = this.hashCode();
    }

    public Tree(String node) {
        this.node = node;
        this.children = Collections.emptyList();
        this.vertexNumber = this.hashCode();
    }

    public String toStringGraph() {
        StringBuilder sb = new StringBuilder();
        sb.append(vertexNumber).append("[label=\"").append(node).append("\"]\n");
        if (TERMINALS.contains(node)) {
            sb.append(vertexNumber).append("[fontcolor=blue]\n");
        }
        for (Tree child : children) {
            sb.append(child.toStringGraph())
                    .append(vertexNumber)
                    .append(" -> ")
                    .append(child.getVertexNumber())
                    .append('\n');
        }
        return sb.toString();
    }

    private int getVertexNumber() {
        return vertexNumber;
    }
}
