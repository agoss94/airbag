package io.github.agoss94.airbag;

import io.github.agoss94.airbag.parser.SchemaBaseListener;
import io.github.agoss94.airbag.parser.SchemaLexer;
import io.github.agoss94.airbag.parser.SchemaParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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

    public static Schema from(String string, Parser parser) {
        var schemaLexer = new SchemaLexer(CharStreams.fromString(string));
        var schemaParser = new SchemaParser(new CommonTokenStream(schemaLexer));
        var listener = new Listener();
        listener.parser = parser;
        var walker = new ParseTreeWalker();
        walker.walk(listener, schemaParser.schema());
        return listener.schema;
    }

    private static class Listener extends SchemaBaseListener {

        private Schema schema;
        private Parser parser;

        @Override
        public void enterRule(SchemaParser.RuleContext ctx) {
            schema = new SchemaNode.Rule(getRuleIndex(ctx.index, parser), schema);
        }

        @Override
        public void exitRule(SchemaParser.RuleContext ctx) {
            schema = schema.getParent();
        }

        @Override
        public void enterLiteral(SchemaParser.LiteralContext ctx) {
            new SchemaNode.Terminal(createToken(ctx), schema);
        }

        @Override
        public void enterSymbolic(SchemaParser.SymbolicContext ctx) {
            new SchemaNode.Terminal(createToken(ctx), schema);
        }

        private Token createToken(SchemaParser.TokenContext ctx) {
            if (ctx instanceof SchemaParser.LiteralContext literalCtx) {
                var literal = literalCtx.STRING().getText();
                int index = Tokens.getTokenType(literal, parser.getVocabulary());
                return new CommonToken(index, literal.substring(1, literal.length() - 1));
            } else if (ctx instanceof SchemaParser.SymbolicContext symbolicCtx) {
                int index = getTokenIndex(symbolicCtx.index);
                String text = symbolicCtx.STRING().getText();
                return new CommonToken(index, text.substring(1, text.length() - 1));
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public void enterError(SchemaParser.ErrorContext ctx) {
            new SchemaNode.Error(createToken(ctx.token()), schema);
        }

        private int getRuleIndex(Token token, Parser parser) {
            String index = token.getText();
            if (index.matches("-?\\d+")) {
                return Integer.parseInt(index);
            } else {
                return parser.getRuleIndexMap().get(token.getText());
            }
        }

        private int getTokenIndex(Token token) {
            String index = token.getText();
            if (index.matches("-?\\d+")) {
                return Integer.parseInt(index);
            } else {
                return Tokens.getTokenType(index, parser.getVocabulary());
            }
        }
    }
}
