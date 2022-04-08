grammar Kek;

forLoop: FOR LPAR init? SEMICOLON cond? SEMICOLON inc? RPAR EOF;
init: TYPE VAR EQUALS VALUE;
cond: VAR COMPARE VALUE;
inc: VAR INC | INC VAR;

FOR: "for";
TYPE: "int|long\\s+long";
VAR: "[a-zA-Z_][a-zA-Z_0-9]*";
VALUE: "(\\+|-)?(([1-9][0-9]*)|0)";
COMPARE: ">=|<=|>|<|!=|==";
INC: "\\+\\+|--";
EQUALS: "=";
LPAR: "\\(";
RPAR: "\\)";
SEMICOLON: ";";
WS: "[ \\n\\t\\r]+" -> skip;