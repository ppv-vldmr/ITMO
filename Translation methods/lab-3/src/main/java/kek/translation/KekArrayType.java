package kek.translation;

public record KekArrayType(KekType innerType) implements KekType {
    @Override
    public String getCString() {
        return innerType.getCString() + "*";
    }
}
