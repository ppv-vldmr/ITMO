grammar Kek;

@header {
import java.util.function.BiFunction;
import java.util.function.Function;
}

fullExpression returns [Double result]: expression EOF {
    result = expression.result;
};

expression returns [Double result]: plusOperator {
    result = plusOperator.result;
};

plus returns [BiFunction<Double,Double,Double> f]: PLUS {
    f = (x, y) -> x + y;
} | MINUS {
    f = (x, y) -> x - y;
};

mul returns [BiFunction<Double,Double,Double> f]: DIV {
    f = (x, y) -> x / y;
} | MUL {
    f = (x, y) -> x * y;
};

fact returns [Function<Double,Double> f]: FACT {
    f = (x) -> {
        if (x == 1) {
            return 1.0;
        }
        return x * f.apply(x - 1);
    };
}

plusOperator returns [Double result]: mulOperator plusOperatorCont[mulOperator.result]? {
    if (plusOperatorCont.result == null) {
        result = mulOperator.result;
    } else {
        result = plusOperatorCont.result;
    }
};

plusOperatorCont [Double acc] returns [Double result]:
    plus mulOperator plusOperatorCont[plus.f.apply(acc, mulOperator.result)]? {
    if (plusOperatorCont.result == null) {
        result = plus.f.apply(acc, mulOperator.result);
    } else {
        result = plusOperatorCont.result;
    }
};

mulOperator returns [Double result]: factOperator mulOperatorCont? {
    if (mulOperatorCont.result == null) {
        result = factOperator.result;
    } else {
        result = mulOperatorCont.f.apply(factOperator.result, mulOperatorCont.result);
    }
};

mulOperatorCont returns [BiFunction<Double,Double,Double> f][Double result]: mul factOperator mulOperatorCont? {
    f = mul.f;

    if (mulOperatorCont.result == null) {
        result = factOperator.result;
    } else {
        result = mulOperatorCont.f.apply(factOperator.result, mulOperatorCont.result);
    }
};

factOperator returns [Double result]: term factOperatorCont[term.result]? {
    if (factOperatorCont.result == null) {
        result = term.result;
    } else {
        result = factOperatorCont.result;
    }
}

term returns [Double result]: NUM {
    result = Double.parseDouble(content(0));
} | LPAR expression RPAR {
    result = expression.result;
};

factOperatorCont [Double acc] returns [Double result]: fact factOperatorCont[fact.f.apply(acc)]? {
    if (factOperatorCont.result == null) {
        result = fact.f.apply(acc);
    } else {
        result = factOperatorCont.result;
    }
}

FACT: "\\?";
PLUS: "\\+";
MINUS: "-";
DIV: "/";
MUL: "\\*";
LPAR: "\\(";
RPAR: "\\)";
NUM: "(-)?[0-9]+(\\.[0-9]+)?";
WS: "[ \\n\\t\\r]+" -> skip;