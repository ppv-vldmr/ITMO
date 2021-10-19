package parser;

import java.util.HashSet;
import java.util.Set;

import proof.SubstitutionInfo;
import token.Variable;

public interface Expression {
    String toStringPrefix();
    String toStringInfix();
    boolean equals(Expression expression);
    void substitution(Expression compareWith, SubstitutionInfo info);
    boolean isFree(Set<String> bound);
    default boolean isFree(String oneBound) {
        HashSet<String> bound = new HashSet<>();
        bound.add(oneBound);
        return isFree(bound);
    }
    boolean isFreeForV(Variable v);
    boolean correctSubstIn(Expression expr, Variable x, SubstitutionInfo info);
}
