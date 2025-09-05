package org.github.tjs2

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import org.github.tjs2.psi.FactorExprNode
import org.intellij.lang.regexp.RegExpLanguage

class TJS2RegexInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {

        if (context is FactorExprNode && context.isRegexpExpr()) {
            registrar.startInjecting(RegExpLanguage.INSTANCE)

            // construct class header, method header,
            // inject method name, append code block start
            val text = context.text!!
            var start = 1
            val end = text.lastIndexOf("/")
            if (text.startsWith("/=")) {
                start = 2
            }
            registrar.addPlace(
                null, null,
                context as PsiLanguageInjectionHost,
                TextRange(start, end)
            )

            registrar.doneInjecting()
        }
    }

    override fun elementsToInjectIn(): MutableList<out Class<out PsiElement>> {
        return mutableListOf(FactorExprNode::class.java)
    }
}