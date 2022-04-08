import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Objects;

public class Parser {
    LexicalAnalyzer lexicalAnalyzer;

    public Tree parse(InputStream is) throws ParseException {
        lexicalAnalyzer = new LexicalAnalyzer(is);
        nextToken();
        if (lexicalAnalyzer.getCurrentToken() == TokenType.END) {
            return null;
        }
        Tree expr = parseStateS();
        assertToken(TokenType.END, "$", 0);
        return expr;
    }

    private void nextToken() throws ParseException {
        lexicalAnalyzer.nextToken();
    }

    public void parseAndGenerate(String expr, String outputFileName) {
        Parser parser = new Parser();
        Tree tree;
        try {
            tree = parser.parse(new ByteArrayInputStream(expr.getBytes()));
        } catch (ParseException e) {
            System.err.println("Parse exception in expression: " + expr + "\n"
                    + e.getMessage() + " at position " + e.getErrorOffset() + "\n");
            return;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(outputFileName))) {
            if (Objects.isNull(tree)) {
                writer.write("digraph {}");
            } else {
                writer.write("digraph { " + tree.toStringGraph() + "}");
            }
        } catch (InvalidPathException e) {
            System.err.println("Invalid name of output file: " + outputFileName);
        } catch (IOException e) {
            System.err.println("I/O error while writing the string representation of graph");
        }

        try {
            Runtime rt = Runtime.getRuntime();
            String[] args = {"dot", "-Tsvg", outputFileName, "-o", outputFileName + ".svg"};
            Process p = rt.exec(args);
        } catch (IOException e) {
            System.err.println("Couldn't run graphviz. " + e.getMessage());
        }
    }

    private Tree parseStateS() throws ParseException {
        if (lexicalAnalyzer.getCurrentToken().equals(TokenType.TYPE)) {
            Tree left = parseStateT();
            String currentToken = lexicalAnalyzer.getCurrentToken().toString();
            assertToken(TokenType.LEFT_PAREN, currentToken, currentToken.length());
            nextToken();
            Tree tree = new Tree("S", left, new Tree("("), parseArgs(), new Tree(");"));
            currentToken = lexicalAnalyzer.getCurrentToken().toString();
            assertToken(TokenType.RIGHT_PAREN_SEM, currentToken, currentToken.length());
            nextToken();
            return tree;
        } else throw getUnexpectedTokenException(lexicalAnalyzer.getCurrentToken().toString().length());
    }

    private Tree parseStateT() throws ParseException {
        if (lexicalAnalyzer.getCurrentToken().equals(TokenType.TYPE)) {
            nextToken();
            Tree tree = new Tree("T", new Tree("type"), parseStateP(), new Tree("name"));
            String currentToken = lexicalAnalyzer.getCurrentToken().toString();
            assertToken(TokenType.NAME, currentToken, currentToken.length());
            nextToken();
            return tree;
        } else throw getUnexpectedTokenException(lexicalAnalyzer.getCurrentToken().toString().length());
    }

    private Tree parseStateP() throws ParseException {
        switch (lexicalAnalyzer.getCurrentToken()) {
            case POINTER -> {
                nextToken();
                return new Tree("P", new Tree("*"), parseStateP());
            }
            case NAME -> {
                return new Tree("P");
            }
            default -> {
                throw getUnexpectedTokenException(lexicalAnalyzer.getCurrentToken().toString().length());
            }
        }
    }

    private Tree parseArgs() throws ParseException {
        return switch (lexicalAnalyzer.getCurrentToken()) {
            case TYPE -> new Tree("args", parseStateT(), parseStateX());
            case RIGHT_PAREN_SEM -> new Tree("X");
            default -> throw getUnexpectedTokenException(lexicalAnalyzer.getCurrentToken().toString().length());
        };
    }

    private Tree parseStateX() throws ParseException {
        switch (lexicalAnalyzer.getCurrentToken()) {
            case COMA -> {
                nextToken();
                return new Tree("X", new Tree(","), parseStateT(), parseStateX());
            }
            case RIGHT_PAREN_SEM -> {
                return new Tree("X");
            }
            default -> {
                throw getUnexpectedTokenException(lexicalAnalyzer.getCurrentToken().toString().length());
            }
        }
    }

    private void assertToken(TokenType expected, String str, int len) throws ParseException {
        if (lexicalAnalyzer.getCurrentToken() != expected) {
            throw new ParseException("Expected token " + expected + " but got " + str, lexicalAnalyzer.getCurrentPosition() - len);
        }
    }

    private ParseException getUnexpectedTokenException(int len) {
        return new ParseException("Unexpected token", lexicalAnalyzer.getCurrentPosition() - len);
    }
}
