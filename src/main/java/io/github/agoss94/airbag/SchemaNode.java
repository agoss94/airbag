package io.github.agoss94.airbag;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code SchemaNode} class is a concrete implementation of the {@link Schema} interface.
 * It serves as the base class for the different types of nodes in the validation tree, i.e. {@link Rule},
 * {@link Terminal}, and {@link Error}.
 */
public sealed abstract class SchemaNode permits SchemaNode.Rule, SchemaNode.Terminal, SchemaNode.Error {

    /**
     * The name of the node.
     */
    private final String name;

    /**
     * The parent of the node.
     */
    private final Schema parent;

    /**
     * Constructs a new {@link SchemaNode} with the given name and parent.
     *
     * @param name   the name of the node.
     * @param parent the parent of the node.
     */
    protected SchemaNode(String name, Schema parent) {
        this.name = name;
        if (parent == null) {
            this.parent = (Schema) this;
        } else if (parent instanceof SchemaNode.Rule rule) {
            this.parent = rule;
            rule.children.add((Schema) this);
        } else {
            throw new IllegalArgumentException(
                    "Error and Terminal node cannot have children");
        }
    }

    /**
     * {@inheritDoc}
     */
    public String name() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public Schema getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        switch (this) {
            case Rule rule -> {
                var sb = new StringBuilder("(%s".formatted(name()));
                for (int i = 0; i < rule.getChildCount(); i++) {
                    sb.append(" %s".formatted(rule.getChild(i)));
                }
                sb.append(")");
                return sb.toString();
            }
            case Terminal terminal -> {
                if (terminal.isLiteral()) {
                    return "'%s'".formatted(terminal.token().getText());
                }
                return "(%s '%s')".formatted(name(), terminal.token().getText());
            }
            case Error error -> {
                return "(<error> (%s '%s'))".formatted(error.name(), error.token().getText());
            }
        }
    }

    /**
     * A {@link SchemaNode} that represents a non-terminal parser rule.
     */
    public final static class Rule extends SchemaNode implements Schema.Rule {

        /**
         * The children of the node.
         */
        private final List<Schema> children;

        /**
         * Constructs a new {@link SchemaNode.Rule} with the given name and parent.
         *
         * @param name   the name of the node.
         * @param parent the parent of the node.
         */
        public Rule(String name, Schema parent) {
            super(Objects.requireNonNull(name), parent);
            children = new ArrayList<>();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Schema getChild(int i) {
            return children.get(i);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getChildCount() {
            return children.size();
        }
    }

    /**
     * A {@link SchemaNode} that represents a terminal symbol (a token).
     */
    public final static class Terminal extends SchemaNode implements Schema.Terminal {

        /**
         * The ANTLR token.
         */
        private final Token token;

        /**
         * A boolean indicating if the node is a literal.
         */
        private final boolean isLiteral;

        /**
         * Constructs a new {@link SchemaNode.Terminal} with the given name, token and parent.
         *
         * @param name   the name of the node.
         * @param token  the ANTLR token.
         * @param parent the parent of the node.
         */
        public Terminal(String name, Token token, Schema parent) {
            super(name, parent);
            isLiteral = name == null;
            this.token = token;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Token token() {
            return token;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isLiteral() {
            return isLiteral;
        }

    }

    /**
     * A {@link SchemaNode} that represents a syntax error in the parse tree.
     */
    public final static class Error extends SchemaNode implements Schema.Error {

        /**
         * The ANTLR token.
         */
        private final Token token;

        /**
         * Constructs a new {@link SchemaNode.Error} with the given name, token and parent.
         *
         * @param name   the name of the node.
         * @param token  the ANTLR token.
         * @param parent the parent of the node.
         */
        public Error(String name, Token token, Schema parent) {
            super(name, parent);
            this.token = token;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Token token() {
            return token;
        }
    }
}
