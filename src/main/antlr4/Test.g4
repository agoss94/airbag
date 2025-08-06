grammar Test;

file: 'grammar' 'test' '{' INPUT TOKEN? TREE? '}' EOF;

INPUT: 'input' WS? '{' .*? '}';
TOKEN: 'token' WS? '{' .*? '}';
TREE: 'tree' WS? '{' .*? '}';
COMMENT: '//'.*? '\n';
LONG_COMMENT: '/*' .*? '*/';
WHITESPACE : WS -> skip;
fragment WS: [ \t\r\n\f]+;