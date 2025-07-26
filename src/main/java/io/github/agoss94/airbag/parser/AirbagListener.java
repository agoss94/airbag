// Generated from Airbag.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AirbagParser}.
 */
public interface AirbagListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AirbagParser#tree}.
	 * @param ctx the parse tree
	 */
	void enterTree(AirbagParser.TreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link AirbagParser#tree}.
	 * @param ctx the parse tree
	 */
	void exitTree(AirbagParser.TreeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ruleNode}
	 * labeled alternative in {@link AirbagParser#node}.
	 * @param ctx the parse tree
	 */
	void enterRuleNode(AirbagParser.RuleNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ruleNode}
	 * labeled alternative in {@link AirbagParser#node}.
	 * @param ctx the parse tree
	 */
	void exitRuleNode(AirbagParser.RuleNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tokenNode}
	 * labeled alternative in {@link AirbagParser#node}.
	 * @param ctx the parse tree
	 */
	void enterTokenNode(AirbagParser.TokenNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tokenNode}
	 * labeled alternative in {@link AirbagParser#node}.
	 * @param ctx the parse tree
	 */
	void exitTokenNode(AirbagParser.TokenNodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link AirbagParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(AirbagParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AirbagParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(AirbagParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AirbagParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(AirbagParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link AirbagParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(AirbagParser.TokenContext ctx);
}