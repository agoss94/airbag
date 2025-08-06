package io.github.agoss94.airbag;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import io.github.agoss94.airbag.parser.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Airbag is a library for testing ANTLR grammars.
 */
public class Airbag {

    /**
     * The ANTLR vocabulary.
     */
    private Vocabulary vocabulary;
    /**
     * The recognizer class.
     */
    private Class<? extends Recognizer<?, ?>> recognizerClass;

    /**
     * Constructs a new Airbag.
     */
    public Airbag() {
    }

    /**
     * Constructs a new Airbag with the given vocabulary.
     *
     * @param recognizerClass the recognizer class.
     */
    public Airbag(Class<? extends Recognizer<?, ?>> recognizerClass) {
        setRecognizerClass(recognizerClass);
    }

    /**
     * Asserts that two tokens are equal.
     *
     * @param expected the expected token.
     * @param actual   the actual token.
     */
    public void assertToken(Token expected, Token actual) {
        if (!Tokens.isEqual(expected, actual)) {
            throw new AssertionError("Tokens are not equal.%nExpected: %s%nActual:   %s".formatted(
                    Tokens.toString(expected, getVocabulary()),
                    Tokens.toString(actual, getVocabulary())));
        }
    }

    /**
     * Asserts that two lists of tokens are equal.
     *
     * @param expected the expected list of tokens.
     * @param actual   the actual list of tokens.
     */
    public void assertTokenList(List<? extends Token> expected, List<? extends Token> actual) {
        if (!Utils.listEquals(expected, actual, Tokens::isEqual)) {
            int width = Stream.concat(expected.stream(), actual.stream())
                    .map(token -> Tokens.toString(token, getVocabulary()).length())
                    .max(Integer::compareTo)
                    .orElse(0);

            List<Token> expectedList = expected.stream().map(t -> (Token) t).toList();
            List<Token> actualList = actual.stream().map(t -> (Token) t).toList();
            Patch<Token> patch = DiffUtils.diff(expectedList, actualList, Tokens::isEqual);
            String tokenSlot = "%%-%ds".formatted(width);
            StringBuilder diffMessage = new StringBuilder("Token lists are not equal.%n%n".formatted());
            diffMessage.append("%s | %s%n".formatted(tokenSlot.formatted("Expected"),
                    tokenSlot.formatted("Actual")));
            diffMessage.append("%s+%s%n".formatted("-".repeat(width + 1), "-".repeat(width + 1)));
            for (AbstractDelta<Token> delta : patch.getDeltas()) {
                List<Token> sourceLines = delta.getSource().getLines();
                List<Token> targetLines = delta.getTarget().getLines();
                int sourceSize = sourceLines.size();
                int targetSize = targetLines.size();
                for (int i = 0; i < Math.max(sourceSize, targetSize); i++) {
                    String source = i < sourceSize ?
                            Tokens.toString(sourceLines.get(i), getVocabulary()) :
                            "";
                    String target = i < targetSize ?
                            Tokens.toString(targetLines.get(i), getVocabulary()) :
                            "";
                    diffMessage.append("%s | %s%n".formatted(tokenSlot.formatted(source),
                            tokenSlot.formatted(target)));
                }
            }
            diffMessage.append("%n%nActual:%n-------%n%n".formatted());
            actual.forEach(t -> diffMessage.append("%s%n".formatted(Tokens.toString(t,
                    getVocabulary()))));
            throw new AssertionError(diffMessage.toString());
        }
    }

    /**
     * Asserts that the given {@link ParseTree} conforms to the {@link Schema}.
     *
     * @param expected the expected schema.
     * @param tree     the parse tree.
     * @throws AssertionError if the parse tree does not conform to the schema.
     */
    public void assertSchema(Schema expected, ParseTree tree) {
        Schema actual = Schemas.from(tree);
        if (!expected.equals(actual)) {
            throw new AssertionError("Schemas are not equal.\nExpected: %s\nActual:   %s".formatted(
                    Schemas.toString(expected, recognizerClass),
                    Schemas.toString(actual, recognizerClass)));
        }
    }

