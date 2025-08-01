package io.github.agoss94.airbag;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

/**
 * Utility class for working with ANTLR schemas.
 */
public class Schemas {

    /**
     * Converts a {@link Schema} tree to an S-expression representation.
     * S-expressions provide a human-readable format for visualizing the nested structure of the tree.
     *
     * @param schema     the {@link Schema} to convert.
     * @param recognizer the ANTLR {@link Parser} to resolve rule and token names.
     * @return a string representing the schema in S-expression format.
     */
    public static String toString(Schema schema, Parser recognizer) {
        var vocabulary = recognizer == null ? null : recognizer.getVocabulary();
        switch (schema) {
            case Schema.Rule rule -> {
                String ruleName = recognizer != null ?
                        recognizer.getRuleNames()[rule.index()] :
                        String.valueOf(rule.index());
                var sb = new StringBuilder("(%s".formatted(ruleName));
                for (int i = 0; i < rule.getChildCount(); i++) {
                    sb.append(" %s".formatted(toString(rule.getChild(i), recognizer)));
                }
                sb.append(")");
                return sb.toString();
            }
            case Schema.Terminal terminal -> {
                return tokenToString(terminal.token(), vocabulary);
            }
            case Schema.Error error -> {
                return "(<error> %s)".formatted(tokenToString(error.token(), vocabulary));
            }
        }
    }

    /**
     * Converts an ANTLR {@link Token} to a string representation.
     *
     * @param token      the {@link Token} to convert.
     * @param vocabulary the ANTLR {@link Vocabulary} to resolve token names.
     * @return a string representation of the token.
     */
    private static String tokenToString(Token token, Vocabulary vocabulary) {
        if (vocabulary == null) {
            return "(%s '%s')".formatted(String.valueOf(token.getType()), token.getText());
        }
        String literalName = vocabulary.getLiteralName(token.getType());
        if (literalName != null) {
            return "'%s'".formatted(token.getText());
        }
        return "(%s '%s')".formatted(vocabulary.getDisplayName(token.getType()), token.getText());
    }
}
