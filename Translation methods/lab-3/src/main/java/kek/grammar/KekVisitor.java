package kek.grammar;// Generated from /Users/ppv-vldmr/Desktop/MT/lab-3/src/main/antlr4/Kek.g4 by ANTLR 4.9.2

    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link KekParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface KekVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link KekParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(KekParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#varTyped}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarTyped(KekParser.VarTypedContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(KekParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#typeAnnotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeAnnotation(KekParser.TypeAnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(KekParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(KekParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(KekParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#importDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportDecl(KekParser.ImportDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#moduleName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleName(KekParser.ModuleNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(KekParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#funcName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncName(KekParser.FuncNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#funcArgsDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncArgsDecl(KekParser.FuncArgsDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#funcModifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncModifiers(KekParser.FuncModifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#funcModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncModifier(KekParser.FuncModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#externModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternModifier(KekParser.ExternModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(KekParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(KekParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#flowStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowStatement(KekParser.FlowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(KekParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(KekParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#continueStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(KekParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(KekParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#ifPositivePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfPositivePart(KekParser.IfPositivePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#ifNegativePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNegativePart(KekParser.IfNegativePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(KekParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(KekParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(KekParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#assignStatementNoEoln}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatementNoEoln(KekParser.AssignStatementNoEolnContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(KekParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#varUsage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarUsage(KekParser.VarUsageContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(KekParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(KekParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#orOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrOperator(KekParser.OrOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(KekParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#andOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndOperator(KekParser.AndOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(KekParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#equalsOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualsOperator(KekParser.EqualsOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#equals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquals(KekParser.EqualsContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#plusOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusOperator(KekParser.PlusOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#plus}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(KekParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#mulOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulOperator(KekParser.MulOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(KekParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#indexOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexOperator(KekParser.IndexOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(KekParser.IndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#notOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotOperator(KekParser.NotOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(KekParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(KekParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(KekParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#arrayLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteral(KekParser.ArrayLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#intLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteral(KekParser.IntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link KekParser#boolLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(KekParser.BoolLiteralContext ctx);
}