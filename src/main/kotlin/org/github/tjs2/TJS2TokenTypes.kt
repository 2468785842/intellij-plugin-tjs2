package org.github.tjs2

import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import org.antlr.intellij.adaptor.lexer.TokenIElementType

class TJS2TokenTypes {
    companion object {

        val TOKEN_ELEMENT_TYPES: MutableList<TokenIElementType> =
            PSIElementTypeFactory.getTokenIElementTypes(TJS2Language.INSTANCE)
        val RULE_ELEMENT_TYPES: MutableList<RuleIElementType> =
            PSIElementTypeFactory.getRuleIElementTypes(TJS2Language.INSTANCE)
    }
}