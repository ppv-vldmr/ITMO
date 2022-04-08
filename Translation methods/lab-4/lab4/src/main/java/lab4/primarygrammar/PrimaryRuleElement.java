package lab4.primarygrammar;

import java.util.List;

public class PrimaryRuleElement {
    private final String label;

    private final PrimaryGrammarElement grammarElement;

    private final List<PrimaryRuleContent> primaryRuleContents;

    private final Quantifier quantifier;
    private final Type type;

    private PrimaryRuleElement(String label, PrimaryGrammarElement grammarElement, List<PrimaryRuleContent> ruleContents, Quantifier quantifier, Type type) {
        this.label = label;
        this.grammarElement = grammarElement;
        this.primaryRuleContents = ruleContents;
        this.quantifier = quantifier;
        this.type = type;
    }

    public PrimaryRuleElement(String label, PrimaryGrammarElement grammarElement, Quantifier quantifier) {
        this(label, grammarElement, null, quantifier, Type.SIMPLE);
    }

    public PrimaryRuleElement(String label, List<PrimaryRuleContent> ruleContents, Quantifier quantifier) {
        this(label, null, ruleContents, quantifier, Type.NESTED);
    }

    public PrimaryRuleElement(PrimaryGrammarElement grammarElement, Quantifier quantifier) {
        this(null, grammarElement, quantifier);
    }

    public PrimaryRuleElement(List<PrimaryRuleContent> ruleContents, Quantifier quantifier) {
        this(null, ruleContents, quantifier);
    }

    public PrimaryRuleElement(String label, PrimaryRuleElement primaryRuleElement) {
        this(
                label,
                primaryRuleElement.grammarElement,
                primaryRuleElement.primaryRuleContents,
                primaryRuleElement.quantifier,
                primaryRuleElement.type
        );
    }

    public boolean isLabeled() {
        return label != null;
    }

    public String getLabel() {
        return label;
    }

    public PrimaryGrammarElement getGrammarElement() {
        return grammarElement;
    }

    public List<PrimaryRuleContent> getPrimaryRuleContents() {
        return primaryRuleContents;
    }

    public Quantifier getQuantifier() {
        return quantifier;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SIMPLE, NESTED
    }
}
