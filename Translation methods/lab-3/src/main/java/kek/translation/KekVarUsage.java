package kek.translation;

public record KekVarUsage(KekVar kekVar) implements KekExpr {
    @Override
    public String getCString() {
        return kekVar.name();
    }

    @Override
    public KekType getType() {
        return kekVar.type();
    }
}
