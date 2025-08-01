package io.github.agoss94.airbag.test;

import io.github.agoss94.airbag.SchemaNode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SchemaNodeTest {

    @Test
    void testSimpleTreeToString() {
        var root = new SchemaNode.Rule("root", null);
        new SchemaNode.Terminal("A", new CommonToken(1, "a"), root);
        new SchemaNode.Terminal("B", new CommonToken(2, "b"), root);
        assertEquals("(root (A 'a') (B 'b'))", root.toString());
    }

    @Test
    void testModeratelyComplexTreeToString() {
        var root = new SchemaNode.Rule("root", null);
        var child1 = new SchemaNode.Rule("child1", root);
        new SchemaNode.Terminal("A", new CommonToken(1, "a"), child1);
        new SchemaNode.Terminal("B", new CommonToken(2, "b"), child1);
        var child2 = new SchemaNode.Rule("child2", root);
        new SchemaNode.Terminal("C", new CommonToken(3, "c"), child2);
        var grandchild = new SchemaNode.Rule("grandchild", child2);
        new SchemaNode.Terminal("D", new CommonToken(4, "d"), grandchild);
        assertEquals("(root (child1 (A 'a') (B 'b')) (child2 (C 'c') (grandchild (D 'd'))))", root.toString());
    }

    @Test
    void testLiteralToString() {
        var root = new SchemaNode.Rule("root", null);
        new SchemaNode.Terminal(null, new CommonToken(1, "="), root);
        assertEquals("(root '=')", root.toString());
    }

    @Test
    void testErrorNodeToString() {
        var root = new SchemaNode.Rule("root", null);
        new SchemaNode.Error("TOKEN", new CommonToken(1, "unexpected"), root);
        assertEquals("(root (<error> (TOKEN 'unexpected')))", root.toString());
    }

    @Test
    void testParentAndChild() {
        var root = new SchemaNode.Rule("root", null);
        var child = new SchemaNode.Rule("child", root);
        assertEquals(root, child.getParent());
        assertEquals(child, root.getChild(0));
    }

    @Test
    void testRootNodeHasItselfAsParent() {
        var root = new SchemaNode.Rule("root", null);
        assertEquals(root, root.getParent());
    }

    @Test
    void testTerminalNodeCannotHaveChildren() {
        var root = new SchemaNode.Rule("root", null);
        var terminal = new SchemaNode.Terminal("A", new CommonToken(1, "a"), root);
        assertThrows(IllegalArgumentException.class, () -> new SchemaNode.Rule("child", terminal));
    }

    @Test
    void testErrorNodeCannotHaveChildren() {
        var root = new SchemaNode.Rule("root", null);
        var error = new SchemaNode.Error("error", new CommonToken(1, "unexpected"), root);
        assertThrows(IllegalArgumentException.class, () -> new SchemaNode.Rule("child", error));
    }
}
