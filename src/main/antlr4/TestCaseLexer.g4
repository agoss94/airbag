lexer grammar TestCaseLexer;

tokens { BLOCK }

TESTCASE: 'testcase';
INPUT: 'input' -> pushMode(INSIDE_MODE);
TOKEN: 'token' -> pushMode(TOKEN_MODE);
TREE: 'tree' -> pushMode(TREE_MODE);
LANGLE: '{';
RANGLE: '}';
COMMENT: '//'.*? '\n' -> skip;
LONG_COMMENT: '/*' .*? '*/' -> skip;
WHITESPACE : [ \t\r\n\f]+ -> skip;

mode INSIDE_MODE;

I_BLOCK: '{'.*? '}' -> type(BLOCK), popMode;
I_COMMENT: '//'.*? '\n' -> skip;
I_LONG_COMMENT: '/*' .*? '*/' -> skip;
I_WHITESPACE : [ \t\r\n\f]+ -> skip;

mode TOKEN_MODE;

TK_BLOCK: '{'.*? '}' -> type(BLOCK), popMode;
TK_COMMENT: '//'.*? '\n' -> skip;
TK_LONG_COMMENT: '/*' .*? '*/' -> skip;
TK_WHITESPACE : [ \t\r\n\f]+ -> skip;

mode TREE_MODE;

RULE: [a-z][a-zA-Z0-9_]*;
LPAREN: '(';
RPAREN: ')';
T_BLOCK: '{'.*? '}' -> type(BLOCK), popMode;
T_COMMENT: '//'.*? '\n' -> skip;
T_LONG_COMMENT: '/*' .*? '*/' -> skip;
T_WHITESPACE : [ \t\r\n\f]+ -> skip;
