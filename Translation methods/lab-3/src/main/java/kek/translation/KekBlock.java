package kek.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static kek.util.Util.*;

// statement can be null
public record KekBlock(List<KekStatement> statements, KekContext ownedContext) implements Block {
    @Override
    public List<String> getC() {
        List<String> result = new ArrayList<>();
        result.add("{");

        for (KekVar var : ownedContext.getVars()) {
            result.add(indent(var.getCString() + ";"));
        }

        for (KekStatement statement : statements.stream().filter(Objects::nonNull).toList()) {
            result.addAll(indent(statement.getC()));
        }

        result.add("}");
        return result;
    }
}
