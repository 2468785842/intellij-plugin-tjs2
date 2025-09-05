package org.github.tjs2

import com.intellij.lang.ASTFactory
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import org.github.tjs2.adaptors.parser.PsiElementFactory
import org.github.tjs2.psi.FactorExprNode


class TJS2ASTFactory : ASTFactory() {

    companion object {
    val ruleElementTypeToPsiFactory: MutableMap<IElementType?, PsiElementFactory> = HashMap()
        init {
            with(ruleElementTypeToPsiFactory) {
                put(
                    TJS2TokenTypes.RULE_ELEMENT_TYPES[TJS2Parser.RULE_factor_expr],
                    FactorExprNode.Factory.INSTANCE
                )
            }
        }
    // later auto gen with tokens from some spec in grammar?
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_rules),
//                RulesNode.Factory.INSTANCE
//            );
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_parserRuleSpec),
//                ParserRuleSpecNode.Factory.INSTANCE
//            );
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_lexerRule),
//                LexerRuleSpecNode.Factory.INSTANCE
//            );
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_grammarSpec),
//                GrammarSpecNode.Factory.INSTANCE
//            );
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_modeSpec),
//                ModeSpecNode.Factory.INSTANCE
//            );
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_action),
//                AtAction.Factory.INSTANCE
//            );
//            ruleElementTypeToPsiFactory.put(
//                ANTLRv4TokenTypes.RULE_ELEMENT_TYPES.get(ANTLRv4Parser.RULE_identifier),
//                TokenSpecNode.Factory.INSTANCE
//            );
//        }
        fun createInternalParseTreeNode(node: ASTNode): PsiElement {
            val tokenType: IElementType = node.elementType
            val factory: PsiElementFactory? = ruleElementTypeToPsiFactory[tokenType]
            return if (factory != null) {
                factory.createElement(node)!!
            } else {
                ANTLRPsiNode(node)
            }

        }

    }
}