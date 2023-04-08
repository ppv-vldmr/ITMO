package org.example.exception;

public class UnexpectedSymbolException extends IllegalStateException {
    public UnexpectedSymbolException(CharSequence charSequence, int index) {
        super("Unexpected symbol " + getWrappedSymbol(charSequence, index) + "at position " + index + ".");
    }

    private static String getWrappedSymbol(CharSequence charSequence, int index) {
        return charSequence.length() > index ? "'" + charSequence.charAt(index) + "' " : "";
    }
}
