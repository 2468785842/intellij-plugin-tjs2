lexer grammar TJS2Lexer;

options {
    superClass = TJS2LexerBase;
}

@lexer::members {
    boolean marcoExprExpect = false;
    int macroExprParenDepth = 0;
    int macroIfParenDepth = 0;
}

T_LINE_COMMENT: '//' ~[\r\n]* -> channel(HIDDEN);
T_BLOCK_COMMENT: '/*' .*? '*/' -> channel(HIDDEN);

WS: [ \t\r\n\f]+ -> channel(HIDDEN);
T_REG_EXPR_LITERAL:
    '/' '='? RegularExpressionFirstChar RegularExpressionChar* {this.isRegexPossible()}? '/';

T_REGEXP_PATTERN: {this.isRegexPattern()}? [gil]+;

T_NEW: 'new';
T_DELETE: 'delete';
T_TYPEOF: 'typeof';
T_ISVALID: 'isvalid';
T_INVALIDATE: 'invalidate';
T_INSTANCEOF: 'instanceof';
T_THIS: 'this';
T_SUPER: 'super';
T_GLOBAL: 'global';
T_CLASS: 'class';
T_CONTINUE: 'continue';
T_FUNCTION: 'function';
T_DEBUGGER: 'debugger';
T_DEFAULT: 'default';
T_CASE: 'case';
T_EXTENDS: 'extends';
T_FINALLY: 'finally';
T_PROPERTY: 'property';
T_PRIVATE: 'private';
T_PUBLIC: 'public';
T_PROTECTED: 'protected';
T_STATIC: 'static';
T_RETURN: 'return';
T_BREAK: 'break';
T_EXPORT: 'export';
T_IMPORT: 'import';
T_SWITCH: 'switch';
T_IN: 'in';
T_INCONTEXTOF: 'incontextof';
T_FOR: 'for';
T_WHILE: 'while';
T_DO: 'do';
T_IF: 'if';
T_VAR: 'var';
T_CONST: 'const';
T_ENUM: 'enum';
T_GOTO: 'goto';
T_THROW: 'throw';
T_TRY: 'try';
T_SETTER: 'setter';
T_GETTER: 'getter';
T_ELSE: 'else';
T_CATCH: 'catch';
T_SYNCHRONIZED: 'synchronized';
T_WITH: 'with';
T_INT: 'int';
T_REAL: 'real';
T_STRING: 'string';
T_OCTET: 'octet';
T_VOID: 'void';
T_NAN: 'NaN';
T_INFINITY: 'Infinity';
T_NULL_LITERAL: 'null';
T_BOOL_LITERAL: 'true' | 'false';

T_SYMBOL: [a-zA-Z_] [a-zA-Z0-9_]*;

T_OCT_STREAM_LITERAL: '<%' .*? '%>';

T_BIN_NUMBER_LITERAL: '0' [bB] [01]+ ('.' [01]+)? ([pP] [+-]? [0-9]+)?;
T_HEX_NUMBER_LITERAL: '0' [xX] [0-9a-fA-F]+ ('.' [0-9a-fA-F]+)? ([pP] [+-]? [0-9]+)?;
T_REAL_LITERAL: ([0-9]+ '.' [0-9]* | '.' [0-9]+) ([eE] [+-]? [0-9]+)? | [0-9]+ [eE] [+-]? [0-9]+;
T_DEC_NUMBER_LITERAL: '0' | [1-9] [0-9]*;
T_OCT_NUMBER_LITERAL: '0' [0-7]+ ('.' [0-7]+)?;

