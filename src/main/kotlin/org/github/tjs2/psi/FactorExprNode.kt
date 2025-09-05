package org.github.tjs2.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiLanguageInjectionHost
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import org.github.tjs2.TJS2Lexer
import org.github.tjs2.adaptors.parser.PsiElementFactory

class FactorExprNode(node: ASTNode?) : ASTWrapperPsiElement(node!!), PsiLanguageInjectionHost {

    /**
     * if is true: childNodes count always two
     */
    fun isRegexpExpr(): Boolean {
        val et = node.firstChildNode.elementType
        if(et !is TokenIElementType)
            return false
        if(et.antlrTokenType != TJS2Lexer.T_REG_EXPR_LITERAL)
            return false
        return true
    }

    override fun isValidHost(): Boolean {
        return true
    }

    override fun updateText(p0: String): PsiLanguageInjectionHost {
        return this
    }

    override fun createLiteralTextEscaper(): LiteralTextEscaper<out PsiLanguageInjectionHost?> {
        return LiteralTextEscaper.createSimple(this, true)
    }

    class Factory : PsiElementFactory {

        override fun createElement(node: ASTNode?) = FactorExprNode(node)

        companion object {
            var INSTANCE: Factory = Factory()
        }
    }
}