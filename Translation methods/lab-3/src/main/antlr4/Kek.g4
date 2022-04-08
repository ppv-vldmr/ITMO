grammar Kek;

@header {
    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;
}

@members {
    private KekContextManager contextManager;
}

file [KekContextManager kekContextManager] returns [KekFile kekFile]: {
    contextManager = kekContextManager;
} (importDecls+=importDecl | varDecls+=varDecl | funcDecls+=funcDecl)* EOF {
    $kekFile = new KekFile(contextManager);
};

importDecl: IMPORT EXTERN? moduleName EOLN;

moduleName: ID;

varDecl: LET varTyped EOLN {
    contextManager.addVar($varTyped.kekVar);
};

varTyped returns [KekVar kekVar]: var typeAnnotation {
    $kekVar = new KekVar($typeAnnotation.kekType, _localctx.getChild(0).getText());
};

var returns [String name]: ID {$name = _localctx.getChild(0).getText();};

typeAnnotation returns [KekType kekType]: COLON type {$kekType = $type.kekType;};

type returns [KekType kekType]:
    primitiveType {$kekType = $primitiveType.kekType;} |
    arrayType {$kekType = $arrayType.kekType;};

primitiveType returns [KekType kekType]:
    INT {$kekType = KekPrimitiveType.INT;} |
    BOOL {$kekType = KekPrimitiveType.BOOL;};

arrayType returns [KekType kekType]: LBRACKET type RBRACKET {$kekType = new KekArrayType($type.kekType);};

funcDecl: DEF funcName LPAR funcArgsDecl RPAR typeAnnotation fm+=funcModifiers? (blocks+=block[$funcArgsDecl.kekVars] | EOLN) {
    KekFunc kekFunc = new KekFunc(
        $funcName.name,
        $typeAnnotation.kekType,
        $funcArgsDecl.kekVars
    );
    if ($fm.isEmpty() || !$fm.get(0).kekModifiers.contains(KekModifier.DEFER)) {
        if ($fm.isEmpty() || !$fm.get(0).kekModifiers.contains(KekModifier.EXTERN)) {
            contextManager.addFunc(kekFunc);
            if ($blocks != null && $blocks.size() == 1)
                contextManager.addFuncContent(kekFunc, $blocks.get(0).kekBlock);
            else
                throw new RuntimeException();
        } else {
            contextManager.addExternFunc(kekFunc);
        }
    } else {
        contextManager.addDeferFunc(kekFunc);
    }
};

funcName returns [String name]: ID {$name = _localctx.getChild(0).getText();};

funcArgsDecl returns [List<KekVar> kekVars]: (vars+=varTyped (COMMA vars+=varTyped)*)? {
    $kekVars = $vars.stream().map(x -> x.kekVar).collect(Collectors.toList());
};

funcModifiers returns [Set<KekModifier> kekModifiers]: mods+=funcModifier+ {
    $kekModifiers = $mods.stream().map(x -> x.kekModifier).collect(Collectors.toSet());
};

funcModifier returns [KekModifier kekModifier]:
    DEFER {$kekModifier = KekModifier.DEFER;} |
    EXTERN {$kekModifier = KekModifier.EXTERN;};

block [List<KekVar> additionalVars] returns [KekBlock kekBlock]: {
    if ($additionalVars != null) {
        KekContext context = contextManager.getContext();
        $additionalVars.forEach(context::addAdditionalVar);
    }
} LBRACE EOLN statements+=statement* RBRACE EOLN {
    $kekBlock = new KekBlock(
        $statements.stream().map(x -> x.kekStatement).collect(Collectors.toList()),
        contextManager.getContext()
    );
};

statement returns [KekStatement kekStatement]:
    simpleStatement {$kekStatement = $simpleStatement.kekStatement;} |
    flowStatement {$kekStatement = $flowStatement.kekStatement;};

flowStatement returns [KekStatement kekStatement]:
    ifStatement {$kekStatement = $ifStatement.kekIf;} |
    whileStatement {$kekStatement = $whileStatement.kekWhile;} |
    returnStatement {$kekStatement = $returnStatement.kekReturn;} |
    breakStatement {$kekStatement = $breakStatement.kekStatement;} |
    continueStatement {$kekStatement = $continueStatement.kekStatement;};

returnStatement returns [KekReturn kekReturn]: RETURN expression? EOLN {$kekReturn = new KekReturn($expression.kekExpr);};

