package lab4.grammar;

import java.util.List;

public record NonTerminal(String name,
                          String beforeCode,
                          List<String> args,
                          List<String> returns) implements Node {
    public static NonTerminal of(String name) {
        return new NonTerminal(name, null, null, null);
    }

    @Override
    public String toHumanReadable() {
        return name;
    }
}
