package org.example.parser;

import org.example.tokenizer.Token;

import java.util.List;

public interface Parser {
    List<Token> parse();
}
