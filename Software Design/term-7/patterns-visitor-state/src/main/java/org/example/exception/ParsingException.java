package org.example.exception;

public class ParsingException extends IllegalStateException {
    public ParsingException() {
        super("Unknown parsing exception.");
    }
}
