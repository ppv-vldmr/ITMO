package kek.grammar;// Generated from /Users/ppv-vldmr/Desktop/MT/lab-3/src/main/antlr4/Kek.g4 by ANTLR 4.9.2

    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link KekParser}.
 */
public interface KekListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link KekParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(KekParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(KekParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#varTyped}.
	 * @param ctx the parse tree
	 */
	void enterVarTyped(KekParser.VarTypedContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#varTyped}.
	 * @param ctx the parse tree
	 */
	void exitVarTyped(KekParser.VarTypedContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(KekParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(KekParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#typeAnnotation}.
	 * @param ctx the parse tree
	 */
	void enterTypeAnnotation(KekParser.TypeAnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#typeAnnotation}.
	 * @param ctx the parse tree
	 */
	void exitTypeAnnotation(KekParser.TypeAnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(KekParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(KekParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(KekParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(KekParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(KekParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(KekParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void enterImportDecl(KekParser.ImportDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#importDecl}.
	 * @param ctx the parse tree
	 */
	void exitImportDecl(KekParser.ImportDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#moduleName}.
	 * @param ctx the parse tree
	 */
	void enterModuleName(KekParser.ModuleNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#moduleName}.
	 * @param ctx the parse tree
	 */
	void exitModuleName(KekParser.ModuleNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(KekParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(KekParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(KekParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(KekParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#funcArgsDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncArgsDecl(KekParser.FuncArgsDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#funcArgsDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncArgsDecl(KekParser.FuncArgsDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#funcModifiers}.
	 * @param ctx the parse tree
	 */
	void enterFuncModifiers(KekParser.FuncModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#funcModifiers}.
	 * @param ctx the parse tree
	 */
	void exitFuncModifiers(KekParser.FuncModifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#funcModifier}.
	 * @param ctx the parse tree
	 */
	void enterFuncModifier(KekParser.FuncModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#funcModifier}.
	 * @param ctx the parse tree
	 */
	void exitFuncModifier(KekParser.FuncModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#externModifier}.
	 * @param ctx the parse tree
	 */
	void enterExternModifier(KekParser.ExternModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#externModifier}.
	 * @param ctx the parse tree
	 */
	void exitExternModifier(KekParser.ExternModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(KekParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(KekParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(KekParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(KekParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#flowStatement}.
	 * @param ctx the parse tree
	 */
	void enterFlowStatement(KekParser.FlowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#flowStatement}.
	 * @param ctx the parse tree
	 */
	void exitFlowStatement(KekParser.FlowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(KekParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(KekParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(KekParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(KekParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(KekParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(KekParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(KekParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(KekParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#ifPositivePart}.
	 * @param ctx the parse tree
	 */
	void enterIfPositivePart(KekParser.IfPositivePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#ifPositivePart}.
	 * @param ctx the parse tree
	 */
	void exitIfPositivePart(KekParser.IfPositivePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#ifNegativePart}.
	 * @param ctx the parse tree
	 */
	void enterIfNegativePart(KekParser.IfNegativePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#ifNegativePart}.
	 * @param ctx the parse tree
	 */
	void exitIfNegativePart(KekParser.IfNegativePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(KekParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(KekParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(KekParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(KekParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(KekParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(KekParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#assignStatementNoEoln}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatementNoEoln(KekParser.AssignStatementNoEolnContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#assignStatementNoEoln}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatementNoEoln(KekParser.AssignStatementNoEolnContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(KekParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(KekParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#varUsage}.
	 * @param ctx the parse tree
	 */
	void enterVarUsage(KekParser.VarUsageContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#varUsage}.
	 * @param ctx the parse tree
	 */
	void exitVarUsage(KekParser.VarUsageContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(KekParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(KekParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(KekParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(KekParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#orOperator}.
	 * @param ctx the parse tree
	 */
	void enterOrOperator(KekParser.OrOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#orOperator}.
	 * @param ctx the parse tree
	 */
	void exitOrOperator(KekParser.OrOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(KekParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(KekParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#andOperator}.
	 * @param ctx the parse tree
	 */
	void enterAndOperator(KekParser.AndOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#andOperator}.
	 * @param ctx the parse tree
	 */
	void exitAndOperator(KekParser.AndOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(KekParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(KekParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#equalsOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualsOperator(KekParser.EqualsOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#equalsOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualsOperator(KekParser.EqualsOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#equals}.
	 * @param ctx the parse tree
	 */
	void enterEquals(KekParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#equals}.
	 * @param ctx the parse tree
	 */
	void exitEquals(KekParser.EqualsContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#plusOperator}.
	 * @param ctx the parse tree
	 */
	void enterPlusOperator(KekParser.PlusOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#plusOperator}.
	 * @param ctx the parse tree
	 */
	void exitPlusOperator(KekParser.PlusOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#plus}.
	 * @param ctx the parse tree
	 */
	void enterPlus(KekParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#plus}.
	 * @param ctx the parse tree
	 */
	void exitPlus(KekParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#mulOperator}.
	 * @param ctx the parse tree
	 */
	void enterMulOperator(KekParser.MulOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#mulOperator}.
	 * @param ctx the parse tree
	 */
	void exitMulOperator(KekParser.MulOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(KekParser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(KekParser.MulContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#indexOperator}.
	 * @param ctx the parse tree
	 */
	void enterIndexOperator(KekParser.IndexOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#indexOperator}.
	 * @param ctx the parse tree
	 */
	void exitIndexOperator(KekParser.IndexOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(KekParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(KekParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#notOperator}.
	 * @param ctx the parse tree
	 */
	void enterNotOperator(KekParser.NotOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#notOperator}.
	 * @param ctx the parse tree
	 */
	void exitNotOperator(KekParser.NotOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(KekParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(KekParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(KekParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(KekParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(KekParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(KekParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(KekParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(KekParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#intLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(KekParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#intLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(KekParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link KekParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(KekParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link KekParser#boolLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(KekParser.BoolLiteralContext ctx);
}