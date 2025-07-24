package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Utils;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import io.github.agoss94.airbag.parser.AirbagLexer;
import io.github.agoss94.airbag.parser.AirbagParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

    @Test
    void testToStringTreeForToken() {
        String input = "[@0,0:0='test',<1>,0:0]";
        AirbagLexer lexer = new AirbagLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AirbagParser parser = new AirbagParser(tokens);
        AirbagParser.TokenContext tree = parser.token();

        String result = Utils.formatTreeString(Utils.toStringTree(tree, parser));

        String expected = """
                (token
                  (LITERAL '[@')
                  (INT '0')
                  (LITERAL ',')
                  (INT '0')
                  (LITERAL ':')
                  (INT '0')
                  (LITERAL '=')
                  (STRING '\\'test\\'')
                  (LITERAL ',<')
                  (INT '1')
                  (LITERAL '>')
                  (LITERAL ',')
                  (INT '0')
                  (LITERAL ':')
                  (INT '0')
                  (LITERAL ']')
                )""";
        assertEquals(expected, result);
    }

    @Test
    void testToStringTreeForTokenListWithError() {
        String input = "[@0,0:0='test',<1>,0:0][@1,1:1='another',<2>,1:1][@A,1:1=]";
        AirbagLexer lexer = new AirbagLexer(CharStreams.fromString(input));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AirbagParser parser = new AirbagParser(tokens);
        parser.removeErrorListeners();
        AirbagParser.TokenListContext tree = parser.tokenList();

        String result =  Utils.formatTreeString(Utils.toStringTree(tree, parser));

        String expected = """
                (tokenlist
                  (token
                    (LITERAL '[@')
                    (INT '0')
                    (LITERAL ',')
                    (INT '0')
                    (LITERAL ':')
                    (INT '0')
                    (LITERAL '=')
                    (STRING '\\'test\\'')
                    (LITERAL ',<')
                    (INT '1')
                    (LITERAL '>')
                    (LITERAL ',')
                    (INT '0')
                    (LITERAL ':')
                    (INT '0')
                    (LITERAL ']')
                  )
                  (token
                    (LITERAL '[@')
                    (INT '1')
                    (LITERAL ',')
                    (INT '1')
                    (LITERAL ':')
                    (INT '1')
                    (LITERAL '=')
                    (STRING '\\'another\\'')
                    (LITERAL ',<')
                    (INT '2')
                    (LITERAL '>')
                    (LITERAL ',')
                    (INT '1')
                    (LITERAL ':')
                    (INT '1')
                    (LITERAL ']')
                  )
                  (token
                    (LITERAL '[@')
                    (ERROR 'A')
                    (ERROR ',')
                    (ERROR '1')
                    (ERROR ':')
                    (ERROR '1')
                    (ERROR '=')
                    (ERROR ']')
                  )
                  (EOF '<EOF>')
                )""";
        assertEquals(expected,result);
    }

    @Test
    void testToStringTreeForTokenList() {
        String input = "[@0,0:0='test',<1>,0:0][@1,1:1='another',<2>,1:1]";
        AirbagLexer lexer = new AirbagLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AirbagParser parser = new AirbagParser(tokens);
        AirbagParser.TokenListContext tree = parser.tokenList();

        String result =  Utils.formatTreeString(Utils.toStringTree(tree, parser));

        String expected = """
                (tokenlist
                  (token
                    (LITERAL '[@')
                    (INT '0')
                    (LITERAL ',')
                    (INT '0')
                    (LITERAL ':')
                    (INT '0')
                    (LITERAL '=')
                    (STRING '\\'test\\'')
                    (LITERAL ',<')
                    (INT '1')
                    (LITERAL '>')
                    (LITERAL ',')
                    (INT '0')
                    (LITERAL ':')
                    (INT '0')
                    (LITERAL ']')
                  )
                  (token
                    (LITERAL '[@')
                    (INT '1')
                    (LITERAL ',')
                    (INT '1')
                    (LITERAL ':')
                    (INT '1')
                    (LITERAL '=')
                    (STRING '\\'another\\'')
                    (LITERAL ',<')
                    (INT '2')
                    (LITERAL '>')
                    (LITERAL ',')
                    (INT '1')
                    (LITERAL ':')
                    (INT '1')
                    (LITERAL ']')
                  )
                  (EOF '<EOF>')
                )""";
        assertEquals(expected, result);
    }

    @Test
    void testToStringTreeForParseTreeList() {
        String input = "(rule (TOKEN 'text'))";
        AirbagLexer lexer = new AirbagLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AirbagParser parser = new AirbagParser(tokens);
        AirbagParser.TreeContext tree = parser.tree();

        String result = Utils.formatTreeString(Utils.toStringTree(tree, parser));

        String expected = """
                (tree
                  (node
                    (LITERAL '(')
                    (RULE 'rule')
                    (node
                      (LITERAL '(')
                      (TOKEN 'TOKEN')
                      (STRING '\\'text\\'')
                      (LITERAL ')')
                    )
                    (LITERAL ')')
                  )
                  (EOF '<EOF>')
                )""";
        assertEquals(expected, result);
    }

    @Test
    void testTreeEquals() {
        String input = """
                193
                a = 5
                b = 6
                a+b*2
                (1+2)*3
                """;
        var charStream = CharStreams.fromString(input);
        var lexer = new ExpressionLexer(charStream);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new ExpressionParser(tokenStream);
        Tree actual = parser.prog();
        String expectedString = """
                (prog
                  (stat
                    (expr
                      (INT '193')
                    )
                    (NEWLINE '\\n')
                  )
                  (stat
                    (ID 'a')
                    (LITERAL '=')
                    (expr
                      (INT '5')
                    )
                    (NEWLINE '\\n')
                  )
                  (stat
                    (ID 'b')
                    (LITERAL '=')
                    (expr
                      (INT '6')
                    )
                    (NEWLINE '\\n')
                  )
                  (stat
                    (expr
                      (expr
                        (ID 'a')
                      )
                      (LITERAL '+')
                      (expr
                        (expr
                          (ID 'b')
                        )
                        (LITERAL '*')
                        (expr
                          (INT '2')
                        )
                      )
                    )
                    (NEWLINE '\\n')
                  )
                  (stat
                    (expr
                      (expr
                        (LITERAL '(')
                        (expr
                          (expr
                            (INT '1')
                          )
                          (LITERAL '+')
                          (expr
                            (INT '2')
                          )
                        )
                        (LITERAL ')')
                      )
                      (LITERAL '*')
                      (expr
                        (INT '3')
                      )
                    )
                    (NEWLINE '\\n')
                  )
                )
                """;
        charStream = CharStreams.fromString(expectedString);
        var airbagLexer = new AirbagLexer(charStream);
        tokenStream = new CommonTokenStream(airbagLexer);
        var airbagParser = new AirbagParser(tokenStream);
        assertTrue(Utils.treeEquals(airbagParser.tree(), actual));
    }
}
