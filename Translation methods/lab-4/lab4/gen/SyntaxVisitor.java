// Generated from /Users/ppv-vldmr/Desktop/MT/lab4 2/lab4/src/main/antlr/Syntax.g4 by ANTLR 4.9.2


    import java.util.*;
	import java.util.stream.Collectors;
    import lab4.primarygrammar.*;
    import lab4.primarylex.*;
    import static lab4.primarygrammar.Util.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SyntaxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SyntaxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#grammarFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarFile(SyntaxParser.GrammarFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#headers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaders(SyntaxParser.HeadersContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#members}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMembers(SyntaxParser.MembersContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#grammarPrelude}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrammarPrelude(SyntaxParser.GrammarPreludeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(SyntaxParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#rules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRules(SyntaxParser.RulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#singleRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleRule(SyntaxParser.SingleRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#singleLexerRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleLexerRule(SyntaxParser.SingleLexerRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#singleParserRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleParserRule(SyntaxParser.SingleParserRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#ruleArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleArgs(SyntaxParser.RuleArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#ruleReturns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleReturns(SyntaxParser.RuleReturnsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#codeBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeBlock(SyntaxParser.CodeBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#alternatives}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternatives(SyntaxParser.AlternativesContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#alternative}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternative(SyntaxParser.AlternativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#elemAlts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemAlts(SyntaxParser.ElemAltsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(SyntaxParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#notLabeledElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotLabeledElement(SyntaxParser.NotLabeledElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#elemModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemModifier(SyntaxParser.ElemModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#labeledElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledElement(SyntaxParser.LabeledElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#terminal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminal(SyntaxParser.TerminalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SyntaxParser#ruleUsage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleUsage(SyntaxParser.RuleUsageContext ctx);
}