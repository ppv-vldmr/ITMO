// Generated from C:/Programing/sem5/parsers/itmo-mt/lab4/src/main/antlr\Syntax.g4 by ANTLR 4.9.2
package lab4.syntaxparse;


    import java.util.*;
	import java.util.stream.Collectors;
    import lab4.primarygrammar.*;
    import lab4.primarylex.*;
    import static lab4.primarygrammar.Util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SyntaxParser extends Parser {
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
	public static final int
		RULE_grammarFile = 0, RULE_headers = 1, RULE_members = 2, RULE_grammarPrelude = 3, 
		RULE_name = 4, RULE_rules = 5, RULE_singleRule = 6, RULE_singleLexerRule = 7, 
		RULE_singleParserRule = 8, RULE_ruleArgs = 9, RULE_ruleReturns = 10, RULE_codeBlock = 11, 
		RULE_alternatives = 12, RULE_alternative = 13, RULE_elemAlts = 14, RULE_element = 15, 
		RULE_notLabeledElement = 16, RULE_elemModifier = 17, RULE_labeledElement = 18, 
		RULE_terminal = 19, RULE_ruleUsage = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarFile", "headers", "members", "grammarPrelude", "name", "rules", 
			"singleRule", "singleLexerRule", "singleParserRule", "ruleArgs", "ruleReturns", 
			"codeBlock", "alternatives", "alternative", "elemAlts", "element", "notLabeledElement", 
			"elemModifier", "labeledElement", "terminal", "ruleUsage"
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

	@Override
	public String getGrammarFileName() { return "Syntax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SyntaxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GrammarFileContext extends ParserRuleContext {
		public PrimaryGrammar grammar;
		public GrammarPreludeContext grammarPrelude;
		public HeadersContext headers;
		public List<HeadersContext> hdrs = new ArrayList<HeadersContext>();
		public MembersContext members;
		public List<MembersContext> mmbrs = new ArrayList<MembersContext>();
		public RulesContext rules;
		public GrammarPreludeContext grammarPrelude() {
			return getRuleContext(GrammarPreludeContext.class,0);
		}
		public RulesContext rules() {
			return getRuleContext(RulesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SyntaxParser.EOF, 0); }
		public List<HeadersContext> headers() {
			return getRuleContexts(HeadersContext.class);
		}
		public HeadersContext headers(int i) {
			return getRuleContext(HeadersContext.class,i);
		}
		public List<MembersContext> members() {
			return getRuleContexts(MembersContext.class);
		}
		public MembersContext members(int i) {
			return getRuleContext(MembersContext.class,i);
		}
		public GrammarFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterGrammarFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitGrammarFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitGrammarFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarFileContext grammarFile() throws RecognitionException {
		GrammarFileContext _localctx = new GrammarFileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarFile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			((GrammarFileContext)_localctx).grammarPrelude = grammarPrelude();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==HEADER) {
				{
				{
				setState(43);
				((GrammarFileContext)_localctx).headers = headers();
				((GrammarFileContext)_localctx).hdrs.add(((GrammarFileContext)_localctx).headers);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MEMBERS) {
				{
				{
				setState(49);
				((GrammarFileContext)_localctx).members = members();
				((GrammarFileContext)_localctx).mmbrs.add(((GrammarFileContext)_localctx).members);
				}
				}
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(55);
			((GrammarFileContext)_localctx).rules = rules();
			setState(56);
			match(EOF);

			    ((GrammarFileContext)_localctx).grammar =  new PrimaryGrammar(
			        ((GrammarFileContext)_localctx).grammarPrelude.grammarName,
			        ((GrammarFileContext)_localctx).hdrs.stream().map(x -> getJava(x.content)).collect(Collectors.joining("\n")),
			        ((GrammarFileContext)_localctx).mmbrs.stream().map(x -> getJava(x.content)).collect(Collectors.joining("\n")),
			        ((GrammarFileContext)_localctx).rules.primaryTokens,
			        ((GrammarFileContext)_localctx).rules.primaryRules
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

	public static class HeadersContext extends ParserRuleContext {
		public String content;
		public TerminalNode HEADER() { return getToken(SyntaxParser.HEADER, 0); }
		public TerminalNode JAVA_CODE_BRACES() { return getToken(SyntaxParser.JAVA_CODE_BRACES, 0); }
		public HeadersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_headers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterHeaders(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitHeaders(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitHeaders(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeadersContext headers() throws RecognitionException {
		HeadersContext _localctx = new HeadersContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_headers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(HEADER);
			setState(60);
			match(JAVA_CODE_BRACES);

			    ((HeadersContext)_localctx).content =  _localctx.getChild(1).getText();

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

	public static class MembersContext extends ParserRuleContext {
		public String content;
		public TerminalNode MEMBERS() { return getToken(SyntaxParser.MEMBERS, 0); }
		public TerminalNode JAVA_CODE_BRACES() { return getToken(SyntaxParser.JAVA_CODE_BRACES, 0); }
		public MembersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_members; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterMembers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitMembers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitMembers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MembersContext members() throws RecognitionException {
		MembersContext _localctx = new MembersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_members);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(MEMBERS);
			setState(64);
			match(JAVA_CODE_BRACES);

			    ((MembersContext)_localctx).content =  _localctx.getChild(1).getText();

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

	public static class GrammarPreludeContext extends ParserRuleContext {
		public String grammarName;
		public NameContext name;
		public TerminalNode GRAMMAR() { return getToken(SyntaxParser.GRAMMAR, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SyntaxParser.SEMICOLON, 0); }
		public GrammarPreludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarPrelude; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterGrammarPrelude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitGrammarPrelude(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitGrammarPrelude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrammarPreludeContext grammarPrelude() throws RecognitionException {
		GrammarPreludeContext _localctx = new GrammarPreludeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_grammarPrelude);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(GRAMMAR);
			setState(68);
			((GrammarPreludeContext)_localctx).name = name();
			setState(69);
			match(SEMICOLON);

			    ((GrammarPreludeContext)_localctx).grammarName =  ((GrammarPreludeContext)_localctx).name.content;

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

	public static class NameContext extends ParserRuleContext {
		public String content;
		public TerminalNode RULE_NAME() { return getToken(SyntaxParser.RULE_NAME, 0); }
		public TerminalNode TOKEN_NAME() { return getToken(SyntaxParser.TOKEN_NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_name);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(RULE_NAME);
				((NameContext)_localctx).content =  _localctx.getChild(0).getText();
				}
				break;
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				match(TOKEN_NAME);
				((NameContext)_localctx).content =  _localctx.getChild(0).getText();
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

	public static class RulesContext extends ParserRuleContext {
		public List<PrimaryToken> primaryTokens;
		public List<PrimaryRule> primaryRules;
		public SingleRuleContext singleRule;
		public List<SingleRuleContext> rls = new ArrayList<SingleRuleContext>();
		public List<SingleRuleContext> singleRule() {
			return getRuleContexts(SingleRuleContext.class);
		}
		public SingleRuleContext singleRule(int i) {
			return getRuleContext(SingleRuleContext.class,i);
		}
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RULE_NAME || _la==TOKEN_NAME) {
				{
				{
				setState(78);
				((RulesContext)_localctx).singleRule = singleRule();
				((RulesContext)_localctx).rls.add(((RulesContext)_localctx).singleRule);
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    ((RulesContext)_localctx).primaryTokens =  ((RulesContext)_localctx).rls.stream().map(x -> x.primaryToken).filter(Objects::nonNull).collect(Collectors.toList());
			    ((RulesContext)_localctx).primaryRules =  ((RulesContext)_localctx).rls.stream().map(x -> x.primaryRule).filter(Objects::nonNull).collect(Collectors.toList());

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

	public static class SingleRuleContext extends ParserRuleContext {
		public PrimaryToken primaryToken;
		public PrimaryRule primaryRule;
		public SingleParserRuleContext singleParserRule;
		public SingleLexerRuleContext singleLexerRule;
		public SingleParserRuleContext singleParserRule() {
			return getRuleContext(SingleParserRuleContext.class,0);
		}
		public SingleLexerRuleContext singleLexerRule() {
			return getRuleContext(SingleLexerRuleContext.class,0);
		}
		public SingleRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterSingleRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitSingleRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitSingleRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleRuleContext singleRule() throws RecognitionException {
		SingleRuleContext _localctx = new SingleRuleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_singleRule);
		try {
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RULE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				((SingleRuleContext)_localctx).singleParserRule = singleParserRule();

				        ((SingleRuleContext)_localctx).primaryToken =  null;
				        ((SingleRuleContext)_localctx).primaryRule =  ((SingleRuleContext)_localctx).singleParserRule.primaryRule;
				    
				}
				break;
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				((SingleRuleContext)_localctx).singleLexerRule = singleLexerRule();

				        ((SingleRuleContext)_localctx).primaryToken =  ((SingleRuleContext)_localctx).singleLexerRule.primaryToken;
				        ((SingleRuleContext)_localctx).primaryRule =  null;
				    
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

	public static class SingleLexerRuleContext extends ParserRuleContext {
		public PrimaryToken primaryToken;
		public TerminalNode TOKEN_NAME() { return getToken(SyntaxParser.TOKEN_NAME, 0); }
		public TerminalNode COLON() { return getToken(SyntaxParser.COLON, 0); }
		public TerminalNode REGEX() { return getToken(SyntaxParser.REGEX, 0); }
		public TerminalNode SEMICOLON() { return getToken(SyntaxParser.SEMICOLON, 0); }
		public TerminalNode ARROW() { return getToken(SyntaxParser.ARROW, 0); }
		public TerminalNode SKIP_WORD() { return getToken(SyntaxParser.SKIP_WORD, 0); }
		public SingleLexerRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleLexerRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterSingleLexerRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitSingleLexerRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitSingleLexerRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleLexerRuleContext singleLexerRule() throws RecognitionException {
		SingleLexerRuleContext _localctx = new SingleLexerRuleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_singleLexerRule);
		boolean skip = false;
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(TOKEN_NAME);
			setState(95);
			match(COLON);
			setState(96);
			match(REGEX);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ARROW) {
				{
				setState(97);
				match(ARROW);
				setState(98);
				match(SKIP_WORD);
				skip = true;
				}
			}

			setState(102);
			match(SEMICOLON);

			    ((SingleLexerRuleContext)_localctx).primaryToken =  new PrimaryToken(_localctx.getChild(0).getText(), _localctx.getChild(2).getText(), skip);

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

	public static class SingleParserRuleContext extends ParserRuleContext {
		public PrimaryRule primaryRule;
		public RuleArgsContext ruleArgs;
		public List<RuleArgsContext> args = new ArrayList<RuleArgsContext>();
		public RuleReturnsContext ruleReturns;
		public List<RuleReturnsContext> rets = new ArrayList<RuleReturnsContext>();
		public CodeBlockContext codeBlock;
		public List<CodeBlockContext> init = new ArrayList<CodeBlockContext>();
		public AlternativesContext alternatives;
		public TerminalNode RULE_NAME() { return getToken(SyntaxParser.RULE_NAME, 0); }
		public TerminalNode COLON() { return getToken(SyntaxParser.COLON, 0); }
		public AlternativesContext alternatives() {
			return getRuleContext(AlternativesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SyntaxParser.SEMICOLON, 0); }
		public RuleArgsContext ruleArgs() {
			return getRuleContext(RuleArgsContext.class,0);
		}
		public RuleReturnsContext ruleReturns() {
			return getRuleContext(RuleReturnsContext.class,0);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public SingleParserRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleParserRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterSingleParserRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitSingleParserRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitSingleParserRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleParserRuleContext singleParserRule() throws RecognitionException {
		SingleParserRuleContext _localctx = new SingleParserRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_singleParserRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(RULE_NAME);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==JAVA_CODE_BRACKETS) {
				{
				setState(106);
				((SingleParserRuleContext)_localctx).ruleArgs = ruleArgs();
				((SingleParserRuleContext)_localctx).args.add(((SingleParserRuleContext)_localctx).ruleArgs);
				}
			}

			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(109);
				((SingleParserRuleContext)_localctx).ruleReturns = ruleReturns();
				((SingleParserRuleContext)_localctx).rets.add(((SingleParserRuleContext)_localctx).ruleReturns);
				}
			}

			setState(112);
			match(COLON);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==JAVA_CODE_BRACES) {
				{
				setState(113);
				((SingleParserRuleContext)_localctx).codeBlock = codeBlock();
				((SingleParserRuleContext)_localctx).init.add(((SingleParserRuleContext)_localctx).codeBlock);
				}
			}

			setState(116);
			((SingleParserRuleContext)_localctx).alternatives = alternatives();
			setState(117);
			match(SEMICOLON);

			    ((SingleParserRuleContext)_localctx).primaryRule =  new PrimaryRule(
			        _localctx.getChild(0).getText(),
			        get(((SingleParserRuleContext)_localctx).args) == null ? Collections.emptyList() : get(((SingleParserRuleContext)_localctx).args).ruleArgsList,
			        get(((SingleParserRuleContext)_localctx).rets) == null ? Collections.emptyList() : get(((SingleParserRuleContext)_localctx).rets).ruleReturnsList,
			        get(((SingleParserRuleContext)_localctx).init) == null ? null : get(((SingleParserRuleContext)_localctx).init).code,
			        ((SingleParserRuleContext)_localctx).alternatives.ruleContents
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

	public static class RuleArgsContext extends ParserRuleContext {
		public List<String> ruleArgsList;
		public Token JAVA_CODE_BRACKETS;
		public List<Token> codes = new ArrayList<Token>();
		public List<TerminalNode> JAVA_CODE_BRACKETS() { return getTokens(SyntaxParser.JAVA_CODE_BRACKETS); }
		public TerminalNode JAVA_CODE_BRACKETS(int i) {
			return getToken(SyntaxParser.JAVA_CODE_BRACKETS, i);
		}
		public RuleArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterRuleArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitRuleArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitRuleArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleArgsContext ruleArgs() throws RecognitionException {
		RuleArgsContext _localctx = new RuleArgsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ruleArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(120);
				((RuleArgsContext)_localctx).JAVA_CODE_BRACKETS = match(JAVA_CODE_BRACKETS);
				((RuleArgsContext)_localctx).codes.add(((RuleArgsContext)_localctx).JAVA_CODE_BRACKETS);
				}
				}
				setState(123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==JAVA_CODE_BRACKETS );

			    ((RuleArgsContext)_localctx).ruleArgsList =  ((RuleArgsContext)_localctx).codes.stream().map(x -> getJava(x.getText())).collect(Collectors.toList());

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

	public static class RuleReturnsContext extends ParserRuleContext {
		public List<String> ruleReturnsList;
		public Token JAVA_CODE_BRACKETS;
		public List<Token> codes = new ArrayList<Token>();
		public TerminalNode RETURNS() { return getToken(SyntaxParser.RETURNS, 0); }
		public List<TerminalNode> JAVA_CODE_BRACKETS() { return getTokens(SyntaxParser.JAVA_CODE_BRACKETS); }
		public TerminalNode JAVA_CODE_BRACKETS(int i) {
			return getToken(SyntaxParser.JAVA_CODE_BRACKETS, i);
		}
		public RuleReturnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleReturns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterRuleReturns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitRuleReturns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitRuleReturns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleReturnsContext ruleReturns() throws RecognitionException {
		RuleReturnsContext _localctx = new RuleReturnsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ruleReturns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(RETURNS);
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(128);
				((RuleReturnsContext)_localctx).JAVA_CODE_BRACKETS = match(JAVA_CODE_BRACKETS);
				((RuleReturnsContext)_localctx).codes.add(((RuleReturnsContext)_localctx).JAVA_CODE_BRACKETS);
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==JAVA_CODE_BRACKETS );

			    ((RuleReturnsContext)_localctx).ruleReturnsList =  ((RuleReturnsContext)_localctx).codes.stream().map(x -> getJava(x.getText())).collect(Collectors.toList());

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

	public static class CodeBlockContext extends ParserRuleContext {
		public String code;
		public TerminalNode JAVA_CODE_BRACES() { return getToken(SyntaxParser.JAVA_CODE_BRACES, 0); }
		public CodeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterCodeBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitCodeBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitCodeBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeBlockContext codeBlock() throws RecognitionException {
		CodeBlockContext _localctx = new CodeBlockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_codeBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(JAVA_CODE_BRACES);

			    ((CodeBlockContext)_localctx).code =  getJava(_localctx.getChild(0).getText());

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

	public static class AlternativesContext extends ParserRuleContext {
		public List<PrimaryRuleContent> ruleContents;
		public AlternativeContext alternative;
		public List<AlternativeContext> alts = new ArrayList<AlternativeContext>();
		public List<AlternativeContext> alternative() {
			return getRuleContexts(AlternativeContext.class);
		}
		public AlternativeContext alternative(int i) {
			return getRuleContext(AlternativeContext.class,i);
		}
		public List<TerminalNode> PIPE() { return getTokens(SyntaxParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(SyntaxParser.PIPE, i);
		}
		public AlternativesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternatives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterAlternatives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitAlternatives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitAlternatives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternativesContext alternatives() throws RecognitionException {
		AlternativesContext _localctx = new AlternativesContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_alternatives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((AlternativesContext)_localctx).alternative = alternative();
			((AlternativesContext)_localctx).alts.add(((AlternativesContext)_localctx).alternative);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PIPE) {
				{
				{
				setState(139);
				match(PIPE);
				setState(140);
				((AlternativesContext)_localctx).alternative = alternative();
				((AlternativesContext)_localctx).alts.add(((AlternativesContext)_localctx).alternative);
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    ((AlternativesContext)_localctx).ruleContents =  ((AlternativesContext)_localctx).alts.stream().map(x -> x.ruleContent).collect(Collectors.toList());

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

	public static class AlternativeContext extends ParserRuleContext {
		public PrimaryRuleContent ruleContent;
		public ElementContext element;
		public List<ElementContext> elems = new ArrayList<ElementContext>();
		public CodeBlockContext codeBlock;
		public List<CodeBlockContext> code = new ArrayList<CodeBlockContext>();
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public CodeBlockContext codeBlock() {
			return getRuleContext(CodeBlockContext.class,0);
		}
		public AlternativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitAlternative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitAlternative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternativeContext alternative() throws RecognitionException {
		AlternativeContext _localctx = new AlternativeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_alternative);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(148);
				((AlternativeContext)_localctx).element = element();
				((AlternativeContext)_localctx).elems.add(((AlternativeContext)_localctx).element);
				}
				}
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAREN) | (1L << RULE_NAME) | (1L << TOKEN_NAME))) != 0) );
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==JAVA_CODE_BRACES) {
				{
				setState(153);
				((AlternativeContext)_localctx).codeBlock = codeBlock();
				((AlternativeContext)_localctx).code.add(((AlternativeContext)_localctx).codeBlock);
				}
			}


			    ((AlternativeContext)_localctx).ruleContent =  new PrimaryRuleContent(
			        ((AlternativeContext)_localctx).elems.stream().map(x -> x.ruleElement).collect(Collectors.toList()),
			        get(((AlternativeContext)_localctx).code) == null ? null : get(((AlternativeContext)_localctx).code).code
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

	public static class ElemAltsContext extends ParserRuleContext {
		public List<PrimaryRuleContent> ruleContents;
		public AlternativeContext alternative;
		public List<AlternativeContext> alts = new ArrayList<AlternativeContext>();
		public List<AlternativeContext> alternative() {
			return getRuleContexts(AlternativeContext.class);
		}
		public AlternativeContext alternative(int i) {
			return getRuleContext(AlternativeContext.class,i);
		}
		public List<TerminalNode> PIPE() { return getTokens(SyntaxParser.PIPE); }
		public TerminalNode PIPE(int i) {
			return getToken(SyntaxParser.PIPE, i);
		}
		public ElemAltsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elemAlts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterElemAlts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitElemAlts(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitElemAlts(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElemAltsContext elemAlts() throws RecognitionException {
		ElemAltsContext _localctx = new ElemAltsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_elemAlts);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			((ElemAltsContext)_localctx).alternative = alternative();
			((ElemAltsContext)_localctx).alts.add(((ElemAltsContext)_localctx).alternative);
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PIPE) {
				{
				{
				setState(159);
				match(PIPE);
				setState(160);
				((ElemAltsContext)_localctx).alternative = alternative();
				((ElemAltsContext)_localctx).alts.add(((ElemAltsContext)_localctx).alternative);
				}
				}
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    ((ElemAltsContext)_localctx).ruleContents =  ((ElemAltsContext)_localctx).alts.stream().map(x -> x.ruleContent).collect(Collectors.toList());

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

	public static class ElementContext extends ParserRuleContext {
		public PrimaryRuleElement ruleElement;
		public LabeledElementContext labeledElement;
		public NotLabeledElementContext notLabeledElement;
		public LabeledElementContext labeledElement() {
			return getRuleContext(LabeledElementContext.class,0);
		}
		public NotLabeledElementContext notLabeledElement() {
			return getRuleContext(NotLabeledElementContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_element);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				((ElementContext)_localctx).labeledElement = labeledElement();
				((ElementContext)_localctx).ruleElement =  ((ElementContext)_localctx).labeledElement.ruleElement;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				((ElementContext)_localctx).notLabeledElement = notLabeledElement();
				((ElementContext)_localctx).ruleElement =  ((ElementContext)_localctx).notLabeledElement.ruleElement;
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

	public static class NotLabeledElementContext extends ParserRuleContext {
		public PrimaryRuleElement ruleElement;
		public TerminalContext terminal;
		public ElemModifierContext elemModifier;
		public List<ElemModifierContext> q = new ArrayList<ElemModifierContext>();
		public RuleUsageContext ruleUsage;
		public ElemAltsContext elemAlts;
		public TerminalContext terminal() {
			return getRuleContext(TerminalContext.class,0);
		}
		public ElemModifierContext elemModifier() {
			return getRuleContext(ElemModifierContext.class,0);
		}
		public RuleUsageContext ruleUsage() {
			return getRuleContext(RuleUsageContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(SyntaxParser.LPAREN, 0); }
		public ElemAltsContext elemAlts() {
			return getRuleContext(ElemAltsContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SyntaxParser.RPAREN, 0); }
		public NotLabeledElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notLabeledElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterNotLabeledElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitNotLabeledElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitNotLabeledElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotLabeledElementContext notLabeledElement() throws RecognitionException {
		NotLabeledElementContext _localctx = new NotLabeledElementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_notLabeledElement);
		int _la;
		try {
			setState(196);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TOKEN_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				((NotLabeledElementContext)_localctx).terminal = terminal();
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QUESTION) | (1L << STAR) | (1L << PLUS))) != 0)) {
					{
					setState(177);
					((NotLabeledElementContext)_localctx).elemModifier = elemModifier();
					((NotLabeledElementContext)_localctx).q.add(((NotLabeledElementContext)_localctx).elemModifier);
					}
				}


				        ((NotLabeledElementContext)_localctx).ruleElement =  new PrimaryRuleElement(
				            ((NotLabeledElementContext)_localctx).terminal.terminalElem,
				            get(((NotLabeledElementContext)_localctx).q) == null ? null : get(((NotLabeledElementContext)_localctx).q).quantifier
				        );
				    
				}
				break;
			case RULE_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(182);
				((NotLabeledElementContext)_localctx).ruleUsage = ruleUsage();
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QUESTION) | (1L << STAR) | (1L << PLUS))) != 0)) {
					{
					setState(183);
					((NotLabeledElementContext)_localctx).elemModifier = elemModifier();
					((NotLabeledElementContext)_localctx).q.add(((NotLabeledElementContext)_localctx).elemModifier);
					}
				}


				        ((NotLabeledElementContext)_localctx).ruleElement =  new PrimaryRuleElement(
				            ((NotLabeledElementContext)_localctx).ruleUsage.nonTerminalElem,
				            get(((NotLabeledElementContext)_localctx).q) == null ? null : get(((NotLabeledElementContext)_localctx).q).quantifier
				        );
				    
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				match(LPAREN);
				setState(189);
				((NotLabeledElementContext)_localctx).elemAlts = elemAlts();
				setState(190);
				match(RPAREN);
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QUESTION) | (1L << STAR) | (1L << PLUS))) != 0)) {
					{
					setState(191);
					((NotLabeledElementContext)_localctx).elemModifier = elemModifier();
					((NotLabeledElementContext)_localctx).q.add(((NotLabeledElementContext)_localctx).elemModifier);
					}
				}


				        ((NotLabeledElementContext)_localctx).ruleElement =  new PrimaryRuleElement(
				                ((NotLabeledElementContext)_localctx).elemAlts.ruleContents,
				                get(((NotLabeledElementContext)_localctx).q) == null ? null : get(((NotLabeledElementContext)_localctx).q).quantifier
				            );
				    
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

	public static class ElemModifierContext extends ParserRuleContext {
		public Quantifier quantifier;
		public TerminalNode QUESTION() { return getToken(SyntaxParser.QUESTION, 0); }
		public TerminalNode STAR() { return getToken(SyntaxParser.STAR, 0); }
		public TerminalNode PLUS() { return getToken(SyntaxParser.PLUS, 0); }
		public ElemModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elemModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterElemModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitElemModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitElemModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElemModifierContext elemModifier() throws RecognitionException {
		ElemModifierContext _localctx = new ElemModifierContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_elemModifier);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUESTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				match(QUESTION);
				((ElemModifierContext)_localctx).quantifier =  Quantifier.ZERO_OR_ONE;
				}
				break;
			case STAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				match(STAR);
				((ElemModifierContext)_localctx).quantifier =  Quantifier.ZERO_OR_MORE;
				}
				break;
			case PLUS:
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				match(PLUS);
				((ElemModifierContext)_localctx).quantifier =  Quantifier.ONE_OR_MORE;
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

	public static class LabeledElementContext extends ParserRuleContext {
		public PrimaryRuleElement ruleElement;
		public NameContext name;
		public NotLabeledElementContext notLabeledElement;
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode APPEND() { return getToken(SyntaxParser.APPEND, 0); }
		public NotLabeledElementContext notLabeledElement() {
			return getRuleContext(NotLabeledElementContext.class,0);
		}
		public LabeledElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeledElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterLabeledElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitLabeledElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitLabeledElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabeledElementContext labeledElement() throws RecognitionException {
		LabeledElementContext _localctx = new LabeledElementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_labeledElement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			((LabeledElementContext)_localctx).name = name();
			setState(207);
			match(APPEND);
			setState(208);
			((LabeledElementContext)_localctx).notLabeledElement = notLabeledElement();

			    ((LabeledElementContext)_localctx).ruleElement =  new PrimaryRuleElement(((LabeledElementContext)_localctx).name.content, ((LabeledElementContext)_localctx).notLabeledElement.ruleElement);

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

	public static class TerminalContext extends ParserRuleContext {
		public PrimaryTerminal terminalElem;
		public TerminalNode TOKEN_NAME() { return getToken(SyntaxParser.TOKEN_NAME, 0); }
		public TerminalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterTerminal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitTerminal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitTerminal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminalContext terminal() throws RecognitionException {
		TerminalContext _localctx = new TerminalContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_terminal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			match(TOKEN_NAME);
			((TerminalContext)_localctx).terminalElem =  new PrimaryTerminal(_localctx.getChild(0).getText());
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

	public static class RuleUsageContext extends ParserRuleContext {
		public PrimaryNonTerminal nonTerminalElem;
		public Token JAVA_CODE_BRACKETS;
		public List<Token> args = new ArrayList<Token>();
		public TerminalNode RULE_NAME() { return getToken(SyntaxParser.RULE_NAME, 0); }
		public List<TerminalNode> JAVA_CODE_BRACKETS() { return getTokens(SyntaxParser.JAVA_CODE_BRACKETS); }
		public TerminalNode JAVA_CODE_BRACKETS(int i) {
			return getToken(SyntaxParser.JAVA_CODE_BRACKETS, i);
		}
		public RuleUsageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleUsage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).enterRuleUsage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SyntaxListener ) ((SyntaxListener)listener).exitRuleUsage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SyntaxVisitor ) return ((SyntaxVisitor<? extends T>)visitor).visitRuleUsage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleUsageContext ruleUsage() throws RecognitionException {
		RuleUsageContext _localctx = new RuleUsageContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ruleUsage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(RULE_NAME);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==JAVA_CODE_BRACKETS) {
				{
				{
				setState(215);
				((RuleUsageContext)_localctx).JAVA_CODE_BRACKETS = match(JAVA_CODE_BRACKETS);
				((RuleUsageContext)_localctx).args.add(((RuleUsageContext)_localctx).JAVA_CODE_BRACKETS);
				}
				}
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			    ((RuleUsageContext)_localctx).nonTerminalElem =  new PrimaryNonTerminal(
			        _localctx.getChild(0).getText(),
			        ((RuleUsageContext)_localctx).args.stream().map(x -> getJava(x.getText())).collect(Collectors.toList())
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34\u00e2\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\7\2/\n\2\f\2\16\2\62"+
		"\13\2\3\2\7\2\65\n\2\f\2\16\28\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\7\7R\n"+
		"\7\f\7\16\7U\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\5\b_\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tg\n\t\3\t\3\t\3\t\3\n\3\n\5\nn\n\n\3\n\5\nq\n\n\3\n\3"+
		"\n\5\nu\n\n\3\n\3\n\3\n\3\n\3\13\6\13|\n\13\r\13\16\13}\3\13\3\13\3\f"+
		"\3\f\6\f\u0084\n\f\r\f\16\f\u0085\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\7"+
		"\16\u0090\n\16\f\16\16\16\u0093\13\16\3\16\3\16\3\17\6\17\u0098\n\17\r"+
		"\17\16\17\u0099\3\17\5\17\u009d\n\17\3\17\3\17\3\20\3\20\3\20\7\20\u00a4"+
		"\n\20\f\20\16\20\u00a7\13\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\5"+
		"\21\u00b1\n\21\3\22\3\22\5\22\u00b5\n\22\3\22\3\22\3\22\3\22\5\22\u00bb"+
		"\n\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c3\n\22\3\22\3\22\5\22\u00c7"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00cf\n\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\26\3\26\7\26\u00db\n\26\f\26\16\26\u00de\13\26"+
		"\3\26\3\26\3\26\2\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2"+
		"\2\2\u00e4\2,\3\2\2\2\4=\3\2\2\2\6A\3\2\2\2\bE\3\2\2\2\nN\3\2\2\2\fS\3"+
		"\2\2\2\16^\3\2\2\2\20`\3\2\2\2\22k\3\2\2\2\24{\3\2\2\2\26\u0081\3\2\2"+
		"\2\30\u0089\3\2\2\2\32\u008c\3\2\2\2\34\u0097\3\2\2\2\36\u00a0\3\2\2\2"+
		" \u00b0\3\2\2\2\"\u00c6\3\2\2\2$\u00ce\3\2\2\2&\u00d0\3\2\2\2(\u00d5\3"+
		"\2\2\2*\u00d8\3\2\2\2,\60\5\b\5\2-/\5\4\3\2.-\3\2\2\2/\62\3\2\2\2\60."+
		"\3\2\2\2\60\61\3\2\2\2\61\66\3\2\2\2\62\60\3\2\2\2\63\65\5\6\4\2\64\63"+
		"\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2"+
		"\29:\5\f\7\2:;\7\2\2\3;<\b\2\1\2<\3\3\2\2\2=>\7\26\2\2>?\7\5\2\2?@\b\3"+
		"\1\2@\5\3\2\2\2AB\7\27\2\2BC\7\5\2\2CD\b\4\1\2D\7\3\2\2\2EF\7\4\2\2FG"+
		"\5\n\6\2GH\7\16\2\2HI\b\5\1\2I\t\3\2\2\2JK\7\32\2\2KO\b\6\1\2LM\7\33\2"+
		"\2MO\b\6\1\2NJ\3\2\2\2NL\3\2\2\2O\13\3\2\2\2PR\5\16\b\2QP\3\2\2\2RU\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\b\7\1\2W\r\3\2\2\2XY"+
		"\5\22\n\2YZ\b\b\1\2Z_\3\2\2\2[\\\5\20\t\2\\]\b\b\1\2]_\3\2\2\2^X\3\2\2"+
		"\2^[\3\2\2\2_\17\3\2\2\2`a\7\33\2\2ab\7\17\2\2bf\7\7\2\2cd\7\30\2\2de"+
		"\7\31\2\2eg\b\t\1\2fc\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\7\16\2\2ij\b\t\1\2"+
		"j\21\3\2\2\2km\7\32\2\2ln\5\24\13\2ml\3\2\2\2mn\3\2\2\2np\3\2\2\2oq\5"+
		"\26\f\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2rt\7\17\2\2su\5\30\r\2ts\3\2\2\2"+
		"tu\3\2\2\2uv\3\2\2\2vw\5\32\16\2wx\7\16\2\2xy\b\n\1\2y\23\3\2\2\2z|\7"+
		"\6\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080"+
		"\b\13\1\2\u0080\25\3\2\2\2\u0081\u0083\7\3\2\2\u0082\u0084\7\6\2\2\u0083"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\f\1\2\u0088\27\3\2\2\2\u0089\u008a"+
		"\7\5\2\2\u008a\u008b\b\r\1\2\u008b\31\3\2\2\2\u008c\u0091\5\34\17\2\u008d"+
		"\u008e\7\21\2\2\u008e\u0090\5\34\17\2\u008f\u008d\3\2\2\2\u0090\u0093"+
		"\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0095\b\16\1\2\u0095\33\3\2\2\2\u0096\u0098\5 \21"+
		"\2\u0097\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a"+
		"\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u009d\5\30\r\2\u009c\u009b\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\b\17\1\2\u009f\35"+
		"\3\2\2\2\u00a0\u00a5\5\34\17\2\u00a1\u00a2\7\21\2\2\u00a2\u00a4\5\34\17"+
		"\2\u00a3\u00a1\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\b\20\1\2"+
		"\u00a9\37\3\2\2\2\u00aa\u00ab\5&\24\2\u00ab\u00ac\b\21\1\2\u00ac\u00b1"+
		"\3\2\2\2\u00ad\u00ae\5\"\22\2\u00ae\u00af\b\21\1\2\u00af\u00b1\3\2\2\2"+
		"\u00b0\u00aa\3\2\2\2\u00b0\u00ad\3\2\2\2\u00b1!\3\2\2\2\u00b2\u00b4\5"+
		"(\25\2\u00b3\u00b5\5$\23\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b7\b\22\1\2\u00b7\u00c7\3\2\2\2\u00b8\u00ba\5"+
		"*\26\2\u00b9\u00bb\5$\23\2\u00ba\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00bd\b\22\1\2\u00bd\u00c7\3\2\2\2\u00be\u00bf\7"+
		"\f\2\2\u00bf\u00c0\5\36\20\2\u00c0\u00c2\7\r\2\2\u00c1\u00c3\5$\23\2\u00c2"+
		"\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\b\22"+
		"\1\2\u00c5\u00c7\3\2\2\2\u00c6\u00b2\3\2\2\2\u00c6\u00b8\3\2\2\2\u00c6"+
		"\u00be\3\2\2\2\u00c7#\3\2\2\2\u00c8\u00c9\7\22\2\2\u00c9\u00cf\b\23\1"+
		"\2\u00ca\u00cb\7\23\2\2\u00cb\u00cf\b\23\1\2\u00cc\u00cd\7\24\2\2\u00cd"+
		"\u00cf\b\23\1\2\u00ce\u00c8\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce\u00cc\3"+
		"\2\2\2\u00cf%\3\2\2\2\u00d0\u00d1\5\n\6\2\u00d1\u00d2\7\25\2\2\u00d2\u00d3"+
		"\5\"\22\2\u00d3\u00d4\b\24\1\2\u00d4\'\3\2\2\2\u00d5\u00d6\7\33\2\2\u00d6"+
		"\u00d7\b\25\1\2\u00d7)\3\2\2\2\u00d8\u00dc\7\32\2\2\u00d9\u00db\7\6\2"+
		"\2\u00da\u00d9\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd"+
		"\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\b\26\1\2"+
		"\u00e0+\3\2\2\2\30\60\66NS^fmpt}\u0085\u0091\u0099\u009c\u00a5\u00b0\u00b4"+
		"\u00ba\u00c2\u00c6\u00ce\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}