package io.github.agoss94.airbag;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import io.github.agoss94.airbag.core.Utils;
import org.antlr.v4.runtime.*;

import java.util.List;
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
     * Constructs a new Airbag.
     */
    public Airbag() {
    }

    /**
     * Constructs a new Airbag with the given vocabulary.
     *
     * @param vocabulary the ANTLR vocabulary.
     */
    public Airbag(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }

    /**
     * Creates a list of {@link Token}s from a given lexer class and input string.
     *
     * @param grammarClass the lexer class to use for tokenizing the input.
     * @param input        the input string to tokenize.
     * @return a list of {@link Token}s generated from the input.
     * @throws RuntimeException if the lexer cannot be instantiated or an error occurs during tokenization.
     */
    public static List<Token> tokenList(Class<? extends Lexer> grammarClass, String input) {
        try {
            Lexer lexer = grammarClass.getConstructor(CharStream.class).newInstance(CharStreams.fromString(
                    input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill(); // Populate the token stream
            return tokens.getTokens();
        } catch (Exception e) {
            throw new RuntimeException("Failed to test lexer", e);
        }
    }

    /**
     * Asserts that two tokens are equal.
     *
     * @param expected the expected token.
     * @param actual   the actual token.
     */
    public static void assertToken(Token expected, Token actual) {
        if (!Tokens.isEqual(expected, actual)) {
            throw new AssertionError("Tokens are not equal.%nExpected: %s%nActual:   %s".formatted(expected, actual));
        }
    }

    /**
     * Asserts that two lists of tokens are equal.
     *
     * @param expected the expected list of tokens.
     * @param actual   the actual list of tokens.
     */
    public static void assertTokenList(List<? extends Token> expected, List<? extends Token> actual) {
        if (!Utils.listEquals(expected, actual, Tokens::isEqual)) {
            int width = Stream.concat(expected.stream(), actual.stream())
                    .map(token -> token.toString().length())
                    .max(Integer::compareTo)
                    .orElse(0);

            Patch<Token> patch = DiffUtils.diff(expected, actual, Tokens::isEqual);
            String tokenSlot = "%%-%ds".formatted(width);
            StringBuilder diffMessage = new StringBuilder("Token lists are not equal.%n%n".formatted());
            diffMessage.append("%s | %s%n".formatted(tokenSlot.formatted("Expected"), tokenSlot.formatted("Actual")));
            diffMessage.append("%s+%s%n".formatted("-".repeat(width + 1), "-".repeat(width + 1)));
            for (AbstractDelta<Token> delta : patch.getDeltas()) {
                List<? extends Token> sourceLines = delta.getSource().getLines();
                List<? extends Token> targetLines = delta.getTarget().getLines();
                int sourceSize = sourceLines.size();
                int targetSize = targetLines.size();
                for (int i = 0; i < Math.max(sourceSize, targetSize); i++) {
                    String source = i < sourceSize ? sourceLines.get(i).toString() : "";
                    String target = i < targetSize ? targetLines.get(i).toString() : "";
                    diffMessage.append("%s | %s%n".formatted(tokenSlot.formatted(source), tokenSlot.formatted(target)));
                }
            }
            diffMessage.append("%n%nActual:%n-------%n%n".formatted());
            actual.forEach(t -> diffMessage.append("%s%n".formatted(t)));
            throw new AssertionError(diffMessage.toString());
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
     * Sets the ANTLR vocabulary.
     *
     * @param vocabulary the ANTLR vocabulary.
     */
    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
}
