package org.example.exception;

public class EvaluationException extends IllegalStateException {
    public EvaluationException(String text) {
        super(text);
    }
}
