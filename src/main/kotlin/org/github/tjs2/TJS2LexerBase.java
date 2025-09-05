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

    protected boolean isRegexPattern() {
        return this.lastToken != null && this.lastToken.getType() == TJS2Lexer.T_REG_EXPR_LITERAL;
    }

    protected boolean isRegexPossible() {

        if (this.lastToken == null) {
            return true;
        }

        return switch (this.lastToken.getType()) {
            case TJS2Lexer.T_CATCH, TJS2Lexer.T_TRY, TJS2Lexer.T_CASE,
                 TJS2Lexer.T_DEFAULT, TJS2Lexer.T_WITH,
                 TJS2Lexer.T_SWITCH, TJS2Lexer.T_WHILE,
                 TJS2Lexer.T_EXTENDS, TJS2Lexer.T_CLASS, TJS2Lexer.T_GETTER,
                 TJS2Lexer.T_SETTER, TJS2Lexer.T_PROPERTY, TJS2Lexer.T_DEBUGGER,
                 TJS2Lexer.T_SYMBOL, TJS2Lexer.T_NULL_LITERAL, TJS2Lexer.T_BOOL_LITERAL,
                 TJS2Lexer.T_THIS, TJS2Lexer.T_RBRACE, TJS2Lexer.T_RPARENTHESIS,
                 TJS2Lexer.T_OCT_STREAM_LITERAL, TJS2Lexer.T_BIN_NUMBER_LITERAL,
                 TJS2Lexer.T_HEX_NUMBER_LITERAL, TJS2Lexer.T_REAL_LITERAL,
                 TJS2Lexer.T_DEC_NUMBER_LITERAL, TJS2Lexer.T_OCT_NUMBER_LITERAL,
                 TJS2Lexer.T_CHAR_LITERAL, TJS2Lexer.T_STRING_LITERAL,
                 TJS2Lexer.T_PLUS, TJS2Lexer.T_MINUS -> false;
            default -> true;
        };
    }

    @Override
    public void reset() {
        this.lastToken = null;
        super.reset();
    }
}