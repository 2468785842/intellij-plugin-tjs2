package org.github.tjs2

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLanguageInjectionHost
import org.github.tjs2.psi.FactorExprNode
import org.intellij.lang.regexp.RegExpLanguage

class TJS2RegexInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        if (context is FactorExprNode && context.isRegexpExpr()) {
            registrar.startInjecting(RegExpLanguage.INSTANCE)

            // construct class header, method header,
            // inject method name, append code block start
            val text = context.firstChild.text!!
            val start = if (text.startsWith("/=")) 2 else 1
            val end = text.lastIndexOf("/")
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