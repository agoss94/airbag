// Generated from TestCaseParser.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestCaseParser}.
 */
public interface TestCaseParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestCaseParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(TestCaseParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(TestCaseParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseParser#input}.
	 * @param ctx the parse tree
	 */
	void enterInput(TestCaseParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseParser#input}.
	 * @param ctx the parse tree
	 */
	void exitInput(TestCaseParser.InputContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(TestCaseParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(TestCaseParser.TokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestCaseParser#tree}.
	 * @param ctx the parse tree
	 */
	void enterTree(TestCaseParser.TreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestCaseParser#tree}.
	 * @param ctx the parse tree
	 */
	void exitTree(TestCaseParser.TreeContext ctx);
}