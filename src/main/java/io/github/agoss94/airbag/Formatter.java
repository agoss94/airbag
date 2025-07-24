package io.github.agoss94.airbag;

import io.github.agoss94.airbag.parser.AirbagBaseListener;
import io.github.agoss94.airbag.parser.AirbagLexer;
import io.github.agoss94.airbag.parser.AirbagParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Formatter {

    public static String formatStringTree(String tree) {
        CharStream charStream = CharStreams.fromString(tree);
        AirbagLexer lexer = new AirbagLexer(charStream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        AirbagParser parser = new AirbagParser(tokenStream);
        Listener listener = new Listener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, parser.tree());
        return listener.format();
    }

    private static class Listener extends AirbagBaseListener {

        int indentLevel = 0;
        StringBuilder stringBuilder;

        public Listener() {
            this.stringBuilder = new StringBuilder();
        }

        @Override
        public void enterRuleNode(AirbagParser.RuleNodeContext ctx) {
            stringBuilder.append("  ".repeat(indentLevel));
            stringBuilder.append("(%s%n".formatted(ctx.RULE().getText()));
            indentLevel++;

        }

        @Override
        public void exitRuleNode(AirbagParser.RuleNodeContext ctx) {
            indentLevel--;
            stringBuilder.append("  ".repeat(indentLevel));
            stringBuilder.append(")%n".formatted());
        }

        @Override
        public void enterTokenNode(AirbagParser.TokenNodeContext ctx) {
            stringBuilder.append("  ".repeat(indentLevel));
            stringBuilder.append("(%s %s)%n".formatted(ctx.TOKEN().getText(), ctx.STRING().getText()));
        }

        public String format() {
            return stringBuilder.toString().trim();
        }
    }
}
