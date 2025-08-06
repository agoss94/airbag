package io.github.agoss94.airbag;

import org.antlr.v4.runtime.*;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Utility class for working with ANTLR {@link Token}s.
 */
public final class Tokens {

    /**
     * The pattern to parse a single token from a string.
     */
    private static final Pattern PATTERN = Pattern.compile(
            "\\[@(-?\\d+),(\\d+):(\\d+)='(.*)',<(.*)>,(channel=(-?\\d+),)?(\\d+):(\\d+)]"
    );

    /**
     * Private constructor to prevent instantiation.
     */
    private Tokens() {
    }

    /**
     * Creates a list of {@link Token}s from a given lexer class and input string.
     *
     * @param input        the input string to tokenize.
     * @param grammarClass the lexer class to use for tokenizing the input.
     * @return a list of {@link Token}s generated from the input.
     * @throws RuntimeException if the lexer cannot be instantiated or an error occurs during tokenization.
     */
    public static List<Token> from(String input, Class<? extends Lexer> grammarClass) {
        try {
            Lexer lexer = grammarClass.getConstructor(CharStream.class).newInstance(CharStreams.fromString(
                    input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            tokens.fill(); // Populate the token stream
            return tokens.getTokens();
        } catch (Exception e) {
            throw new RuntimeException("Failed to test lexer", e);
        }
    }

    /**
     * Creates a list of {@link Token}s from a given input string.
     *
     * @param input the input string to tokenize.
     * @return a list of {@link Token}s.
     */
    public static List<Token> from(String input) {
        return from(input, (Vocabulary) null);
    }

    /**
     * Creates a list of {@link Token}s from a given vocabulary and input string.
     *
     * @param input      the input string to tokenize.
     * @param vocabulary the ANTLR vocabulary.
     * @return a list of {@link Token}s.
     */
    public static List<Token> from(String input, Vocabulary vocabulary) {
        if (input.isBlank()) {
            return List.of();
        }
        String[] lines = input.split("\r?\n");
        return Stream.of(lines).map(t -> singleToken(t, vocabulary)).toList();
    }

    /**
     * Creates a single {@link Token} from a given vocabulary and input string.
     * The default escape character is {@code %}.
     *
     * @param vocabulary the ANTLR vocabulary.
     * @param input      the input string to tokenize.
     * @return a single {@link Token}.
     */
    public static Token singleToken(String input, Vocabulary vocabulary) {
        return singleToken(input,  "%", vocabulary);
    }

    /**
     * Returns a new {@link Builder} for creating a {@link Token}.
     *
     * @return a new {@link Builder}.
     */
    public static Builder singleTokenOf() {
        return new Builder();
    }

    /**
     * Creates a single {@link Token} from a given vocabulary and input string.
     *
     * @param vocabulary the ANTLR vocabulary.
     * @param input      the input string to tokenize.
     * @param escape     the escape character used for special characters like newline, tab, and carriage return.
     * @return a single {@link Token}.
     */
    public static Token singleToken(String input, String escape, Vocabulary vocabulary) {
        Matcher matcher = PATTERN.matcher(input);
        if (matcher.matches()) {
            String text = replaceEscaped(matcher.group(4), escape);
            var token = new CommonToken(getTokenType(matcher.group(5), vocabulary), text);
            token.setTokenIndex(Integer.parseInt(matcher.group(1)));
            token.setStartIndex(Integer.parseInt(matcher.group(2)));
            token.setStopIndex(Integer.parseInt(matcher.group(3)));
            token.setChannel(matcher.group(6) != null ? Integer.parseInt(matcher.group(7)) : 0);
            token.setLine(Integer.parseInt(matcher.group(8)));
            token.setCharPositionInLine(Integer.parseInt(matcher.group(9)));
            return token;
        } else {
            throw new RuntimeException("The line: \"%s\" does not match the pattern.".formatted(
                    input));
        }
    }

    /**
     * Returns the token type for the given symbolic name.
     *
     * @param type the symbolic name of the token type.
     * @param voc  the ANTLR vocabulary.
     * @return the token type.
     */
    public static int getTokenType(String type, Vocabulary voc) {
        if (type.matches("-?\\d+")) {
            return Integer.parseInt(type);
        }
        for (int i = 0; i < voc.getMaxTokenType() + 1; i++) {
            if (type.equals("EOF")) {
                return Token.EOF;
            }
            if (Objects.equals(voc.getSymbolicName(i), type)) {
                return i;
            }
            if (Objects.equals(voc.getLiteralName(i), type)) {
                return i;
            }
        }
        throw new RuntimeException("Type \"%s\" not found".formatted(type));
    }

    /**
     * Checks if two {@link Token}s are equal by comparing their properties.
     *
     * @param t1 the first token.
     * @param t2 the second token.
     * @return {@code true} if the tokens are equal, {@code false} otherwise.
     */
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

    /**
     * Returns a string representation of the given token.
     * The default escape character is {@code %}.
     *
     * @param t   the token.
     * @param voc the ANTLR vocabulary.
     * @return a string representation of the token.
     */
    public static String toString(Token t, Vocabulary voc) {
        return toString(t, voc, "%");
    }

    /**
     * Returns a string representation of the given token.
     *
     * @param t      the token.
     * @param voc    the ANTLR vocabulary.
     * @param escape the escape character used for special characters like newline, tab, and carriage return.
     * @return a string representation of the token.
     */
    public static String toString(Token t, Vocabulary voc, String escape) {
        String channelStr = "";
        if (t.getChannel() > 0) {
            channelStr = ",channel=" + t.getChannel();
        }
        String txt = t.getText();
        txt = Utils.escape(txt, escape);
        int type = t.getType();
        String typeString = String.valueOf(type);
        if (voc != null) {
            String symbolicName = voc.getSymbolicName(type);
            String literalName = voc.getLiteralName(type);
            if (symbolicName != null) {
                typeString = symbolicName;
            } else if (literalName != null) {
                typeString = literalName;
            }
        }
        return "[@" +
                t.getTokenIndex() +
                "," +
                t.getStartIndex() +
                ":" +
                t.getStopIndex() +
                "='" +
                txt +
                "',<" +
                typeString +
                ">" +
                channelStr +
                "," +
                t.getLine() +
                ":" +
                t.getCharPositionInLine() +
                "]";
    }

    /**
     * Replaces escaped characters in the given string with their original characters.
     *
     * @param txt    the string to replace.
     * @param escape the escape character.
     * @return the replaced string.
     */
    private static String replaceEscaped(String txt, String escape) {
        if (txt != null) {
            txt = txt.replace("%sn".formatted(escape), "\n");
            txt = txt.replace("%sr".formatted(escape), "\r");
            txt = txt.replace("%st".formatted(escape), "\t");
            return txt;
        } else {
            return null;
        }
    }

    /**
     * A builder for creating a {@link Token}.
     */
    public static class Builder {

        private int tokenIndex;
        private int startIndex;
        private int stopIndex;
        private String txt = "";
        private int type;
        private int channel;
        private int line;
        private int charPositionInLine;

        /**
         * Sets the token index.
         *
         * @param tokenIndex the token index.
         * @return the builder.
         */
        public Builder tokenIndex(int tokenIndex) {
            this.tokenIndex = tokenIndex;
            return this;
        }

        /**
         * Sets the start index.
         *
         * @param startIndex the start index.
         * @return the builder.
         */
        public Builder startIndex(int startIndex) {
            this.startIndex = startIndex;
            return this;
        }

        /**
         * Sets the stop index.
         *
         * @param stopIndex the stop index.
         * @return the builder.
         */
        public Builder stopIndex(int stopIndex) {
            this.stopIndex = stopIndex;
            return this;
        }

        /**
         * Sets the text.
         *
         * @param text the text.
         * @return the builder.
         */
        public Builder text(String text) {
            this.txt = text;
            return this;
        }

        /**
         * Sets the type.
         *
         * @param type the type.
         * @return the builder.
         */
        public Builder type(int type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the channel.
         *
         * @param channel the channel.
         * @return the builder.
         */
        public Builder channel(int channel) {
            this.channel = channel;
            return this;
        }

        /**
         * Sets the line.
         *
         * @param line the line.
         * @return the builder.
         */
        public Builder line(int line) {
            this.line = line;
            return this;
        }

        /**
         * Sets the char position in line.
         *
         * @param charPositionInLine the char position in line.
         * @return the builder.
         */
        public Builder charPositionInLine(int charPositionInLine) {
            this.charPositionInLine = charPositionInLine;
            return this;
        }

        /**
         * Builds the token.
         *
         * @return the token.
         */
        public Token get() {
            var token = new CommonToken(type, txt);
            token.setTokenIndex(tokenIndex);
            token.setStartIndex(startIndex);
            token.setStopIndex(stopIndex);
            token.setChannel(channel);
            token.setLine(line);
            token.setCharPositionInLine(charPositionInLine);
            return token;
        }

    }
}
