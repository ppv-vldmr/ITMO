package kek.translation;

import java.util.List;
import java.util.stream.Collectors;

public record KekFuncCall(KekFunc kekFunc, List<KekExpr> args) implements KekExpr {
    @Override
    public String getCString() {
        return kekFunc.name() + "(" + args.stream().map(Unit::getCString).collect(Collectors.joining(", ")) + ")";
    }

    @Override
    public KekType getType() {
        return kekFunc.returnType();
    }
}
