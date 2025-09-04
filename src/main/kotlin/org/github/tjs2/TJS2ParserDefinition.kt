package org.github.tjs2

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.github.tjs2.adaptors.TJS2LexerAdapter
import org.github.tjs2.adaptors.TJS2ParserAdaptor
import org.jetbrains.annotations.NotNull

class TJS2ParserDefinition : ParserDefinition {

    override fun createLexer(project: Project?): Lexer {
        return TJS2LexerAdapter()
    }

    override fun getCommentTokens(): TokenSet {
        return TJS2TokenSets.create(
            TJS2Lexer.T_LINE_COMMENT,
            TJS2Lexer.T_BLOCK_COMMENT,
            *TJS2TokenSets.MACRO.toIntArray()
        )
    }

    override fun getWhitespaceTokens(): TokenSet {
        return TJS2TokenSets.create(
            TJS2Lexer.WS
        )
    }

    override fun getStringLiteralElements(): TokenSet {
        return TJS2TokenSets.create(TJS2Lexer.T_STRING)
    }

    override fun createParser(project: Project?): PsiParser {
        return TJS2ParserAdaptor()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return TJS2File(viewProvider)
    }

    override fun createElement(node: ASTNode): PsiElement {
        return TJS2ASTFactory.createInternalParseTreeNode(node)
    }

}

val FILE: IFileElementType = IFileElementType(TJS2Language.INSTANCE)