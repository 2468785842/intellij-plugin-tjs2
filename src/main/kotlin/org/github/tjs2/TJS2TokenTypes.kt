package org.github.tjs2

import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.RuleIElementType

class TJS2TokenTypes {
    companion object {
        val RULE_ELEMENT_TYPES: MutableList<RuleIElementType?>? =
            PSIElementTypeFactory.getRuleIElementTypes(TJS2Language.INSTANCE)
    }
}