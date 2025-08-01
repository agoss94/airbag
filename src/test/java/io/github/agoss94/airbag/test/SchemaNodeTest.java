package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.SchemaNode;
import org.antlr.v4.runtime.CommonToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link SchemaNode} class.
 */
class SchemaNodeTest {

    @Test
    void testSimpleTreeToString() {
        var root = new SchemaNode.Rule(0, null);
        new SchemaNode.Terminal(new CommonToken(1, "a"), root);
        new SchemaNode.Terminal(new CommonToken(2, "b"), root);
        assertEquals("(0 (1 'a') (2 'b'))", root.toString());
    }

    @Test
    void testModeratelyComplexTreeToString() {
        var root = new SchemaNode.Rule(0, null);
        var child1 = new SchemaNode.Rule(1, root);
        new SchemaNode.Terminal(new CommonToken(1, "a"), child1);
        new SchemaNode.Terminal(new CommonToken(2, "b"), child1);
        var child2 = new SchemaNode.Rule(4, root);
        new SchemaNode.Terminal(new CommonToken(3, "c"), child2);
        var grandchild = new SchemaNode.Rule(6, child2);
        new SchemaNode.Terminal(new CommonToken(4, "d"), grandchild);
        assertEquals("(0 (1 (1 'a') (2 'b')) (4 (3 'c') (6 (4 'd'))))", root.toString());
    }

    @Test
    void testLiteralToString() {
        var root = new SchemaNode.Rule(0, null);
        new SchemaNode.Terminal(new CommonToken(1, "="), root);
        assertEquals("(0 (1 '='))", root.toString());
    }

    @Test
    void testErrorNodeToString() {
        var root = new SchemaNode.Rule(0, null);
        new SchemaNode.Error(new CommonToken(1, "unexpected"), root);
        assertEquals("(0 (<error> (1 'unexpected')))", root.toString());
    }

    @Test
    void testParentAndChild() {
        var root = new SchemaNode.Rule(0, null);
        var child = new SchemaNode.Rule(1, root);
        assertEquals(root, child.getParent());
        assertEquals(child, root.getChild(0));
    }

    @Test
    void testRootNodeHasItselfAsParent() {
        var root = new SchemaNode.Rule(0, null);
        assertEquals(root, root.getParent());
    }

    @Test
    void testTerminalNodeCannotHaveChildren() {
        var root = new SchemaNode.Rule(0, null);
        var terminal = new SchemaNode.Terminal(new CommonToken(1, "a"), root);
        assertThrows(IllegalArgumentException.class, () -> new SchemaNode.Rule(2, terminal));
    }

    @Test
    void testErrorNodeCannotHaveChildren() {
        var root = new SchemaNode.Rule(0, null);
        var error = new SchemaNode.Error(new CommonToken(1, "unexpected"), root);
        assertThrows(IllegalArgumentException.class, () -> new SchemaNode.Rule(2, error));
    }
}