breakStatement returns [KekStatement kekStatement]: BREAK EOLN {$kekStatement = new KekBreak();};
continueStatement returns [KekStatement kekStatement]: CONTINUE EOLN {$kekStatement = new KekContinue();};

ifStatement returns [KekIf kekIf]: ifPositivePart ifNegativeParts+=ifNegativePart? {
    $kekIf = new KekIf(
        $ifPositivePart.kekExpr,
        $ifPositivePart.kekBlock,
        ($ifNegativeParts != null && $ifNegativeParts.size() == 1 ? $ifNegativeParts.get(0).kekBlock : null)
    );
};

ifPositivePart returns [KekExpr kekExpr, KekBlock kekBlock]: IF expression block[null] {
    $kekExpr = $expression.kekExpr;
    $kekBlock = $block.kekBlock;
};
ifNegativePart returns [KekBlock kekBlock]: ELSE block[null] {$kekBlock = $block.kekBlock;};

whileStatement returns [KekWhile kekWhile]: WHILE expression block[null] {$kekWhile = new KekWhile($expression.kekExpr, $block.kekBlock);};

simpleStatement returns [KekStatement kekStatement]:
    varDecl |
    assignStatement {$kekStatement = $assignStatement.kekAssign;} |
    (expression EOLN) {$kekStatement = KekStatement.of($expression.kekExpr);};

assignStatement returns [KekAssign kekAssign]:
    varUsages+=varUsage (COMMA varUsages+=varUsage)* ASSIGN exprs+=expression (COMMA exprs+=expression)* EOLN
{
    if ($varUsages.size() != $exprs.size()) {
        throw new RuntimeException("Expected the same count of vars and expressions");
    }

    $kekAssign = new KekAssign(
        $varUsages.stream().map(x -> x.kekVarUsage).collect(Collectors.toList()),
        $exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList()),
        false
    );
};

varUsage returns [KekVarUsage kekVarUsage]: var {
    KekVar kekVar = contextManager.getVar($var.name);
    $kekVarUsage = new KekVarUsage(kekVar);
};

expression returns [KekExpr kekExpr]: operator {$kekExpr = $operator.kekExpr;};

operator returns [KekExpr kekExpr]: orOperator {$kekExpr = $orOperator.kekExpr;};

