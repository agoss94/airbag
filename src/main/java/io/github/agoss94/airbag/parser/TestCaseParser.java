// Generated from TestCaseParser.g4 by ANTLR 4.13.1
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
public class TestCaseParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BLOCK=1, TESTCASE=2, INPUT=3, TOKEN=4, TREE=5, LANGLE=6, RANGLE=7, COMMENT=8, 
		LONG_COMMENT=9, WHITESPACE=10, I_COMMENT=11, I_LONG_COMMENT=12, I_WHITESPACE=13, 
		TK_COMMENT=14, TK_LONG_COMMENT=15, TK_WHITESPACE=16, RULE=17, LPAREN=18, 
		RPAREN=19, T_COMMENT=20, T_LONG_COMMENT=21, T_WHITESPACE=22;
	public static final int
		RULE_file = 0, RULE_input = 1, RULE_token = 2, RULE_tree = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "input", "token", "tree"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'testcase'", "'input'", "'token'", "'tree'", "'{'", "'}'", 
			null, null, null, null, null, null, null, null, null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BLOCK", "TESTCASE", "INPUT", "TOKEN", "TREE", "LANGLE", "RANGLE", 
			"COMMENT", "LONG_COMMENT", "WHITESPACE", "I_COMMENT", "I_LONG_COMMENT", 
			"I_WHITESPACE", "TK_COMMENT", "TK_LONG_COMMENT", "TK_WHITESPACE", "RULE", 
			"LPAREN", "RPAREN", "T_COMMENT", "T_LONG_COMMENT", "T_WHITESPACE"
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
	public String getGrammarFileName() { return "TestCaseParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestCaseParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public TerminalNode TESTCASE() { return getToken(TestCaseParser.TESTCASE, 0); }
		public TerminalNode LANGLE() { return getToken(TestCaseParser.LANGLE, 0); }
		public InputContext input() {
			return getRuleContext(InputContext.class,0);
		}
		public TerminalNode RANGLE() { return getToken(TestCaseParser.RANGLE, 0); }
		public TerminalNode EOF() { return getToken(TestCaseParser.EOF, 0); }
		public TokenContext token() {
			return getRuleContext(TokenContext.class,0);
		}
		public TreeContext tree() {
			return getRuleContext(TreeContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestCaseParserVisitor ) return ((TestCaseParserVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(TESTCASE);
			setState(9);
			match(LANGLE);
			setState(10);
			input();
			setState(12);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TOKEN) {
				{
				setState(11);
				token();
				}
			}

			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TREE) {
				{
				setState(14);
				tree();
				}
			}

			setState(17);
			match(RANGLE);
			setState(18);
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
	public static class InputContext extends ParserRuleContext {
		public TerminalNode INPUT() { return getToken(TestCaseParser.INPUT, 0); }
		public TerminalNode BLOCK() { return getToken(TestCaseParser.BLOCK, 0); }
		public InputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_input; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).enterInput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).exitInput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestCaseParserVisitor ) return ((TestCaseParserVisitor<? extends T>)visitor).visitInput(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InputContext input() throws RecognitionException {
		InputContext _localctx = new InputContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_input);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(INPUT);
			setState(21);
			match(BLOCK);
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
		public TerminalNode TOKEN() { return getToken(TestCaseParser.TOKEN, 0); }
		public TerminalNode BLOCK() { return getToken(TestCaseParser.BLOCK, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestCaseParserVisitor ) return ((TestCaseParserVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_token);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(TOKEN);
			setState(24);
			match(BLOCK);
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
	public static class TreeContext extends ParserRuleContext {
		public TerminalNode TREE() { return getToken(TestCaseParser.TREE, 0); }
		public TerminalNode BLOCK() { return getToken(TestCaseParser.BLOCK, 0); }
		public TerminalNode LPAREN() { return getToken(TestCaseParser.LPAREN, 0); }
		public TerminalNode RULE() { return getToken(TestCaseParser.RULE, 0); }
		public TerminalNode RPAREN() { return getToken(TestCaseParser.RPAREN, 0); }
		public TreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).enterTree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseParserListener ) ((TestCaseParserListener)listener).exitTree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TestCaseParserVisitor ) return ((TestCaseParserVisitor<? extends T>)visitor).visitTree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TreeContext tree() throws RecognitionException {
		TreeContext _localctx = new TreeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tree);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(TREE);
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(27);
				match(LPAREN);
				setState(28);
				match(RULE);
				setState(29);
				match(RPAREN);
				}
			}

			setState(32);
			match(BLOCK);
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
		"\u0004\u0001\u0016#\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0003\u0000\r\b\u0000\u0001\u0000\u0003\u0000\u0010"+
		"\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003\u001f\b\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0000\u0000\u0004\u0000\u0002\u0004\u0006\u0000\u0000!\u0000\b"+
		"\u0001\u0000\u0000\u0000\u0002\u0014\u0001\u0000\u0000\u0000\u0004\u0017"+
		"\u0001\u0000\u0000\u0000\u0006\u001a\u0001\u0000\u0000\u0000\b\t\u0005"+
		"\u0002\u0000\u0000\t\n\u0005\u0006\u0000\u0000\n\f\u0003\u0002\u0001\u0000"+
		"\u000b\r\u0003\u0004\u0002\u0000\f\u000b\u0001\u0000\u0000\u0000\f\r\u0001"+
		"\u0000\u0000\u0000\r\u000f\u0001\u0000\u0000\u0000\u000e\u0010\u0003\u0006"+
		"\u0003\u0000\u000f\u000e\u0001\u0000\u0000\u0000\u000f\u0010\u0001\u0000"+
		"\u0000\u0000\u0010\u0011\u0001\u0000\u0000\u0000\u0011\u0012\u0005\u0007"+
		"\u0000\u0000\u0012\u0013\u0005\u0000\u0000\u0001\u0013\u0001\u0001\u0000"+
		"\u0000\u0000\u0014\u0015\u0005\u0003\u0000\u0000\u0015\u0016\u0005\u0001"+
		"\u0000\u0000\u0016\u0003\u0001\u0000\u0000\u0000\u0017\u0018\u0005\u0004"+
		"\u0000\u0000\u0018\u0019\u0005\u0001\u0000\u0000\u0019\u0005\u0001\u0000"+
		"\u0000\u0000\u001a\u001e\u0005\u0005\u0000\u0000\u001b\u001c\u0005\u0012"+
		"\u0000\u0000\u001c\u001d\u0005\u0011\u0000\u0000\u001d\u001f\u0005\u0013"+
		"\u0000\u0000\u001e\u001b\u0001\u0000\u0000\u0000\u001e\u001f\u0001\u0000"+
		"\u0000\u0000\u001f \u0001\u0000\u0000\u0000 !\u0005\u0001\u0000\u0000"+
		"!\u0007\u0001\u0000\u0000\u0000\u0003\f\u000f\u001e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}