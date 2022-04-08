package kek.translation;

public enum KekPrimitiveType implements KekType {
    INT("long long"), BOOL("bool");

    private final String cType;

    KekPrimitiveType(String cType) {
        this.cType = cType;
    }

    @Override
    public String getCString() {
        return cType;
    }
}
