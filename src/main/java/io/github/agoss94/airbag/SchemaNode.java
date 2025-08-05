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
     * The index of the node.
     */
    private final int index;

    /**
     * The parent of the node.
     */
    private final Schema parent;

    /**
     * Constructs a new {@link SchemaNode} with the given index and parent.
     *
     * @param index  the index of the node.
     * @param parent the parent of the node.
     */
    protected SchemaNode(int index, Schema parent) {
        this.index = index;
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
    public int index() {
        return index;
    }

    /**
     * Returns the name of the node, which is its index converted to a string.
     *
     * @return the string representation of the node's index.
     */
    private String name() {
        return String.valueOf(index);
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
        return Schemas.toString((Schema) this, null);
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
         * Constructs a new {@link SchemaNode.Rule} with the given index and parent.
         *
         * @param index  the index of the node.
         * @param parent the parent of the node.
         */
        private Rule(int index, Schema parent) {
            super(index, parent);
            children = new ArrayList<>();
        }

        /**
         * Creating and attaching a {@link Schema.Rule } node to the given parent. The created
         * node is returned.
         *
         * @param index  the index of the rule.
         * @param parent the given parent.
         * @return the newly created node.
         */
        public static Schema.Rule attach(int index, Schema parent) {
            return new SchemaNode.Rule(index, parent);
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

        @Override
        public boolean equals(Object o) {
            if (o instanceof Schema.Rule ruleNode) {
                if (index() != ruleNode.index()) {
                    return false;
                } else {
                    int thisChildCount = getChildCount();
                    int thatChildCount = ruleNode.getChildCount();
                    for (int i = 0; i < Math.max(thisChildCount, thatChildCount); i++) {
                        if (!Objects.equals(i < thisChildCount ? getChild(i) : null,
                                i < thatChildCount ? ruleNode.getChild(i) : null)) {
                            return false;
                        }
                    }
                    return true;
                }
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hash(index(), children);
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
         * Constructs a new {@link SchemaNode.Terminal} with the given index, token and parent.
         *
         * @param token  the ANTLR token.
         * @param parent the parent of the node.
         */
        private Terminal(Token token, Schema parent) {
            super(token.getType(), parent);
            this.token = token;
        }

        /**
         * Creating and attaching a {@link Schema.Terminal } node to the given parent. The created
         * node is returned.
         *
         * @param token  the token which is the payload of the schema.
         * @param parent the given parent.
         * @return the newly created node.
         */
        public static Schema.Terminal attach(Token token, Schema parent) {
            return new SchemaNode.Terminal(token, parent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Token token() {
            return token;
        }

        /**
         * Return {@code true} if the two nodes share the same index and text.
         *
         * @param o the other node.
         * @return {@code true} if the two nodes share the same index and text.
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof Schema.Terminal terminalNode) {
                return index() == terminalNode.index() &&
                        token().getText().equals(terminalNode.token().getText());
            } else {
                return false;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Objects.hash(index(), token().getText());
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
         * Constructs a new {@link SchemaNode.Error} with the given index, token and parent.
         *
         * @param token  the ANTLR token.
         * @param parent the parent of the node.
         */
        private Error(Token token, Schema parent) {
            super(token.getType(), parent);
            this.token = token;
        }

        /**
         * Creating and attaching a {@link Schema.Error } node to the given parent. The created
         * node is returned.
         *
         * @param token  the token which is the payload of the schema.
         * @param parent the given parent.
         * @return the newly created node.
         */
        public static Schema.Error attach(Token token, Schema parent) {
            return new SchemaNode.Error(token, parent);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Token token() {
            return token;
        }

        /**
         * Return {@code true} if the two nodes share the same index and text.
         *
         * @param o the other node.
         * @return {@code true} if the two nodes share the same index and text.
         */
        @Override
        public boolean equals(Object o) {
            if (o instanceof Schema.Error errorNode) {
                return index() == errorNode.index() &&
                        token().getText().equals(errorNode.token().getText());
            } else {
                return false;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Objects.hash(index(), token().getText());
        }
    }
}