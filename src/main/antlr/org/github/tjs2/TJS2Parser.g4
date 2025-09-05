parser grammar TJS2Parser;

options {
    tokenVocab = TJS2Lexer;
}

// 全局定义列表
global_list : def_list EOF;

// 定义列表
def_list : (block_or_statement | ';')*;
//def_list : (block_or_statement | ';')* {recoverWhile=def_list_recovery}
//private def_list_recovery : !(<<eof>>)

// 块或语句
block_or_statement : statement | block;

// 语句
statement : ';'
            | expr ';'
            | if_else
            | if
            | while
            | do_while
            | for
            | 'break' ';'
            | 'continue' ';'
            | 'debugger' ';'
            | variable_def
            | func_def
            | func_decl
            | property_def
            | class_def
            | return
            | switch
            | with
            | case
            | try
            | throw;

// 块
block : '{' def_list '}';

// while循环
while : 'while' '(' expr ')' block_or_statement;

// do-while循环
do_while : 'do' block_or_statement 'while' '(' expr ')' ';';

// if语句
if : 'if' '(' expr ')' block_or_statement;

// if-else语句
if_else : if 'else' block_or_statement;

// for循环
for : 'for' '(' for_first_clause ';' for_second_clause ';' for_third_clause ')' block_or_statement;

// for循环的第一个子句
for_first_clause : (variable_def_inner | expr)?;

// for循环的第二个子句
for_second_clause : expr?;

// for循环的第三个子句
for_third_clause : expr?;

// 变量定义
variable_def : variable_def_inner ';';

// 变量定义内部
variable_def_inner : ('var' | 'const') variable_id_list;

// 变量ID列表
variable_id_list : variable_id (T_COMMA variable_id)*;

// 变量ID
variable_id : T_SYMBOL variable_type ('=' expr_no_comma)?;

// 变量类型
variable_type : (':' (T_SYMBOL | T_VOID | T_INT | T_REAL | T_STRING | T_OCTET))?;

// 函数定义
func_def : 'function' T_SYMBOL func_decl_arg_opt variable_type block;

// 函数声明
func_decl : 'function' T_SYMBOL func_decl_arg_opt variable_type ';';

// 函数表达式定义
func_expr_def : 'function' func_decl_arg_opt variable_type block;

// 函数声明参数可选
func_decl_arg_opt : (func_decl_arg
                    | '(' func_decl_arg_collapse ')'
                    | '(' func_decl_arg_at_least_one? ')'
                    | '(' func_decl_arg_at_least_one T_COMMA func_decl_arg_collapse ')')?;

// 至少一个函数声明参数
func_decl_arg_at_least_one : func_decl_arg (T_COMMA func_decl_arg)*;

// 函数声明参数
func_decl_arg : T_SYMBOL variable_type ('=' expr_no_comma)?;

// 函数声明参数折叠
func_decl_arg_collapse : '*' | T_SYMBOL variable_type '*';

// 属性定义
property_def : 'property' T_SYMBOL '{' property_handler_def_list '}';

// 属性处理程序定义列表
property_handler_def_list : (property_handler_setter | property_handler_getter)+;

// 属性设置器
property_handler_setter : 'setter' '(' T_SYMBOL variable_type ')' block;

// 属性获取器
property_handler_getter : property_getter_handler_head block;

// 属性获取器头部
property_getter_handler_head : 'getter' ('(' ')')? variable_type;

// 类定义
class_def : 'class' T_SYMBOL class_extender block;

// 类扩展器
class_extender : ('extends' expr_no_comma (T_COMMA extends_list)?)?;

// 扩展列表
extends_list : extends_name (T_COMMA extends_name)*;

// 扩展名称
extends_name : expr_no_comma;

// return语句
return : 'return' (';' | expr ';');

// switch语句
switch : 'switch' '(' expr ')' block;

// with语句
with : 'with' '(' expr ')' block_or_statement;

// case语句
case : 'case' expr ':' | 'default' ':';

// try语句
try : 'try' block_or_statement catch_ block_or_statement;

// catch语句
catch_ : 'catch' ('(' (T_SYMBOL)? ')')?;

// throw语句
throw : 'throw' expr ';';

// 表达式（无逗号）
expr_no_comma : assign_expr;

// 表达式
expr : comma_expr ('if' expr)?;

// 逗号表达式
comma_expr : assign_expr (T_COMMA assign_expr)*;

// 赋值表达式
assign_expr : cond_expr (('<->' | '=' | '&=' | '|=' | '^=' | '-=' | '+=' | '%=' | '/=' | '\\=' | '*=' | '||=' | '&&=' | '>>=' | '<<=' | '>>>=') assign_expr)?;

// 条件表达式
cond_expr : logical_or_expr ('?' cond_expr ':' cond_expr)?;

// 逻辑或表达式
logical_or_expr : logical_and_expr ('||' logical_and_expr)*;

// 逻辑与表达式
logical_and_expr : inclusive_or_expr ('&&' inclusive_or_expr)*;

// 包含或表达式
inclusive_or_expr : exclusive_or_expr ('|' exclusive_or_expr)*;

// 排他或表达式
exclusive_or_expr : and_expr ('^' and_expr)*;

// 与表达式
and_expr : identical_expr ('&' identical_expr)*;

// 相同表达式
identical_expr : compare_expr (('!=' | '==' | '!==' | '===') compare_expr)*;

