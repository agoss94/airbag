package io.github.agoss94.airbag;

import org.antlr.v4.runtime.Token;

/**
 * Represents a node in a validation tree, serving as a clean abstraction over an ANTLR parse tree.
 * <p>
 * This interface decouples the core validation logic from the specific implementation details
 * of ANTLR's {@code org.antlr.v4.runtime.tree.ParseTree}. It provides a simplified
 * and uniform way to navigate the tree structure, access node properties, and compare
 * an actual parse result against an expected structure.
 */
public sealed interface Schema permits Schema.Rule, Schema.Terminal, Schema.Error {

    /**
     * Returns the index of the node.
     * <p>
     * For a non-terminal node, this is the parser rule index.
     * For a terminal node, this is the token type.
     * For an error node, this is the token type of the erroneous token.
     *
     * @return the index of the parser rule or token type.
     */
    int index();

    /**
     * Returns the parent of this node in the validation tree.
     * <p>
     * The root of the tree has itself as a parent.
     *
     * @return the parent {@code Schema} node.
     */
    Schema getParent();


    /**
     * Converts the Schema tree to an S-expression representation.
     * S-expressions provide a human-readable format for visualizing the nested structure of the tree.
     * <p>
     * The format is as follows:
     * <ul>
     *     <li>A <b>rule node</b> is represented as {@code (INDEX CHILD_1 CHILD_2 ...)}, where {@code INDEX} is the rule index.</li>
     *     <li>A <b>terminal node</b> is represented as {@code (INDEX 'text')}, where {@code INDEX} is the token type and {@code 'text'} is the token's text.</li>
     *     <li>An <b>error node</b> is represented as {@code (<error> (INDEX 'text'))}, indicating a syntax error.</li>
     * </ul>
     *
     * <h4>Example</h4>
     * For example, given the following ANTLR grammar:
     * <pre>{@code
     * grammar Expression;
     * stat : expr NEWLINE | ID '=' expr NEWLINE | NEWLINE;
     * expr : INT | ID;
     * ID: [a-zA-Z]+;
     * INT: [0-9]+;
     * NEWLINE: '\r'?'\n';
     * WS: [ \t]+ -> skip;
     * // Assuming token types: '='=1, ID=8, INT=9
     * // Assuming rule indices: stat=1, expr=2
     * }</pre>
     * An input like {@code x = 1} would be parsed into a tree, producing the following S-expression:
     * <pre>{@code
     * (1 (8 'x') (1 '=') (2 (9 '1')))
     * }</pre>
     *
     * @return a string representing the schema in S-expression format.
     */
    String toString();

    /**
     * A {@link Schema} node that represents a non-terminal parser rule.
     * <p>
     * This corresponds to a {@code RuleNode} in the ANTLR parse tree. It serves as an internal node
     * in the schema tree and has children that are other rules or terminals.
     */
    non-sealed interface Rule extends Schema {

        /**
         * Returns the child of this node at the specified index.
         *
         * @param i the index of the child to return.
         * @return the child {@code Schema} node at the given index.
         * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getChildCount()).
         */
        Schema getChild(int i);

        /**
         * Returns the number of children of this node.
         *
         * @return the number of children.
         */
        int getChildCount();
    }

    /**
     * A {@link Schema} node that represents a terminal symbol (a token).
     * <p>
     * This corresponds to a {@code TerminalNode} in the ANTLR parse tree. It is a leaf in the
     * schema tree and has no children.
     */
    non-sealed interface Terminal extends Schema {

        /**
         * Returns the ANTLR token associated with this terminal node.
         * <p>
         * For a schema constructed from a real parse tree, this will be the actual token.
         * For a manually constructed schema, this can be a lightweight implementation of the {@link Token} interface.
         *
         * @return The ANTLR {@link Token}.
         */
        Token token();

        /**
         * Returns the token type of the underlying token.
         * @return the token type.
         */
        @Override
        default int index() {
            return token().getType();
        }
    }

    /**
     * A {@link Schema} node that represents a syntax error in the parse tree.
     * <p>
     * This corresponds to an {@code ErrorNode} in the ANTLR parse tree. It is a leaf in the
     * schema tree and indicates that the parser encountered an unexpected token.
     */
    non-sealed interface Error extends Schema {

        /**
         * Returns the ANTLR token that caused the error.
         *
         * @return The ANTLR {@link Token}.
         */
        Token token();

        /**
         * Returns the token type of the underlying error token.
         * @return the token type.
         */
        @Override
        default int index() {
            return token().getType();
        }
    }
}
