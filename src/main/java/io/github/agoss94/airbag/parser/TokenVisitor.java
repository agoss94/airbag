// Generated from Token.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TokenParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TokenVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TokenParser#tokenList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTokenList(TokenParser.TokenListContext ctx);
	/**
	 * Visit a parse tree produced by {@link TokenParser#singleToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleToken(TokenParser.SingleTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link TokenParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(TokenParser.TokenContext ctx);
}