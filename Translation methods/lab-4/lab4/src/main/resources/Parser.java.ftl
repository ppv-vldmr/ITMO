<#-- @ftlvariable name="startNonTerminal" type="lab4.grammar.NonTerminal" -->
<#-- @ftlvariable name="helper" type="lab4.grammar.ParserFactory.Helper" -->
<#-- @ftlvariable name="rules" type="lab4.grammar.Rule[][]" -->
<#-- @ftlvariable name="nonTerminals" type="lab4.grammar.NonTerminal[]" -->
<#if package?has_content>package ${package};

</#if><#if package?has_content>import ${package}.Token;</#if>
<#if package?has_content>import static ${package}.TokenType.*;</#if>

import java.util.*;
import java.util.function.Supplier;

${header}

<#macro ensureToken tokenTypeName>
    if (token.type() != ${tokenTypeName})
        throw new RuntimeException("Expected ${tokenTypeName}, but found " + token.type().name());
</#macro>

public class Parser {
    private final Supplier<Token> lexer;

    private Token token;

    ${members}

    public Parser(Supplier<Token> lexer) {
        this.lexer = lexer;
    }

    public Tree_${startNonTerminal.name()} parse() {
        nextToken();
        var result = new Tree_${startNonTerminal.name()}();
        result.parse();
        return result;
    }

    <#list nonTerminals as nonTerminal>
        public class Tree_${nonTerminal.name()} implements Tree {
            <#if nonTerminal.args()?has_content>
                <#list nonTerminal.args() as arg>
                    ${arg};
                </#list>
            </#if>
            <#if nonTerminal.returns()?has_content>
                <#list nonTerminal.returns() as ret>
                    public ${ret};
                </#list>
            </#if>

            List<Tree> children = new ArrayList<>();

            <#if nonTerminal.args()?has_content>
                Tree_${nonTerminal.name()}(
                    <#list nonTerminal.args() as arg>
                        ${arg}<#if arg_has_next>,</#if>
                    </#list>
                ) {
                    <#list nonTerminal.args() as arg>
                        this.${arg?split(" ")[1]} = ${arg?split(" ")[1]};
                    </#list>
                }
            </#if>

            public List<Tree> getChildren() {
                return children;
            }

            void addChild(Tree tree) {
                children.add(tree);
            }

            String content(int i) {
                return ((Token) children.get(i)).content();
            }

            void parse() {
                switch (token.type()) {
                    <#list rules[nonTerminal_index] as rule>
                        <#list helper.first1(nonTerminal.name(), rule.nodes()) as terminal>
                            case ${terminal}:
                        </#list>

                        {
                            <#assign ruleArgs = rule.getArgs()/>

                            <#list rule.nodes() as node>
                                <#if node.terminal>
                                    <@ensureToken node.asTerminal().tokenTypeName()/>
                                    addChild(token);
                                    nextToken();
                                <#else>
                                    var child_${rule_index}_${node_index} = new Tree_${node.asNonTerminal().name()}(
                                        <#if ruleArgs[node_index]?has_content>
                                            <#list ruleArgs[node_index] as arg>
                                                ${arg}<#if arg_has_next>,</#if>
                                            </#list>
                                        </#if>
                                    );
                                    child_${rule_index}_${node_index}.parse();
                                    addChild(child_${rule_index}_${node_index});

                                    var ${node.asNonTerminal().name()} = child_${rule_index}_${node_index};
                                    <#if node.asNonTerminal().name()?contains("_")>
                                        <#if rule.afterCode()?has_content>
                                            var ${node.asNonTerminal().name()?split("_")[0]} = ${node.asNonTerminal().name()};
                                        <#else>
                                            var ${node.asNonTerminal().name()?split("_")[0]+node_index} = ${node.asNonTerminal().name()};
                                        </#if>

                                    </#if>
                                </#if>
                            </#list>

                            <#if rule.afterCode()?has_content>${rule.afterCode()}</#if>

                        }
                        break;
                    </#list>

                    default:
                        throw new RuntimeException("Unexpected token " + token.type());
                }
            }
        }

    </#list>

    private void nextToken() {
        token = lexer.get();
    }
}
