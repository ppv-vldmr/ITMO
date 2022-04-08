import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

//Поддерживаем типы
//char
//int
//float
//double

//unsigned char
//unsigned int
//unsigned short int
//unsigned long int

//signed char
//signed int
//signed short int
//signed long int

//short int

//long int
//long double

public class LexicalAnalyzer {

    private final InputStream inputStream;
    private TokenType currentToken;
    private int currentChar;
    private int currentPosition;
    private static final List<String> TYPES = List.of("long", "unsigned", "signed", "short");
    private static final List<String> TYPES_ENDS = List.of("int", "char", "float", "double", "void");

    public LexicalAnalyzer(InputStream inputStream) throws ParseException {
        this.inputStream = inputStream;
        currentPosition = 0;
        nextSymbol();
    }

    private void nextSymbol() throws ParseException {
        currentPosition++;
        try {
            currentChar = inputStream.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), currentPosition);
        }
    }

    public void nextToken() throws ParseException {
        skipWhitespaces();

        switch_symbol:
        switch (currentChar) {
            case '(' -> {
                nextSymbol();
                currentToken = TokenType.LEFT_PAREN;
            }
            case ')' -> {
                nextSymbol();
                if (currentChar == ';') {
                    currentToken = TokenType.RIGHT_PAREN_SEM;
                    nextSymbol();
                } else {
                    throw new ParseException("Illegal character " + (char) currentChar, currentPosition);
                }
            }
            case ',' -> {
                nextSymbol();
                currentToken = TokenType.COMA;
            }
            case '*' -> {
                nextSymbol();
                currentToken = TokenType.POINTER;
            }
            case -1 -> currentToken = TokenType.END;
            default -> {
                String token = getNextToken().toString();
                if (TYPES_ENDS.contains(token)) {
                    currentToken = TokenType.TYPE;
                } else if (TYPES.contains(token)) {
                    String nextToken = getNextToken().toString();
                    switch (token) {
                        case "signed":
                        case "unsigned":
                            if (nextToken.equals("char") || nextToken.equals("int")) {
                                currentToken = TokenType.TYPE;
                                break switch_symbol;
                            } else if (nextToken.equals("short") || nextToken.equals("long")) {
                                String lastToken = getNextToken().toString();
                                if (lastToken.equals("int")) {
                                    currentToken = TokenType.TYPE;
                                    break switch_symbol;
                                } else {
                                    throw new ParseException("Illegal character " + (char) currentChar, currentPosition - currentToken.toString().length());
                                }
                            }
                            break;
                        case "short":
                            if (nextToken.equals("int")) {
                                currentToken = TokenType.TYPE;
                                break switch_symbol;
                            } else {
                                throw new ParseException("Illegal character " + (char) currentChar, currentPosition - currentToken.toString().length());
                            }
                        case "long":
                            if (nextToken.equals("int") || nextToken.equals("double")) {
                                currentToken = TokenType.TYPE;
                                break switch_symbol;
                            } else {
                                throw new ParseException("Illegal character " + (char) currentChar, currentPosition - token.length());
                            }
                        default:
                            throw new ParseException("Illegal character " + (char) currentChar, currentPosition);
                    }
                } else {
                    if (validateName(token)) {
                        currentToken = TokenType.NAME;
                    }
                }
            }
        }
    }

    private StringBuilder getNextToken() throws ParseException {
        skipWhitespaces();
        StringBuilder sb = new StringBuilder();
        while (currentChar == '_' || Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)) {
            sb.append((char) currentChar);
            nextSymbol();
        }
        if (sb.length() == 0) {
            throw new ParseException("Illegal character " + (char) currentChar, currentPosition);
        }
        return sb;
    }

    private void skipWhitespaces() throws ParseException {
        while (isWhitespace(currentChar)) {
            nextSymbol();
        }
    }

    private boolean isWhitespace(int c) {
        return Character.isWhitespace(c);
    }

    private boolean validateName(String name) throws ParseException {
        int position = currentPosition - name.length();
        char firstSymbol = name.charAt(0);
        if (!(firstSymbol == '_' || Character.isAlphabetic(firstSymbol))) {
            throw new ParseException("Illegal character " + firstSymbol, position);
        }
        for (int i = 1; i < name.length(); i++) {
            char symbol = name.charAt(i);
            if (!(symbol == '_' || Character.isAlphabetic(symbol) || Character.isDigit(symbol))) {
                throw new ParseException("Illegal character " + symbol, position + i);
            }
        }
        return true;
    }

    public TokenType getCurrentToken() {
//        System.out.println(currentToken + " " + currentPosition);
        return currentToken;
    }

    public int getCurrentPosition() {
        return currentPosition - 1;
    }
}
