package kek.translation;

import java.util.ArrayList;
import java.util.List;

public record KekWhile(KekExpr expr, KekBlock block) implements KekStatement {
    @Override
    public List<String> getC() {
        List<String> result = new ArrayList<>();
        result.add("while (" + expr.getCString() + ")");
        result.addAll(block.getC());
        return result;
    }
}
