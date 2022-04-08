package lab4.grammar;

import lab4.primarygrammar.*;
import lab4.util.Util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GrammarFactory implements Function<PrimaryGrammar, Grammar> {
    private static final Random random = new Random();

    private Grammar grammar;

    @Override
    public Grammar apply(PrimaryGrammar primaryGrammar) {
        grammar = new Grammar(
                primaryGrammar.name(),
                primaryGrammar.header(),
                primaryGrammar.members(),
                primaryGrammar.parserRules().get(0).name()
        );
        processGrammar(primaryGrammar);
        return grammar;
    }

    private String genSubruleName(String name) {
        return name + "_inner" + random.nextInt(0, Integer.MAX_VALUE);
    }

    private String genQuantifierRuleName(String name, Quantifier quantifier) {
        return name + "_" + quantifier.name() + random.nextInt(0, Integer.MAX_VALUE);
    }

    private Node processQuantifier(PrimaryTerminal primaryTerminal, Quantifier quantifier) {
        if (quantifier == null) {
            return new Terminal(primaryTerminal.tokenName());
        }

        String name = genQuantifierRuleName(primaryTerminal.tokenName(), quantifier);
        NonTerminal resultNode = NonTerminal.of(name);
        grammar.addNonTerminal(resultNode);

        switch (quantifier) {
            case ONE_OR_MORE -> {
                NonTerminal helperNode = NonTerminal.of(genQuantifierRuleName(primaryTerminal.tokenName(), quantifier));
                grammar.addNonTerminal(helperNode);
                grammar.addRule(resultNode.name(), List.of(new Terminal(primaryTerminal.tokenName()), helperNode));
                grammar.addRule(helperNode.name(), Collections.emptyList());
                grammar.addRule(helperNode.name(), List.of(new Terminal(primaryTerminal.tokenName()), helperNode));
            }
            case ZERO_OR_ONE -> {
                grammar.addRule(resultNode.name(), Collections.emptyList());
                grammar.addRule(resultNode.name(), List.of(new Terminal(primaryTerminal.tokenName())));
            }
            case ZERO_OR_MORE -> {
                grammar.addRule(resultNode.name(), Collections.emptyList());
                grammar.addRule(resultNode.name(), List.of(new Terminal(primaryTerminal.tokenName()), resultNode));
            }
        }

        return resultNode;
    }

    private Node processQuantifier(PrimaryNonTerminal primaryNonTerminal, Quantifier quantifier, List<String> args) {
        if (quantifier == null) {
            return NonTerminal.of(primaryNonTerminal.name());
        }

        NonTerminal parentNonTerminal = grammar.getNonTerminal(primaryNonTerminal.name());

        String name = genQuantifierRuleName(primaryNonTerminal.name(), quantifier);
//        NonTerminal resultNode = new NonTerminal(name, null, parentNonTerminal.args(), parentNonTerminal.returns());
        NonTerminal resultNode = new NonTerminal(name, null, parentNonTerminal.args(), parentNonTerminal.returns());
        grammar.addNonTerminal(resultNode);

        switch (quantifier) {
            case ONE_OR_MORE -> {
                NonTerminal helperNode = NonTerminal.of(genQuantifierRuleName(primaryNonTerminal.name(), quantifier));
                grammar.addNonTerminal(helperNode);
                grammar.addRule(resultNode.name(), List.of(NonTerminal.of(primaryNonTerminal.name()), helperNode));
                grammar.addRule(helperNode.name(), Collections.emptyList());
                grammar.addRule(helperNode.name(), List.of(NonTerminal.of(primaryNonTerminal.name()), helperNode));
            }
            case ZERO_OR_ONE -> {
                grammar.addRule(resultNode.name(), Collections.emptyList());
//                grammar.addRule(resultNode.name(), List.of(NonTerminal.of(primaryNonTerminal.name())));

                StringBuilder afterCode = new StringBuilder();
                for (String ret : parentNonTerminal.returns()) {
                    String newRet = ret.split(" ")[1];
                    afterCode.append(newRet).append(" = ").append(parentNonTerminal.name()).append(".").append(newRet).append(";\n");
                }

                grammar.addRule(resultNode.name(), List.of(NonTerminal.of(primaryNonTerminal.name())), afterCode.toString(), Map.of(
                        0, parentNonTerminal.args().stream().map(x -> x.split(" ")[1]).collect(Collectors.toList())
                ));
            }
            case ZERO_OR_MORE -> {
                grammar.addRule(resultNode.name(), Collections.emptyList());
                grammar.addRule(resultNode.name(), List.of(NonTerminal.of(primaryNonTerminal.name()), resultNode));
            }
        }

        return resultNode;
    }

    private void processRule(String nonTerminalName, PrimaryRuleContent primaryRuleContent) {
        List<PrimaryRuleElement> elements = primaryRuleContent.elements();
        List<Node> nodes = new ArrayList<>();
        Map<Integer, List<String>> indexToArgs = new LinkedHashMap<>();

        for (int i = 0; i < elements.size(); ++i) {
            PrimaryRuleElement element = elements.get(i);

            if (element.getType() == PrimaryRuleElement.Type.SIMPLE) {
                if (element.getGrammarElement() instanceof PrimaryTerminal primaryTerminal) {
                    nodes.add(processQuantifier(primaryTerminal, element.getQuantifier()));

                } else if (element.getGrammarElement() instanceof PrimaryNonTerminal primaryNonTerminal) {
                    if (Util.isNotEmpty(primaryNonTerminal.args())) {
                        indexToArgs.put(i, primaryNonTerminal.args());
                    }
                    nodes.add(processQuantifier(primaryNonTerminal, element.getQuantifier(), primaryNonTerminal.args()));

                } else {
                    // Impossible case
                    throw new RuntimeException();
                }
            } else if (element.getType() == PrimaryRuleElement.Type.NESTED) {
                String subruleName = genSubruleName(nonTerminalName);
                processNonTerminal(subruleName, element.getPrimaryRuleContents());
                nodes.add(processQuantifier(new PrimaryNonTerminal(subruleName, null), element.getQuantifier(), null));
            } else {
                // Impossible case
                throw new RuntimeException();
            }
        }

        grammar.addRule(nonTerminalName, nodes, primaryRuleContent.afterCode(), indexToArgs);
    }

    private void processNonTerminal(String name,
                                    List<PrimaryRuleContent> primaryRuleContents) {
        grammar.addNonTerminal(NonTerminal.of(name));
        for (PrimaryRuleContent primaryRuleContent : primaryRuleContents) {
            processRule(name, primaryRuleContent);
        }
    }

    private void processGrammar(PrimaryGrammar primaryGrammar) {
        List<PrimaryRule> primaryRules = primaryGrammar.parserRules();
        for (PrimaryRule primaryRule : primaryRules) {
            grammar.addNonTerminal(new NonTerminal(
                    primaryRule.name(),
                    primaryRule.initCode(),
                    primaryRule.ruleArgs(),
                    primaryRule.ruleReturns()
            ));
        }

        for (PrimaryRule primaryRule : primaryRules) {
            processNonTerminal(primaryRule.name(), primaryRule.ruleContents());
        }
    }
}
