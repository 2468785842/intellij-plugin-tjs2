package org.github.tjs2.adaptors.parser

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

/**
 * This interface supports constructing a [PsiElement] from an [ASTNode].
 */
interface PsiElementFactory {
    fun createElement(node: ASTNode?): PsiElement?
}