// Generated from /Users/ppv-vldmr/Desktop/MT/lab-3/src/main/antlr4/Kek.g4 by ANTLR 4.9.2

    import kek.translation.*;
    import kek.util.Util.*;
    import java.util.stream.Collectors;
	import java.util.Set;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KekLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LET", "COLON", "SEMICOLON", "INT", "BOOL", "CHAR", "STRING", "IMPORT", 
			"DEF", "LPAR", "RPAR", "COMMA", "DEFER", "LBRACE", "RBRACE", "BREAK", 
			"CONTINUE", "IF", "ELSE", "WHILE", "ASSIGN", "RETURN", "EXTERN", "OR", 
			"OR_SIGN_BITWISE", "OR_SIGN", "AND", "AND_SIGN_BITWISE", "AND_SIGN", 
			"NOT", "NOT_SIGN", "NOT_SIGN_BITWISE", "PLUS_SIGN", "MINUS_SIGN", "MUL_SIGN", 
			"DIV", "DIV_SIGN", "MOD", "MOD_SIGN", "EQUALS", "NOT_EQUALS", "LBRACKET", 
			"RBRACKET", "INDEX", "INT_LITERAL", "BOOL_LITERAL", "ID", "EOLN", "WS"
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


	    private KekContextManager contextManager;


	public KekLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Kek.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\63\u012c\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3\"\3\""+
		"\3#\3#\3$\3$\3%\3%\3%\3%\3&\3&\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3)\3*\3*\3"+
		"*\3+\3+\3,\3,\3-\3-\3.\5.\u0100\n.\3.\6.\u0103\n.\r.\16.\u0104\3/\3/\3"+
		"/\3/\3/\3/\3/\3/\3/\5/\u0110\n/\3\60\3\60\7\60\u0114\n\60\f\60\16\60\u0117"+
		"\13\60\3\61\7\61\u011a\n\61\f\61\16\61\u011d\13\61\3\61\3\61\7\61\u0121"+
		"\n\61\f\61\16\61\u0124\13\61\3\62\6\62\u0127\n\62\r\62\16\62\u0128\3\62"+
		"\3\62\2\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63\3\2\6"+
		"\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\2\u0132\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2"+
		"c\3\2\2\2\3e\3\2\2\2\5i\3\2\2\2\7k\3\2\2\2\tm\3\2\2\2\13q\3\2\2\2\rv\3"+
		"\2\2\2\17{\3\2\2\2\21\177\3\2\2\2\23\u0086\3\2\2\2\25\u008a\3\2\2\2\27"+
		"\u008c\3\2\2\2\31\u008e\3\2\2\2\33\u0090\3\2\2\2\35\u0096\3\2\2\2\37\u0098"+
		"\3\2\2\2!\u009a\3\2\2\2#\u00a0\3\2\2\2%\u00a9\3\2\2\2\'\u00ac\3\2\2\2"+
		")\u00b1\3\2\2\2+\u00b7\3\2\2\2-\u00b9\3\2\2\2/\u00c0\3\2\2\2\61\u00c7"+
		"\3\2\2\2\63\u00ca\3\2\2\2\65\u00cc\3\2\2\2\67\u00cf\3\2\2\29\u00d3\3\2"+
		"\2\2;\u00d5\3\2\2\2=\u00d8\3\2\2\2?\u00dc\3\2\2\2A\u00de\3\2\2\2C\u00e0"+
		"\3\2\2\2E\u00e2\3\2\2\2G\u00e4\3\2\2\2I\u00e6\3\2\2\2K\u00ea\3\2\2\2M"+
		"\u00ec\3\2\2\2O\u00f0\3\2\2\2Q\u00f2\3\2\2\2S\u00f5\3\2\2\2U\u00f8\3\2"+
		"\2\2W\u00fa\3\2\2\2Y\u00fc\3\2\2\2[\u00ff\3\2\2\2]\u010f\3\2\2\2_\u0111"+
		"\3\2\2\2a\u011b\3\2\2\2c\u0126\3\2\2\2ef\7n\2\2fg\7g\2\2gh\7v\2\2h\4\3"+
		"\2\2\2ij\7<\2\2j\6\3\2\2\2kl\7=\2\2l\b\3\2\2\2mn\7k\2\2no\7p\2\2op\7v"+
		"\2\2p\n\3\2\2\2qr\7d\2\2rs\7q\2\2st\7q\2\2tu\7n\2\2u\f\3\2\2\2vw\7e\2"+
		"\2wx\7j\2\2xy\7c\2\2yz\7t\2\2z\16\3\2\2\2{|\7u\2\2|}\7v\2\2}~\7t\2\2~"+
		"\20\3\2\2\2\177\u0080\7k\2\2\u0080\u0081\7o\2\2\u0081\u0082\7r\2\2\u0082"+
		"\u0083\7q\2\2\u0083\u0084\7t\2\2\u0084\u0085\7v\2\2\u0085\22\3\2\2\2\u0086"+
		"\u0087\7f\2\2\u0087\u0088\7g\2\2\u0088\u0089\7h\2\2\u0089\24\3\2\2\2\u008a"+
		"\u008b\7*\2\2\u008b\26\3\2\2\2\u008c\u008d\7+\2\2\u008d\30\3\2\2\2\u008e"+
		"\u008f\7.\2\2\u008f\32\3\2\2\2\u0090\u0091\7f\2\2\u0091\u0092\7g\2\2\u0092"+
		"\u0093\7h\2\2\u0093\u0094\7g\2\2\u0094\u0095\7t\2\2\u0095\34\3\2\2\2\u0096"+
		"\u0097\7}\2\2\u0097\36\3\2\2\2\u0098\u0099\7\177\2\2\u0099 \3\2\2\2\u009a"+
		"\u009b\7d\2\2\u009b\u009c\7t\2\2\u009c\u009d\7g\2\2\u009d\u009e\7c\2\2"+
		"\u009e\u009f\7m\2\2\u009f\"\3\2\2\2\u00a0\u00a1\7e\2\2\u00a1\u00a2\7q"+
		"\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6"+
		"\7p\2\2\u00a6\u00a7\7w\2\2\u00a7\u00a8\7g\2\2\u00a8$\3\2\2\2\u00a9\u00aa"+
		"\7k\2\2\u00aa\u00ab\7h\2\2\u00ab&\3\2\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae"+
		"\7n\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7g\2\2\u00b0(\3\2\2\2\u00b1\u00b2"+
		"\7y\2\2\u00b2\u00b3\7j\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7n\2\2\u00b5"+
		"\u00b6\7g\2\2\u00b6*\3\2\2\2\u00b7\u00b8\7?\2\2\u00b8,\3\2\2\2\u00b9\u00ba"+
		"\7t\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd\7w\2\2\u00bd"+
		"\u00be\7t\2\2\u00be\u00bf\7p\2\2\u00bf.\3\2\2\2\u00c0\u00c1\7g\2\2\u00c1"+
		"\u00c2\7z\2\2\u00c2\u00c3\7v\2\2\u00c3\u00c4\7g\2\2\u00c4\u00c5\7t\2\2"+
		"\u00c5\u00c6\7p\2\2\u00c6\60\3\2\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9\7"+
		"t\2\2\u00c9\62\3\2\2\2\u00ca\u00cb\7~\2\2\u00cb\64\3\2\2\2\u00cc\u00cd"+
		"\7~\2\2\u00cd\u00ce\7~\2\2\u00ce\66\3\2\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1"+
		"\7p\2\2\u00d1\u00d2\7f\2\2\u00d28\3\2\2\2\u00d3\u00d4\7(\2\2\u00d4:\3"+
		"\2\2\2\u00d5\u00d6\7(\2\2\u00d6\u00d7\7(\2\2\u00d7<\3\2\2\2\u00d8\u00d9"+
		"\7p\2\2\u00d9\u00da\7q\2\2\u00da\u00db\7v\2\2\u00db>\3\2\2\2\u00dc\u00dd"+
		"\7#\2\2\u00dd@\3\2\2\2\u00de\u00df\7\u0080\2\2\u00dfB\3\2\2\2\u00e0\u00e1"+
		"\7-\2\2\u00e1D\3\2\2\2\u00e2\u00e3\7/\2\2\u00e3F\3\2\2\2\u00e4\u00e5\7"+
		",\2\2\u00e5H\3\2\2\2\u00e6\u00e7\7f\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9"+
		"\7x\2\2\u00e9J\3\2\2\2\u00ea\u00eb\7\61\2\2\u00ebL\3\2\2\2\u00ec\u00ed"+
		"\7o\2\2\u00ed\u00ee\7q\2\2\u00ee\u00ef\7f\2\2\u00efN\3\2\2\2\u00f0\u00f1"+
		"\7\'\2\2\u00f1P\3\2\2\2\u00f2\u00f3\7?\2\2\u00f3\u00f4\7?\2\2\u00f4R\3"+
		"\2\2\2\u00f5\u00f6\7#\2\2\u00f6\u00f7\7?\2\2\u00f7T\3\2\2\2\u00f8\u00f9"+
		"\7]\2\2\u00f9V\3\2\2\2\u00fa\u00fb\7_\2\2\u00fbX\3\2\2\2\u00fc\u00fd\7"+
		"\60\2\2\u00fdZ\3\2\2\2\u00fe\u0100\7/\2\2\u00ff\u00fe\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u0103\t\2\2\2\u0102\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\\\3\2\2\2"+
		"\u0106\u0107\7v\2\2\u0107\u0108\7t\2\2\u0108\u0109\7w\2\2\u0109\u0110"+
		"\7g\2\2\u010a\u010b\7h\2\2\u010b\u010c\7c\2\2\u010c\u010d\7n\2\2\u010d"+
		"\u010e\7u\2\2\u010e\u0110\7g\2\2\u010f\u0106\3\2\2\2\u010f\u010a\3\2\2"+
		"\2\u0110^\3\2\2\2\u0111\u0115\t\3\2\2\u0112\u0114\t\4\2\2\u0113\u0112"+
		"\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"`\3\2\2\2\u0117\u0115\3\2\2\2\u0118\u011a\5c\62\2\u0119\u0118\3\2\2\2"+
		"\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e"+
		"\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0122\7\f\2\2\u011f\u0121\5c\62\2\u0120"+
		"\u011f\3\2\2\2\u0121\u0124\3\2\2\2\u0122\u0120\3\2\2\2\u0122\u0123\3\2"+
		"\2\2\u0123b\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0127\t\5\2\2\u0126\u0125"+
		"\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u012b\b\62\2\2\u012bd\3\2\2\2\n\2\u00ff\u0104\u010f"+
		"\u0115\u011b\u0122\u0128\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}