// Generated from Schema.g4 by ANTLR 4.13.1
package io.github.agoss94.airbag.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SchemaParser}.
 */
public interface SchemaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SchemaParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(SchemaParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SchemaParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(SchemaParser.SchemaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ruleNode}
	 * labeled alternative in {@link SchemaParser#node}.
	 * @param ctx the parse tree
	 */
	void enterRuleNode(SchemaParser.RuleNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ruleNode}
	 * labeled alternative in {@link SchemaParser#node}.
	 * @param ctx the parse tree
	 */
	void exitRuleNode(SchemaParser.RuleNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tokenNode}
	 * labeled alternative in {@link SchemaParser#node}.
	 * @param ctx the parse tree
	 */
	void enterTokenNode(SchemaParser.TokenNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tokenNode}
	 * labeled alternative in {@link SchemaParser#node}.
	 * @param ctx the parse tree
	 */
	void exitTokenNode(SchemaParser.TokenNodeContext ctx);
}