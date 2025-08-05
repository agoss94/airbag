package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Schema;
import io.github.agoss94.airbag.SchemaNode;
import io.github.agoss94.airbag.Schemas;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * Unit tests for the {@link Schemas} utility class.
 */
class SchemasTest {

    @Test
    void testToStringWithParser() {
        //No input needed
        ExpressionParser parser = new ExpressionParser(null);

        // Manually construct a schema tree
        Schema.Rule root = SchemaNode.Rule.attach(ExpressionParser.RULE_stat, null);
        Schema.Terminal child = SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.ID, "x"),
                root);
        SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.T__0, "="), root);
        Schema.Rule expr = SchemaNode.Rule.attach(ExpressionParser.RULE_expr, root);
        SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.INT, "1"), expr);

        // Convert the schema to a string using the parser
        String result = Schemas.toString(root, parser);

        // Assert the expected S-expression
        assertEquals(child, root.getChild(0));
        assertEquals("(stat (ID 'x') '=' (expr (INT '1')))", result);
    }

    @Test
    void testFromWithStringAndParserExplicitNodes() {
        ExpressionParser parser = new ExpressionParser(null);

        // Define a schema string for a simple rule with a terminal child
        String schemaString = "(stat (ID 'x'))";

        // Convert the string to a Schema object
        Schema resultSchema = Schemas.from(schemaString, parser);

        // Assert the top-level schema is a Rule
        assertInstanceOf(Schema.Rule.class, resultSchema);
        Schema.Rule resultRootRule = (Schema.Rule) resultSchema;
        assertEquals(ExpressionParser.RULE_stat, resultRootRule.index());
        assertEquals(1, resultRootRule.getChildCount());

        // Assert the child is a Terminal
        Schema resultChild = resultRootRule.getChild(0);
        assertInstanceOf(Schema.Terminal.class, resultChild);
        Schema.Terminal resultTerminal = (Schema.Terminal) resultChild;
        assertEquals(ExpressionLexer.ID, resultTerminal.token().getType());
        assertEquals("x", resultTerminal.token().getText());

        assertEquals(schemaString, Schemas.toString(resultRootRule, parser));
    }

    @Test
    void testComplexSchema() {
        ExpressionParser parser = new ExpressionParser(null);
        // Test a more complex schema string
        String schemaString = "(stat (ID 'x') '=' (expr (INT '1')))";
        Schema resultSchema = Schemas.from(schemaString, parser);

        assertInstanceOf(Schema.Rule.class, resultSchema);
        Schema.Rule resultRootRule = (Schema.Rule) resultSchema;
        assertEquals(ExpressionParser.RULE_stat, resultRootRule.index());
        assertEquals(3, resultRootRule.getChildCount());

        // Check first child: ID 'x'
        Schema resultChild = resultRootRule.getChild(0);
        assertInstanceOf(Schema.Terminal.class, resultChild);
        Schema.Terminal resultTerminal = (Schema.Terminal) resultChild;
        assertEquals(ExpressionLexer.ID, resultTerminal.token().getType());
        assertEquals("x", resultTerminal.token().getText());

        // Check second child: '='
        resultChild = resultRootRule.getChild(1);
        assertInstanceOf(Schema.Terminal.class, resultChild);
        resultTerminal = (Schema.Terminal) resultChild;
        assertEquals(ExpressionLexer.T__0, resultTerminal.token().getType());
        assertEquals("=", resultTerminal.token().getText());

        // Check third child: expr (INT '1')
        resultChild = resultRootRule.getChild(2);
        assertInstanceOf(Schema.Rule.class, resultChild);
        Schema.Rule resultExprRule = (Schema.Rule) resultChild;
        assertEquals(ExpressionParser.RULE_expr, resultExprRule.index());
        assertEquals(1, resultExprRule.getChildCount());

        // Check expr's child: INT '1'
        Schema resultIntChild = resultExprRule.getChild(0);
        assertInstanceOf(Schema.Terminal.class, resultIntChild);
        Schema.Terminal resultIntTerminal = (Schema.Terminal) resultIntChild;
        assertEquals(ExpressionLexer.INT, resultIntTerminal.token().getType());
        assertEquals("1", resultIntTerminal.token().getText());

        assertEquals(schemaString, Schemas.toString(resultRootRule, parser));
    }

    @Test
    void testFromParseTree() {
        String input = "x = 1\n";
        ExpressionLexer lexer = new ExpressionLexer(CharStreams.fromString(input));
        ExpressionParser parser = new ExpressionParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.stat();

        // Manually construct the expected schema tree
        Schema.Rule expected = SchemaNode.Rule.attach(ExpressionParser.RULE_stat, null);
        SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.ID, "x"), expected);
        SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.T__0, "="), expected);
        Schema.Rule expr = SchemaNode.Rule.attach(ExpressionParser.RULE_expr, expected);
        SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.INT, "1"), expr);
        SchemaNode.Terminal.attach(new CommonToken(ExpressionLexer.NEWLINE, "%n".formatted()), expected);

        Schema actual = Schemas.from(tree);

        assertEquals(expected, actual);
    }
}
