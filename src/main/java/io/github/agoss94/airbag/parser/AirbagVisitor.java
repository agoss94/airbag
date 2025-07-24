// Generated from Airbag.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AirbagParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AirbagVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AirbagParser#tree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTree(AirbagParser.TreeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ruleNode}
	 * labeled alternative in {@link AirbagParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuleNode(AirbagParser.RuleNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tokenNode}
	 * labeled alternative in {@link AirbagParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTokenNode(AirbagParser.TokenNodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AirbagParser#tokenList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTokenList(AirbagParser.TokenListContext ctx);
	/**
	 * Visit a parse tree produced by {@link AirbagParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(AirbagParser.TokenContext ctx);
}