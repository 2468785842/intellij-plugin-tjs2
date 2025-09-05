package org.github.tjs2

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import org.github.tjs2.adaptors.TJS2LexerAdapter


class TJS2SyntaxHighlighter : SyntaxHighlighter {
    companion object {
        val SYMBOL = createTextAttributesKey("TJS2_SYMBOL", DefaultLanguageHighlighterColors.IDENTIFIER)
        val KEYWORD = createTextAttributesKey("TJS2_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING = createTextAttributesKey("TJS2_STRING", DefaultLanguageHighlighterColors.STRING)
        val NUMBER = createTextAttributesKey("TJS2_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val MACRO = createTextAttributesKey("TJS2_MACRO", DefaultLanguageHighlighterColors.METADATA)

        val LINE_COMMENT =
            createTextAttributesKey("TJS2_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BLOCK_COMMENT =
            createTextAttributesKey("TJS2_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val BAD_CHARACTER =
            createTextAttributesKey("TJS2_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)


        val SYMBOL_KEYS = arrayOf<TextAttributesKey>(SYMBOL)
        val KEYWORD_KEYS = arrayOf<TextAttributesKey>(KEYWORD)
        val BAD_CHAR_KEYS= arrayOf<TextAttributesKey>(BAD_CHARACTER)
        val STRING_KEYS = arrayOf<TextAttributesKey>(STRING)
        val NUMBER_KEYS = arrayOf<TextAttributesKey>(NUMBER)
        val MACRO_KEYS = arrayOf<TextAttributesKey>(MACRO)

        val LINE_COMMENT_KEYS= arrayOf<TextAttributesKey>(LINE_COMMENT)
        val BLOCK_COMMENT_KEYS= arrayOf<TextAttributesKey>(BLOCK_COMMENT)
        val EMPTY_KEYS = arrayOfNulls<TextAttributesKey>(0)
    }

    override fun getHighlightingLexer(): Lexer {
        return TJS2LexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey?> {
        if (tokenType !is TokenIElementType) return EMPTY_KEYS
        val ttype = tokenType.antlrTokenType
        return when (ttype) {
            TJS2Lexer.T_SYMBOL -> SYMBOL_KEYS

            in TJS2TokenSets.KEYWORD,
            TJS2Lexer.MACRO_SET,
            TJS2Lexer.MACRO_IF_END,
            TJS2Lexer.MACRO_IF_START-> KEYWORD_KEYS

            TJS2Lexer.T_STRING_LITERAL -> STRING_KEYS
            in TJS2TokenSets.NUMBER -> NUMBER_KEYS
//            in TJS2TokenSets.MACRO -> MACRO_KEYS
            TJS2Lexer.T_LINE_COMMENT -> LINE_COMMENT_KEYS
            TJS2Lexer.T_BLOCK_COMMENT -> BLOCK_COMMENT_KEYS
            TJS2Lexer.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }
}