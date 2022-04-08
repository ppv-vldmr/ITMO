package lab4.grammar;

public interface Node {
    String toHumanReadable();

    default boolean isTerminal() {
        return this instanceof Terminal;
    }

    default Terminal asTerminal() {
        return (Terminal) this;
    }

    default NonTerminal asNonTerminal() {
        return (NonTerminal) this;
    }
}
