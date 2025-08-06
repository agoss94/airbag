grammar Token;

tokenList: token+ EOF;
singleToken: token EOF;
token: '[''@'
    tokenIndex = INT
    ','
    startIndex = INT
    ':'
    stopIndex = INT
    '='
    txt = STRING
    ','
    '<' type = (TOKEN | STRING | INT) '>'
    ','
    ('channel' '=' channel = INT ',')?
    line = INT
    ':'
    charPositionInLine = INT
    ']';

INT: '-'?[0-9]+;
TOKEN: [A-Z][a-zA-Z0-9_]*;
STRING: '\''.*?'\'';
COMMENT: '//'.*? '\n';
LONG_COMMENT: '/*' .*? '*/';
WS: [ \t\n\f\r] -> skip;