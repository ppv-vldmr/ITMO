package org.example.visitor;

import org.example.parser.Parser;
import org.example.tokenizer.Token;
import org.example.tokenizer.Tokenizer;

import java.util.Iterator;
import java.util.function.Supplier;

public abstract class ParseableVisitor extends TokenVisitor {
    private final Supplier<Iterator<Token>> tokenIterator;

    public ParseableVisitor(Parser parser) {
        this.tokenIterator = parser.parse()::iterator;
    }

    public ParseableVisitor(CharSequence charSequence) {
        this.tokenIterator = () -> new Tokenizer(charSequence).iterator();
    }

    public Iterator<Token> getIterator() {
        return tokenIterator.get();
    }
}
