package io.github.agoss94.airbag;

import io.github.agoss94.airbag.parser.AirbagBaseVisitor;
import io.github.agoss94.airbag.parser.AirbagParser;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class Utils {

    private Utils() {
    }

    public static String toStringTree(Tree tree, Parser parser) {
        if (tree instanceof RuleContext ruleContext) {
            String ruleName = parser.getRuleNames()[ruleContext.getRuleIndex()].toLowerCase();
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(ruleName);
            for (int i = 0; i < tree.getChildCount(); i++) {
                sb.append(" ");
                sb.append(toStringTree(tree.getChild(i), parser));
            }
            sb.append(")");
            return sb.toString();
        } else if (tree instanceof TerminalNode terminalNode) {
            if (terminalNode.getSymbol().getType() == Token.EOF) {
                return "(EOF '<EOF>')";
            }
            String tokenName;
            Vocabulary vocabulary = parser.getVocabulary();
            if (terminalNode instanceof ErrorNode errorNode) {
                tokenName = "ERROR";
            } else {
                tokenName = vocabulary.getSymbolicName(terminalNode.getSymbol().getType());
            }
            tokenName = tokenName == null ? "LITERAL" : tokenName;
            if (tokenName.startsWith("'") && tokenName.endsWith("'")) {
                tokenName = tokenName.substring(1, tokenName.length() - 1);
            }
            String tokenText = terminalNode.getText();
            tokenText = org.antlr.v4.runtime.misc.Utils.escapeWhitespace(tokenText, false);
            String escapedText = "'" + tokenText.replace("'", "\\'") + "'";
            return String.format("(%s %s)", tokenName, escapedText);
        }
        return "";
    }

    public static String formatTreeString(String treeString) {
        return Formatter.formatStringTree(treeString);
    }

    public static <T> boolean listEquals(List<? extends T> list1, List<? extends T> list2, BiPredicate<? super T, ? super T> predicate) {
        if (list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }
        return IntStream.range(0, list1.size())
                .allMatch(i -> predicate.test(list1.get(i), list2.get(i)));
    }

    public static boolean treeEquals(Tree t1, Tree t2) {
        if (t1 == t2) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1 instanceof RuleContext rc1 && t2 instanceof RuleContext rc2) {
            if (rc1.getRuleIndex() != rc2.getRuleIndex()) {
                return false;
            }
        }
        if (t1.getChildCount() != t2.getChildCount()) {
            return false;
        }
        if (t1 instanceof TerminalNode tn1 && t2 instanceof TerminalNode tn2) {
            return tn1.getSymbol().getType() == tn2.getSymbol().getType()
                    && tn1.getText().equals(tn2.getText());
        }
        for (int i = 0; i < t1.getChildCount(); i++) {
            if (!treeEquals(t1.getChild(i), t2.getChild(i))) {
                return false;
            }
        }
        return true;
    }

}
