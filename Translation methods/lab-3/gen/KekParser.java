// Generated from /Users/ppv-vldmr/Desktop/MT/lab-3/src/main/antlr4/Kek.g4 by ANTLR 4.9.2

    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KekParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LET=1, COLON=2, SEMICOLON=3, INT=4, BOOL=5, CHAR=6, STRING=7, IMPORT=8, 
		DEF=9, LPAR=10, RPAR=11, COMMA=12, DEFER=13, LBRACE=14, RBRACE=15, BREAK=16, 
		CONTINUE=17, IF=18, ELSE=19, WHILE=20, ASSIGN=21, RETURN=22, EXTERN=23, 
		OR=24, OR_SIGN_BITWISE=25, OR_SIGN=26, AND=27, AND_SIGN_BITWISE=28, AND_SIGN=29, 
		NOT=30, NOT_SIGN=31, NOT_SIGN_BITWISE=32, PLUS_SIGN=33, MINUS_SIGN=34, 
		MUL_SIGN=35, DIV=36, DIV_SIGN=37, MOD=38, MOD_SIGN=39, EQUALS=40, NOT_EQUALS=41, 
		LBRACKET=42, RBRACKET=43, INDEX=44, INT_LITERAL=45, BOOL_LITERAL=46, ID=47, 
		EOLN=48, WS=49;
	public static final int
		RULE_file = 0, RULE_importDecl = 1, RULE_moduleName = 2, RULE_varDecl = 3, 
		RULE_varTyped = 4, RULE_var = 5, RULE_typeAnnotation = 6, RULE_type = 7, 
		RULE_primitiveType = 8, RULE_arrayType = 9, RULE_funcDecl = 10, RULE_funcName = 11, 
		RULE_funcArgsDecl = 12, RULE_funcModifiers = 13, RULE_funcModifier = 14, 
		RULE_block = 15, RULE_statement = 16, RULE_flowStatement = 17, RULE_returnStatement = 18, 
		RULE_breakStatement = 19, RULE_continueStatement = 20, RULE_ifStatement = 21, 
		RULE_ifPositivePart = 22, RULE_ifNegativePart = 23, RULE_whileStatement = 24, 
		RULE_simpleStatement = 25, RULE_assignStatement = 26, RULE_varUsage = 27, 
		RULE_expression = 28, RULE_operator = 29, RULE_orOperator = 30, RULE_or = 31, 
		RULE_andOperator = 32, RULE_and = 33, RULE_equalsOperator = 34, RULE_equals = 35, 
		RULE_plusOperator = 36, RULE_plus = 37, RULE_mulOperator = 38, RULE_mul = 39, 
		RULE_indexOperator = 40, RULE_index = 41, RULE_notOperator = 42, RULE_not = 43, 
		RULE_funcCall = 44, RULE_literal = 45, RULE_arrayLiteral = 46, RULE_intLiteral = 47, 
		RULE_boolLiteral = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "importDecl", "moduleName", "varDecl", "varTyped", "var", "typeAnnotation", 
			"type", "primitiveType", "arrayType", "funcDecl", "funcName", "funcArgsDecl", 
			"funcModifiers", "funcModifier", "block", "statement", "flowStatement", 
			"returnStatement", "breakStatement", "continueStatement", "ifStatement", 
			"ifPositivePart", "ifNegativePart", "whileStatement", "simpleStatement", 
			"assignStatement", "varUsage", "expression", "operator", "orOperator", 
			"or", "andOperator", "and", "equalsOperator", "equals", "plusOperator", 
			"plus", "mulOperator", "mul", "indexOperator", "index", "notOperator", 
			"not", "funcCall", "literal", "arrayLiteral", "intLiteral", "boolLiteral"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'let'", "':'", "';'", "'int'", "'bool'", "'char'", "'str'", "'import'", 
			"'def'", "'('", "')'", "','", "'defer'", "'{'", "'}'", "'break'", "'continue'", 
			"'if'", "'else'", "'while'", "'='", "'return'", "'extern'", "'or'", "'|'", 
			"'||'", "'and'", "'&'", "'&&'", "'not'", "'!'", "'~'", "'+'", "'-'", 
			"'*'", "'div'", "'/'", "'mod'", "'%'", "'=='", "'!='", "'['", "']'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LET", "COLON", "SEMICOLON", "INT", "BOOL", "CHAR", "STRING", "IMPORT", 
			"DEF", "LPAR", "RPAR", "COMMA", "DEFER", "LBRACE", "RBRACE", "BREAK", 
			"CONTINUE", "IF", "ELSE", "WHILE", "ASSIGN", "RETURN", "EXTERN", "OR", 
			"OR_SIGN_BITWISE", "OR_SIGN", "AND", "AND_SIGN_BITWISE", "AND_SIGN", 
			"NOT", "NOT_SIGN", "NOT_SIGN_BITWISE", "PLUS_SIGN", "MINUS_SIGN", "MUL_SIGN", 
			"DIV", "DIV_SIGN", "MOD", "MOD_SIGN", "EQUALS", "NOT_EQUALS", "LBRACKET", 
			"RBRACKET", "INDEX", "INT_LITERAL", "BOOL_LITERAL", "ID", "EOLN", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Kek.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private KekContextManager contextManager;

	public KekParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public KekContextManager kekContextManager;
		public KekFile kekFile;
		public ImportDeclContext importDecl;
		public List<ImportDeclContext> importDecls = new ArrayList<ImportDeclContext>();
		public VarDeclContext varDecl;
		public List<VarDeclContext> varDecls = new ArrayList<VarDeclContext>();
		public FuncDeclContext funcDecl;
		public List<FuncDeclContext> funcDecls = new ArrayList<FuncDeclContext>();
		public TerminalNode EOF() { return getToken(KekParser.EOF, 0); }
		public List<ImportDeclContext> importDecl() {
			return getRuleContexts(ImportDeclContext.class);
		}
		public ImportDeclContext importDecl(int i) {
			return getRuleContext(ImportDeclContext.class,i);
		}
		public List<VarDeclContext> varDecl() {
			return getRuleContexts(VarDeclContext.class);
		}
		public VarDeclContext varDecl(int i) {
			return getRuleContext(VarDeclContext.class,i);
		}
		public List<FuncDeclContext> funcDecl() {
			return getRuleContexts(FuncDeclContext.class);
		}
		public FuncDeclContext funcDecl(int i) {
			return getRuleContext(FuncDeclContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FileContext(ParserRuleContext parent, int invokingState, KekContextManager kekContextManager) {
			super(parent, invokingState);
			this.kekContextManager = kekContextManager;
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file(KekContextManager kekContextManager) throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState(), kekContextManager);
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    contextManager = kekContextManager;

			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << IMPORT) | (1L << DEF))) != 0)) {
				{
				setState(102);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(99);
					((FileContext)_localctx).importDecl = importDecl();
					((FileContext)_localctx).importDecls.add(((FileContext)_localctx).importDecl);
					}
					break;
				case LET:
					{
					setState(100);
					((FileContext)_localctx).varDecl = varDecl();
					((FileContext)_localctx).varDecls.add(((FileContext)_localctx).varDecl);
					}
					break;
				case DEF:
					{
					setState(101);
					((FileContext)_localctx).funcDecl = funcDecl();
					((FileContext)_localctx).funcDecls.add(((FileContext)_localctx).funcDecl);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107);
			match(EOF);

			    ((FileContext)_localctx).kekFile =  new KekFile(contextManager);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(KekParser.IMPORT, 0); }
		public ModuleNameContext moduleName() {
			return getRuleContext(ModuleNameContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public TerminalNode EXTERN() { return getToken(KekParser.EXTERN, 0); }
		public ImportDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterImportDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitImportDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitImportDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportDeclContext importDecl() throws RecognitionException {
		ImportDeclContext _localctx = new ImportDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(IMPORT);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTERN) {
				{
				setState(111);
				match(EXTERN);
				}
			}

			setState(114);
			moduleName();
			setState(115);
			match(EOLN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public ModuleNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterModuleName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitModuleName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitModuleName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleNameContext moduleName() throws RecognitionException {
		ModuleNameContext _localctx = new ModuleNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_moduleName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclContext extends ParserRuleContext {
		public VarTypedContext varTyped;
		public TerminalNode LET() { return getToken(KekParser.LET, 0); }
		public VarTypedContext varTyped() {
			return getRuleContext(VarTypedContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(LET);
			setState(120);
			((VarDeclContext)_localctx).varTyped = varTyped();
			setState(121);
			match(EOLN);

			    contextManager.addVar(((VarDeclContext)_localctx).varTyped.kekVar);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypedContext extends ParserRuleContext {
		public KekVar kekVar;
		public TypeAnnotationContext typeAnnotation;
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public VarTypedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varTyped; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVarTyped(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVarTyped(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVarTyped(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypedContext varTyped() throws RecognitionException {
		VarTypedContext _localctx = new VarTypedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varTyped);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			var();
			setState(125);
			((VarTypedContext)_localctx).typeAnnotation = typeAnnotation();

			    ((VarTypedContext)_localctx).kekVar =  new KekVar(((VarTypedContext)_localctx).typeAnnotation.kekType, _localctx.getChild(0).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public String name;
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(ID);
			((VarContext)_localctx).name =  _localctx.getChild(0).getText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeAnnotationContext extends ParserRuleContext {
		public KekType kekType;
		public TypeContext type;
		public TerminalNode COLON() { return getToken(KekParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeAnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAnnotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterTypeAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitTypeAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitTypeAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAnnotationContext typeAnnotation() throws RecognitionException {
		TypeAnnotationContext _localctx = new TypeAnnotationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typeAnnotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(COLON);
			setState(132);
			((TypeAnnotationContext)_localctx).type = type();
			((TypeAnnotationContext)_localctx).kekType =  ((TypeAnnotationContext)_localctx).type.kekType;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public KekType kekType;
		public PrimitiveTypeContext primitiveType;
		public ArrayTypeContext arrayType;
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_type);
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				((TypeContext)_localctx).primitiveType = primitiveType();
				((TypeContext)_localctx).kekType =  ((TypeContext)_localctx).primitiveType.kekType;
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				((TypeContext)_localctx).arrayType = arrayType();
				((TypeContext)_localctx).kekType =  ((TypeContext)_localctx).arrayType.kekType;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public KekType kekType;
		public TerminalNode INT() { return getToken(KekParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(KekParser.BOOL, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_primitiveType);
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				match(INT);
				((PrimitiveTypeContext)_localctx).kekType =  KekPrimitiveType.INT;
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				match(BOOL);
				((PrimitiveTypeContext)_localctx).kekType =  KekPrimitiveType.BOOL;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayTypeContext extends ParserRuleContext {
		public KekType kekType;
		public TypeContext type;
		public TerminalNode LBRACKET() { return getToken(KekParser.LBRACKET, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(KekParser.RBRACKET, 0); }
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(LBRACKET);
			setState(150);
			((ArrayTypeContext)_localctx).type = type();
			setState(151);
			match(RBRACKET);
			((ArrayTypeContext)_localctx).kekType =  new KekArrayType(((ArrayTypeContext)_localctx).type.kekType);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDeclContext extends ParserRuleContext {
		public FuncNameContext funcName;
		public FuncArgsDeclContext funcArgsDecl;
		public TypeAnnotationContext typeAnnotation;
		public FuncModifiersContext funcModifiers;
		public List<FuncModifiersContext> fm = new ArrayList<FuncModifiersContext>();
		public BlockContext block;
		public List<BlockContext> blocks = new ArrayList<BlockContext>();
		public TerminalNode DEF() { return getToken(KekParser.DEF, 0); }
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public FuncArgsDeclContext funcArgsDecl() {
			return getRuleContext(FuncArgsDeclContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public TypeAnnotationContext typeAnnotation() {
			return getRuleContext(TypeAnnotationContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncModifiersContext funcModifiers() {
			return getRuleContext(FuncModifiersContext.class,0);
		}
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(DEF);
			setState(155);
			((FuncDeclContext)_localctx).funcName = funcName();
			setState(156);
			match(LPAR);
			setState(157);
			((FuncDeclContext)_localctx).funcArgsDecl = funcArgsDecl();
			setState(158);
			match(RPAR);
			setState(159);
			((FuncDeclContext)_localctx).typeAnnotation = typeAnnotation();
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFER || _la==EXTERN) {
				{
				setState(160);
				((FuncDeclContext)_localctx).funcModifiers = funcModifiers();
				((FuncDeclContext)_localctx).fm.add(((FuncDeclContext)_localctx).funcModifiers);
				}
			}

			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(163);
				((FuncDeclContext)_localctx).block = block(((FuncDeclContext)_localctx).funcArgsDecl.kekVars);
				((FuncDeclContext)_localctx).blocks.add(((FuncDeclContext)_localctx).block);
				}
				break;
			case EOLN:
				{
				setState(164);
				match(EOLN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			    KekFunc kekFunc = new KekFunc(
			        ((FuncDeclContext)_localctx).funcName.name,
			        ((FuncDeclContext)_localctx).typeAnnotation.kekType,
			        ((FuncDeclContext)_localctx).funcArgsDecl.kekVars
			    );
			    if (((FuncDeclContext)_localctx).fm.isEmpty() || !((FuncDeclContext)_localctx).fm.get(0).kekModifiers.contains(KekModifier.DEFER)) {
			        if (((FuncDeclContext)_localctx).fm.isEmpty() || !((FuncDeclContext)_localctx).fm.get(0).kekModifiers.contains(KekModifier.EXTERN)) {
			            contextManager.addFunc(kekFunc);
			            if (((FuncDeclContext)_localctx).blocks != null && ((FuncDeclContext)_localctx).blocks.size() == 1)
			                contextManager.addFuncContent(kekFunc, ((FuncDeclContext)_localctx).blocks.get(0).kekBlock);
			            else
			                throw new RuntimeException();
			        } else {
			            contextManager.addExternFunc(kekFunc);
			        }
			    } else {
			        contextManager.addDeferFunc(kekFunc);
			    }

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncNameContext extends ParserRuleContext {
		public String name;
		public TerminalNode ID() { return getToken(KekParser.ID, 0); }
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(ID);
			((FuncNameContext)_localctx).name =  _localctx.getChild(0).getText();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncArgsDeclContext extends ParserRuleContext {
		public List<KekVar> kekVars;
		public VarTypedContext varTyped;
		public List<VarTypedContext> vars = new ArrayList<VarTypedContext>();
		public List<VarTypedContext> varTyped() {
			return getRuleContexts(VarTypedContext.class);
		}
		public VarTypedContext varTyped(int i) {
			return getRuleContext(VarTypedContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public FuncArgsDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcArgsDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncArgsDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncArgsDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncArgsDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncArgsDeclContext funcArgsDecl() throws RecognitionException {
		FuncArgsDeclContext _localctx = new FuncArgsDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_funcArgsDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(172);
				((FuncArgsDeclContext)_localctx).varTyped = varTyped();
				((FuncArgsDeclContext)_localctx).vars.add(((FuncArgsDeclContext)_localctx).varTyped);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(173);
					match(COMMA);
					setState(174);
					((FuncArgsDeclContext)_localctx).varTyped = varTyped();
					((FuncArgsDeclContext)_localctx).vars.add(((FuncArgsDeclContext)_localctx).varTyped);
					}
					}
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}


			    ((FuncArgsDeclContext)_localctx).kekVars =  ((FuncArgsDeclContext)_localctx).vars.stream().map(x -> x.kekVar).collect(Collectors.toList());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncModifiersContext extends ParserRuleContext {
		public Set<KekModifier> kekModifiers;
		public FuncModifierContext funcModifier;
		public List<FuncModifierContext> mods = new ArrayList<FuncModifierContext>();
		public List<FuncModifierContext> funcModifier() {
			return getRuleContexts(FuncModifierContext.class);
		}
		public FuncModifierContext funcModifier(int i) {
			return getRuleContext(FuncModifierContext.class,i);
		}
		public FuncModifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcModifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncModifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncModifiers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncModifiers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncModifiersContext funcModifiers() throws RecognitionException {
		FuncModifiersContext _localctx = new FuncModifiersContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_funcModifiers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(184);
				((FuncModifiersContext)_localctx).funcModifier = funcModifier();
				((FuncModifiersContext)_localctx).mods.add(((FuncModifiersContext)_localctx).funcModifier);
				}
				}
				setState(187); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DEFER || _la==EXTERN );

			    ((FuncModifiersContext)_localctx).kekModifiers =  ((FuncModifiersContext)_localctx).mods.stream().map(x -> x.kekModifier).collect(Collectors.toSet());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncModifierContext extends ParserRuleContext {
		public KekModifier kekModifier;
		public TerminalNode DEFER() { return getToken(KekParser.DEFER, 0); }
		public TerminalNode EXTERN() { return getToken(KekParser.EXTERN, 0); }
		public FuncModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncModifierContext funcModifier() throws RecognitionException {
		FuncModifierContext _localctx = new FuncModifierContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_funcModifier);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DEFER:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				match(DEFER);
				((FuncModifierContext)_localctx).kekModifier =  KekModifier.DEFER;
				}
				break;
			case EXTERN:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				match(EXTERN);
				((FuncModifierContext)_localctx).kekModifier =  KekModifier.EXTERN;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<KekVar> additionalVars;
		public KekBlock kekBlock;
		public StatementContext statement;
		public List<StatementContext> statements = new ArrayList<StatementContext>();
		public TerminalNode LBRACE() { return getToken(KekParser.LBRACE, 0); }
		public List<TerminalNode> EOLN() { return getTokens(KekParser.EOLN); }
		public TerminalNode EOLN(int i) {
			return getToken(KekParser.EOLN, i);
		}
		public TerminalNode RBRACE() { return getToken(KekParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BlockContext(ParserRuleContext parent, int invokingState, List<KekVar> additionalVars) {
			super(parent, invokingState);
			this.additionalVars = additionalVars;
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block(List<KekVar> additionalVars) throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState(), additionalVars);
		enterRule(_localctx, 30, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    if (_localctx.additionalVars != null) {
			        KekContext context = contextManager.getContext();
			        _localctx.additionalVars.forEach(context::addAdditionalVar);
			    }

			setState(198);
			match(LBRACE);
			setState(199);
			match(EOLN);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << LPAR) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << WHILE) | (1L << RETURN) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				{
				setState(200);
				((BlockContext)_localctx).statement = statement();
				((BlockContext)_localctx).statements.add(((BlockContext)_localctx).statement);
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
			match(RBRACE);
			setState(207);
			match(EOLN);

			    ((BlockContext)_localctx).kekBlock =  new KekBlock(
			        ((BlockContext)_localctx).statements.stream().map(x -> x.kekStatement).collect(Collectors.toList()),
			        contextManager.getContext()
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public SimpleStatementContext simpleStatement;
		public FlowStatementContext flowStatement;
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public FlowStatementContext flowStatement() {
			return getRuleContext(FlowStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statement);
		try {
			setState(216);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
			case LPAR:
			case NOT:
			case NOT_SIGN:
			case NOT_SIGN_BITWISE:
			case LBRACKET:
			case INT_LITERAL:
			case BOOL_LITERAL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				((StatementContext)_localctx).simpleStatement = simpleStatement();
				((StatementContext)_localctx).kekStatement =  ((StatementContext)_localctx).simpleStatement.kekStatement;
				}
				break;
			case BREAK:
			case CONTINUE:
			case IF:
			case WHILE:
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				((StatementContext)_localctx).flowStatement = flowStatement();
				((StatementContext)_localctx).kekStatement =  ((StatementContext)_localctx).flowStatement.kekStatement;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlowStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public IfStatementContext ifStatement;
		public WhileStatementContext whileStatement;
		public ReturnStatementContext returnStatement;
		public BreakStatementContext breakStatement;
		public ContinueStatementContext continueStatement;
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public FlowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flowStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFlowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFlowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFlowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FlowStatementContext flowStatement() throws RecognitionException {
		FlowStatementContext _localctx = new FlowStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_flowStatement);
		try {
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				((FlowStatementContext)_localctx).ifStatement = ifStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).ifStatement.kekIf;
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				((FlowStatementContext)_localctx).whileStatement = whileStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).whileStatement.kekWhile;
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
				((FlowStatementContext)_localctx).returnStatement = returnStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).returnStatement.kekReturn;
				}
				break;
			case BREAK:
				enterOuterAlt(_localctx, 4);
				{
				setState(227);
				((FlowStatementContext)_localctx).breakStatement = breakStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).breakStatement.kekStatement;
				}
				break;
			case CONTINUE:
				enterOuterAlt(_localctx, 5);
				{
				setState(230);
				((FlowStatementContext)_localctx).continueStatement = continueStatement();
				((FlowStatementContext)_localctx).kekStatement =  ((FlowStatementContext)_localctx).continueStatement.kekStatement;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public KekReturn kekReturn;
		public ExpressionContext expression;
		public TerminalNode RETURN() { return getToken(KekParser.RETURN, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(RETURN);
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(236);
				((ReturnStatementContext)_localctx).expression = expression();
				}
			}

			setState(239);
			match(EOLN);
			((ReturnStatementContext)_localctx).kekReturn =  new KekReturn(((ReturnStatementContext)_localctx).expression.kekExpr);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public TerminalNode BREAK() { return getToken(KekParser.BREAK, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(BREAK);
			setState(243);
			match(EOLN);
			((BreakStatementContext)_localctx).kekStatement =  new KekBreak();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public TerminalNode CONTINUE() { return getToken(KekParser.CONTINUE, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitContinueStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitContinueStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_continueStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(CONTINUE);
			setState(247);
			match(EOLN);
			((ContinueStatementContext)_localctx).kekStatement =  new KekContinue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public KekIf kekIf;
		public IfPositivePartContext ifPositivePart;
		public IfNegativePartContext ifNegativePart;
		public List<IfNegativePartContext> ifNegativeParts = new ArrayList<IfNegativePartContext>();
		public IfPositivePartContext ifPositivePart() {
			return getRuleContext(IfPositivePartContext.class,0);
		}
		public IfNegativePartContext ifNegativePart() {
			return getRuleContext(IfNegativePartContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			((IfStatementContext)_localctx).ifPositivePart = ifPositivePart();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(251);
				((IfStatementContext)_localctx).ifNegativePart = ifNegativePart();
				((IfStatementContext)_localctx).ifNegativeParts.add(((IfStatementContext)_localctx).ifNegativePart);
				}
			}


			    ((IfStatementContext)_localctx).kekIf =  new KekIf(
			        ((IfStatementContext)_localctx).ifPositivePart.kekExpr,
			        ((IfStatementContext)_localctx).ifPositivePart.kekBlock,
			        (((IfStatementContext)_localctx).ifNegativeParts != null && ((IfStatementContext)_localctx).ifNegativeParts.size() == 1 ? ((IfStatementContext)_localctx).ifNegativeParts.get(0).kekBlock : null)
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfPositivePartContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public KekBlock kekBlock;
		public ExpressionContext expression;
		public BlockContext block;
		public TerminalNode IF() { return getToken(KekParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfPositivePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifPositivePart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIfPositivePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIfPositivePart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIfPositivePart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfPositivePartContext ifPositivePart() throws RecognitionException {
		IfPositivePartContext _localctx = new IfPositivePartContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ifPositivePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(IF);
			setState(257);
			((IfPositivePartContext)_localctx).expression = expression();
			setState(258);
			((IfPositivePartContext)_localctx).block = block(null);

			    ((IfPositivePartContext)_localctx).kekExpr =  ((IfPositivePartContext)_localctx).expression.kekExpr;
			    ((IfPositivePartContext)_localctx).kekBlock =  ((IfPositivePartContext)_localctx).block.kekBlock;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfNegativePartContext extends ParserRuleContext {
		public KekBlock kekBlock;
		public BlockContext block;
		public TerminalNode ELSE() { return getToken(KekParser.ELSE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfNegativePartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifNegativePart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIfNegativePart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIfNegativePart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIfNegativePart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfNegativePartContext ifNegativePart() throws RecognitionException {
		IfNegativePartContext _localctx = new IfNegativePartContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifNegativePart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(ELSE);
			setState(262);
			((IfNegativePartContext)_localctx).block = block(null);
			((IfNegativePartContext)_localctx).kekBlock =  ((IfNegativePartContext)_localctx).block.kekBlock;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public KekWhile kekWhile;
		public ExpressionContext expression;
		public BlockContext block;
		public TerminalNode WHILE() { return getToken(KekParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(WHILE);
			setState(266);
			((WhileStatementContext)_localctx).expression = expression();
			setState(267);
			((WhileStatementContext)_localctx).block = block(null);
			((WhileStatementContext)_localctx).kekWhile =  new KekWhile(((WhileStatementContext)_localctx).expression.kekExpr, ((WhileStatementContext)_localctx).block.kekBlock);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleStatementContext extends ParserRuleContext {
		public KekStatement kekStatement;
		public AssignStatementContext assignStatement;
		public ExpressionContext expression;
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public SimpleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterSimpleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitSimpleStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitSimpleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleStatementContext simpleStatement() throws RecognitionException {
		SimpleStatementContext _localctx = new SimpleStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_simpleStatement);
		try {
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				varDecl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				((SimpleStatementContext)_localctx).assignStatement = assignStatement();
				((SimpleStatementContext)_localctx).kekStatement =  ((SimpleStatementContext)_localctx).assignStatement.kekAssign;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(274);
				((SimpleStatementContext)_localctx).expression = expression();
				setState(275);
				match(EOLN);
				}
				((SimpleStatementContext)_localctx).kekStatement =  KekStatement.of(((SimpleStatementContext)_localctx).expression.kekExpr);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStatementContext extends ParserRuleContext {
		public KekAssign kekAssign;
		public VarUsageContext varUsage;
		public List<VarUsageContext> varUsages = new ArrayList<VarUsageContext>();
		public ExpressionContext expression;
		public List<ExpressionContext> exprs = new ArrayList<ExpressionContext>();
		public TerminalNode ASSIGN() { return getToken(KekParser.ASSIGN, 0); }
		public TerminalNode EOLN() { return getToken(KekParser.EOLN, 0); }
		public List<VarUsageContext> varUsage() {
			return getRuleContexts(VarUsageContext.class);
		}
		public VarUsageContext varUsage(int i) {
			return getRuleContext(VarUsageContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAssignStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_assignStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			((AssignStatementContext)_localctx).varUsage = varUsage();
			((AssignStatementContext)_localctx).varUsages.add(((AssignStatementContext)_localctx).varUsage);
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(282);
				match(COMMA);
				setState(283);
				((AssignStatementContext)_localctx).varUsage = varUsage();
				((AssignStatementContext)_localctx).varUsages.add(((AssignStatementContext)_localctx).varUsage);
				}
				}
				setState(288);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			match(ASSIGN);
			setState(290);
			((AssignStatementContext)_localctx).expression = expression();
			((AssignStatementContext)_localctx).exprs.add(((AssignStatementContext)_localctx).expression);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(291);
				match(COMMA);
				setState(292);
				((AssignStatementContext)_localctx).expression = expression();
				((AssignStatementContext)_localctx).exprs.add(((AssignStatementContext)_localctx).expression);
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(298);
			match(EOLN);

			    if (((AssignStatementContext)_localctx).varUsages.size() != ((AssignStatementContext)_localctx).exprs.size()) {
			        throw new RuntimeException("Expected the same count of vars and expressions");
			    }

			    ((AssignStatementContext)_localctx).kekAssign =  new KekAssign(
			        ((AssignStatementContext)_localctx).varUsages.stream().map(x -> x.kekVarUsage).collect(Collectors.toList()),
			        ((AssignStatementContext)_localctx).exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList()),
			        false
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarUsageContext extends ParserRuleContext {
		public KekVarUsage kekVarUsage;
		public VarContext var;
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public VarUsageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varUsage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterVarUsage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitVarUsage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitVarUsage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarUsageContext varUsage() throws RecognitionException {
		VarUsageContext _localctx = new VarUsageContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_varUsage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			((VarUsageContext)_localctx).var = var();

			    KekVar kekVar = contextManager.getVar(((VarUsageContext)_localctx).var.name);
			    ((VarUsageContext)_localctx).kekVarUsage =  new KekVarUsage(kekVar);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public OperatorContext operator;
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			((ExpressionContext)_localctx).operator = operator();
			((ExpressionContext)_localctx).kekExpr =  ((ExpressionContext)_localctx).operator.kekExpr;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public OrOperatorContext orOperator;
		public OrOperatorContext orOperator() {
			return getRuleContext(OrOperatorContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			((OperatorContext)_localctx).orOperator = orOperator();
			((OperatorContext)_localctx).kekExpr =  ((OperatorContext)_localctx).orOperator.kekExpr;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public AndOperatorContext andOperator;
		public List<AndOperatorContext> lst = new ArrayList<AndOperatorContext>();
		public OrContext or;
		public List<OrContext> rules = new ArrayList<OrContext>();
		public List<AndOperatorContext> andOperator() {
			return getRuleContexts(AndOperatorContext.class);
		}
		public AndOperatorContext andOperator(int i) {
			return getRuleContext(AndOperatorContext.class,i);
		}
		public List<OrContext> or() {
			return getRuleContexts(OrContext.class);
		}
		public OrContext or(int i) {
			return getRuleContext(OrContext.class,i);
		}
		public OrOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOrOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOrOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOrOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrOperatorContext orOperator() throws RecognitionException {
		OrOperatorContext _localctx = new OrOperatorContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_orOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			((OrOperatorContext)_localctx).andOperator = andOperator();
			((OrOperatorContext)_localctx).lst.add(((OrOperatorContext)_localctx).andOperator);
			setState(316);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(311);
					((OrOperatorContext)_localctx).or = or();
					((OrOperatorContext)_localctx).rules.add(((OrOperatorContext)_localctx).or);
					setState(312);
					((OrOperatorContext)_localctx).andOperator = andOperator();
					((OrOperatorContext)_localctx).lst.add(((OrOperatorContext)_localctx).andOperator);
					}
					} 
				}
				setState(318);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}

			    ((OrOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((OrOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((OrOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode OR() { return getToken(KekParser.OR, 0); }
		public TerminalNode OR_SIGN() { return getToken(KekParser.OR_SIGN, 0); }
		public TerminalNode OR_SIGN_BITWISE() { return getToken(KekParser.OR_SIGN_BITWISE, 0); }
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_or);
		try {
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OR:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				match(OR);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			case OR_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(OR_SIGN);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			case OR_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				match(OR_SIGN_BITWISE);
				((OrContext)_localctx).kekOperatorRule =  KekOperatorRule.OR;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public EqualsOperatorContext equalsOperator;
		public List<EqualsOperatorContext> lst = new ArrayList<EqualsOperatorContext>();
		public AndContext and;
		public List<AndContext> rules = new ArrayList<AndContext>();
		public List<EqualsOperatorContext> equalsOperator() {
			return getRuleContexts(EqualsOperatorContext.class);
		}
		public EqualsOperatorContext equalsOperator(int i) {
			return getRuleContext(EqualsOperatorContext.class,i);
		}
		public List<AndContext> and() {
			return getRuleContexts(AndContext.class);
		}
		public AndContext and(int i) {
			return getRuleContext(AndContext.class,i);
		}
		public AndOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAndOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAndOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAndOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndOperatorContext andOperator() throws RecognitionException {
		AndOperatorContext _localctx = new AndOperatorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_andOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			((AndOperatorContext)_localctx).equalsOperator = equalsOperator();
			((AndOperatorContext)_localctx).lst.add(((AndOperatorContext)_localctx).equalsOperator);
			setState(335);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(330);
					((AndOperatorContext)_localctx).and = and();
					((AndOperatorContext)_localctx).rules.add(((AndOperatorContext)_localctx).and);
					setState(331);
					((AndOperatorContext)_localctx).equalsOperator = equalsOperator();
					((AndOperatorContext)_localctx).lst.add(((AndOperatorContext)_localctx).equalsOperator);
					}
					} 
				}
				setState(337);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}

			    ((AndOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((AndOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((AndOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode AND() { return getToken(KekParser.AND, 0); }
		public TerminalNode AND_SIGN() { return getToken(KekParser.AND_SIGN, 0); }
		public TerminalNode AND_SIGN_BITWISE() { return getToken(KekParser.AND_SIGN_BITWISE, 0); }
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_and);
		try {
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AND:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				match(AND);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			case AND_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(342);
				match(AND_SIGN);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			case AND_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				match(AND_SIGN_BITWISE);
				((AndContext)_localctx).kekOperatorRule =  KekOperatorRule.AND;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualsOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public PlusOperatorContext plusOperator;
		public List<PlusOperatorContext> lst = new ArrayList<PlusOperatorContext>();
		public EqualsContext equals;
		public List<EqualsContext> rules = new ArrayList<EqualsContext>();
		public List<PlusOperatorContext> plusOperator() {
			return getRuleContexts(PlusOperatorContext.class);
		}
		public PlusOperatorContext plusOperator(int i) {
			return getRuleContext(PlusOperatorContext.class,i);
		}
		public List<EqualsContext> equals() {
			return getRuleContexts(EqualsContext.class);
		}
		public EqualsContext equals(int i) {
			return getRuleContext(EqualsContext.class,i);
		}
		public EqualsOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalsOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterEqualsOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitEqualsOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitEqualsOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualsOperatorContext equalsOperator() throws RecognitionException {
		EqualsOperatorContext _localctx = new EqualsOperatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_equalsOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(348);
			((EqualsOperatorContext)_localctx).plusOperator = plusOperator();
			((EqualsOperatorContext)_localctx).lst.add(((EqualsOperatorContext)_localctx).plusOperator);
			setState(354);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(349);
					((EqualsOperatorContext)_localctx).equals = equals();
					((EqualsOperatorContext)_localctx).rules.add(((EqualsOperatorContext)_localctx).equals);
					setState(350);
					((EqualsOperatorContext)_localctx).plusOperator = plusOperator();
					((EqualsOperatorContext)_localctx).lst.add(((EqualsOperatorContext)_localctx).plusOperator);
					}
					} 
				}
				setState(356);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}

			    ((EqualsOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((EqualsOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((EqualsOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualsContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode EQUALS() { return getToken(KekParser.EQUALS, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(KekParser.NOT_EQUALS, 0); }
		public EqualsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterEquals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitEquals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitEquals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualsContext equals() throws RecognitionException {
		EqualsContext _localctx = new EqualsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_equals);
		try {
			setState(363);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EQUALS:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				match(EQUALS);
				((EqualsContext)_localctx).kekOperatorRule =  KekOperatorRule.EQUALS;
				}
				break;
			case NOT_EQUALS:
				enterOuterAlt(_localctx, 2);
				{
				setState(361);
				match(NOT_EQUALS);
				((EqualsContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT_EQUALS;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public MulOperatorContext mulOperator;
		public List<MulOperatorContext> lst = new ArrayList<MulOperatorContext>();
		public PlusContext plus;
		public List<PlusContext> rules = new ArrayList<PlusContext>();
		public List<MulOperatorContext> mulOperator() {
			return getRuleContexts(MulOperatorContext.class);
		}
		public MulOperatorContext mulOperator(int i) {
			return getRuleContext(MulOperatorContext.class,i);
		}
		public List<PlusContext> plus() {
			return getRuleContexts(PlusContext.class);
		}
		public PlusContext plus(int i) {
			return getRuleContext(PlusContext.class,i);
		}
		public PlusOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plusOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPlusOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPlusOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPlusOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusOperatorContext plusOperator() throws RecognitionException {
		PlusOperatorContext _localctx = new PlusOperatorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_plusOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			((PlusOperatorContext)_localctx).mulOperator = mulOperator();
			((PlusOperatorContext)_localctx).lst.add(((PlusOperatorContext)_localctx).mulOperator);
			setState(371);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(366);
					((PlusOperatorContext)_localctx).plus = plus();
					((PlusOperatorContext)_localctx).rules.add(((PlusOperatorContext)_localctx).plus);
					setState(367);
					((PlusOperatorContext)_localctx).mulOperator = mulOperator();
					((PlusOperatorContext)_localctx).lst.add(((PlusOperatorContext)_localctx).mulOperator);
					}
					} 
				}
				setState(373);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}

			    ((PlusOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((PlusOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((PlusOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlusContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode PLUS_SIGN() { return getToken(KekParser.PLUS_SIGN, 0); }
		public TerminalNode MINUS_SIGN() { return getToken(KekParser.MINUS_SIGN, 0); }
		public PlusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_plus; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitPlus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlusContext plus() throws RecognitionException {
		PlusContext _localctx = new PlusContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_plus);
		try {
			setState(380);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(PLUS_SIGN);
				((PlusContext)_localctx).kekOperatorRule =  KekOperatorRule.PLUS;
				}
				break;
			case MINUS_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				match(MINUS_SIGN);
				((PlusContext)_localctx).kekOperatorRule =  KekOperatorRule.MINUS;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public IndexOperatorContext indexOperator;
		public List<IndexOperatorContext> lst = new ArrayList<IndexOperatorContext>();
		public MulContext mul;
		public List<MulContext> rules = new ArrayList<MulContext>();
		public List<IndexOperatorContext> indexOperator() {
			return getRuleContexts(IndexOperatorContext.class);
		}
		public IndexOperatorContext indexOperator(int i) {
			return getRuleContext(IndexOperatorContext.class,i);
		}
		public List<MulContext> mul() {
			return getRuleContexts(MulContext.class);
		}
		public MulContext mul(int i) {
			return getRuleContext(MulContext.class,i);
		}
		public MulOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterMulOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitMulOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitMulOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulOperatorContext mulOperator() throws RecognitionException {
		MulOperatorContext _localctx = new MulOperatorContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_mulOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			((MulOperatorContext)_localctx).indexOperator = indexOperator();
			((MulOperatorContext)_localctx).lst.add(((MulOperatorContext)_localctx).indexOperator);
			setState(388);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(383);
					((MulOperatorContext)_localctx).mul = mul();
					((MulOperatorContext)_localctx).rules.add(((MulOperatorContext)_localctx).mul);
					setState(384);
					((MulOperatorContext)_localctx).indexOperator = indexOperator();
					((MulOperatorContext)_localctx).lst.add(((MulOperatorContext)_localctx).indexOperator);
					}
					} 
				}
				setState(390);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}

			    ((MulOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((MulOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((MulOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode MUL_SIGN() { return getToken(KekParser.MUL_SIGN, 0); }
		public TerminalNode DIV() { return getToken(KekParser.DIV, 0); }
		public TerminalNode DIV_SIGN() { return getToken(KekParser.DIV_SIGN, 0); }
		public TerminalNode MOD() { return getToken(KekParser.MOD, 0); }
		public TerminalNode MOD_SIGN() { return getToken(KekParser.MOD_SIGN, 0); }
		public MulContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterMul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitMul(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitMul(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulContext mul() throws RecognitionException {
		MulContext _localctx = new MulContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_mul);
		try {
			setState(403);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MUL_SIGN:
				enterOuterAlt(_localctx, 1);
				{
				setState(393);
				match(MUL_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MUL;
				}
				break;
			case DIV:
				enterOuterAlt(_localctx, 2);
				{
				setState(395);
				match(DIV);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.DIV;
				}
				break;
			case DIV_SIGN:
				enterOuterAlt(_localctx, 3);
				{
				setState(397);
				match(DIV_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.DIV;
				}
				break;
			case MOD:
				enterOuterAlt(_localctx, 4);
				{
				setState(399);
				match(MOD);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MOD;
				}
				break;
			case MOD_SIGN:
				enterOuterAlt(_localctx, 5);
				{
				setState(401);
				match(MOD_SIGN);
				((MulContext)_localctx).kekOperatorRule =  KekOperatorRule.MOD;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public NotOperatorContext notOperator;
		public List<NotOperatorContext> lst = new ArrayList<NotOperatorContext>();
		public IndexContext index;
		public List<IndexContext> rules = new ArrayList<IndexContext>();
		public List<NotOperatorContext> notOperator() {
			return getRuleContexts(NotOperatorContext.class);
		}
		public NotOperatorContext notOperator(int i) {
			return getRuleContext(NotOperatorContext.class,i);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public IndexOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIndexOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIndexOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIndexOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexOperatorContext indexOperator() throws RecognitionException {
		IndexOperatorContext _localctx = new IndexOperatorContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_indexOperator);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			((IndexOperatorContext)_localctx).notOperator = notOperator();
			((IndexOperatorContext)_localctx).lst.add(((IndexOperatorContext)_localctx).notOperator);
			setState(411);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(406);
					((IndexOperatorContext)_localctx).index = index();
					((IndexOperatorContext)_localctx).rules.add(((IndexOperatorContext)_localctx).index);
					setState(407);
					((IndexOperatorContext)_localctx).notOperator = notOperator();
					((IndexOperatorContext)_localctx).lst.add(((IndexOperatorContext)_localctx).notOperator);
					}
					} 
				}
				setState(413);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}

			    ((IndexOperatorContext)_localctx).kekExpr =  KekOperator.fold(
			        ((IndexOperatorContext)_localctx).rules.stream().map(x -> x.kekOperatorRule).collect(Collectors.toList()),
			        ((IndexOperatorContext)_localctx).lst.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode INDEX() { return getToken(KekParser.INDEX, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			match(INDEX);
			((IndexContext)_localctx).kekOperatorRule =  KekOperatorRule.INDEX;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotOperatorContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public ExpressionContext expression;
		public NotContext not;
		public FuncCallContext funcCall;
		public VarUsageContext varUsage;
		public LiteralContext literal;
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public VarUsageContext varUsage() {
			return getRuleContext(VarUsageContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public NotOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterNotOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitNotOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitNotOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotOperatorContext notOperator() throws RecognitionException {
		NotOperatorContext _localctx = new NotOperatorContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_notOperator);
		try {
			setState(439);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(419);
				match(LPAR);
				setState(420);
				((NotOperatorContext)_localctx).expression = expression();
				setState(421);
				match(RPAR);
				}
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).expression.kekExpr;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(425);
				((NotOperatorContext)_localctx).not = not();
				setState(426);
				((NotOperatorContext)_localctx).expression = expression();
				}
				((NotOperatorContext)_localctx).kekExpr =  new KekOperator(((NotOperatorContext)_localctx).not.kekOperatorRule, ((NotOperatorContext)_localctx).expression.kekExpr);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(430);
				((NotOperatorContext)_localctx).funcCall = funcCall();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).funcCall.kekFuncCall;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(433);
				((NotOperatorContext)_localctx).varUsage = varUsage();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).varUsage.kekVarUsage;
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(436);
				((NotOperatorContext)_localctx).literal = literal();
				((NotOperatorContext)_localctx).kekExpr =  ((NotOperatorContext)_localctx).literal.kekExpr;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NotContext extends ParserRuleContext {
		public KekOperatorRule kekOperatorRule;
		public TerminalNode NOT() { return getToken(KekParser.NOT, 0); }
		public TerminalNode NOT_SIGN() { return getToken(KekParser.NOT_SIGN, 0); }
		public TerminalNode NOT_SIGN_BITWISE() { return getToken(KekParser.NOT_SIGN_BITWISE, 0); }
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_not);
		try {
			setState(447);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(441);
				match(NOT);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			case NOT_SIGN:
				enterOuterAlt(_localctx, 2);
				{
				setState(443);
				match(NOT_SIGN);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			case NOT_SIGN_BITWISE:
				enterOuterAlt(_localctx, 3);
				{
				setState(445);
				match(NOT_SIGN_BITWISE);
				((NotContext)_localctx).kekOperatorRule =  KekOperatorRule.NOT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncCallContext extends ParserRuleContext {
		public KekFuncCall kekFuncCall;
		public FuncNameContext funcName;
		public ExpressionContext expression;
		public List<ExpressionContext> args = new ArrayList<ExpressionContext>();
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(KekParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(KekParser.RPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			((FuncCallContext)_localctx).funcName = funcName();
			setState(450);
			match(LPAR);
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << NOT) | (1L << NOT_SIGN) | (1L << NOT_SIGN_BITWISE) | (1L << LBRACKET) | (1L << INT_LITERAL) | (1L << BOOL_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(451);
				((FuncCallContext)_localctx).expression = expression();
				((FuncCallContext)_localctx).args.add(((FuncCallContext)_localctx).expression);
				setState(456);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(452);
					match(COMMA);
					setState(453);
					((FuncCallContext)_localctx).expression = expression();
					((FuncCallContext)_localctx).args.add(((FuncCallContext)_localctx).expression);
					}
					}
					setState(458);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(461);
			match(RPAR);

			    KekFunc kekFunc = contextManager.getFunc(((FuncCallContext)_localctx).funcName.name);
			    ((FuncCallContext)_localctx).kekFuncCall =  new KekFuncCall(
			        kekFunc,
			        ((FuncCallContext)_localctx).args.stream().map(x -> x.kekExpr).collect(Collectors.toList())
			    );

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public IntLiteralContext intLiteral;
		public BoolLiteralContext boolLiteral;
		public ArrayLiteralContext arrayLiteral;
		public IntLiteralContext intLiteral() {
			return getRuleContext(IntLiteralContext.class,0);
		}
		public BoolLiteralContext boolLiteral() {
			return getRuleContext(BoolLiteralContext.class,0);
		}
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_literal);
		try {
			setState(473);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(464);
				((LiteralContext)_localctx).intLiteral = intLiteral();
				((LiteralContext)_localctx).kekExpr =  ((LiteralContext)_localctx).intLiteral.kekLiteral;
				}
				break;
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(467);
				((LiteralContext)_localctx).boolLiteral = boolLiteral();
				((LiteralContext)_localctx).kekExpr =  ((LiteralContext)_localctx).boolLiteral.kekLiteral;
				}
				break;
			case LBRACKET:
				enterOuterAlt(_localctx, 3);
				{
				setState(470);
				((LiteralContext)_localctx).arrayLiteral = arrayLiteral();
				((LiteralContext)_localctx).kekExpr =  ((LiteralContext)_localctx).arrayLiteral.kekExpr;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayLiteralContext extends ParserRuleContext {
		public KekExpr kekExpr;
		public ExpressionContext expression;
		public List<ExpressionContext> exprs = new ArrayList<ExpressionContext>();
		public TerminalNode LBRACKET() { return getToken(KekParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(KekParser.RBRACKET, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(KekParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(KekParser.COMMA, i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(LBRACKET);
			setState(476);
			((ArrayLiteralContext)_localctx).expression = expression();
			((ArrayLiteralContext)_localctx).exprs.add(((ArrayLiteralContext)_localctx).expression);
			setState(481);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(477);
				match(COMMA);
				setState(478);
				((ArrayLiteralContext)_localctx).expression = expression();
				((ArrayLiteralContext)_localctx).exprs.add(((ArrayLiteralContext)_localctx).expression);
				}
				}
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(484);
			match(RBRACKET);

			    List<KekExpr> args = ((ArrayLiteralContext)_localctx).exprs.stream().map(x -> x.kekExpr).collect(Collectors.toList());
			    KekType expectedType = args.get(0).getType();
			    if (!args.stream().map(KekExpr::getType).allMatch(expectedType::equals))
			        throw new RuntimeException();

			    ((ArrayLiteralContext)_localctx).kekExpr =  KekArrayLiteral.get(contextManager.getGlobalContext(), expectedType, args);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntLiteralContext extends ParserRuleContext {
		public KekLiteral kekLiteral;
		public TerminalNode INT_LITERAL() { return getToken(KekParser.INT_LITERAL, 0); }
		public IntLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntLiteralContext intLiteral() throws RecognitionException {
		IntLiteralContext _localctx = new IntLiteralContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_intLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			match(INT_LITERAL);

			    ((IntLiteralContext)_localctx).kekLiteral =  new KekLiteral(KekPrimitiveType.INT, _localctx.getChild(0).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolLiteralContext extends ParserRuleContext {
		public KekLiteral kekLiteral;
		public TerminalNode BOOL_LITERAL() { return getToken(KekParser.BOOL_LITERAL, 0); }
		public BoolLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KekListener ) ((KekListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof KekVisitor ) return ((KekVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolLiteralContext boolLiteral() throws RecognitionException {
		BoolLiteralContext _localctx = new BoolLiteralContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_boolLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			match(BOOL_LITERAL);

			    ((BoolLiteralContext)_localctx).kekLiteral =  new KekLiteral(KekPrimitiveType.BOOL, _localctx.getChild(0).getText());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u01f0\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\3\2\7"+
		"\2i\n\2\f\2\16\2l\13\2\3\2\3\2\3\2\3\3\3\3\5\3s\n\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u0090\n\t\3\n\3\n\3\n\3\n\5\n\u0096\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a4\n\f\3\f\3\f"+
		"\5\f\u00a8\n\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u00b2\n\16\f\16"+
		"\16\16\u00b5\13\16\5\16\u00b7\n\16\3\16\3\16\3\17\6\17\u00bc\n\17\r\17"+
		"\16\17\u00bd\3\17\3\17\3\20\3\20\3\20\3\20\5\20\u00c6\n\20\3\21\3\21\3"+
		"\21\3\21\7\21\u00cc\n\21\f\21\16\21\u00cf\13\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\5\22\u00db\n\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00ec\n\23\3\24"+
		"\3\24\5\24\u00f0\n\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\5\27\u00ff\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\5\33\u011a\n\33\3\34\3\34\3\34\7\34\u011f\n\34\f\34\16"+
		"\34\u0122\13\34\3\34\3\34\3\34\3\34\7\34\u0128\n\34\f\34\16\34\u012b\13"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 "+
		"\3 \3 \7 \u013d\n \f \16 \u0140\13 \3 \3 \3!\3!\3!\3!\3!\3!\5!\u014a\n"+
		"!\3\"\3\"\3\"\3\"\7\"\u0150\n\"\f\"\16\"\u0153\13\"\3\"\3\"\3#\3#\3#\3"+
		"#\3#\3#\5#\u015d\n#\3$\3$\3$\3$\7$\u0163\n$\f$\16$\u0166\13$\3$\3$\3%"+
		"\3%\3%\3%\5%\u016e\n%\3&\3&\3&\3&\7&\u0174\n&\f&\16&\u0177\13&\3&\3&\3"+
		"\'\3\'\3\'\3\'\5\'\u017f\n\'\3(\3(\3(\3(\7(\u0185\n(\f(\16(\u0188\13("+
		"\3(\3(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0196\n)\3*\3*\3*\3*\7*\u019c"+
		"\n*\f*\16*\u019f\13*\3*\3*\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3"+
		",\3,\3,\3,\3,\3,\3,\3,\3,\5,\u01ba\n,\3-\3-\3-\3-\3-\3-\5-\u01c2\n-\3"+
		".\3.\3.\3.\3.\7.\u01c9\n.\f.\16.\u01cc\13.\5.\u01ce\n.\3.\3.\3.\3/\3/"+
		"\3/\3/\3/\3/\3/\3/\3/\5/\u01dc\n/\3\60\3\60\3\60\3\60\7\60\u01e2\n\60"+
		"\f\60\16\60\u01e5\13\60\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62\3\62\3"+
		"\62\2\2\63\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRTVXZ\\^`b\2\2\2\u01f1\2d\3\2\2\2\4p\3\2\2\2\6w\3\2\2\2\b"+
		"y\3\2\2\2\n~\3\2\2\2\f\u0082\3\2\2\2\16\u0085\3\2\2\2\20\u008f\3\2\2\2"+
		"\22\u0095\3\2\2\2\24\u0097\3\2\2\2\26\u009c\3\2\2\2\30\u00ab\3\2\2\2\32"+
		"\u00b6\3\2\2\2\34\u00bb\3\2\2\2\36\u00c5\3\2\2\2 \u00c7\3\2\2\2\"\u00da"+
		"\3\2\2\2$\u00eb\3\2\2\2&\u00ed\3\2\2\2(\u00f4\3\2\2\2*\u00f8\3\2\2\2,"+
		"\u00fc\3\2\2\2.\u0102\3\2\2\2\60\u0107\3\2\2\2\62\u010b\3\2\2\2\64\u0119"+
		"\3\2\2\2\66\u011b\3\2\2\28\u012f\3\2\2\2:\u0132\3\2\2\2<\u0135\3\2\2\2"+
		">\u0138\3\2\2\2@\u0149\3\2\2\2B\u014b\3\2\2\2D\u015c\3\2\2\2F\u015e\3"+
		"\2\2\2H\u016d\3\2\2\2J\u016f\3\2\2\2L\u017e\3\2\2\2N\u0180\3\2\2\2P\u0195"+
		"\3\2\2\2R\u0197\3\2\2\2T\u01a2\3\2\2\2V\u01b9\3\2\2\2X\u01c1\3\2\2\2Z"+
		"\u01c3\3\2\2\2\\\u01db\3\2\2\2^\u01dd\3\2\2\2`\u01e9\3\2\2\2b\u01ec\3"+
		"\2\2\2dj\b\2\1\2ei\5\4\3\2fi\5\b\5\2gi\5\26\f\2he\3\2\2\2hf\3\2\2\2hg"+
		"\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7\2\2\3n"+
		"o\b\2\1\2o\3\3\2\2\2pr\7\n\2\2qs\7\31\2\2rq\3\2\2\2rs\3\2\2\2st\3\2\2"+
		"\2tu\5\6\4\2uv\7\62\2\2v\5\3\2\2\2wx\7\61\2\2x\7\3\2\2\2yz\7\3\2\2z{\5"+
		"\n\6\2{|\7\62\2\2|}\b\5\1\2}\t\3\2\2\2~\177\5\f\7\2\177\u0080\5\16\b\2"+
		"\u0080\u0081\b\6\1\2\u0081\13\3\2\2\2\u0082\u0083\7\61\2\2\u0083\u0084"+
		"\b\7\1\2\u0084\r\3\2\2\2\u0085\u0086\7\4\2\2\u0086\u0087\5\20\t\2\u0087"+
		"\u0088\b\b\1\2\u0088\17\3\2\2\2\u0089\u008a\5\22\n\2\u008a\u008b\b\t\1"+
		"\2\u008b\u0090\3\2\2\2\u008c\u008d\5\24\13\2\u008d\u008e\b\t\1\2\u008e"+
		"\u0090\3\2\2\2\u008f\u0089\3\2\2\2\u008f\u008c\3\2\2\2\u0090\21\3\2\2"+
		"\2\u0091\u0092\7\6\2\2\u0092\u0096\b\n\1\2\u0093\u0094\7\7\2\2\u0094\u0096"+
		"\b\n\1\2\u0095\u0091\3\2\2\2\u0095\u0093\3\2\2\2\u0096\23\3\2\2\2\u0097"+
		"\u0098\7,\2\2\u0098\u0099\5\20\t\2\u0099\u009a\7-\2\2\u009a\u009b\b\13"+
		"\1\2\u009b\25\3\2\2\2\u009c\u009d\7\13\2\2\u009d\u009e\5\30\r\2\u009e"+
		"\u009f\7\f\2\2\u009f\u00a0\5\32\16\2\u00a0\u00a1\7\r\2\2\u00a1\u00a3\5"+
		"\16\b\2\u00a2\u00a4\5\34\17\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4\3\2\2\2"+
		"\u00a4\u00a7\3\2\2\2\u00a5\u00a8\5 \21\2\u00a6\u00a8\7\62\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\f\1\2\u00aa"+
		"\27\3\2\2\2\u00ab\u00ac\7\61\2\2\u00ac\u00ad\b\r\1\2\u00ad\31\3\2\2\2"+
		"\u00ae\u00b3\5\n\6\2\u00af\u00b0\7\16\2\2\u00b0\u00b2\5\n\6\2\u00b1\u00af"+
		"\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00ae\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\b\16\1\2\u00b9\33\3\2\2\2\u00ba\u00bc"+
		"\5\36\20\2\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2"+
		"\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\b\17\1\2\u00c0\35"+
		"\3\2\2\2\u00c1\u00c2\7\17\2\2\u00c2\u00c6\b\20\1\2\u00c3\u00c4\7\31\2"+
		"\2\u00c4\u00c6\b\20\1\2\u00c5\u00c1\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\37\3\2\2\2\u00c7\u00c8\b\21\1\2\u00c8\u00c9\7\20\2\2\u00c9\u00cd\7\62"+
		"\2\2\u00ca\u00cc\5\"\22\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2"+
		"\2\2\u00d0\u00d1\7\21\2\2\u00d1\u00d2\7\62\2\2\u00d2\u00d3\b\21\1\2\u00d3"+
		"!\3\2\2\2\u00d4\u00d5\5\64\33\2\u00d5\u00d6\b\22\1\2\u00d6\u00db\3\2\2"+
		"\2\u00d7\u00d8\5$\23\2\u00d8\u00d9\b\22\1\2\u00d9\u00db\3\2\2\2\u00da"+
		"\u00d4\3\2\2\2\u00da\u00d7\3\2\2\2\u00db#\3\2\2\2\u00dc\u00dd\5,\27\2"+
		"\u00dd\u00de\b\23\1\2\u00de\u00ec\3\2\2\2\u00df\u00e0\5\62\32\2\u00e0"+
		"\u00e1\b\23\1\2\u00e1\u00ec\3\2\2\2\u00e2\u00e3\5&\24\2\u00e3\u00e4\b"+
		"\23\1\2\u00e4\u00ec\3\2\2\2\u00e5\u00e6\5(\25\2\u00e6\u00e7\b\23\1\2\u00e7"+
		"\u00ec\3\2\2\2\u00e8\u00e9\5*\26\2\u00e9\u00ea\b\23\1\2\u00ea\u00ec\3"+
		"\2\2\2\u00eb\u00dc\3\2\2\2\u00eb\u00df\3\2\2\2\u00eb\u00e2\3\2\2\2\u00eb"+
		"\u00e5\3\2\2\2\u00eb\u00e8\3\2\2\2\u00ec%\3\2\2\2\u00ed\u00ef\7\30\2\2"+
		"\u00ee\u00f0\5:\36\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1"+
		"\3\2\2\2\u00f1\u00f2\7\62\2\2\u00f2\u00f3\b\24\1\2\u00f3\'\3\2\2\2\u00f4"+
		"\u00f5\7\22\2\2\u00f5\u00f6\7\62\2\2\u00f6\u00f7\b\25\1\2\u00f7)\3\2\2"+
		"\2\u00f8\u00f9\7\23\2\2\u00f9\u00fa\7\62\2\2\u00fa\u00fb\b\26\1\2\u00fb"+
		"+\3\2\2\2\u00fc\u00fe\5.\30\2\u00fd\u00ff\5\60\31\2\u00fe\u00fd\3\2\2"+
		"\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0101\b\27\1\2\u0101"+
		"-\3\2\2\2\u0102\u0103\7\24\2\2\u0103\u0104\5:\36\2\u0104\u0105\5 \21\2"+
		"\u0105\u0106\b\30\1\2\u0106/\3\2\2\2\u0107\u0108\7\25\2\2\u0108\u0109"+
		"\5 \21\2\u0109\u010a\b\31\1\2\u010a\61\3\2\2\2\u010b\u010c\7\26\2\2\u010c"+
		"\u010d\5:\36\2\u010d\u010e\5 \21\2\u010e\u010f\b\32\1\2\u010f\63\3\2\2"+
		"\2\u0110\u011a\5\b\5\2\u0111\u0112\5\66\34\2\u0112\u0113\b\33\1\2\u0113"+
		"\u011a\3\2\2\2\u0114\u0115\5:\36\2\u0115\u0116\7\62\2\2\u0116\u0117\3"+
		"\2\2\2\u0117\u0118\b\33\1\2\u0118\u011a\3\2\2\2\u0119\u0110\3\2\2\2\u0119"+
		"\u0111\3\2\2\2\u0119\u0114\3\2\2\2\u011a\65\3\2\2\2\u011b\u0120\58\35"+
		"\2\u011c\u011d\7\16\2\2\u011d\u011f\58\35\2\u011e\u011c\3\2\2\2\u011f"+
		"\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2"+
		"\2\2\u0122\u0120\3\2\2\2\u0123\u0124\7\27\2\2\u0124\u0129\5:\36\2\u0125"+
		"\u0126\7\16\2\2\u0126\u0128\5:\36\2\u0127\u0125\3\2\2\2\u0128\u012b\3"+
		"\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012c\u012d\7\62\2\2\u012d\u012e\b\34\1\2\u012e\67\3\2"+
		"\2\2\u012f\u0130\5\f\7\2\u0130\u0131\b\35\1\2\u01319\3\2\2\2\u0132\u0133"+
		"\5<\37\2\u0133\u0134\b\36\1\2\u0134;\3\2\2\2\u0135\u0136\5> \2\u0136\u0137"+
		"\b\37\1\2\u0137=\3\2\2\2\u0138\u013e\5B\"\2\u0139\u013a\5@!\2\u013a\u013b"+
		"\5B\"\2\u013b\u013d\3\2\2\2\u013c\u0139\3\2\2\2\u013d\u0140\3\2\2\2\u013e"+
		"\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2"+
		"\2\2\u0141\u0142\b \1\2\u0142?\3\2\2\2\u0143\u0144\7\32\2\2\u0144\u014a"+
		"\b!\1\2\u0145\u0146\7\34\2\2\u0146\u014a\b!\1\2\u0147\u0148\7\33\2\2\u0148"+
		"\u014a\b!\1\2\u0149\u0143\3\2\2\2\u0149\u0145\3\2\2\2\u0149\u0147\3\2"+
		"\2\2\u014aA\3\2\2\2\u014b\u0151\5F$\2\u014c\u014d\5D#\2\u014d\u014e\5"+
		"F$\2\u014e\u0150\3\2\2\2\u014f\u014c\3\2\2\2\u0150\u0153\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151\3\2"+
		"\2\2\u0154\u0155\b\"\1\2\u0155C\3\2\2\2\u0156\u0157\7\35\2\2\u0157\u015d"+
		"\b#\1\2\u0158\u0159\7\37\2\2\u0159\u015d\b#\1\2\u015a\u015b\7\36\2\2\u015b"+
		"\u015d\b#\1\2\u015c\u0156\3\2\2\2\u015c\u0158\3\2\2\2\u015c\u015a\3\2"+
		"\2\2\u015dE\3\2\2\2\u015e\u0164\5J&\2\u015f\u0160\5H%\2\u0160\u0161\5"+
		"J&\2\u0161\u0163\3\2\2\2\u0162\u015f\3\2\2\2\u0163\u0166\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0167\3\2\2\2\u0166\u0164\3\2"+
		"\2\2\u0167\u0168\b$\1\2\u0168G\3\2\2\2\u0169\u016a\7*\2\2\u016a\u016e"+
		"\b%\1\2\u016b\u016c\7+\2\2\u016c\u016e\b%\1\2\u016d\u0169\3\2\2\2\u016d"+
		"\u016b\3\2\2\2\u016eI\3\2\2\2\u016f\u0175\5N(\2\u0170\u0171\5L\'\2\u0171"+
		"\u0172\5N(\2\u0172\u0174\3\2\2\2\u0173\u0170\3\2\2\2\u0174\u0177\3\2\2"+
		"\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0178\3\2\2\2\u0177\u0175"+
		"\3\2\2\2\u0178\u0179\b&\1\2\u0179K\3\2\2\2\u017a\u017b\7#\2\2\u017b\u017f"+
		"\b\'\1\2\u017c\u017d\7$\2\2\u017d\u017f\b\'\1\2\u017e\u017a\3\2\2\2\u017e"+
		"\u017c\3\2\2\2\u017fM\3\2\2\2\u0180\u0186\5R*\2\u0181\u0182\5P)\2\u0182"+
		"\u0183\5R*\2\u0183\u0185\3\2\2\2\u0184\u0181\3\2\2\2\u0185\u0188\3\2\2"+
		"\2\u0186\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0189\3\2\2\2\u0188\u0186"+
		"\3\2\2\2\u0189\u018a\b(\1\2\u018aO\3\2\2\2\u018b\u018c\7%\2\2\u018c\u0196"+
		"\b)\1\2\u018d\u018e\7&\2\2\u018e\u0196\b)\1\2\u018f\u0190\7\'\2\2\u0190"+
		"\u0196\b)\1\2\u0191\u0192\7(\2\2\u0192\u0196\b)\1\2\u0193\u0194\7)\2\2"+
		"\u0194\u0196\b)\1\2\u0195\u018b\3\2\2\2\u0195\u018d\3\2\2\2\u0195\u018f"+
		"\3\2\2\2\u0195\u0191\3\2\2\2\u0195\u0193\3\2\2\2\u0196Q\3\2\2\2\u0197"+
		"\u019d\5V,\2\u0198\u0199\5T+\2\u0199\u019a\5V,\2\u019a\u019c\3\2\2\2\u019b"+
		"\u0198\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e\3\2"+
		"\2\2\u019e\u01a0\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a1\b*\1\2\u01a1"+
		"S\3\2\2\2\u01a2\u01a3\7.\2\2\u01a3\u01a4\b+\1\2\u01a4U\3\2\2\2\u01a5\u01a6"+
		"\7\f\2\2\u01a6\u01a7\5:\36\2\u01a7\u01a8\7\r\2\2\u01a8\u01a9\3\2\2\2\u01a9"+
		"\u01aa\b,\1\2\u01aa\u01ba\3\2\2\2\u01ab\u01ac\5X-\2\u01ac\u01ad\5:\36"+
		"\2\u01ad\u01ae\3\2\2\2\u01ae\u01af\b,\1\2\u01af\u01ba\3\2\2\2\u01b0\u01b1"+
		"\5Z.\2\u01b1\u01b2\b,\1\2\u01b2\u01ba\3\2\2\2\u01b3\u01b4\58\35\2\u01b4"+
		"\u01b5\b,\1\2\u01b5\u01ba\3\2\2\2\u01b6\u01b7\5\\/\2\u01b7\u01b8\b,\1"+
		"\2\u01b8\u01ba\3\2\2\2\u01b9\u01a5\3\2\2\2\u01b9\u01ab\3\2\2\2\u01b9\u01b0"+
		"\3\2\2\2\u01b9\u01b3\3\2\2\2\u01b9\u01b6\3\2\2\2\u01baW\3\2\2\2\u01bb"+
		"\u01bc\7 \2\2\u01bc\u01c2\b-\1\2\u01bd\u01be\7!\2\2\u01be\u01c2\b-\1\2"+
		"\u01bf\u01c0\7\"\2\2\u01c0\u01c2\b-\1\2\u01c1\u01bb\3\2\2\2\u01c1\u01bd"+
		"\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2Y\3\2\2\2\u01c3\u01c4\5\30\r\2\u01c4"+
		"\u01cd\7\f\2\2\u01c5\u01ca\5:\36\2\u01c6\u01c7\7\16\2\2\u01c7\u01c9\5"+
		":\36\2\u01c8\u01c6\3\2\2\2\u01c9\u01cc\3\2\2\2\u01ca\u01c8\3\2\2\2\u01ca"+
		"\u01cb\3\2\2\2\u01cb\u01ce\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01c5\3\2"+
		"\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d0\7\r\2\2\u01d0"+
		"\u01d1\b.\1\2\u01d1[\3\2\2\2\u01d2\u01d3\5`\61\2\u01d3\u01d4\b/\1\2\u01d4"+
		"\u01dc\3\2\2\2\u01d5\u01d6\5b\62\2\u01d6\u01d7\b/\1\2\u01d7\u01dc\3\2"+
		"\2\2\u01d8\u01d9\5^\60\2\u01d9\u01da\b/\1\2\u01da\u01dc\3\2\2\2\u01db"+
		"\u01d2\3\2\2\2\u01db\u01d5\3\2\2\2\u01db\u01d8\3\2\2\2\u01dc]\3\2\2\2"+
		"\u01dd\u01de\7,\2\2\u01de\u01e3\5:\36\2\u01df\u01e0\7\16\2\2\u01e0\u01e2"+
		"\5:\36\2\u01e1\u01df\3\2\2\2\u01e2\u01e5\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e3"+
		"\u01e4\3\2\2\2\u01e4\u01e6\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e6\u01e7\7-"+
		"\2\2\u01e7\u01e8\b\60\1\2\u01e8_\3\2\2\2\u01e9\u01ea\7/\2\2\u01ea\u01eb"+
		"\b\61\1\2\u01eba\3\2\2\2\u01ec\u01ed\7\60\2\2\u01ed\u01ee\b\62\1\2\u01ee"+
		"c\3\2\2\2&hjr\u008f\u0095\u00a3\u00a7\u00b3\u00b6\u00bd\u00c5\u00cd\u00da"+
		"\u00eb\u00ef\u00fe\u0119\u0120\u0129\u013e\u0149\u0151\u015c\u0164\u016d"+
		"\u0175\u017e\u0186\u0195\u019d\u01b9\u01c1\u01ca\u01cd\u01db\u01e3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}