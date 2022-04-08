package kek.translation;

import java.util.List;
import java.util.stream.Collectors;

public record KekFunc(String name, KekType returnType, List<KekVar> args) implements Unit {
    @Override
    public String getCString() {
        String returnTypeC = returnType.getCString();
        if ("main".equals(name)) {
            returnTypeC = "int";
        }
        return returnTypeC + " " + name + "("
                + args.stream().map(KekVar::getCString).collect(Collectors.joining(", ")) + ")";
    }
}
