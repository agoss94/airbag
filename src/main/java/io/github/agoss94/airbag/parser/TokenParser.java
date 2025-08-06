// Generated from Token.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TokenParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		INT=10, TOKEN=11, STRING=12, COMMENT=13, LONG_COMMENT=14, WS=15;
	public static final int
		RULE_tokenList = 0, RULE_singleToken = 1, RULE_token = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"tokenList", "singleToken", "token"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "'@'", "','", "':'", "'='", "'<'", "'>'", "'channel'", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "INT", "TOKEN", 
			"STRING", "COMMENT", "LONG_COMMENT", "WS"
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
	public String getGrammarFileName() { return "Token.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TokenParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TokenListContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TokenParser.EOF, 0); }
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public TokenListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tokenList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TokenListener ) ((TokenListener)listener).enterTokenList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TokenListener ) ((TokenListener)listener).exitTokenList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TokenVisitor ) return ((TokenVisitor<? extends T>)visitor).visitTokenList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenListContext tokenList() throws RecognitionException {
		TokenListContext _localctx = new TokenListContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tokenList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6);
				token();
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(11);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class SingleTokenContext extends ParserRuleContext {
		public TokenContext token() {
			return getRuleContext(TokenContext.class,0);
		}
		public TerminalNode EOF() { return getToken(TokenParser.EOF, 0); }
		public SingleTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TokenListener ) ((TokenListener)listener).enterSingleToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TokenListener ) ((TokenListener)listener).exitSingleToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TokenVisitor ) return ((TokenVisitor<? extends T>)visitor).visitSingleToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleTokenContext singleToken() throws RecognitionException {
		SingleTokenContext _localctx = new SingleTokenContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_singleToken);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			token();
			setState(14);
			match(EOF);
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

	@SuppressWarnings("CheckReturnValue")
	public static class TokenContext extends ParserRuleContext {
		public Token tokenIndex;
		public Token startIndex;
		public Token stopIndex;
		public Token txt;
		public Token type;
		public Token channel;
		public Token line;
		public Token charPositionInLine;
		public List<TerminalNode> INT() { return getTokens(TokenParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(TokenParser.INT, i);
		}
		public List<TerminalNode> STRING() { return getTokens(TokenParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(TokenParser.STRING, i);
		}
		public TerminalNode TOKEN() { return getToken(TokenParser.TOKEN, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TokenListener ) ((TokenListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TokenListener ) ((TokenListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TokenVisitor ) return ((TokenVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_token);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(17);
			match(T__1);
			setState(18);
			((TokenContext)_localctx).tokenIndex = match(INT);
			setState(19);
			match(T__2);
			setState(20);
			((TokenContext)_localctx).startIndex = match(INT);
			setState(21);
			match(T__3);
			setState(22);
			((TokenContext)_localctx).stopIndex = match(INT);
			setState(23);
			match(T__4);
			setState(24);
			((TokenContext)_localctx).txt = match(STRING);
			setState(25);
			match(T__2);
			setState(26);
			match(T__5);
			setState(27);
			((TokenContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) ) {
				((TokenContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(28);
			match(T__6);
			setState(29);
			match(T__2);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(30);
				match(T__7);
				setState(31);
				match(T__4);
				setState(32);
				((TokenContext)_localctx).channel = match(INT);
				setState(33);
				match(T__2);
				}
			}

			setState(36);
			((TokenContext)_localctx).line = match(INT);
			setState(37);
			match(T__3);
			setState(38);
			((TokenContext)_localctx).charPositionInLine = match(INT);
			setState(39);
			match(T__8);
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
		"\u0004\u0001\u000f*\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0004\u0000\b\b\u0000\u000b\u0000\f\u0000"+
		"\t\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"#\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0000\u0000\u0003\u0000\u0002\u0004\u0000\u0001\u0001\u0000"+
		"\n\f(\u0000\u0007\u0001\u0000\u0000\u0000\u0002\r\u0001\u0000\u0000\u0000"+
		"\u0004\u0010\u0001\u0000\u0000\u0000\u0006\b\u0003\u0004\u0002\u0000\u0007"+
		"\u0006\u0001\u0000\u0000\u0000\b\t\u0001\u0000\u0000\u0000\t\u0007\u0001"+
		"\u0000\u0000\u0000\t\n\u0001\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000"+
		"\u0000\u000b\f\u0005\u0000\u0000\u0001\f\u0001\u0001\u0000\u0000\u0000"+
		"\r\u000e\u0003\u0004\u0002\u0000\u000e\u000f\u0005\u0000\u0000\u0001\u000f"+
		"\u0003\u0001\u0000\u0000\u0000\u0010\u0011\u0005\u0001\u0000\u0000\u0011"+
		"\u0012\u0005\u0002\u0000\u0000\u0012\u0013\u0005\n\u0000\u0000\u0013\u0014"+
		"\u0005\u0003\u0000\u0000\u0014\u0015\u0005\n\u0000\u0000\u0015\u0016\u0005"+
		"\u0004\u0000\u0000\u0016\u0017\u0005\n\u0000\u0000\u0017\u0018\u0005\u0005"+
		"\u0000\u0000\u0018\u0019\u0005\f\u0000\u0000\u0019\u001a\u0005\u0003\u0000"+
		"\u0000\u001a\u001b\u0005\u0006\u0000\u0000\u001b\u001c\u0007\u0000\u0000"+
		"\u0000\u001c\u001d\u0005\u0007\u0000\u0000\u001d\"\u0005\u0003\u0000\u0000"+
		"\u001e\u001f\u0005\b\u0000\u0000\u001f \u0005\u0005\u0000\u0000 !\u0005"+
		"\n\u0000\u0000!#\u0005\u0003\u0000\u0000\"\u001e\u0001\u0000\u0000\u0000"+
		"\"#\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$%\u0005\n\u0000"+
		"\u0000%&\u0005\u0004\u0000\u0000&\'\u0005\n\u0000\u0000\'(\u0005\t\u0000"+
		"\u0000(\u0005\u0001\u0000\u0000\u0000\u0002\t\"";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}