// Generated from Airbag.g4 by ANTLR 4.13.1
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
public class AirbagParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, RULE=11, TOKEN=12, INT=13, STRING=14, WS=15;
	public static final int
		RULE_schema = 0, RULE_node = 1, RULE_list = 2, RULE_token = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"schema", "node", "list", "token"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'[@'", "','", "':'", "'='", "',<'", "'>'", "'channel='", 
			"']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "RULE", 
			"TOKEN", "INT", "STRING", "WS"
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
	public String getGrammarFileName() { return "Airbag.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AirbagParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemaContext extends ParserRuleContext {
		public NodeContext node() {
			return getRuleContext(NodeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(AirbagParser.EOF, 0); }
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).exitSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AirbagVisitor ) return ((AirbagVisitor<? extends T>)visitor).visitSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_schema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			node();
			setState(9);
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
		public NodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_node; }
	 
		public NodeContext() { }
		public void copyFrom(NodeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RuleNodeContext extends NodeContext {
		public TerminalNode RULE() { return getToken(AirbagParser.RULE, 0); }
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public RuleNodeContext(NodeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).enterRuleNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).exitRuleNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AirbagVisitor ) return ((AirbagVisitor<? extends T>)visitor).visitRuleNode(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TokenNodeContext extends NodeContext {
		public TerminalNode TOKEN() { return getToken(AirbagParser.TOKEN, 0); }
		public TerminalNode STRING() { return getToken(AirbagParser.STRING, 0); }
		public TokenNodeContext(NodeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).enterTokenNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).exitTokenNode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AirbagVisitor ) return ((AirbagVisitor<? extends T>)visitor).visitTokenNode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeContext node() throws RecognitionException {
		NodeContext _localctx = new NodeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_node);
		int _la;
		try {
			setState(24);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new RuleNodeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(11);
				match(T__0);
				setState(12);
				match(RULE);
				setState(14); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(13);
					node();
					}
					}
					setState(16); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__0 );
				setState(18);
				match(T__1);
				}
				break;
			case 2:
				_localctx = new TokenNodeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(20);
				match(T__0);
				setState(21);
				match(TOKEN);
				setState(22);
				match(STRING);
				setState(23);
				match(T__1);
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
	public static class ListContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AirbagParser.EOF, 0); }
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).exitList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AirbagVisitor ) return ((AirbagVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				token();
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(31);
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
		public Token text;
		public Token type;
		public Token channel;
		public Token line;
		public Token charPositionInLine;
		public List<TerminalNode> INT() { return getTokens(AirbagParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(AirbagParser.INT, i);
		}
		public TerminalNode STRING() { return getToken(AirbagParser.STRING, 0); }
		public TerminalNode TOKEN() { return getToken(AirbagParser.TOKEN, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AirbagListener ) ((AirbagListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AirbagVisitor ) return ((AirbagVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_token);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(T__2);
			setState(34);
			((TokenContext)_localctx).tokenIndex = match(INT);
			setState(35);
			match(T__3);
			setState(36);
			((TokenContext)_localctx).startIndex = match(INT);
			setState(37);
			match(T__4);
			setState(38);
			((TokenContext)_localctx).stopIndex = match(INT);
			setState(39);
			match(T__5);
			setState(40);
			((TokenContext)_localctx).text = match(STRING);
			setState(41);
			match(T__6);
			setState(42);
			((TokenContext)_localctx).type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==TOKEN || _la==INT) ) {
				((TokenContext)_localctx).type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(43);
			match(T__7);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(44);
				match(T__8);
				setState(45);
				((TokenContext)_localctx).channel = match(INT);
				}
			}

			setState(48);
			match(T__3);
			setState(49);
			((TokenContext)_localctx).line = match(INT);
			setState(50);
			match(T__4);
			setState(51);
			((TokenContext)_localctx).charPositionInLine = match(INT);
			setState(52);
			match(T__9);
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
		"\u0004\u0001\u000f7\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001\u000f\b\u0001\u000b"+
		"\u0001\f\u0001\u0010\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u0019\b\u0001\u0001\u0002\u0004\u0002\u001c"+
		"\b\u0002\u000b\u0002\f\u0002\u001d\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003/\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0000\u0000\u0004\u0000\u0002\u0004"+
		"\u0006\u0000\u0001\u0001\u0000\f\r6\u0000\b\u0001\u0000\u0000\u0000\u0002"+
		"\u0018\u0001\u0000\u0000\u0000\u0004\u001b\u0001\u0000\u0000\u0000\u0006"+
		"!\u0001\u0000\u0000\u0000\b\t\u0003\u0002\u0001\u0000\t\n\u0005\u0000"+
		"\u0000\u0001\n\u0001\u0001\u0000\u0000\u0000\u000b\f\u0005\u0001\u0000"+
		"\u0000\f\u000e\u0005\u000b\u0000\u0000\r\u000f\u0003\u0002\u0001\u0000"+
		"\u000e\r\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000\u0000\u0000\u0010"+
		"\u000e\u0001\u0000\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000\u0011"+
		"\u0012\u0001\u0000\u0000\u0000\u0012\u0013\u0005\u0002\u0000\u0000\u0013"+
		"\u0019\u0001\u0000\u0000\u0000\u0014\u0015\u0005\u0001\u0000\u0000\u0015"+
		"\u0016\u0005\f\u0000\u0000\u0016\u0017\u0005\u000e\u0000\u0000\u0017\u0019"+
		"\u0005\u0002\u0000\u0000\u0018\u000b\u0001\u0000\u0000\u0000\u0018\u0014"+
		"\u0001\u0000\u0000\u0000\u0019\u0003\u0001\u0000\u0000\u0000\u001a\u001c"+
		"\u0003\u0006\u0003\u0000\u001b\u001a\u0001\u0000\u0000\u0000\u001c\u001d"+
		"\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e"+
		"\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000\u0000\u0000\u001f \u0005"+
		"\u0000\u0000\u0001 \u0005\u0001\u0000\u0000\u0000!\"\u0005\u0003\u0000"+
		"\u0000\"#\u0005\r\u0000\u0000#$\u0005\u0004\u0000\u0000$%\u0005\r\u0000"+
		"\u0000%&\u0005\u0005\u0000\u0000&\'\u0005\r\u0000\u0000\'(\u0005\u0006"+
		"\u0000\u0000()\u0005\u000e\u0000\u0000)*\u0005\u0007\u0000\u0000*+\u0007"+
		"\u0000\u0000\u0000+.\u0005\b\u0000\u0000,-\u0005\t\u0000\u0000-/\u0005"+
		"\r\u0000\u0000.,\u0001\u0000\u0000\u0000./\u0001\u0000\u0000\u0000/0\u0001"+
		"\u0000\u0000\u000001\u0005\u0004\u0000\u000012\u0005\r\u0000\u000023\u0005"+
		"\u0005\u0000\u000034\u0005\r\u0000\u000045\u0005\n\u0000\u00005\u0007"+
		"\u0001\u0000\u0000\u0000\u0004\u0010\u0018\u001d.";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}