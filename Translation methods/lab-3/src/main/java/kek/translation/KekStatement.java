package kek.translation;

public interface KekStatement extends Unit {
    static KekStatement of(KekExpr kekExpr) {
        return new KekStatement() {
            @Override
            public String getCString() {
                return kekExpr.getCString() + ";";
            }
        };
    }
}
