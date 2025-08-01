package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Airbag;
import io.github.agoss94.airbag.Tokens;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Airbag} assertion methods.
 */
class AssertionTest {

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
}