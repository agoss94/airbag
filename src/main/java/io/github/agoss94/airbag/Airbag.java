package io.github.agoss94.airbag;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.AbstractDelta;
import com.github.difflib.patch.Patch;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

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
                List<Token> sourceLines = delta.getSource().getLines();
                List<Token> targetLines = delta.getTarget().getLines();
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
     * Asserts that the given {@link ParseTree} conforms to the {@link Schema}.
     *
     * @param expected the expected schema.
     * @param tree the parse tree.
     * @throws AssertionError if the parse tree does not conform to the schema.
     */
    public void assertSchema(Schema expected, ParseTree tree) {
        switch (expected) {
            case Schema.Rule rule -> {
                if (tree instanceof RuleContext ruleContext) {
                    assertRuleNode(rule, ruleContext);
                } else {
                    throw new AssertionError(
                            "Expected rule node with index %d but instead was given the terminal node.".formatted(
                                    rule.index()));
                }
            }
            case Schema.Terminal terminal -> {
                if (tree instanceof ErrorNode) {
                    throw new AssertionError("Expected a terminal node but was given an error node");
                } else {
                    assertWeakToken(terminal.token(), (Token) tree.getPayload());
                }
            }
            case Schema.Error error -> {
                if (tree instanceof ErrorNode) {
                    assertWeakToken(error.token(), (Token) tree.getPayload());
                } else {
                    throw new AssertionError("Expected a error node but was given an terminal node");
                }
            }
        }
    }

    /**
     * Asserts that the given {@link RuleContext} conforms to the {@link Schema.Rule}.
     *
     * @param ruleNode the expected rule schema.
     * @param ctx the rule context.
     * @throws AssertionError if the rule context does not conform to the rule schema.
     */
    private void assertRuleNode(Schema.Rule ruleNode, RuleContext ctx) {
        int expectedIndex = ruleNode.index();
        int actualIndex = ctx.getRuleIndex();
        if (expectedIndex != actualIndex) {
            throw new AssertionError("Expected the rule index %s, but instead was %d".formatted(
                    expectedIndex,
                    actualIndex));
        } else {
            int expectedCount = ruleNode.getChildCount();
            int actualCount = ctx.getChildCount();
            for (int i = 0; i < Math.max(expectedCount, actualCount); i++) {
                if (!(i < expectedCount)) {
                    throw new AssertionError("The rule node %d has more children than expected".formatted(
                            actualIndex));
                } else if (!(i < actualCount)) {
                    throw new AssertionError("The rule node %d has less children than expected".formatted(
                            actualIndex));
                } else {
                    assertSchema(ruleNode.getChild(i), ctx.getChild(i));
                }
            }
        }
    }

    /**
     * Asserts that two tokens are weakly equal.
     * <p>
     * Two tokens are weakly equal if they have the same type and text. Other properties like channel, line, and char
     * position in line are ignored.
     *
     * @param expected the expected token.
     * @param actual the actual token.
     * @throws AssertionError if the tokens are not weakly equal.
     */
    private void assertWeakToken(Token expected, Token actual) {
        if (expected.getType() != actual.getType() ||
                !Objects.equals(expected.getText(), actual.getText())) {
            throw new AssertionError("Tokens are not equal.%nExpected: %s%nActual:   %s".formatted(
                    Tokens.toString(expected, vocabulary),
                    Tokens.toString(actual, vocabulary)));
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
