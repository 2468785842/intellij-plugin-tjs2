package org.github.tjs2

import com.intellij.psi.tree.TokenSet
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory

interface TJS2TokenSets {

    companion object {
        fun create(vararg ids: Int): TokenSet = PSIElementTypeFactory.createTokenSet(TJS2Language.INSTANCE, *ids)

        val MACRO = arrayOf(
            TJS2Lexer.MACRO_SET,
            TJS2Lexer.MACRO_IF_START,
            TJS2Lexer.MACRO_IF_EXPR_END,
            TJS2Lexer.MACRO_SET_EXPR_END,
            TJS2Lexer.MACRO_IF_EXPR_START,
            TJS2Lexer.MACRO_SET_EXPR_START,
            TJS2Lexer.MACRO_IF_END,
            TJS2Lexer.MACRO_INNER_IF,
            TJS2Lexer.MACRO_INNER_IF_END,
            TJS2Lexer.MACRO_EXPR,
            TJS2Lexer.MACRO_IF_CONTENT
        )

        val NUMBER = arrayOf(
            TJS2Lexer.T_BIN_NUMBER_LITERAL,
            TJS2Lexer.T_HEX_NUMBER_LITERAL,
            TJS2Lexer.T_REAL_LITERAL,
            TJS2Lexer.T_DEC_NUMBER_LITERAL,
            TJS2Lexer.T_OCT_NUMBER_LITERAL,
        )

        val KEYWORD = arrayOf(
            TJS2Lexer.T_NEW,
            TJS2Lexer.T_VAR, TJS2Lexer.T_CONST,
            TJS2Lexer.T_DELETE,
            TJS2Lexer.T_TYPEOF,
            TJS2Lexer.T_ISVALID,
            TJS2Lexer.T_INVALIDATE,
            TJS2Lexer.T_INSTANCEOF,
            TJS2Lexer.T_THIS,
            TJS2Lexer.T_SUPER,
            TJS2Lexer.T_GLOBAL,
            TJS2Lexer.T_CLASS,
            TJS2Lexer.T_CONTINUE,
            TJS2Lexer.T_FUNCTION,
            TJS2Lexer.T_DEBUGGER,
            TJS2Lexer.T_DEFAULT,
            TJS2Lexer.T_CASE,
            TJS2Lexer.T_EXTENDS,
            TJS2Lexer.T_FINALLY,
            TJS2Lexer.T_PROPERTY,
            TJS2Lexer.T_PRIVATE,
            TJS2Lexer.T_PUBLIC,
            TJS2Lexer.T_PROTECTED,
            TJS2Lexer.T_STATIC,
            TJS2Lexer.T_RETURN,
            TJS2Lexer.T_BREAK,
            TJS2Lexer.T_EXPORT,
            TJS2Lexer.T_IMPORT,
            TJS2Lexer.T_SWITCH,
            TJS2Lexer.T_IN,
            TJS2Lexer.T_INCONTEXTOF,
            TJS2Lexer.T_FOR,
            TJS2Lexer.T_WHILE,
            TJS2Lexer.T_DO,
            TJS2Lexer.T_IF,
            TJS2Lexer.T_ENUM,
            TJS2Lexer.T_GOTO,
            TJS2Lexer.T_THROW,
            TJS2Lexer.T_TRY,
            TJS2Lexer.T_SETTER,
            TJS2Lexer.T_GETTER,
            TJS2Lexer.T_ELSE,
            TJS2Lexer.T_CATCH,
            TJS2Lexer.T_SYNCHRONIZED,
            TJS2Lexer.T_WITH,
            TJS2Lexer.T_INT,
            TJS2Lexer.T_REAL,
            TJS2Lexer.T_STRING,
            TJS2Lexer.T_OCTET,
            TJS2Lexer.T_VOID,
            TJS2Lexer.T_NAN,
            TJS2Lexer.T_INFINITY,
            TJS2Lexer.T_BOOL_LITERAL,
            TJS2Lexer.T_NULL_LITERAL,
        )
    }
}