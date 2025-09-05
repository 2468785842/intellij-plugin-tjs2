package org.github.tjs2.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.LiteralTextEscaper
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.impl.source.tree.PsiCommentImpl
import org.github.tjs2.adaptors.parser.PsiElementFactory

class RegexpNode(node: ASTNode?) : ASTWrapperPsiElement(node!!), PsiLanguageInjectionHost {
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

        override fun createElement(node: ASTNode?): PsiElement {
            return RegexpNode(node)
        }

        companion object {
            var INSTANCE: Factory = Factory()
        }
    }
}