    /**
     * Asserts that the given test file is valid.
     *
     * @param fileName the name of the test file.
     * @param recognizerClass the recognizer class.
     */
    public void assertTestFile(String fileName, Class<? extends Recognizer<?, ?>> recognizerClass) {
        setRecognizerClass(recognizerClass);
        try {
            TestLexer testLexer = new TestLexer(CharStreams.fromFileName(fileName));
            TestParser parser = new TestParser(new CommonTokenStream(testLexer));
            TestListener listener = new TestBaseListener() {

                @Override
                public void enterFile(TestParser.FileContext ctx) {
                    String input = getText(ctx.INPUT());

                    if (ctx.TOKEN() != null) {
                        List<Token> expected = Tokens.from(getText(ctx.TOKEN()),
                                getVocabulary(recognizerClass));
                        List<Token> actual = Tokens.from(input, getLexerClass(recognizerClass));
                        assertTokenList(expected, actual);
                    }

                    if (ctx.TREE() != null) {
                        try {
                            Lexer lexer = getLexerClass(recognizerClass).getConstructor(CharStream.class).newInstance(
                                    CharStreams.fromString(
                                            input));
                            CommonTokenStream tokens = new CommonTokenStream(lexer);
                            @SuppressWarnings("unchecked") Parser parser = ((Class<? extends Parser>) recognizerClass).getConstructor(
                                    TokenStream.class).newInstance(tokens);
                            String[] ruleNames = parser.getRuleNames();
                            ParseTree tree = (ParseTree) parser.getClass().getMethod(ruleNames[0]).invoke(
                                    parser);
                            assertSchema(Schemas.from(getText(ctx.TREE()), parser), tree);
                        } catch (InstantiationException | IllegalAccessException |
                                 InvocationTargetException |
                                 NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }

                private Vocabulary getVocabulary(Class<? extends Recognizer<?, ?>> recognizerClass) {
                    try {
                        return (Vocabulary) recognizerClass.getField("VOCABULARY").get(null);
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        return null;
                    }
                }

                @SuppressWarnings("unchecked")
                private Class<? extends Lexer> getLexerClass(Class<? extends Recognizer<?, ?>> recognizerClass) {
                    if (Lexer.class.isAssignableFrom(recognizerClass)) {
                        return (Class<? extends Lexer>) recognizerClass;
                    } else {
                        try {
                            return (Class<? extends Lexer>) Class.forName(recognizerClass.getName().replace(
                                    "Parser",
                                    "Lexer"));
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                private String getText(TerminalNode rule) {
                    String text = rule.getText();
                    int start = text.indexOf("{");
                    int end = text.indexOf("}");
                    text = text.substring(start + 1, end);
                    if (text.startsWith("\r\n")) {
                        text = text.substring(2);
                    } else if (text.startsWith("\n")) {
                        text = text.substring(1);
                    }
                    if (text.endsWith("\r\n")) {
                        text = text.substring(0, text.length() - 2);
                    } else if (text.endsWith("\n")) {
                        text = text.substring(0, text.length() - 1);
                    }
                    return text;
                }

            };

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(listener, parser.file());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the ANTLR recognizer.
     *
     * @return the ANTLR recognizer.
     */
    private Recognizer<?, ?> getRecognizer() {
        try {
            return recognizerClass.getConstructor(TokenStream.class).newInstance((TokenStream) null);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns the ANTLR vocabulary.
     *
     * @return the ANTLR vocabulary.
     */
    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    /**
     * Returns the recognizer class.
     *
     * @return the recognizer class.
     */
    public Class<? extends Recognizer<?, ?>> getRecognizerClass() {
        return recognizerClass;
    }

    /**
     * Sets the recognizer class.
     *
     * @param recognizerClass the recognizer class.
     */
    public void setRecognizerClass(Class<? extends Recognizer<?, ?>> recognizerClass) {
        this.recognizerClass = recognizerClass;
        try {
            this.vocabulary = (Vocabulary) recognizerClass.getField("VOCABULARY").get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
