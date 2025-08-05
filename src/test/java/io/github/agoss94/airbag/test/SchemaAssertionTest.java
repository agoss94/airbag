package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Airbag;
import io.github.agoss94.airbag.SchemaNode;
import io.github.agoss94.airbag.Schemas;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SchemaAssertionTest {

    private Airbag airbag;

    @BeforeEach
    void setUp() {
        airbag = new Airbag(ExpressionParser.class);
    }

    private ParseTree parse(String expression) {
        var lexer = new ExpressionLexer(CharStreams.fromString(expression));
        var tokens = new CommonTokenStream(lexer);
        var parser = new ExpressionParser(tokens);
        return parser.stat();
    }

    @Test
    void assertSchema_withMatchingRule_shouldSucceed() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(stat (expr (INT '1')) (NEWLINE '\n'))", parser);
        var tree = parse("1\n");
        airbag.assertSchema(schema, tree);
    }

    @Test
    void assertSchema_whenExpectingRuleButGivenTerminal_shouldThrowAssertionError() {
        var schema = SchemaNode.Rule.attach(ExpressionParser.RULE_stat, null);
        var tree = parse("1\n").getChild(0).getChild(0);
        Assertions.assertInstanceOf(TerminalNode.class, tree);
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (stat)\nActual:   (INT '1')", exception.getMessage());
    }

    @Test
    void assertSchema_whenExpectingTerminalButGivenError_shouldThrowAssertionError() {
        var token = new CommonToken(ExpressionLexer.INT, "1");
        var schema = SchemaNode.Terminal.attach(token, null);
        var errorNode = new ErrorNodeImpl(new CommonToken(Token.INVALID_TYPE, "<error>"));
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, errorNode));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (INT '1')\nActual:   (<error> (0 '<error>'))", exception.getMessage());
    }

    @Test
    void assertSchema_withMatchingTerminal_shouldSucceed() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(expr (INT '1'))", parser);
        var tree = parse("1\n").getChild(0);
        airbag.assertSchema(schema, tree);
    }

    @Test
    void assertSchema_withMatchingError_shouldSucceed() {
        var errorToken = new CommonToken(Token.INVALID_TYPE, "!");
        var schema = SchemaNode.Error.attach(errorToken, null);
        var errorNode = new ErrorNodeImpl(errorToken);
        airbag.assertSchema(schema, errorNode);
    }

    @Test
    void assertSchema_whenExpectingErrorButGivenTerminal_shouldThrowAssertionError() {
        var errorToken = new CommonToken(Token.INVALID_TYPE, "!");
        var schema = SchemaNode.Error.attach(errorToken, null);
        var tree = parse("1\n").getChild(0).getChild(0);
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (<error> (0 '!'))\nActual:   (INT '1')", exception.getMessage());
    }

    @Test
    void assertSchema_withMismatchedRuleIndex_shouldThrowAssertionError() {
        var schema = SchemaNode.Rule.attach(ExpressionParser.RULE_expr, null);
        var tree = parse("1\n");
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (expr)\nActual:   (stat (expr (INT '1')) (NEWLINE '%n'))", exception.getMessage());
    }

    @Test
    void assertSchema_withTooFewChildren_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(stat (expr (INT '1')) (NEWLINE '\n') (EOF '<EOF>'))", parser);
        var tree = parse("1\n");
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        var expectedMessage = """
                Schemas are not equal.
                Expected: (stat (expr (INT '1')) (NEWLINE '%n') (EOF '<EOF>'))
                Actual:   (stat (expr (INT '1')) (NEWLINE '%n'))""";
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void assertSchema_withTooManyChildren_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(stat (expr (INT '1')))", parser);
        var tree = parse("1\n");
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (stat (expr (INT '1')))\nActual:   (stat (expr (INT '1')) (NEWLINE '%n'))", exception.getMessage());
    }

    @Test
    void assertSchema_withMismatchedTokenType_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(ID '1')", parser);
        var tree = parse("1\n").getChild(0).getChild(0);
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (ID '1')\nActual:   (INT '1')", exception.getMessage());
    }

    @Test
    void assertSchema_withMismatchedTokenText_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(INT '2')", parser);
        var tree = parse("1\n").getChild(0).getChild(0);
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Schemas are not equal.\nExpected: (INT '2')\nActual:   (INT '1')", exception.getMessage());
    }
}
