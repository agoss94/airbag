// Generated from Token.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TokenParser}.
 */
public interface TokenListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TokenParser#tokenList}.
	 * @param ctx the parse tree
	 */
	void enterTokenList(TokenParser.TokenListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TokenParser#tokenList}.
	 * @param ctx the parse tree
	 */
	void exitTokenList(TokenParser.TokenListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TokenParser#singleToken}.
	 * @param ctx the parse tree
	 */
	void enterSingleToken(TokenParser.SingleTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link TokenParser#singleToken}.
	 * @param ctx the parse tree
	 */
	void exitSingleToken(TokenParser.SingleTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link TokenParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(TokenParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link TokenParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(TokenParser.TokenContext ctx);
}