T_CHAR_LITERAL: '\'' (~['\\\r\n] | EscapeSequence)* '\'';
T_STRING_LITERAL: '"' (~["\r\n] | EscapeSequence)* '"';

T_COMMA: '=>' | ',';
T_AMPERSANDEQUAL: '&=';
T_VERTLINEEQUAL: '|=';
T_CHEVRONEQUAL: '^=';
T_MINUSEQUAL: '-=';
T_PLUSEQUAL: '+=';
T_PERCENTEQUAL: '%=';
T_BACKSLASHEQUAL: '\\=';
T_ASTERISKEQUAL: '*=';
T_LOGICALOREQUAL: '||=';
T_LOGICALANDEQUAL: '&&=';
T_RBITSHIFTEQUAL: '>>>=';
T_LARITHSHIFTEQUAL: '<<=';
T_RARITHSHIFTEQUAL: '>>=';
T_LOGICALOR: '||';
T_LOGICALAND: '&&';
T_NOTEQUAL: '!=';
T_EQUALEQUAL: '==';
T_DISCNOTEQUAL: '!==';
T_DISCEQUAL: '===';
T_SWAP: '<->';
T_LTOREQUAL: '<=';
T_GTOREQUAL: '>=';
T_RARITHSHIFT: '>>';
T_LARITHSHIFT: '<<';
T_RBITSHIFT: '>>>';
T_OMIT: '...';
T_DECREMENT: '--';
T_INCREMENT: '++';
T_SLASHEQUAL: '/=';

T_QUESTION: '?';
T_VERTLINE: '|';
T_CHEVRON: '^';
T_AMPERSAND: '&';
T_PERCENT: '%';
T_EQUAL: '=';
T_LT: '<';
T_GT: '>';
T_BACKSLASH: '\\';
T_ASTERISK: '*';
T_EXCRAMATION: '!';
T_TILDE: '~';

T_PLUS: '+';
T_MINUS: '-';
T_SHARP: '#';
T_DOLLAR: '$';
T_LPARENTHESIS: '(';
T_DOT: '.';
T_LBRACKET: '[';
T_RBRACKET: ']';
T_RPARENTHESIS: ')';
T_COLON: ':';
T_SEMICOLON: ';';
T_LBRACE: '{';
T_RBRACE: '}';
T_SLASH: '/';

// 宏开始标记
MACRO_SET: '@set' {marcoExprExpect=true;} -> channel(HIDDEN), pushMode(MACRO_MODE);
MACRO_IF_START: '@if' {marcoExprExpect=true;macroIfParenDepth=1;} -> channel(HIDDEN), pushMode(MACRO_MODE);

BAD_CHARACTER: .  -> channel(HIDDEN);

// 宏模式
mode MACRO_MODE;
MACRO_IF_EXPR_END: {marcoExprExpect && macroExprParenDepth == 1 && macroIfParenDepth != 0}? ')' {macroExprParenDepth--;marcoExprExpect=false;} -> channel(HIDDEN);
MACRO_SET_EXPR_END: {marcoExprExpect && macroExprParenDepth == 1 && macroIfParenDepth == 0}? ')' {macroExprParenDepth--;marcoExprExpect=false;} -> channel(HIDDEN), popMode;

MACRO_IF_EXPR_START: {macroIfParenDepth > 0 && marcoExprExpect}? '(' {macroExprParenDepth++;} -> channel(HIDDEN);
MACRO_SET_EXPR_START: {marcoExprExpect}? '(' {macroExprParenDepth++;}  -> channel(HIDDEN);

MACRO_IF_END: {macroIfParenDepth == 1 && !marcoExprExpect}? '@endif' {macroIfParenDepth--;} -> channel(HIDDEN), popMode;
MACRO_INNER_IF: {macroIfParenDepth > 0 && !marcoExprExpect}? '@if' {marcoExprExpect=true;macroIfParenDepth++;} -> channel(HIDDEN);
MACRO_INNER_IF_END: {macroIfParenDepth > 1 && !marcoExprExpect}? '@endif' {macroIfParenDepth--;} -> channel(HIDDEN);

MACRO_EXPR: {marcoExprExpect && macroExprParenDepth > 0}? ( ')' {macroExprParenDepth--;} | (~[()@])+) -> channel(HIDDEN);
MACRO_IF_CONTENT: {macroIfParenDepth > 0 && !marcoExprExpect}? (~[@])+ -> channel(HIDDEN);
MARCO_BAD_CHARACTER: .  -> channel(HIDDEN);

fragment HexDigit: [0-9a-fA-F];

fragment EscapeSequence:
    '\\' 'u005c'? [bstnfr"'\\]
    | '\\' 'u005c'? ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
;

fragment RegularExpressionFirstChar:
    ~[*\r\n\\/[]
    | RegularExpressionBackslashSequence
    | '[' RegularExpressionClassChar* ']'
;

fragment RegularExpressionChar:
    ~[\r\n\\/[]
    | RegularExpressionBackslashSequence
    | '[' RegularExpressionClassChar* ']'
;

fragment RegularExpressionClassChar: ~[\r\n\]\\] | RegularExpressionBackslashSequence;

fragment RegularExpressionBackslashSequence: '\\' ~[\r\n];