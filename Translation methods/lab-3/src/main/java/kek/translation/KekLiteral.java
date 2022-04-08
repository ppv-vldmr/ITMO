package kek.translation;

public record KekLiteral(KekPrimitiveType type, String kekValue) implements KekExpr {
    @Override
    public String getCString() {
        return switch (type) {
            case INT -> kekValue + "LL";
            case BOOL -> kekValue;
        };
    }

    @Override
    public KekType getType() {
        return type;
    }
}
