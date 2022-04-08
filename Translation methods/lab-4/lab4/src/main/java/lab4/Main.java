package lab4;

import lab4.grammar.*;
import lab4.lexer.LexerFactory;
import lab4.primarygrammar.PrimaryGrammar;
import lab4.syntaxparse.SyntaxLexer;
import lab4.syntaxparse.SyntaxParser;
import lab4.util.Settings;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Settings settings = Settings.fromCommandLine(args);

        CharStream charStream = CharStreams.fromPath(settings.getInputGrammarFilePath());
        SyntaxLexer syntaxLexer = new SyntaxLexer(charStream);
        SyntaxParser syntaxParser = new SyntaxParser(new CommonTokenStream(syntaxLexer));
        SyntaxParser.GrammarFileContext grammarFileContext = syntaxParser.grammarFile();

        PrimaryGrammar primaryGrammar = grammarFileContext.grammar;

        LexerFactory lexerFactory = new LexerFactory(settings);
        lexerFactory.accept(primaryGrammar.primaryTokens());

        GrammarFactory grammarFactory = new GrammarFactory();
        Grammar grammar = grammarFactory.apply(primaryGrammar);

        ParserFactory parserFactory = new ParserFactory(settings);
        parserFactory.accept(grammar);

        debug(grammar);
    }

    private static void debug(Grammar grammar) {
        for (Map.Entry<String, Rule> nameAndRule : grammar.getRules()) {
            String name = nameAndRule.getKey();
            Rule rule = nameAndRule.getValue();

            System.out.println(name + " -> " + rule.nodes().stream().map(Node::toHumanReadable).collect(Collectors.joining(" ")));
        }

        System.out.println("FIRST =====================");

        for (NonTerminal nonTerminal : grammar.getNonTerminals().values()) {
            System.out.println(nonTerminal.name() + " | " + String.join(", ", grammar.first(nonTerminal)));
        }

        System.out.println("FOLLOW =====================");

        for (NonTerminal nonTerminal : grammar.getNonTerminals().values()) {
            System.out.println(nonTerminal.name() + " | " + String.join(", ", grammar.follow(nonTerminal)));
        }
    }
}
