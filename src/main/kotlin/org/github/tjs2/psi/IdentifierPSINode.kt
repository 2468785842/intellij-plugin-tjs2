package org.github.tjs2.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiReference
import com.intellij.psi.tree.IElementType
import com.intellij.util.IncorrectOperationException
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode
import org.antlr.intellij.adaptor.psi.Trees
import org.github.tjs2.TJS2Language
import org.jetbrains.annotations.NonNls

class IdentifierPSINode(type: IElementType?, text: CharSequence?) : ANTLRPsiLeafNode(type, text), PsiNamedElement {
//    val name = getText()

    @Throws(IncorrectOperationException::class)
    override fun setName(name: @NonNls String): PsiElement {
        return this
    }

//    val reference: PsiReference?
//        get() {
////            val parent: PsiElement = getParent()
////            val elType: IElementType = parent.node.elementType
//            // do not return a reference for the ID nodes in a definition
////            if (elType is RuleIElementType) {
////                when (elType.ruleIndex) {
////                    RULE_statement, RULE_expr, RULE_primary -> return VariableRef(this)
////                    RULE_call_expr -> return FunctionRef(this)
////                }
////            }
//            return null
//        }
}