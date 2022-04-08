package lab4.primarygrammar;

import java.util.List;

public record PrimaryNonTerminal(String name, List<String> args) implements PrimaryGrammarElement {

}
