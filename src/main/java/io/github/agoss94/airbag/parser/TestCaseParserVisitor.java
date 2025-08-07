// Generated from TestCaseParser.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TestCaseParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TestCaseParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TestCaseParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(TestCaseParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestCaseParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(TestCaseParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestCaseParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(TestCaseParser.TokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link TestCaseParser#tree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTree(TestCaseParser.TreeContext ctx);
}