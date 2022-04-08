// Generated from /Users/ppv-vldmr/Desktop/MT/lab4 2/lab4/src/main/antlr/Syntax.g4 by ANTLR 4.9.2


    import java.util.*;
	import java.util.stream.Collectors;
    import lab4.primarygrammar.*;
    import lab4.primarylex.*;
    import static lab4.primarygrammar.Util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SyntaxParser}.
 */
public interface SyntaxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#grammarFile}.
	 * @param ctx the parse tree
	 */
	void enterGrammarFile(SyntaxParser.GrammarFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#grammarFile}.
	 * @param ctx the parse tree
	 */
	void exitGrammarFile(SyntaxParser.GrammarFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#headers}.
	 * @param ctx the parse tree
	 */
	void enterHeaders(SyntaxParser.HeadersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#headers}.
	 * @param ctx the parse tree
	 */
	void exitHeaders(SyntaxParser.HeadersContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#members}.
	 * @param ctx the parse tree
	 */
	void enterMembers(SyntaxParser.MembersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#members}.
	 * @param ctx the parse tree
	 */
	void exitMembers(SyntaxParser.MembersContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#grammarPrelude}.
	 * @param ctx the parse tree
	 */
	void enterGrammarPrelude(SyntaxParser.GrammarPreludeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#grammarPrelude}.
	 * @param ctx the parse tree
	 */
	void exitGrammarPrelude(SyntaxParser.GrammarPreludeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(SyntaxParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(SyntaxParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#rules}.
	 * @param ctx the parse tree
	 */
	void enterRules(SyntaxParser.RulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#rules}.
	 * @param ctx the parse tree
	 */
	void exitRules(SyntaxParser.RulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#singleRule}.
	 * @param ctx the parse tree
	 */
	void enterSingleRule(SyntaxParser.SingleRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#singleRule}.
	 * @param ctx the parse tree
	 */
	void exitSingleRule(SyntaxParser.SingleRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#singleLexerRule}.
	 * @param ctx the parse tree
	 */
	void enterSingleLexerRule(SyntaxParser.SingleLexerRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#singleLexerRule}.
	 * @param ctx the parse tree
	 */
	void exitSingleLexerRule(SyntaxParser.SingleLexerRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#singleParserRule}.
	 * @param ctx the parse tree
	 */
	void enterSingleParserRule(SyntaxParser.SingleParserRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#singleParserRule}.
	 * @param ctx the parse tree
	 */
	void exitSingleParserRule(SyntaxParser.SingleParserRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#ruleArgs}.
	 * @param ctx the parse tree
	 */
	void enterRuleArgs(SyntaxParser.RuleArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#ruleArgs}.
	 * @param ctx the parse tree
	 */
	void exitRuleArgs(SyntaxParser.RuleArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#ruleReturns}.
	 * @param ctx the parse tree
	 */
	void enterRuleReturns(SyntaxParser.RuleReturnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#ruleReturns}.
	 * @param ctx the parse tree
	 */
	void exitRuleReturns(SyntaxParser.RuleReturnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void enterCodeBlock(SyntaxParser.CodeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void exitCodeBlock(SyntaxParser.CodeBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#alternatives}.
	 * @param ctx the parse tree
	 */
	void enterAlternatives(SyntaxParser.AlternativesContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#alternatives}.
	 * @param ctx the parse tree
	 */
	void exitAlternatives(SyntaxParser.AlternativesContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#alternative}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(SyntaxParser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#alternative}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(SyntaxParser.AlternativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#elemAlts}.
	 * @param ctx the parse tree
	 */
	void enterElemAlts(SyntaxParser.ElemAltsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#elemAlts}.
	 * @param ctx the parse tree
	 */
	void exitElemAlts(SyntaxParser.ElemAltsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(SyntaxParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(SyntaxParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#notLabeledElement}.
	 * @param ctx the parse tree
	 */
	void enterNotLabeledElement(SyntaxParser.NotLabeledElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#notLabeledElement}.
	 * @param ctx the parse tree
	 */
	void exitNotLabeledElement(SyntaxParser.NotLabeledElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#elemModifier}.
	 * @param ctx the parse tree
	 */
	void enterElemModifier(SyntaxParser.ElemModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#elemModifier}.
	 * @param ctx the parse tree
	 */
	void exitElemModifier(SyntaxParser.ElemModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#labeledElement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledElement(SyntaxParser.LabeledElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#labeledElement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledElement(SyntaxParser.LabeledElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#terminal}.
	 * @param ctx the parse tree
	 */
	void enterTerminal(SyntaxParser.TerminalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#terminal}.
	 * @param ctx the parse tree
	 */
	void exitTerminal(SyntaxParser.TerminalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SyntaxParser#ruleUsage}.
	 * @param ctx the parse tree
	 */
	void enterRuleUsage(SyntaxParser.RuleUsageContext ctx);
	/**
	 * Exit a parse tree produced by {@link SyntaxParser#ruleUsage}.
	 * @param ctx the parse tree
	 */
	void exitRuleUsage(SyntaxParser.RuleUsageContext ctx);
}