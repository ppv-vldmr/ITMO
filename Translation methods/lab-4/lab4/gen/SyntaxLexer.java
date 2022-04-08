// Generated from /Users/ppv-vldmr/Desktop/MT/lab4 2/lab4/src/main/antlr/Syntax.g4 by ANTLR 4.9.2


    import java.util.*;
	import java.util.stream.Collectors;
    import lab4.primarygrammar.*;
    import lab4.primarylex.*;
    import static lab4.primarygrammar.Util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SyntaxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		RETURNS=1, GRAMMAR=2, JAVA_CODE_BRACES=3, JAVA_CODE_BRACKETS=4, REGEX=5, 
		LBRACKET=6, RBRACKET=7, LBRACE=8, RBRACE=9, LPAREN=10, RPAREN=11, SEMICOLON=12, 
		COLON=13, COMMA=14, PIPE=15, QUESTION=16, STAR=17, PLUS=18, APPEND=19, 
		HEADER=20, MEMBERS=21, ARROW=22, SKIP_WORD=23, RULE_NAME=24, TOKEN_NAME=25, 
		WS=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"RETURNS", "GRAMMAR", "JAVA_CODE_BRACES", "JAVA_CODE_BRACKETS", "REGEX", 
			"LBRACKET", "RBRACKET", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "SEMICOLON", 
			"COLON", "COMMA", "PIPE", "QUESTION", "STAR", "PLUS", "APPEND", "HEADER", 
			"MEMBERS", "ARROW", "SKIP_WORD", "RULE_NAME", "TOKEN_NAME", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'returns'", "'grammar'", null, null, null, "'['", "']'", "'{'", 
			"'}'", "'('", "')'", "';'", "':'", "','", "'|'", "'?'", "'*'", "'+'", 
			"'+='", "'@header'", "'@members'", "'->'", "'skip'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "RETURNS", "GRAMMAR", "JAVA_CODE_BRACES", "JAVA_CODE_BRACKETS", 
			"REGEX", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", "LPAREN", "RPAREN", 
			"SEMICOLON", "COLON", "COMMA", "PIPE", "QUESTION", "STAR", "PLUS", "APPEND", 
			"HEADER", "MEMBERS", "ARROW", "SKIP_WORD", "RULE_NAME", "TOKEN_NAME", 
			"WS"
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


	public SyntaxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Syntax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4\3\4\3\5\3"+
		"\5\3\5\7\5U\n\5\f\5\16\5X\13\5\3\5\3\5\3\6\3\6\7\6^\n\6\f\6\16\6a\13\6"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\7\31"+
		"\u009d\n\31\f\31\16\31\u00a0\13\31\3\32\3\32\7\32\u00a4\n\32\f\32\16\32"+
		"\u00a7\13\32\3\33\6\33\u00aa\n\33\r\33\16\33\u00ab\3\33\3\33\2\2\34\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\t\4\2}}\177"+
		"\177\4\2]]__\3\2$$\3\2c|\6\2\62;C\\aac|\3\2C\\\5\2\13\f\17\17\"\"\2\u00b6"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5?\3\2\2\2\7G\3"+
		"\2\2\2\tQ\3\2\2\2\13[\3\2\2\2\rd\3\2\2\2\17f\3\2\2\2\21h\3\2\2\2\23j\3"+
		"\2\2\2\25l\3\2\2\2\27n\3\2\2\2\31p\3\2\2\2\33r\3\2\2\2\35t\3\2\2\2\37"+
		"v\3\2\2\2!x\3\2\2\2#z\3\2\2\2%|\3\2\2\2\'~\3\2\2\2)\u0081\3\2\2\2+\u0089"+
		"\3\2\2\2-\u0092\3\2\2\2/\u0095\3\2\2\2\61\u009a\3\2\2\2\63\u00a1\3\2\2"+
		"\2\65\u00a9\3\2\2\2\678\7t\2\289\7g\2\29:\7v\2\2:;\7w\2\2;<\7t\2\2<=\7"+
		"p\2\2=>\7u\2\2>\4\3\2\2\2?@\7i\2\2@A\7t\2\2AB\7c\2\2BC\7o\2\2CD\7o\2\2"+
		"DE\7c\2\2EF\7t\2\2F\6\3\2\2\2GL\7}\2\2HK\5\7\4\2IK\n\2\2\2JH\3\2\2\2J"+
		"I\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\177\2"+
		"\2P\b\3\2\2\2QV\7]\2\2RU\5\t\5\2SU\n\3\2\2TR\3\2\2\2TS\3\2\2\2UX\3\2\2"+
		"\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\7_\2\2Z\n\3\2\2\2[_\7$\2"+
		"\2\\^\n\4\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2"+
		"\2\2bc\7$\2\2c\f\3\2\2\2de\7]\2\2e\16\3\2\2\2fg\7_\2\2g\20\3\2\2\2hi\7"+
		"}\2\2i\22\3\2\2\2jk\7\177\2\2k\24\3\2\2\2lm\7*\2\2m\26\3\2\2\2no\7+\2"+
		"\2o\30\3\2\2\2pq\7=\2\2q\32\3\2\2\2rs\7<\2\2s\34\3\2\2\2tu\7.\2\2u\36"+
		"\3\2\2\2vw\7~\2\2w \3\2\2\2xy\7A\2\2y\"\3\2\2\2z{\7,\2\2{$\3\2\2\2|}\7"+
		"-\2\2}&\3\2\2\2~\177\7-\2\2\177\u0080\7?\2\2\u0080(\3\2\2\2\u0081\u0082"+
		"\7B\2\2\u0082\u0083\7j\2\2\u0083\u0084\7g\2\2\u0084\u0085\7c\2\2\u0085"+
		"\u0086\7f\2\2\u0086\u0087\7g\2\2\u0087\u0088\7t\2\2\u0088*\3\2\2\2\u0089"+
		"\u008a\7B\2\2\u008a\u008b\7o\2\2\u008b\u008c\7g\2\2\u008c\u008d\7o\2\2"+
		"\u008d\u008e\7d\2\2\u008e\u008f\7g\2\2\u008f\u0090\7t\2\2\u0090\u0091"+
		"\7u\2\2\u0091,\3\2\2\2\u0092\u0093\7/\2\2\u0093\u0094\7@\2\2\u0094.\3"+
		"\2\2\2\u0095\u0096\7u\2\2\u0096\u0097\7m\2\2\u0097\u0098\7k\2\2\u0098"+
		"\u0099\7r\2\2\u0099\60\3\2\2\2\u009a\u009e\t\5\2\2\u009b\u009d\t\6\2\2"+
		"\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f"+
		"\3\2\2\2\u009f\62\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a5\t\7\2\2\u00a2"+
		"\u00a4\t\6\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\64\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa"+
		"\t\b\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\33\2\2\u00ae\66\3\2\2"+
		"\2\13\2JLTV_\u009e\u00a5\u00ab\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}