orOperator returns [KekExpr kekExpr]: lst+=andOperator (rules+=or lst+=andOperator)* {
    $kekExpr = KekOperator.fold(
        $rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
        $lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};
or returns [KekOperatorRule kekOperatorRule]:
    OR {$kekOperatorRule = KekOperatorRule.OR;} |
    OR_SIGN {$kekOperatorRule = KekOperatorRule.OR;} |
    OR_SIGN_BITWISE {$kekOperatorRule = KekOperatorRule.OR;};

andOperator returns [KekExpr kekExpr]: lst+=equalsOperator (rules+=and lst+=equalsOperator)* {
    $kekExpr = KekOperator.fold(
        $rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
        $lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};
and returns [KekOperatorRule kekOperatorRule]:
    AND {$kekOperatorRule = KekOperatorRule.AND;} |
    AND_SIGN {$kekOperatorRule = KekOperatorRule.AND;} |
    AND_SIGN_BITWISE {$kekOperatorRule = KekOperatorRule.AND;};

equalsOperator returns [KekExpr kekExpr]: lst+=plusOperator (rules+=equals lst+=plusOperator)* {
    $kekExpr = KekOperator.fold(
        $rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
        $lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};
equals returns [KekOperatorRule kekOperatorRule]:
    EQUALS {$kekOperatorRule = KekOperatorRule.EQUALS;} |
    NOT_EQUALS {$kekOperatorRule = KekOperatorRule.NOT_EQUALS;};

plusOperator returns [KekExpr kekExpr]: lst+=mulOperator (rules+=plus lst+=mulOperator)* {
    $kekExpr = KekOperator.fold(
        $rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
        $lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};
plus returns [KekOperatorRule kekOperatorRule]:
    PLUS_SIGN {$kekOperatorRule = KekOperatorRule.PLUS;} |
    MINUS_SIGN {$kekOperatorRule = KekOperatorRule.MINUS;};

mulOperator returns [KekExpr kekExpr]: lst+=indexOperator (rules+=mul lst+=indexOperator)* {
    $kekExpr = KekOperator.fold(
        $rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
        $lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};
mul returns [KekOperatorRule kekOperatorRule]:
    MUL_SIGN  {$kekOperatorRule = KekOperatorRule.MUL;} |
    DIV {$kekOperatorRule = KekOperatorRule.DIV;} |
    DIV_SIGN {$kekOperatorRule = KekOperatorRule.DIV;} |
    MOD {$kekOperatorRule = KekOperatorRule.MOD;} |
    MOD_SIGN {$kekOperatorRule = KekOperatorRule.MOD;};

indexOperator returns [KekExpr kekExpr]: lst+=notOperator (rules+=index lst += notOperator)* {
    $kekExpr = KekOperator.fold(
        $rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
        $lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};

index returns [KekOperatorRule kekOperatorRule]:
    INDEX {$kekOperatorRule = KekOperatorRule.INDEX;};

notOperator returns [KekExpr kekExpr]:
    (LPAR expression RPAR) {$kekExpr = $expression.kekExpr;} |
    (not expression) {$kekExpr = new KekOperator($not.kekOperatorRule, $expression.kekExpr);} |
    funcCall {$kekExpr = $funcCall.kekFuncCall;} |
    varUsage {$kekExpr = $varUsage.kekVarUsage;} |
    literal {$kekExpr = $literal.kekExpr;};

not returns [KekOperatorRule kekOperatorRule]:
    NOT {$kekOperatorRule = KekOperatorRule.NOT;} |
    NOT_SIGN {$kekOperatorRule = KekOperatorRule.NOT;} |
    NOT_SIGN_BITWISE  {$kekOperatorRule = KekOperatorRule.NOT;};

funcCall returns [KekFuncCall kekFuncCall]: funcName LPAR (args+=expression (COMMA args+=expression)*)? RPAR {
    KekFunc kekFunc = contextManager.getFunc($funcName.name);
    $kekFuncCall = new KekFuncCall(
        kekFunc,
        $args.stream().map(x -> x.kekExpr).collect(Collectors.toList())
    );
};

literal returns [KekExpr kekExpr]:
    intLiteral {$kekExpr = $intLiteral.kekLiteral;} |
    boolLiteral {$kekExpr = $boolLiteral.kekLiteral;} |
    arrayLiteral {$kekExpr = $arrayLiteral.kekExpr;};

arrayLiteral returns [KekExpr kekExpr]: LBRACKET exprs+=expression (COMMA exprs+=expression)* RBRACKET {
    List<KekExpr> args = $exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList());
    KekType expectedType = args.get(0).getType();
    if (!args.stream().map(KekExpr::getType).allMatch(expectedType::equals))
        throw new RuntimeException();

    $kekExpr = KekArrayLiteral.get(contextManager.getGlobalContext(), expectedType, args);
};

intLiteral returns [KekLiteral kekLiteral]: INT_LITERAL {
    $kekLiteral = new KekLiteral(KekPrimitiveType.INT, _localctx.getChild(0).getText());
};
boolLiteral returns [KekLiteral kekLiteral]: BOOL_LITERAL {
    $kekLiteral = new KekLiteral(KekPrimitiveType.BOOL, _localctx.getChild(0).getText());
};

LET: 'let';
COLON: ':';
SEMICOLON: ';';
INT: 'int';
BOOL: 'bool';
CHAR: 'char';
STRING: 'str';
IMPORT: 'import';
DEF: 'def';
LPAR: '(';
RPAR: ')';
COMMA: ',';
DEFER: 'defer';
LBRACE: '{';
RBRACE: '}';
BREAK: 'break';
CONTINUE: 'continue';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
ASSIGN: '=';
RETURN: 'return';
EXTERN: 'extern';
OR: 'or';
OR_SIGN_BITWISE: '|';
OR_SIGN: '||';
AND: 'and';
AND_SIGN_BITWISE: '&';
AND_SIGN: '&&';
NOT: 'not';
NOT_SIGN: '!';
NOT_SIGN_BITWISE: '~';
PLUS_SIGN: '+';
MINUS_SIGN: '-';
MUL_SIGN: '*';
DIV: 'div';
DIV_SIGN: '/';
MOD: 'mod';
MOD_SIGN: '%';
EQUALS: '==';
NOT_EQUALS: '!=';
LBRACKET: '[';
RBRACKET: ']';
INDEX: '.';


INT_LITERAL: ('-')?[0-9]+;
BOOL_LITERAL: 'true'|'false';

ID: [A-Za-z_][A-Za-z0-9_]*;

EOLN: WS* '\n' WS*;
WS:[ \n\t\r]+ -> skip;