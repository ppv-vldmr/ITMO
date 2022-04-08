grammar Syntax;

@header {

    import java.util.*;
	import java.util.stream.Collectors;
    import lab4.primarygrammar.*;
    import lab4.primarylex.*;
    import static lab4.primarygrammar.Util.*;
}

grammarFile returns [PrimaryGrammar grammar]: grammarPrelude hdrs+=headers* mmbrs+=members* rules EOF {
    $grammar = new PrimaryGrammar(
        $grammarPrelude.grammarName,
        $hdrs.stream().map(x -> getJava(x.content)).collect(Collectors.joining("\n")),
        $mmbrs.stream().map(x -> getJava(x.content)).collect(Collectors.joining("\n")),
        $rules.primaryTokens,
        $rules.primaryRules
    );
};

headers returns [String content]: HEADER JAVA_CODE_BRACES {
    $content = _localctx.getChild(1).getText();
};

members returns [String content]: MEMBERS JAVA_CODE_BRACES {
    $content = _localctx.getChild(1).getText();
};

grammarPrelude returns [String grammarName]: GRAMMAR name SEMICOLON {
    $grammarName = $name.content;
};

name returns [String content]:
    RULE_NAME {$content = _localctx.getChild(0).getText();} |
    TOKEN_NAME {$content = _localctx.getChild(0).getText();};

rules returns [List<PrimaryToken> primaryTokens, List<PrimaryRule> primaryRules]: rls+=singleRule* {
    $primaryTokens = $rls.stream().map(x -> x.primaryToken).filter(Objects::nonNull).collect(Collectors.toList());
    $primaryRules = $rls.stream().map(x -> x.primaryRule).filter(Objects::nonNull).collect(Collectors.toList());
};

singleRule returns [PrimaryToken primaryToken, PrimaryRule primaryRule]:
    singleParserRule {
        $primaryToken = null;
        $primaryRule = $singleParserRule.primaryRule;
    } | singleLexerRule {
        $primaryToken = $singleLexerRule.primaryToken;
        $primaryRule = null;
    };

singleLexerRule returns [PrimaryToken primaryToken] @init{boolean skip = false;}:
    TOKEN_NAME COLON REGEX (ARROW SKIP_WORD {skip = true;})? SEMICOLON {
    $primaryToken = new PrimaryToken(_localctx.getChild(0).getText(), _localctx.getChild(2).getText(), skip);
};

singleParserRule returns [PrimaryRule primaryRule]:
    RULE_NAME args+=ruleArgs? rets+=ruleReturns? COLON init+=codeBlock? alternatives SEMICOLON {
    $primaryRule = new PrimaryRule(
        _localctx.getChild(0).getText(),
        get($args) == null ? Collections.emptyList() : get($args).ruleArgsList,
        get($rets) == null ? Collections.emptyList() : get($rets).ruleReturnsList,
        get($init) == null ? null : get($init).code,
        $alternatives.ruleContents
    );
};

ruleArgs returns [List<String> ruleArgsList]: codes+=JAVA_CODE_BRACKETS+ {
    $ruleArgsList = $codes.stream().map(x -> getJava(x.getText())).collect(Collectors.toList());
};

ruleReturns returns [List<String> ruleReturnsList]: RETURNS codes+=JAVA_CODE_BRACKETS+ {
    $ruleReturnsList = $codes.stream().map(x -> getJava(x.getText())).collect(Collectors.toList());
};

codeBlock returns [String code]: JAVA_CODE_BRACES {
    $code = getJava(_localctx.getChild(0).getText());
};

alternatives returns [List<PrimaryRuleContent> ruleContents]: alts+=alternative (PIPE alts+=alternative)* {
    $ruleContents = $alts.stream().map(x -> x.ruleContent).collect(Collectors.toList());
};

alternative returns [PrimaryRuleContent ruleContent]: elems+=element+ code+=codeBlock? {
    $ruleContent = new PrimaryRuleContent(
        $elems.stream().map(x -> x.ruleElement).collect(Collectors.toList()),
        get($code) == null ? null : get($code).code
    );
};

elemAlts returns [List<PrimaryRuleContent> ruleContents]: alts+=alternative (PIPE alts+=alternative)* {
    $ruleContents = $alts.stream().map(x -> x.ruleContent).collect(Collectors.toList());
};

element returns [PrimaryRuleElement ruleElement]:
    labeledElement {$ruleElement = $labeledElement.ruleElement;} |
    notLabeledElement {$ruleElement = $notLabeledElement.ruleElement;};

notLabeledElement returns [PrimaryRuleElement ruleElement]:
    terminal  q+=elemModifier? {
        $ruleElement = new PrimaryRuleElement(
            $terminal.terminalElem,
            get($q) == null ? null : get($q).quantifier
        );
    } |
    ruleUsage q+=elemModifier? {
        $ruleElement = new PrimaryRuleElement(
            $ruleUsage.nonTerminalElem,
            get($q) == null ? null : get($q).quantifier
        );
    } |
    LPAREN elemAlts RPAREN q+=elemModifier? {
        $ruleElement = new PrimaryRuleElement(
                $elemAlts.ruleContents,
                get($q) == null ? null : get($q).quantifier
            );
    };

elemModifier returns [Quantifier quantifier]:
    QUESTION {$quantifier = Quantifier.ZERO_OR_ONE;} |
    STAR {$quantifier = Quantifier.ZERO_OR_MORE;} |
    PLUS {$quantifier = Quantifier.ONE_OR_MORE;};

labeledElement returns [PrimaryRuleElement ruleElement]: name APPEND notLabeledElement {
    $ruleElement = new PrimaryRuleElement($name.content, $notLabeledElement.ruleElement);
};

terminal returns [PrimaryTerminal terminalElem]: TOKEN_NAME {$terminalElem = new PrimaryTerminal(_localctx.getChild(0).getText());};

ruleUsage returns [PrimaryNonTerminal nonTerminalElem]: RULE_NAME args+=JAVA_CODE_BRACKETS* {
    $nonTerminalElem = new PrimaryNonTerminal(
        _localctx.getChild(0).getText(),
        $args.stream().map(x -> getJava(x.getText())).collect(Collectors.toList())
    );
};

RETURNS: 'returns';
GRAMMAR: 'grammar';
JAVA_CODE_BRACES: '{' ( JAVA_CODE_BRACES | ~[{}] )* '}';
JAVA_CODE_BRACKETS: '[' ( JAVA_CODE_BRACKETS | ~('['|']') )* ']';
REGEX: '"' ( ~'"' )* '"';
LBRACKET: '[';
RBRACKET: ']';
LBRACE: '{';
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';
SEMICOLON: ';';
COLON: ':';
COMMA: ',';
PIPE: '|';
QUESTION: '?';
STAR: '*';
PLUS: '+';
APPEND: '+=';
HEADER: '@header';
MEMBERS: '@members';
ARROW: '->';
SKIP_WORD: 'skip';
RULE_NAME: [a-z][A-Za-z0-9_]*;
TOKEN_NAME: [A-Z][A-Za-z0-9_]*;

WS:[ \n\t\r]+ -> skip;