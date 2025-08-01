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
		T__0=1, T__1=2, RULE=3, TOKEN=4, STRING=5, WS=6;
	public static final int
		RULE_schema = 0, RULE_node = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"schema", "node"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "RULE", "TOKEN", "STRING", "WS"
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
			setState(4);
			node();
			setState(5);
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
		public TerminalNode RULE() { return getToken(SchemaParser.RULE, 0); }
		public List<NodeContext> node() {
			return getRuleContexts(NodeContext.class);
		}
		public NodeContext node(int i) {
			return getRuleContext(NodeContext.class,i);
		}
		public TerminalNode TOKEN() { return getToken(SchemaParser.TOKEN, 0); }
		public TerminalNode STRING() { return getToken(SchemaParser.STRING, 0); }
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
		int _la;
		try {
			setState(21);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(7);
				match(T__0);
				setState(8);
				match(RULE);
				setState(10); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(9);
					node();
					}
					}
					setState(12); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__0 || _la==STRING );
				setState(14);
				match(T__1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(16);
				match(T__0);
				setState(17);
				match(TOKEN);
				setState(18);
				match(STRING);
				setState(19);
				match(T__1);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(20);
				match(STRING);
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

	public static final String _serializedATN =
		"\u0004\u0001\u0006\u0018\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0004\u0001\u000b\b\u0001\u000b\u0001\f\u0001\f\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u0016\b\u0001\u0001\u0001\u0000\u0000\u0002\u0000\u0002\u0000\u0000\u0018"+
		"\u0000\u0004\u0001\u0000\u0000\u0000\u0002\u0015\u0001\u0000\u0000\u0000"+
		"\u0004\u0005\u0003\u0002\u0001\u0000\u0005\u0006\u0005\u0000\u0000\u0001"+
		"\u0006\u0001\u0001\u0000\u0000\u0000\u0007\b\u0005\u0001\u0000\u0000\b"+
		"\n\u0005\u0003\u0000\u0000\t\u000b\u0003\u0002\u0001\u0000\n\t\u0001\u0000"+
		"\u0000\u0000\u000b\f\u0001\u0000\u0000\u0000\f\n\u0001\u0000\u0000\u0000"+
		"\f\r\u0001\u0000\u0000\u0000\r\u000e\u0001\u0000\u0000\u0000\u000e\u000f"+
		"\u0005\u0002\u0000\u0000\u000f\u0016\u0001\u0000\u0000\u0000\u0010\u0011"+
		"\u0005\u0001\u0000\u0000\u0011\u0012\u0005\u0004\u0000\u0000\u0012\u0013"+
		"\u0005\u0005\u0000\u0000\u0013\u0016\u0005\u0002\u0000\u0000\u0014\u0016"+
		"\u0005\u0005\u0000\u0000\u0015\u0007\u0001\u0000\u0000\u0000\u0015\u0010"+
		"\u0001\u0000\u0000\u0000\u0015\u0014\u0001\u0000\u0000\u0000\u0016\u0003"+
		"\u0001\u0000\u0000\u0000\u0002\f\u0015";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}