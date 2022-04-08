package lab4.primarygrammar;

import lab4.primarylex.PrimaryToken;

import java.util.List;

public record PrimaryGrammar(String name,
                             String header,
                             String members,
                             List<PrimaryToken> primaryTokens,
                             List<PrimaryRule> parserRules) {

}
