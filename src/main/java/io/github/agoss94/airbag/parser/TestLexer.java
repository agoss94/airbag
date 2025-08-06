// Generated from Test.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TestLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, INPUT=5, TOKEN=6, TREE=7, COMMENT=8, LONG_COMMENT=9, 
		WHITESPACE=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "INPUT", "TOKEN", "TREE", "COMMENT", 
			"LONG_COMMENT", "WHITESPACE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'grammar'", "'test'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "INPUT", "TOKEN", "TREE", "COMMENT", "LONG_COMMENT", 
			"WHITESPACE"
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


	public TestLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Test.g4"; }

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
		"\u0004\u0000\n}\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"0\b\u0004\u0001\u0004\u0001\u0004\u0005\u00044\b\u0004\n\u0004\f\u0004"+
		"7\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005B\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005F\b\u0005\n\u0005\f\u0005I\t\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006S\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0005\u0006W\b\u0006\n\u0006\f\u0006Z\t\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007b\b\u0007"+
		"\n\u0007\f\u0007e\t\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0005\bm\b\b\n\b\f\bp\t\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0004\nz\b\n\u000b\n\f\n{\u00055GXcn\u0000"+
		"\u000b\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u0000\u0001\u0000\u0001\u0003\u0000"+
		"\t\n\f\r  \u0084\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0001\u0017\u0001\u0000\u0000\u0000\u0003\u001f\u0001\u0000\u0000"+
		"\u0000\u0005$\u0001\u0000\u0000\u0000\u0007&\u0001\u0000\u0000\u0000\t"+
		"(\u0001\u0000\u0000\u0000\u000b:\u0001\u0000\u0000\u0000\rL\u0001\u0000"+
		"\u0000\u0000\u000f]\u0001\u0000\u0000\u0000\u0011h\u0001\u0000\u0000\u0000"+
		"\u0013t\u0001\u0000\u0000\u0000\u0015y\u0001\u0000\u0000\u0000\u0017\u0018"+
		"\u0005g\u0000\u0000\u0018\u0019\u0005r\u0000\u0000\u0019\u001a\u0005a"+
		"\u0000\u0000\u001a\u001b\u0005m\u0000\u0000\u001b\u001c\u0005m\u0000\u0000"+
		"\u001c\u001d\u0005a\u0000\u0000\u001d\u001e\u0005r\u0000\u0000\u001e\u0002"+
		"\u0001\u0000\u0000\u0000\u001f \u0005t\u0000\u0000 !\u0005e\u0000\u0000"+
		"!\"\u0005s\u0000\u0000\"#\u0005t\u0000\u0000#\u0004\u0001\u0000\u0000"+
		"\u0000$%\u0005{\u0000\u0000%\u0006\u0001\u0000\u0000\u0000&\'\u0005}\u0000"+
		"\u0000\'\b\u0001\u0000\u0000\u0000()\u0005i\u0000\u0000)*\u0005n\u0000"+
		"\u0000*+\u0005p\u0000\u0000+,\u0005u\u0000\u0000,-\u0005t\u0000\u0000"+
		"-/\u0001\u0000\u0000\u0000.0\u0003\u0015\n\u0000/.\u0001\u0000\u0000\u0000"+
		"/0\u0001\u0000\u0000\u000001\u0001\u0000\u0000\u000015\u0005{\u0000\u0000"+
		"24\t\u0000\u0000\u000032\u0001\u0000\u0000\u000047\u0001\u0000\u0000\u0000"+
		"56\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u000068\u0001\u0000\u0000"+
		"\u000075\u0001\u0000\u0000\u000089\u0005}\u0000\u00009\n\u0001\u0000\u0000"+
		"\u0000:;\u0005t\u0000\u0000;<\u0005o\u0000\u0000<=\u0005k\u0000\u0000"+
		"=>\u0005e\u0000\u0000>?\u0005n\u0000\u0000?A\u0001\u0000\u0000\u0000@"+
		"B\u0003\u0015\n\u0000A@\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000"+
		"BC\u0001\u0000\u0000\u0000CG\u0005{\u0000\u0000DF\t\u0000\u0000\u0000"+
		"ED\u0001\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000"+
		"\u0000GE\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000IG\u0001\u0000"+
		"\u0000\u0000JK\u0005}\u0000\u0000K\f\u0001\u0000\u0000\u0000LM\u0005t"+
		"\u0000\u0000MN\u0005r\u0000\u0000NO\u0005e\u0000\u0000OP\u0005e\u0000"+
		"\u0000PR\u0001\u0000\u0000\u0000QS\u0003\u0015\n\u0000RQ\u0001\u0000\u0000"+
		"\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TX\u0005{\u0000"+
		"\u0000UW\t\u0000\u0000\u0000VU\u0001\u0000\u0000\u0000WZ\u0001\u0000\u0000"+
		"\u0000XY\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000Y[\u0001\u0000"+
		"\u0000\u0000ZX\u0001\u0000\u0000\u0000[\\\u0005}\u0000\u0000\\\u000e\u0001"+
		"\u0000\u0000\u0000]^\u0005/\u0000\u0000^_\u0005/\u0000\u0000_c\u0001\u0000"+
		"\u0000\u0000`b\t\u0000\u0000\u0000a`\u0001\u0000\u0000\u0000be\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000df\u0001"+
		"\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fg\u0005\n\u0000\u0000g\u0010"+
		"\u0001\u0000\u0000\u0000hi\u0005/\u0000\u0000ij\u0005*\u0000\u0000jn\u0001"+
		"\u0000\u0000\u0000km\t\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000mp\u0001"+
		"\u0000\u0000\u0000no\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"oq\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000qr\u0005*\u0000\u0000"+
		"rs\u0005/\u0000\u0000s\u0012\u0001\u0000\u0000\u0000tu\u0003\u0015\n\u0000"+
		"uv\u0001\u0000\u0000\u0000vw\u0006\t\u0000\u0000w\u0014\u0001\u0000\u0000"+
		"\u0000xz\u0007\u0000\u0000\u0000yx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|\u0016"+
		"\u0001\u0000\u0000\u0000\n\u0000/5AGRXcn{\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}