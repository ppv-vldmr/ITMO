package kek.translation;

public class KekBreak implements KekStatement {
    @Override
    public String getCString() {
        return "break;";
    }
}
