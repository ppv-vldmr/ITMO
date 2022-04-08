package kek.translation;

import java.util.ArrayList;
import java.util.List;

public record KekIf(KekExpr expr, KekBlock trueBlock, KekBlock falseBlock) implements KekStatement {
    @Override
    public List<String> getC() {
        List<String> result = new ArrayList<>();
        result.add("if (" + expr.getCString() + ")");
        result.addAll(trueBlock.getC());
        if (falseBlock != null) {
            result.add("else");
            result.addAll(falseBlock.getC());
        }
        return result;
    }
}
