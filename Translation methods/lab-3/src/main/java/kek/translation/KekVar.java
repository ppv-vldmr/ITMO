package kek.translation;

public record KekVar(KekType type, String name) implements Unit {
    @Override
    public String getCString() {
        return type.getCString() + " " + name;
    }
}
