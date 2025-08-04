package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Airbag;
import io.github.agoss94.airbag.SchemaNode;
import io.github.agoss94.airbag.Schemas;
import io.github.agoss94.airbag.Tokens;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Airbag} assertion methods.
 */
class TokenAssertionTest {

    private Airbag airbag;

    @BeforeEach
    void setUp() {
        airbag = new Airbag(ExpressionLexer.VOCABULARY);
    }

    @Test
    void testInteger() {
        var actual = Tokens.from("1\n", ExpressionLexer.class);
        var expected = Tokens.from("""
                [@0,0:0='1',<INT>,1:0]
                [@1,1:1='%n',<NEWLINE>,1:1]
                [@2,2:1='<EOF>',<EOF>,2:0]
                """, ExpressionLexer.VOCABULARY);
        airbag.assertTokenList(expected, actual);
    }

    @Test
    void testIdentifier() {
        var actual = Tokens.from("a\n", ExpressionLexer.class);
        var expected = Tokens.from("""
                [@0,0:0='a',<ID>,1:0]
                [@1,1:1='%n',<NEWLINE>,1:1]
                [@2,2:1='<EOF>',<EOF>,2:0]
                """, ExpressionLexer.VOCABULARY);
        airbag.assertTokenList(expected, actual);
    }

    @Test
    void testAssignment() {
        var actual = Tokens.from("a = 1\n", ExpressionLexer.class);
        var expected = Tokens.from("""
                [@0,0:0='a',<ID>,1:0]
                [@1,2:2='=',<'='>,1:2]
                [@2,4:4='1',<INT>,1:4]
                [@3,5:5='%n',<NEWLINE>,1:5]
                [@4,6:5='<EOF>',<EOF>,2:0]
                """, ExpressionLexer.VOCABULARY);
        airbag.assertTokenList(expected, actual);
    }

    @Test
    void testComplexExpression() {
        var actual = Tokens.from("(a + b) * 2\n", ExpressionLexer.class);
        var expected = Tokens.from("""
                [@0,0:0='(',<'('>,1:0]
                [@1,1:1='a',<ID>,1:1]
                [@2,3:3='+',<'+'>,1:3]
                [@3,5:5='b',<ID>,1:5]
                [@4,6:6=')',<')'>,1:6]
                [@5,8:8='*',<'*'>,1:8]
                [@6,10:10='2',<INT>,1:10]
                [@7,11:11='%n',<NEWLINE>,1:11]
                [@8,12:11='<EOF>',<EOF>,2:0]
                """, ExpressionLexer.VOCABULARY);
        airbag.assertTokenList(expected, actual);
    }

    @Test
    void testAnotherComplexExpression() {
        var actual = Tokens.from("(a - 5) / b\n", ExpressionLexer.class);
        var expected = Tokens.from("""
                [@0,0:0='(',<'('>,1:0]
                [@1,1:1='a',<ID>,1:1]
                [@2,3:3='-',<'-'>,1:3]
                [@3,5:5='5',<INT>,1:5]
                [@4,6:6=')',<')'>,1:6]
                [@5,8:8='/',<'/'>,1:8]
                [@6,10:10='b',<ID>,1:10]
                [@7,11:11='%n',<NEWLINE>,1:11]
                [@8,12:11='<EOF>',<EOF>,2:0]
                """, ExpressionLexer.VOCABULARY);
        airbag.assertTokenList(expected, actual);
    }

    @Test
    void testBlankLine() {
        var actual = Tokens.from("\n", ExpressionLexer.class);
        var expected = Tokens.from("""
                [@0,0:0='%n',<NEWLINE>,1:0]
                [@1,1:0='<EOF>',<EOF>,2:0]
                """, ExpressionLexer.VOCABULARY);
            airbag.assertTokenList(expected, actual);
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
        Assertions.assertEquals("Expected rule node with index 1 but instead was given the terminal node.", exception.getMessage());
    }

    @Test
    void assertSchema_whenExpectingTerminalButGivenError_shouldThrowAssertionError() {
        var token = new CommonToken(ExpressionLexer.INT, "1");
        var schema = SchemaNode.Terminal.attach(token, null);
        var errorNode = new ErrorNodeImpl(new CommonToken(Token.INVALID_TYPE, "<error>"));
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, errorNode));
        Assertions.assertEquals("Expected a terminal node but was given an error node", exception.getMessage());
    }

    @Test
    void assertSchema_withMatchingTerminal_shouldSucceed() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(INT '1')", parser);
        var tree = parse("1\n").getChild(0).getChild(0);
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
        Assertions.assertEquals("Expected a error node but was given an terminal node", exception.getMessage());
    }

    @Test
    void assertSchema_withMismatchedRuleIndex_shouldThrowAssertionError() {
        var schema = SchemaNode.Rule.attach(ExpressionParser.RULE_expr, null);
        var tree = parse("1\n");
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("Expected the rule index 2, but instead was 1", exception.getMessage());
    }

    @Test
    void assertSchema_withTooFewChildren_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(stat (expr (INT '1')) (NEWLINE '\n') (EOF '<EOF>') (EOF '<EOF>'))", parser);
        var tree = parse("1\n");
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("The rule node 1 has less children than expected", exception.getMessage());
    }

    @Test
    void assertSchema_withTooManyChildren_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(stat (expr (INT '1')))", parser);
        var tree = parse("1\n");
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertEquals("The rule node 1 has more children than expected", exception.getMessage());
    }

    @Test
    void assertSchema_withMismatchedTokenType_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(ID '1')", parser);
        var tree = parse("1\n").getChild(0).getChild(0);
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertTrue(exception.getMessage().startsWith("Tokens are not equal."));
    }

    @Test
    void assertSchema_withMismatchedTokenText_shouldThrowAssertionError() {
        var parser = new ExpressionParser(null);
        var schema = Schemas.from("(INT '2')", parser);
        var tree = parse("1\n").getChild(0).getChild(0);
        var exception = assertThrows(AssertionError.class, () -> airbag.assertSchema(schema, tree));
        Assertions.assertTrue(exception.getMessage().startsWith("Tokens are not equal."));
    }
}