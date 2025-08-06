// Generated from Test.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestParser}.
 */
public interface TestListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(TestParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(TestParser.FileContext ctx);
}