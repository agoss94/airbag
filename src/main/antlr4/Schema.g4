grammar Schema;

schema: node EOF;

node: '('RULE node+')' #ruleNode
    | '('TOKEN STRING')' #tokenNode
    ;

// --- Lexer Rules ---
RULE            : [a-z][a-zA-Z0-9_]*;
TOKEN            : [A-Z][a-zA-Z0-9_]*;
STRING        : '\'' (ESC | .)*? '\'';
WS            : [ \t\r\n]+ -> skip;

// --- Fragments ---
fragment ESC           : '\\'['tnfr];
