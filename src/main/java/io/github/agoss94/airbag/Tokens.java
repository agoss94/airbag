package io.github.agoss94.airbag;

import io.github.agoss94.airbag.parser.AirbagBaseVisitor;
import io.github.agoss94.airbag.parser.AirbagLexer;
import io.github.agoss94.airbag.parser.AirbagParser;
import org.antlr.v4.runtime.*;

import java.util.List;
import java.util.Objects;

public class Tokens {

    private Tokens() {
    }

    public static String toString(Token token, Recognizer<?, ?> r) {
        String channelStr = "";
        if (token.getChannel() > 0) {
            channelStr = ",channel=" + token.getChannel();
        }
        String txt = token.getText();
        if (txt != null) {
            txt = txt.replace("\n", "\\n");
            txt = txt.replace("\r", "\\r");
            txt = txt.replace("\t", "\\t");
        } else {
            txt = "<no text>";
        }
        String typeString = String.valueOf(token.getType());
        if (r != null) {
            typeString = r.getVocabulary().getDisplayName(token.getType());
        }
        return "[@" +
                token.getTokenIndex() +
                "," +
                token.getStartIndex() +
                ":" +
                token.getStopIndex() +
                "='" +
                txt +
                "',<" +
                typeString +
                ">" +
                channelStr +
                "," +
                token.getLine() +
                ":" +
                token.getCharPositionInLine() +
                "]";
    }

    public static boolean isEqual(Token t1, Token t2) {
        if (t1 == t2) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.getType() == t2.getType()
                && t1.getChannel() == t2.getChannel()
                && t1.getLine() == t2.getLine()
                && t1.getCharPositionInLine() == t2.getCharPositionInLine()
                && t1.getTokenIndex() == t2.getTokenIndex()
                && t1.getStartIndex() == t2.getStartIndex()
                && t1.getStopIndex() == t2.getStopIndex()
                && Objects.equals(t1.getText(), t2.getText());
    }

    public static class Builder {

        private String text;
        private int type;
        private int line;
        private int charPositionInLine;
        private int channel;
        private int tokenIndex;
        private int startIndex;
        private int stopIndex;

        public Builder() {
            text = null;
            type = 0;
            line = -1;
            charPositionInLine = -1;
            channel = -1;
            tokenIndex = -1;
            startIndex = -1;
            stopIndex = -1;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder line(int line) {
            this.line = line;
            return this;
        }

        public Builder charPositionInLine(int charPositionInLine) {
            this.charPositionInLine = charPositionInLine;
            return this;
        }

        public Builder channel(int channel) {
            this.channel = channel;
            return this;
        }

        public Builder tokenIndex(int tokenIndex) {
            this.tokenIndex = tokenIndex;
            return this;
        }

        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        public Builder stopIndex(int stopIndex) {
            this.stopIndex = stopIndex;
            return this;
        }

        public Token build() {
            var token = new CommonToken(type);
            token.setText(text);
            token.setLine(line);
            token.setCharPositionInLine(charPositionInLine);
            token.setChannel(channel);
            token.setTokenIndex(tokenIndex);
            token.setStartIndex(startIndex);
            token.setStopIndex(stopIndex);
            return token;
        }
    }

    public static class Parser {

        private final Vocabulary vocabulary;

        public Parser(Vocabulary vocabulary) {
            this.vocabulary = vocabulary;
        }

        public Token parseToken(String input) {
            return getVisitor(vocabulary).visitToken(getParser(input).token());
        }

        public List<Token> parseTokens(String input) {
            return getVisitor(vocabulary).visitList(getParser(input).list());
        }

        private TokenVisitor getVisitor(Vocabulary vocabulary) {
            var visitor = new TokenVisitor();
            visitor.setVocabulary(vocabulary);
            return visitor;
        }

        private AirbagParser getParser(String input) {
            AirbagLexer lexer = new AirbagLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            return new AirbagParser(tokens);
        }

    }

    private static class TokenVisitor extends AirbagBaseVisitor<Object> {

        private Vocabulary vocabulary;

        public void setVocabulary(Vocabulary vocabulary) {
            this.vocabulary = vocabulary;
        }

        @Override
        public Token visitToken(AirbagParser.TokenContext ctx) {
            Tokens.Builder builder = new Builder();
            builder.tokenIndex(Integer.parseInt(ctx.tokenIndex.getText()));
            builder.startIndex(Integer.parseInt(ctx.startIndex.getText()));
            builder.stopIndex(Integer.parseInt(ctx.stopIndex.getText()));
            // Remove the surrounding quotes from the string literal
            String text = replaceEscapedCharacters(ctx.text.getText());
            builder.text(text.substring(1, text.length() - 1));
            builder.type(getTokenType(ctx.type.getText()));

            if (ctx.channel != null) {
                builder.channel(Integer.parseInt(ctx.channel.getText()));
            } else {
                builder.channel(Token.DEFAULT_CHANNEL);
            }

            builder.line(Integer.parseInt(ctx.line.getText()));
            builder.charPositionInLine(Integer.parseInt(ctx.charPositionInLine.getText()));

            return builder.build();
        }

        @Override
        public List<Token> visitList(AirbagParser.ListContext ctx) {
            return ctx.token().stream().map(this::visitToken).toList();
        }

        private int getTokenType(String type) {
            if (type.matches("-?\\d+")) {
                return Integer.parseInt(type);
            }
            if (vocabulary == null) {
                throw new IllegalStateException(
                        "A vocabulary must be provided to resolve symbolic token name: " + type
                );
            }
            if (type.equals("EOF")) {
                return Token.EOF;
            }
            for (int i = 0; i < vocabulary.getMaxTokenType() + 1; i++) {
                if (Objects.equals(vocabulary.getSymbolicName(i), type)) {
                    return i;
                }
            }
            throw new IllegalArgumentException("Type \"%s\" not found in vocabulary".formatted(type));
        }

        private String replaceEscapedCharacters(String txt) {
            if ( txt!=null ) {
                txt = txt.replace("\\n","\n");
                txt = txt.replace("\\r","\r");
                txt = txt.replace("\\t","\t");
            }
            return txt;
        }
    }
}
