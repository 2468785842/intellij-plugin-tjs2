package org.github.tjs2.adaptors

import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.github.tjs2.TJS2Language
import org.github.tjs2.TJS2Lexer
import org.github.tjs2.TJS2Parser


class TJS2LexerAdapter(lexer: TJS2Lexer = TJS2Lexer(null)) : ANTLRLexerAdaptor(TJS2Language.INSTANCE, lexer){
    companion object {
        init {
            initializeElementTypeFactory()
        }

        fun initializeElementTypeFactory() {
            PSIElementTypeFactory.defineLanguageIElementTypes(
                TJS2Language.INSTANCE,
                TJS2Lexer.tokenNames,
                TJS2Parser.ruleNames
            )
        }
    }
}