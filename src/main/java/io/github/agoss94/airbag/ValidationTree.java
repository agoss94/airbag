package io.github.agoss94.airbag;

import org.antlr.v4.runtime.tree.Tree;

public interface ValidationTree {

    /**
     * Validates the given Tree against the Schema provides by the ValidationTree.
     *
     * @param tree the given tree to validate.
     * @return {@code true} if the given tree conforms to the given Validation.
     */
    boolean validate(Tree tree);
}
