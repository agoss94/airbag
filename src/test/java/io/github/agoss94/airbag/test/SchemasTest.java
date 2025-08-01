package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Schema;
import io.github.agoss94.airbag.SchemaNode;
import io.github.agoss94.airbag.Schemas;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link Schemas} utility class.
 */
class SchemasTest {

    @Test
    void testToStringWithParser() {
        // Create a parser for the expression grammar
        ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString("x = 1"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);

        // Manually construct a schema tree
        Schema.Rule root = new SchemaNode.Rule(ExpressionParser.RULE_stat, null);
        new SchemaNode.Terminal(new CommonToken(ExpressionLexer.ID, "x"), root);
        new SchemaNode.Terminal(new CommonToken(ExpressionLexer.T__0, "="), root);
        Schema.Rule expr = new SchemaNode.Rule(ExpressionParser.RULE_expr, root);
        new SchemaNode.Terminal(new CommonToken(ExpressionLexer.INT, "1"), expr);

        // Convert the schema to a string using the parser
        String result = Schemas.toString(root, parser);

        // Assert the expected S-expression
        assertEquals("(stat (ID 'x') '=' (expr (INT '1')))", result);
    }
}
