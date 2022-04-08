package lab4.grammar;

import java.util.*;

public class Grammar {
    private final String name;
    private final String header;
    private final String members;
    private final String startNonTerminalName;
    private final Map<String, NonTerminal> nonTerminals = new LinkedHashMap<>();
    private final Map<String, List<Rule>> rules = new LinkedHashMap<>();
    private final List<Map.Entry<String, Rule>> rulesList = new ArrayList<>();
    private final Map<String, Set<String>> first = new HashMap<>();
    private final Map<String, Set<String>> follow = new HashMap<>();

    public Grammar(String name, String header, String members, String startNonTerminalName) {
        this.name = name;
        this.header = header;
        this.members = members;
        this.startNonTerminalName = startNonTerminalName;
    }

    public Rule addRule(String nonTerminalName, List<Node> nodes) {
        return addRule(nonTerminalName, nodes, null);
    }

    public Rule addRule(String nonTerminalName, List<Node> nodes, String afterCode) {
        return addRule(nonTerminalName, nodes, afterCode, new LinkedHashMap<>());
    }

    public Rule addRule(String nonTerminalName, List<Node> nodes, String afterCode, Map<Integer, List<String>> indexToArgs) {
        Rule rule = new Rule(nodes, afterCode, indexToArgs);
        rules.computeIfAbsent(nonTerminalName, _k -> new ArrayList<>()).add(rule);
        rulesList.add(Map.entry(nonTerminalName, rule));
        return rule;
    }

    public void addNonTerminal(NonTerminal nonTerminal) {
        if (!nonTerminals.containsKey(nonTerminal.name()))
            nonTerminals.put(nonTerminal.name(), nonTerminal);
    }

    public NonTerminal getNonTerminal(String name) {
        return nonTerminals.get(name);
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getMembers() {
        return members;
    }

    public NonTerminal getStartNonTerminal() {
        return nonTerminals.get(startNonTerminalName);
    }

    public Map<String, NonTerminal> getNonTerminals() {
        return nonTerminals;
    }

    public List<Map.Entry<String, Rule>> getRules() {
        return rulesList;
    }

    public List<Rule> getRules(NonTerminal nonTerminal) {
        return rules.get(nonTerminal.name());
    }

    public Set<String> first1(String nonTerminalName, List<Node> nodes) {
        Set<String> firstTmp = findFirstTerminal(nodes);
        if (firstTmp.remove(Terminal.eps().tokenTypeName()))
            firstTmp.addAll(follow(NonTerminal.of(nonTerminalName)));
        return firstTmp;
    }

    public Set<String> first(NonTerminal nonTerminal) {
        if (first.isEmpty()) {
            buildFirst();
            buildFollow();
        }
        return first.get(nonTerminal.name());
    }

    public Set<String> follow(NonTerminal nonTerminal) {
        if (first.isEmpty()) {
            buildFirst();
            buildFollow();
        }
        return follow.get(nonTerminal.name());
    }

    private Set<String> findFirstTerminal(List<Node> nodes) {
        if (nodes.isEmpty())
            return new HashSet<>(Set.of(Terminal.eps().tokenTypeName()));

        if (nodes.get(0) instanceof Terminal terminal)
            return new HashSet<>(Set.of(terminal.tokenTypeName()));

        NonTerminal node = (NonTerminal) nodes.get(0);
        Set<String> firstTmp = new HashSet<>(first.get(node.name()));
        boolean hadEps = firstTmp.remove(Terminal.eps().tokenTypeName());
        if (hadEps) {
            firstTmp.addAll(findFirstTerminal(nodes.subList(1, nodes.size())));
        }
        return firstTmp;
    }

    public void buildFirst() {
        for (String nonTerminalName : nonTerminals.keySet()) {
            first.computeIfAbsent(nonTerminalName, _k -> new HashSet<>());
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Map.Entry<String, Rule> nonTerminalNameAndRule : rulesList) {
                String nonTerminalName = nonTerminalNameAndRule.getKey();
                Rule rule = nonTerminalNameAndRule.getValue();
                Set<String> firstSet = first.get(nonTerminalName);
                if (firstSet.addAll(findFirstTerminal(rule.nodes())))
                    changed = true;
            }
        }
    }

    public void buildFollow() {
        for (String nonTerminalName : nonTerminals.keySet()) {
            follow.computeIfAbsent(nonTerminalName, _k -> new HashSet<>());
        }

        follow.get(startNonTerminalName).add(Terminal.eof().tokenTypeName());

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Map.Entry<String, Rule> nonTerminalNameAndRule : rulesList) {
                String nonTerminalName = nonTerminalNameAndRule.getKey();
                Rule rule = nonTerminalNameAndRule.getValue();
                List<Node> nodes = rule.nodes();

                for (int i = 0; i < nodes.size(); i++) {
                    if (nodes.get(i) instanceof NonTerminal nonTerminal) {
                        Set<String> followTmp = findFirstTerminal(nodes.subList(i + 1, nodes.size()));
                        boolean hadEps = followTmp.remove(Terminal.eps().tokenTypeName());
                        if (hadEps) {
                            followTmp.addAll(follow.get(nonTerminalName));
                        }

                        Set<String> followSet = follow.get(nonTerminal.name());
                        if (followSet.addAll(followTmp))
                            changed = true;
                    }
                }
            }
        }
    }
}
