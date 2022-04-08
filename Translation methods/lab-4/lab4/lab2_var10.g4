grammar Kek;

func: type LPAR argument? (COMMA argument)* RPAR SEMICOLON EOF;

type: TYPE_NAME POINTER* NAME;

argument: VALUE | type;

TYPE_NAME: "int|long\\s+long|void|char|float";
POINTER: "\\*";
VALUE: "(\\+|-)?(([1-9][0-9]*)|0)";
NAME: "[a-zA-Z_][a-zA-Z_0-9]*";
LPAR: "\\(";
RPAR: "\\)";
COMMA: ",";
SEMICOLON: ";";
WS: "[ \\n\\t\\r]+" -> skip;