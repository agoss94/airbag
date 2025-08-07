parser grammar TestCaseParser;

options {
    tokenVocab = TestCaseLexer;
}

file: TESTCASE LANGLE input token? tree? RANGLE EOF;
input: INPUT BLOCK;
token: TOKEN BLOCK;
tree: TREE (LPAREN RULE RPAREN)? BLOCK;