// Generated from Airbag.g4 by ANTLR 4.13.1
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
public class AirbagLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, RULE=11, TOKEN=12, INT=13, STRING=14, WS=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "RULE", "TOKEN", "INT", "STRING", "WS", "DIGIT", "ESC"
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


	public AirbagLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Airbag.g4"; }

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
		"\u0004\u0000\u000fl\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0005\nC\b\n\n\n\f\nF\t\n\u0001\u000b\u0001\u000b\u0005\u000bJ\b\u000b"+
		"\n\u000b\f\u000bM\t\u000b\u0001\f\u0003\fP\b\f\u0001\f\u0004\fS\b\f\u000b"+
		"\f\f\fT\u0001\r\u0001\r\u0001\r\u0005\rZ\b\r\n\r\f\r]\t\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0004\u000eb\b\u000e\u000b\u000e\f\u000ec\u0001\u000e\u0001"+
		"\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"[\u0000\u0011\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0000!\u0000\u0001\u0000\u0006\u0001"+
		"\u0000az\u0004\u000009AZ__az\u0001\u0000AZ\u0003\u0000\t\n\r\r  \u0001"+
		"\u000009\u0005\u0000\'\'ffnnrrttp\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0001#\u0001"+
		"\u0000\u0000\u0000\u0003%\u0001\u0000\u0000\u0000\u0005\'\u0001\u0000"+
		"\u0000\u0000\u0007*\u0001\u0000\u0000\u0000\t,\u0001\u0000\u0000\u0000"+
		"\u000b.\u0001\u0000\u0000\u0000\r0\u0001\u0000\u0000\u0000\u000f3\u0001"+
		"\u0000\u0000\u0000\u00115\u0001\u0000\u0000\u0000\u0013>\u0001\u0000\u0000"+
		"\u0000\u0015@\u0001\u0000\u0000\u0000\u0017G\u0001\u0000\u0000\u0000\u0019"+
		"O\u0001\u0000\u0000\u0000\u001bV\u0001\u0000\u0000\u0000\u001da\u0001"+
		"\u0000\u0000\u0000\u001fg\u0001\u0000\u0000\u0000!i\u0001\u0000\u0000"+
		"\u0000#$\u0005(\u0000\u0000$\u0002\u0001\u0000\u0000\u0000%&\u0005)\u0000"+
		"\u0000&\u0004\u0001\u0000\u0000\u0000\'(\u0005[\u0000\u0000()\u0005@\u0000"+
		"\u0000)\u0006\u0001\u0000\u0000\u0000*+\u0005,\u0000\u0000+\b\u0001\u0000"+
		"\u0000\u0000,-\u0005:\u0000\u0000-\n\u0001\u0000\u0000\u0000./\u0005="+
		"\u0000\u0000/\f\u0001\u0000\u0000\u000001\u0005,\u0000\u000012\u0005<"+
		"\u0000\u00002\u000e\u0001\u0000\u0000\u000034\u0005>\u0000\u00004\u0010"+
		"\u0001\u0000\u0000\u000056\u0005c\u0000\u000067\u0005h\u0000\u000078\u0005"+
		"a\u0000\u000089\u0005n\u0000\u00009:\u0005n\u0000\u0000:;\u0005e\u0000"+
		"\u0000;<\u0005l\u0000\u0000<=\u0005=\u0000\u0000=\u0012\u0001\u0000\u0000"+
		"\u0000>?\u0005]\u0000\u0000?\u0014\u0001\u0000\u0000\u0000@D\u0007\u0000"+
		"\u0000\u0000AC\u0007\u0001\u0000\u0000BA\u0001\u0000\u0000\u0000CF\u0001"+
		"\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000"+
		"E\u0016\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000GK\u0007\u0002"+
		"\u0000\u0000HJ\u0007\u0001\u0000\u0000IH\u0001\u0000\u0000\u0000JM\u0001"+
		"\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000"+
		"L\u0018\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000NP\u0005-\u0000"+
		"\u0000ON\u0001\u0000\u0000\u0000OP\u0001\u0000\u0000\u0000PR\u0001\u0000"+
		"\u0000\u0000QS\u0003\u001f\u000f\u0000RQ\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000"+
		"U\u001a\u0001\u0000\u0000\u0000V[\u0005\'\u0000\u0000WZ\u0003!\u0010\u0000"+
		"XZ\t\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000YX\u0001\u0000\u0000\u0000"+
		"Z]\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000"+
		"\u0000\\^\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000^_\u0005\'"+
		"\u0000\u0000_\u001c\u0001\u0000\u0000\u0000`b\u0007\u0003\u0000\u0000"+
		"a`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000"+
		"\u0000cd\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0006\u000e"+
		"\u0000\u0000f\u001e\u0001\u0000\u0000\u0000gh\u0007\u0004\u0000\u0000"+
		"h \u0001\u0000\u0000\u0000ij\u0005\\\u0000\u0000jk\u0007\u0005\u0000\u0000"+
		"k\"\u0001\u0000\u0000\u0000\b\u0000DKOTY[c\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}