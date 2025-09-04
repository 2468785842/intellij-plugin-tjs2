package org.github.tjs2.adaptors

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree
import org.github.tjs2.TJS2Language
import org.github.tjs2.TJS2Parser


class TJS2ParserAdaptor : ANTLRParserAdaptor(TJS2Language.INSTANCE, TJS2Parser(null)) {
    override fun parse(
        parser: Parser,
        root: IElementType
    ): ParseTree? {
        if (root is IFileElementType) {
            return (parser as TJS2Parser).global_list()
        }
        return null
    }
}