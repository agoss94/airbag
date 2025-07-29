package io.github.agoss94.airbag.core;

import io.github.agoss94.airbag.parser.SchemaBaseListener;
import io.github.agoss94.airbag.parser.SchemaLexer;
import io.github.agoss94.airbag.parser.SchemaParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Formatter {

    public static String formatStringTree(String tree) {
        CharStream charStream = CharStreams.fromString(tree);
        SchemaLexer lexer = new SchemaLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        SchemaParser parser = new SchemaParser(tokenStream);
        Listener listener = new Listener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parser.schema());
        return listener.format();
    }

    private static class Listener extends SchemaBaseListener {

        int indentLevel = 0;
        StringBuilder stringBuilder;

        public Listener() {
            this.stringBuilder = new StringBuilder();
        }

        @Override
        public void enterRuleNode(SchemaParser.RuleNodeContext ctx) {
            stringBuilder.append("  ".repeat(indentLevel));
            stringBuilder.append("(%s%n".formatted(ctx.RULE().getText()));
            indentLevel++;

        }

        @Override
        public void exitRuleNode(SchemaParser.RuleNodeContext ctx) {
            indentLevel--;
            stringBuilder.append("  ".repeat(indentLevel));
            stringBuilder.append(")%n".formatted());
        }

        @Override
        public void enterTokenNode(SchemaParser.TokenNodeContext ctx) {
            stringBuilder.append("  ".repeat(indentLevel));
            stringBuilder.append("(%s %s)%n".formatted(ctx.TOKEN().getText(), ctx.STRING().getText()));
        }

        public String format() {
            return stringBuilder.toString().trim();
        }
    }
}
