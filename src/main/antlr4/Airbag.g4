grammar Airbag;

tree: node EOF;

node: '('RULE node+')' #ruleNode
    | '('TOKEN STRING')' #tokenNode
    ;

list
    : token+ EOF
    ;

token
    : '[@' tokenIndex=INT ',' startIndex=INT ':' stopIndex=INT '=' text=STRING ',<' type=(INT | TOKEN) '>' ('channel=' channel=INT)? ',' line=INT ':' charPositionInLine=INT ']'
    ;

// --- Lexer Rules ---

RULE            : [a-z][a-zA-Z0-9_]*;
TOKEN            : [A-Z][a-zA-Z0-9_]*;
INT           : '-'? DIGIT+;
STRING        : '\'' (ESC | .)*? '\'';
WS            : [ \t\r\n]+ -> skip;

// --- Fragments ---

fragment DIGIT         : [0-9];
fragment ESC           : '\\'['tnfr];
