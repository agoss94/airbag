package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Airbag;
import io.github.agoss94.airbag.Tokens;
import io.github.agoss94.airbag.grammar.ExpressionLexer;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import org.antlr.v4.runtime.Token;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AirbagTest {

    private static Tokens.Parser factory;

    @BeforeAll
    static void setup() {
        factory = new Tokens.Parser(ExpressionLexer.VOCABULARY);
    }

    @Test
    void testTokenList() {
        List<Token> expected = factory.parseTokens("""
                [@0,0:2='193',<9>,1:0]
                [@1,3:3='\\n',<10>,1:3]
                [@2,4:4='a',<8>,2:0]
                [@3,6:6='=',<1>,2:2]
                [@4,8:8='5',<9>,2:4]
                [@5,9:9='\\n',<10>,2:5]
                [@6,10:10='b',<8>,3:0]
                [@7,12:12='=',<1>,3:2]
                [@8,14:14='6',<9>,3:4]
                [@9,15:15='\\n',<10>,3:5]
                [@10,16:16='a',<8>,4:0]
                [@11,17:17='+',<4>,4:1]
                [@12,18:18='b',<8>,4:2]
                [@13,19:19='*',<2>,4:3]
                [@14,20:20='2',<9>,4:4]
                [@15,21:21='\\n',<10>,4:5]
                [@16,22:22='(',<6>,5:0]
                [@17,23:23='1',<9>,5:1]
                [@18,24:24='+',<4>,5:2]
                [@19,25:25='2',<9>,5:3]
                [@20,26:26=')',<7>,5:4]
                [@21,27:27='*',<2>,5:5]
                [@22,28:28='3',<9>,5:6]
                [@23,29:29='\\n',<10>,5:7]
                [@24,30:29='<EOF>',<-1>,6:0]
                """);
        List<Token> actual = Airbag.tokenList(ExpressionLexer.class, """
                193
                a = 5
                b = 6
                a+b*2
                (1+2)*3
                """);
        Airbag.assertTokenList(expected, actual);
    }
}
