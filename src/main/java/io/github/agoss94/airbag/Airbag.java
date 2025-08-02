package io.github.agoss94.airbag;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
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
     * Asserts that two tokens are equal.
     *
     * @param expected the expected token.
     * @param actual   the actual token.
     */
    public void assertToken(Token expected, Token actual) {
        if (!Tokens.isEqual(expected, actual)) {
            throw new AssertionError("Tokens are not equal.%nExpected: %s%nActual:   %s".formatted(
                    Tokens.toString(expected, vocabulary),
                    Tokens.toString(actual, vocabulary)));
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
                    .map(token -> Tokens.toString(token, vocabulary).length())
                    .max(Integer::compareTo)
                    .orElse(0);

            Patch<Token> patch = DiffUtils.diff(expected, actual, Tokens::isEqual);
            String tokenSlot = "%%-%ds".formatted(width);
            StringBuilder diffMessage = new StringBuilder("Token lists are not equal.%n%n".formatted());
            diffMessage.append("%s | %s%n".formatted(tokenSlot.formatted("Expected"),
                    tokenSlot.formatted("Actual")));
            diffMessage.append("%s+%s%n".formatted("-".repeat(width + 1), "-".repeat(width + 1)));
            for (AbstractDelta<Token> delta : patch.getDeltas()) {
                List<? extends Token> sourceLines = delta.getSource().getLines();
                List<? extends Token> targetLines = delta.getTarget().getLines();
                int sourceSize = sourceLines.size();
                int targetSize = targetLines.size();
                for (int i = 0; i < Math.max(sourceSize, targetSize); i++) {
                    String source = i < sourceSize ?
                            Tokens.toString(sourceLines.get(i), vocabulary) :
                            "";
                    String target = i < targetSize ?
                            Tokens.toString(targetLines.get(i), vocabulary) :
                            "";
                    diffMessage.append("%s | %s%n".formatted(tokenSlot.formatted(source),
                            tokenSlot.formatted(target)));
                }
            }
            diffMessage.append("%n%nActual:%n-------%n%n".formatted());
            actual.forEach(t -> diffMessage.append("%s%n".formatted(Tokens.toString(t,
                    vocabulary))));
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
