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
     * Returns the name of the node.
     * <p>
     * For a non-terminal node, this is the parser rule name (e.g., "stat", "expr").
     * For a terminal node, this is the token type name (e.g., "ID", "INT", "'+'").
     * For an error node, this is the special marker "&lt;error&gt;".
     *
     * @return the name of the parser rule, token type, or error marker.
     */
    String name();

    /**
     * Returns the parent of this node in the validation tree.
     *
     * @return the parent {@code Schema} node, or {@code null} if this is the root of the tree.
     */
    Schema getParent();


    /**
     * Converts the validation tree to an S-expression representation.
     * S-expressions are a Lisp-style notation for nested list data, which in this case represents the parse tree.
     * This provides a human-readable format for visualizing the structure of the validation tree, similar to
     * formats used by tools like TreeSitter.
     * <p>
     * For example, given the following ANTLR grammar for expressions:
     * <pre>{@code
     * stat: expr NEWLINE
     *     | ID '=' expr NEWLINE
     *     ;
     *
     * expr: expr ('*'|'/') expr
     *     | expr ('+'|'-') expr
     *     | INT
     *     | ID
     *     ;
     * }</pre>
     * An input like {@code x = 1 + 2} followed by a newline would be parsed into a tree.
     * This method would then produce the following S-expression for the {@code stat} rule:
     * <pre>{@code
     * (stat (ID 'x') '=' (expr (expr (INT '1')) '+' (expr (INT '2'))) (NEWLINE '\n'))
     * }</pre>
     * In this representation:
     * <ul>
     *     <li>{@code stat}, {@code expr} are parser rule names (non-terminals).</li>
     *     <li>{@code (ID 'x')}, {@code '='}, {@code (INT '1')}, {@code '+'}, {@code (INT '2')}, {@code (NEWLINE '\n')} are terminal tokens.
     *         A token is represented as {@code (TOKEN_TYPE 'value')} or as a literal string for fixed tokens like {@code '='}.</li>
     * </ul>
     *
     * <h4>Error Handling</h4>
     * If the parser encounters a syntax error, it will be represented by an {@code <error>} node.
     * For example, the invalid input {@code 5 * / 6} would produce an S-expression like this:
     * <pre>{@code
     * (expr (expr (INT '5')) '*' (<error> (DIV '/')) (expr (INT '6')))
     * }</pre>
     *
     * @return a string representing the tree in S-expression format.
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
         * Return {@code true} if the terminal node is a literal, indicating that {@link Schema#name()}
         * is {@code null}
         *
         * @return {@code true} if the terminal node is a literal
         */
        boolean isLiteral();

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

    }
}
