package org.github.tjs2;

import org.antlr.v4.runtime.*;

public abstract class TJS2LexerBase extends Lexer {

    private Token lastToken = null;

    public TJS2LexerBase(CharStream input) {
        super(input);
    }
    @Override
    public Token nextToken() {
        Token next = super.nextToken();

        if (next.getChannel() == Token.DEFAULT_CHANNEL) {
            // Keep track of the last token on the default channel.
            this.lastToken = next;
        }

        return next;
    }

    protected boolean isRegexPossible() {

        if (this.lastToken == null) {
            return true;
        }

        switch (this.lastToken.getType()) {
            case TJS2Lexer.T_CATCH:
            case TJS2Lexer.T_TRY:
            case TJS2Lexer.T_CASE:
            case TJS2Lexer.T_DEFAULT:
            case TJS2Lexer.T_WITH:
            case TJS2Lexer.T_SWITCH:
            case TJS2Lexer.T_WHILE:
            case TJS2Lexer.T_EXTENDS:
            case TJS2Lexer.T_CLASS:
            case TJS2Lexer.T_GETTER:
            case TJS2Lexer.T_SETTER:
            case TJS2Lexer.T_PROPERTY:
            case TJS2Lexer.T_DEBUGGER:
            case TJS2Lexer.T_SYMBOL:
            case TJS2Lexer.T_NULL_LITERAL:
            case TJS2Lexer.T_BOOL_LITERAL:
            case TJS2Lexer.T_THIS:
            case TJS2Lexer.T_RBRACE:
            case TJS2Lexer.T_RPARENTHESIS:
            case TJS2Lexer.T_OCT_STREAM_LITERAL:
            case TJS2Lexer.T_BIN_NUMBER_LITERAL:
            case TJS2Lexer.T_HEX_NUMBER_LITERAL:
            case TJS2Lexer.T_REAL_LITERAL:
            case TJS2Lexer.T_DEC_NUMBER_LITERAL:
            case TJS2Lexer.T_OCT_NUMBER_LITERAL:
            case TJS2Lexer.T_CHAR_LITERAL:
            case TJS2Lexer.T_STRING_LITERAL:
            case TJS2Lexer.T_PLUS:
            case TJS2Lexer.T_MINUS:
                return false;
            default:
                return true;
        }
    }

    @Override
    public void reset() {
        this.lastToken = null;
        super.reset();
    }
}