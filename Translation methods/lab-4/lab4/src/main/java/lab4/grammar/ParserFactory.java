package lab4.grammar;

import lab4.util.AbstractFileFactory;
import lab4.util.Settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class ParserFactory extends AbstractFileFactory implements Consumer<Grammar> {
    private static final String[] templateNames = new String[] {
            "Parser.java.ftl"
    };

    public ParserFactory(Settings settings) {
        super(settings);
    }

    @Override
    protected String[] getTemplateNames() {
        return templateNames;
    }

    @Override
    public void accept(Grammar grammar) {
        grammar.buildFirst();
        grammar.buildFollow();

        var map = settings().map();
        map.put("header", grammar.getHeader());
        map.put("members", grammar.getMembers());

        Collection<NonTerminal> nonTerminals = grammar.getNonTerminals().values();
        map.put("nonTerminals", nonTerminals);

        List<List<Rule>> rules = new ArrayList<>(nonTerminals.size());
        for (NonTerminal nonTerminal : nonTerminals) {
            rules.add(grammar.getRules(nonTerminal));
        }
        map.put("rules", rules);
        map.put("helper", new Helper(grammar));
        map.put("startNonTerminal", grammar.getStartNonTerminal());

        writeFiles(map);
    }

    public static class Helper {
        private final Grammar grammar;

        public Helper(Grammar grammar) {
            this.grammar = grammar;
        }

        public Set<String> first1(String nonTerminalName, List<Node> nodes) {
            return grammar.first1(nonTerminalName, nodes);
        }
    }
}
