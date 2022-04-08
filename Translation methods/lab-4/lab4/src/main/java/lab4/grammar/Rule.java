package lab4.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record Rule(List<Node> nodes, String afterCode, Map<Integer, List<String>> indexToArgs) {
    public List<List<String>> getArgs() {
        int n = nodes.size();
        List<List<String>> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(i, indexToArgs.get(i));
        }
        return result;
    }
}
