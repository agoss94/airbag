package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.SchemaNode;
import org.antlr.v4.runtime.CommonToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link SchemaNode} class.
 */
class SchemaNodeTest {

    @Test
    void testSimpleTreeToString() {
        var root = SchemaNode.Rule.attach(0, null);
        SchemaNode.Terminal.attach(new CommonToken(1, "a"), root);
        SchemaNode.Terminal.attach(new CommonToken(2, "b"), root);
        assertEquals("(0 (1 'a') (2 'b'))", root.toString());
    }

    @Test
    void testModeratelyComplexTreeToString() {
        var root = SchemaNode.Rule.attach(0, null);
        var child1 = SchemaNode.Rule.attach(1, root);
        SchemaNode.Terminal.attach(new CommonToken(1, "a"), child1);
        SchemaNode.Terminal.attach(new CommonToken(2, "b"), child1);
        var child2 = SchemaNode.Rule.attach(4, root);
        SchemaNode.Terminal.attach(new CommonToken(3, "c"), child2);
        var grandchild = SchemaNode.Rule.attach(6, child2);
        SchemaNode.Terminal.attach(new CommonToken(4, "d"), grandchild);
        assertEquals("(0 (1 (1 'a') (2 'b')) (4 (3 'c') (6 (4 'd'))))", root.toString());
    }

    @Test
    void testLiteralToString() {
        var root = SchemaNode.Rule.attach(0, null);
        SchemaNode.Terminal.attach(new CommonToken(1, "="), root);
        assertEquals("(0 (1 '='))", root.toString());
    }

    @Test
    void testErrorNodeToString() {
        var root = SchemaNode.Rule.attach(0, null);
        var error = SchemaNode.Error.attach(new CommonToken(1, "unexpected"), root);
        assertEquals("(0 (<error> (1 'unexpected')))", root.toString());
        assertEquals(error, root.getChild(0));
    }

    @Test
    void testParentAndChild() {
        var root = SchemaNode.Rule.attach(0, null);
        var child = SchemaNode.Rule.attach(1, root);
        assertEquals(root, child.getParent());
        assertEquals(child, root.getChild(0));
    }

    @Test
    void testRootNodeHasItselfAsParent() {
        var root = SchemaNode.Rule.attach(0, null);
        assertEquals(root, root.getParent());
    }

    @Test
    void testTerminalNodeCannotHaveChildren() {
        var root = SchemaNode.Rule.attach(0, null);
        var terminal = SchemaNode.Terminal.attach(new CommonToken(1, "a"), root);
        assertThrows(IllegalArgumentException.class, () -> SchemaNode.Rule.attach(2, terminal));
    }

    @Test
    void testErrorNodeCannotHaveChildren() {
        var root = SchemaNode.Rule.attach(0, null);
        var error = SchemaNode.Error.attach(new CommonToken(1, "unexpected"), root);
        assertThrows(IllegalArgumentException.class, () -> SchemaNode.Rule.attach(2, error));
    }

    @Test
    void testRuleEquals() {
        var root1 = SchemaNode.Rule.attach(0, null);
        SchemaNode.Terminal.attach(new CommonToken(1, "a"), root1);
        SchemaNode.Terminal.attach(new CommonToken(2, "b"), root1);

        var root2 = SchemaNode.Rule.attach(0, null);
        SchemaNode.Terminal.attach(new CommonToken(1, "a"), root2);
        SchemaNode.Terminal.attach(new CommonToken(2, "b"), root2);

        assertEquals(root1, root2);
    }

    @Test
    void testRuleNotEquals() {
        var root1 = SchemaNode.Rule.attach(0, null);
        SchemaNode.Terminal.attach(new CommonToken(1, "a"), root1);
        SchemaNode.Terminal.attach(new CommonToken(2, "b"), root1);

        var root2 = SchemaNode.Rule.attach(0, null);
        SchemaNode.Terminal.attach(new CommonToken(1, "a"), root2);
        SchemaNode.Terminal.attach(new CommonToken(2, "c"), root2);

        assertNotEquals(root1, root2);
    }

    @Test
    void testTerminalEquals() {
        var terminal1 = SchemaNode.Terminal.attach(new CommonToken(1, "a"), null);
        var terminal2 = SchemaNode.Terminal.attach(new CommonToken(1, "a"), null);

        assertEquals(terminal1, terminal2);
    }

    @Test
    void testTerminalNotEquals() {
        var terminal1 = SchemaNode.Terminal.attach(new CommonToken(1, "a"), null);
        var terminal2 = SchemaNode.Terminal.attach(new CommonToken(1, "b"), null);

        assertNotEquals(terminal1, terminal2);
    }

    @Test
    void testErrorEquals() {
        var error1 = SchemaNode.Error.attach(new CommonToken(1, "unexpected"), null);
        var error2 = SchemaNode.Error.attach(new CommonToken(1, "unexpected"), null);

        assertEquals(error1, error2);
    }

    @Test
    void testErrorNotEquals() {
        var error1 = SchemaNode.Error.attach(new CommonToken(1, "unexpected"), null);
        var error2 = SchemaNode.Error.attach(new CommonToken(1, "different"), null);

        assertNotEquals(error1, error2);
    }
}