package kek.translation;

public record KekReturn(KekExpr expr) implements KekStatement {
    @Override
    public String getCString() {
        return "return " + (expr == null ? "" : expr.getCString()) + ";";
    }
}