// 比较表达式
compare_expr : shift_expr (('<' | '>' | '<=' | '>=') shift_expr)*;

// 位移表达式
shift_expr : add_sub_expr (('>>' | '<<' | '>>>') add_sub_expr)*;

// 加减表达式
add_sub_expr : mul_div_expr (('+' | '-') mul_div_expr)*;

// 乘除表达式
mul_div_expr : unary_expr (('%' | '/' | '\\' | '*') unary_expr)*;

// 一元表达式
unary_expr : incontextof_expr
             | '!' unary_expr
             | '~' unary_expr
             | '--' unary_expr
             | '++' unary_expr
             | 'new' priority_expr
             | 'invalidate' unary_expr
             | 'isvalid' unary_expr
             | incontextof_expr 'isvalid'
             | 'delete' unary_expr
             | 'typeof' unary_expr
             | '#' unary_expr
             | '$' unary_expr
             | '+' unary_expr
             | '-' unary_expr
             | '&' unary_expr
             | '*' unary_expr
             | incontextof_expr 'instanceof' unary_expr
             | incontextof_expr 'in' unary_expr
             | '(' 'int' ')' unary_expr
             | 'int' unary_expr
             | '(' 'real' ')' unary_expr
             | 'real' unary_expr
             | '(' 'string' ')' unary_expr
             | 'string' unary_expr;

// incontextof表达式
incontextof_expr : priority_expr ('incontextof' incontextof_expr)?;

// 优先级表达式
//priority_expr : factor_expr
//                | '(' expr ')'
//                | priority_expr '[' expr ']'
//                | func_call_expr
//                | priority_expr '.' T_SYMBOL
//                | priority_expr '++'
//                | priority_expr '--'
//                | priority_expr '!'
//                | '.' T_SYMBOL

// 因子表达式
//factor_expr : constval
//              | T_SYMBOL
//              | 'this'
//              | 'super'
//              | func_expr_def
//              | 'global'
//              | 'void'
//              | inline_array
//              | inline_dic
//              | const_inline_array
//              | const_inline_dic
//              | ('/=' | '/') T_REGEXP

// 函数调用表达式
//func_call_expr : priority_expr '(' call_arg_list ')'

// 优先级表达式 - 重构后避免左递归
priority_expr : factor_expr postfix_ops*;

// 后缀操作
postfix_ops : index_access
                      | member_access
                      | func_call
                      | '++'
                      | '--'
                      | '!';

// 索引访问
index_access : '[' expr ']';

// 成员访问
member_access : '.' T_SYMBOL;

// 函数调用
func_call : '(' call_arg_list ')';

// 因子表达式 - 保持不变
factor_expr : constval
              | T_SYMBOL
              | 'this'
              | 'super'
              | func_expr_def
              | 'global'
              | 'void'
              | inline_array
              | inline_dic
              | const_inline_array
              | const_inline_dic
//              | '/' '='? T_REGEXP '/' T_REGEXP_PATTERN
              | '/' '='? T_SYMBOL '/' T_REGEXP_PATTERN* // not support regexp /xxx/
              | '(' expr ')'
              | '.' T_SYMBOL;

// 调用参数列表
//call_arg_list : '...' | call_arg | call_arg_list T_COMMA call_arg
call_arg_list : call_arg_item (T_COMMA call_arg_item)*;
call_arg_item : '...' | call_arg;

// 调用参数
call_arg : ('*' | mul_div_expr '*' | expr_no_comma)?;

// 内联数组
inline_array : '[' array_elm_list ']';

// 数组元素列表
array_elm_list : array_elm (T_COMMA array_elm)*;

// 数组元素
array_elm : expr_no_comma?;

// 内联字典
inline_dic : '%' '[' dic_elm_list dic_dummy_elm_opt ']';

// 字典元素列表
dic_elm_list : (dic_elm (T_COMMA dic_elm)*)?;

// 字典元素
dic_elm : expr_no_comma T_COMMA expr_no_comma | T_SYMBOL ':' expr_no_comma;

// 字典虚拟元素可选
dic_dummy_elm_opt : T_COMMA?;

// 常量内联数组
const_inline_array : '(' 'const' ')' '[' const_array_elm_list_opt ']';

// 常量数组元素列表可选
const_array_elm_list_opt : const_array_elm_list?;

// 常量数组元素列表
const_array_elm_list : const_array_elm (T_COMMA const_array_elm)*;

// 常量数组元素
const_array_elm : ('-' | '+')? constval | 'void' | const_inline_array | const_inline_dic;

// 常量内联字典
const_inline_dic : '(' 'const' ')' '%' '[' const_dic_elm_list ']';

// 常量字典元素列表
const_dic_elm_list : (const_dic_elm (T_COMMA const_dic_elm)*)?;

// 常量字典元素
const_dic_elm : constval T_COMMA (('-' | '+')? constval | 'void' | const_inline_array | const_inline_dic);

constval : T_STRING_LITERAL
            | T_BOOL_LITERAL
            | T_NULL_LITERAL
            | T_REAL_LITERAL
            | T_BIN_NUMBER_LITERAL
            | T_DEC_NUMBER_LITERAL
            | T_HEX_NUMBER_LITERAL
            | T_OCT_NUMBER_LITERAL
            | T_OCT_STREAM_LITERAL
            | T_CHAR_LITERAL;