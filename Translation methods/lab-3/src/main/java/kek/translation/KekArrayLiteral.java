package kek.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static kek.util.Util.*;

public record KekArrayLiteral(String funcName, KekType innerType, List<KekExpr> kekExprs) implements KekExpr {
    private static final Random random = new Random();

    @Override
    public KekType getType() {
        return new KekArrayType(innerType);
    }

    @Override
    public String getCString() {
        return funcName + "(" + kekExprs.stream().map(Unit::getCString).collect(Collectors.joining(", ")) + ")";
    }

    public static KekArrayLiteral get(KekGlobalContext globalContext, KekType innerType, List<KekExpr> kekExprs) {
        String funcName = "arrayLit" + Math.abs(random.nextLong());
        KekFunc kekFunc = new KekFunc(
                funcName,
                new KekArrayType(innerType),
                genArgs(kekExprs)
        );
        globalContext.addFunc(kekFunc);
        globalContext.addFuncContent(kekFunc, new Block() {
            @Override
            public List<String> getC() {
                List<String> result = new ArrayList<>();
                result.add("{");
                String resultName = "tmp" + Math.abs(random.nextLong());
                result.add(indent(innerType.getCString() + "* " + resultName +
                        " = (" + innerType.getCString() + "*)malloc(" + kekExprs.size() + " * sizeof(" + innerType.getCString() + "));"));
                for (int i = 0; i < kekExprs.size(); i++) {
                    result.add(indent(resultName + "[" + i + "] = arg" + i + ";"));
                }
                result.add(indent("return " + resultName + ";"));
                result.add("}");
                return result;
            }
        });

        return new KekArrayLiteral(funcName, innerType, kekExprs);
    }

    public static List<KekVar> genArgs(List<KekExpr> kekExprs) {
        List<KekVar> kekVars = new ArrayList<>(kekExprs.size());
        for (int i = 0; i < kekExprs.size(); i++) {
            KekExpr kekExpr = kekExprs.get(i);
            kekVars.add(new KekVar(kekExpr.getType(), "arg" + i));
        }
        return kekVars;
    }
}
