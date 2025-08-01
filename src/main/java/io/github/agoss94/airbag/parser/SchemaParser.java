// Generated from Schema.g4 by ANTLR 4.13.1
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
public class SchemaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, ERROR=3, RULE=4, TOKEN=5, INT=6, STRING=7, WS=8;
	public static final int
		RULE_schema = 0, RULE_node = 1, RULE_rule = 2, RULE_token = 3, RULE_error = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"schema", "node", "rule", "token", "error"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'<error>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "ERROR", "RULE", "TOKEN", "INT", "STRING", "WS"
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
	public String getGrammarFileName() { return "Schema.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SchemaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemaContext extends ParserRuleContext {
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SchemaParser.EOF, 0); }
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).exitSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaVisitor ) return ((SchemaVisitor<? extends T>)visitor).visitSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_schema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			node();
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
	public static class NodeContext extends ParserRuleContext {
		public RuleContext rule_() {
			return getRuleContext(RuleContext.class,0);
		}
		public TokenContext token() {
			return getRuleContext(TokenContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).enterNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).exitNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaVisitor ) return ((SchemaVisitor<? extends T>)visitor).visitNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_node);
		try {
			setState(16);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(13);
				rule_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
				token();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(15);
				error();
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

	@SuppressWarnings("CheckReturnValue")
	public static class RuleContext extends ParserRuleContext {
		public Token index;
		public TerminalNode INT() { return getToken(SchemaParser.INT, 0); }
		public TerminalNode RULE() { return getToken(SchemaParser.RULE, 0); }
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public RuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).enterRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).exitRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaVisitor ) return ((SchemaVisitor<? extends T>)visitor).visitRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RuleContext rule_() throws RecognitionException {
		RuleContext _localctx = new RuleContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_rule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(T__0);
			setState(19);
			((RuleContext)_localctx).index = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==RULE || _la==INT) ) {
				((RuleContext)_localctx).index = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				node();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 || _la==STRING );
			setState(25);
			match(T__1);
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
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
	 
		public TokenContext() { }
		public void copyFrom(TokenContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SymbolicContext extends TokenContext {
		public Token index;
		public TerminalNode STRING() { return getToken(SchemaParser.STRING, 0); }
		public TerminalNode INT() { return getToken(SchemaParser.INT, 0); }
		public TerminalNode TOKEN() { return getToken(SchemaParser.TOKEN, 0); }
		public SymbolicContext(TokenContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).enterSymbolic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).exitSymbolic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaVisitor ) return ((SchemaVisitor<? extends T>)visitor).visitSymbolic(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends TokenContext {
		public TerminalNode STRING() { return getToken(SchemaParser.STRING, 0); }
		public LiteralContext(TokenContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaVisitor ) return ((SchemaVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_token);
		int _la;
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new SymbolicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(T__0);
				setState(28);
				((SymbolicContext)_localctx).index = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TOKEN || _la==INT) ) {
					((SymbolicContext)_localctx).index = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(29);
				match(STRING);
				setState(30);
				match(T__1);
				}
				break;
			case STRING:
				_localctx = new LiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				match(STRING);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ErrorContext extends ParserRuleContext {
		public TerminalNode ERROR() { return getToken(SchemaParser.ERROR, 0); }
		public TokenContext token() {
			return getRuleContext(TokenContext.class,0);
		}
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SchemaListener ) ((SchemaListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SchemaVisitor ) return ((SchemaVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			match(ERROR);
			setState(36);
			token();
			setState(37);
			match(T__1);
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
		"\u0004\u0001\b(\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u0011\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002\u0016"+
		"\b\u0002\u000b\u0002\f\u0002\u0017\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003!\b\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0000\u0000\u0005\u0000\u0002\u0004\u0006\b\u0000\u0002\u0002\u0000\u0004"+
		"\u0004\u0006\u0006\u0001\u0000\u0005\u0006&\u0000\n\u0001\u0000\u0000"+
		"\u0000\u0002\u0010\u0001\u0000\u0000\u0000\u0004\u0012\u0001\u0000\u0000"+
		"\u0000\u0006 \u0001\u0000\u0000\u0000\b\"\u0001\u0000\u0000\u0000\n\u000b"+
		"\u0003\u0002\u0001\u0000\u000b\f\u0005\u0000\u0000\u0001\f\u0001\u0001"+
		"\u0000\u0000\u0000\r\u0011\u0003\u0004\u0002\u0000\u000e\u0011\u0003\u0006"+
		"\u0003\u0000\u000f\u0011\u0003\b\u0004\u0000\u0010\r\u0001\u0000\u0000"+
		"\u0000\u0010\u000e\u0001\u0000\u0000\u0000\u0010\u000f\u0001\u0000\u0000"+
		"\u0000\u0011\u0003\u0001\u0000\u0000\u0000\u0012\u0013\u0005\u0001\u0000"+
		"\u0000\u0013\u0015\u0007\u0000\u0000\u0000\u0014\u0016\u0003\u0002\u0001"+
		"\u0000\u0015\u0014\u0001\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000"+
		"\u0000\u0017\u0015\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000"+
		"\u0000\u0018\u0019\u0001\u0000\u0000\u0000\u0019\u001a\u0005\u0002\u0000"+
		"\u0000\u001a\u0005\u0001\u0000\u0000\u0000\u001b\u001c\u0005\u0001\u0000"+
		"\u0000\u001c\u001d\u0007\u0001\u0000\u0000\u001d\u001e\u0005\u0007\u0000"+
		"\u0000\u001e!\u0005\u0002\u0000\u0000\u001f!\u0005\u0007\u0000\u0000 "+
		"\u001b\u0001\u0000\u0000\u0000 \u001f\u0001\u0000\u0000\u0000!\u0007\u0001"+
		"\u0000\u0000\u0000\"#\u0005\u0001\u0000\u0000#$\u0005\u0003\u0000\u0000"+
		"$%\u0003\u0006\u0003\u0000%&\u0005\u0002\u0000\u0000&\t\u0001\u0000\u0000"+
		"\u0000\u0003\u0010\u0017 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}