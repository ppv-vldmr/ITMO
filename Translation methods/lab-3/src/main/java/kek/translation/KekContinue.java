package kek.translation;

public class KekContinue implements KekStatement {
    @Override
    public String getCString() {
        return "continue;";
    }
}
