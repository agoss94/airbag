grammar Schema;

schema
    : node EOF
    ;

node
    : rule
    | token
    | error
    ;

rule
    : '(' index = (INT | RULE) node+ ')'
    ;

token
    : '(' index = (INT | TOKEN) STRING ')' #symbolic
    | STRING #literal
    ;

error
    : '(' ERROR token ')'
    ;

// --- Lexer Rules ---
ERROR: '<error>';
RULE: [a-z][a-zA-Z0-9_]*;
TOKEN: [A-Z][a-zA-Z0-9_]*;
INT: '-'?[0-9]+;
STRING: '\'' (ESC | .)*? '\'';
WS: [ \t\r\n]+ -> skip;

// --- Fragments ---
fragment ESC: '\\'['tnfr];