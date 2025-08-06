package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.Airbag;
import io.github.agoss94.airbag.grammar.ExpressionParser;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

class AirbagTest {

    @Test
    void testAssertTestFile() throws URISyntaxException {
        Airbag airbag = new Airbag(ExpressionParser.class);
        airbag.assertTestFile(Path.of(getClass().getResource("/testfile001.txt").toURI()).toString(), ExpressionParser.class);
    }

}
