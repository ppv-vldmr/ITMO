"use strict";

//=====VARIABLES=====
function Const(value) {
    this.value = value;

    Const.prototype.toString = function () {
        return this.value.toString();
    };

    this.evaluate = function() {
        return this.value;
    };

    this.prefix = function() {
        return this.value.toString();
    }
}

function Variable(name) {
    this.name = name;

    this.evaluate = function(valueX, valueY, valueZ) {
        switch (this.name) {
            case "x":
                return valueX;
            case "y":
                return valueY;
            case "z":
                return valueZ;
        }
    };

    Variable.prototype.toString = function() {
        return this.name.toString();
    };

    this.prefix = function() {
        return this.name.toString();
    }
}
//===================

//=====UNARY===OPERATIONS=====
function UnaryOperation(value, operationType, func) {
    this.value = value;
    this.operationType = operationType;
    this.func = func;

    UnaryOperation.prototype.toString = function() {
        return this.value.toString() + " " + this.operationType;
    };

    UnaryOperation.prototype.evaluate = function(valueX, valueY, valueZ) {
        return this.func(this.value.evaluate(valueX, valueY, valueZ));
    };

    UnaryOperation.prototype.prefix = function() {
        return "(" + this.operationType + " " + this.value.prefix() + ")";
    }
}

let Negate = function(value) {
    return new UnaryOperation(value, "negate", (a) => -a);
};

let ArcTan = function (value) {
    return new UnaryOperation(value, "atan", (a) => Math.atan(a));
};

let Exp = function(value) {
    return new UnaryOperation(value, "exp", (a) => Math.exp(a));
};
//============================

//=====BINARY===OPERATIONS=====
function BinaryOperation(left, right, operationType, func) {
    this.left = left;
    this.right = right;
    this.operationType = operationType;
    this.func = func;

    BinaryOperation.prototype.toString = function() {
        return this.left.toString() + " " + this.right.toString() + " " + this.operationType;
    };

    BinaryOperation.prototype.evaluate = function(valueX, valueY, valueZ) {
        return this.func(this.left.evaluate(valueX, valueY, valueZ), this.right.evaluate(valueX, valueY, valueZ));
    };

    BinaryOperation.prototype.prefix = function() {
        return "(" + this.operationType + " " + this.left.prefix() + " " + this.right.prefix() + ")";
    }
}

let Add = function(left, right) {
    return new BinaryOperation(left, right, "+", (a, b) => a + b);
};

let Subtract = function(left, right) {
    return new BinaryOperation(left, right, "-", (a, b) => a - b);
};

let Multiply = function(left, right) {
    return new BinaryOperation(left, right, "*", (a, b) => a * b);
};

let Divide = function(left, right) {
    return new BinaryOperation(left, right, "/", (a, b) => a / b);
};
//=============================

//=====OTHER===OPERATIONS=====
function Min3(value1, value2, value3) {
    this.listValue = [];
    this.listValue[0] = value1;
    this.listValue[1] = value2;
    this.listValue[2] = value3;
    this.min = value1;

    this.evaluate = function (valueX, valueY, valueZ) {
        for (let i = 0; i < 3; i++) {
            if (this.listValue[i].evaluate(valueX, valueY, valueZ) <= this.min.evaluate(valueX, valueY, valueZ))
                this.min = this.listValue[i];
        }
        return this.min.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        let result = "";
        for (let i = 0; i < 3; i++) {
            result += this.listValue[i].toString() + " ";
        }
        result += "min3";
        return result;
    };
}

function Max5(value1, value2, value3, value4, value5) {
    this.listValue = [];
    this.listValue[0] = value1;
    this.listValue[1] = value2;
    this.listValue[2] = value3;
    this.listValue[3] = value4;
    this.listValue[4] = value5;
    this.max = value1;

    this.evaluate = function (valueX, valueY, valueZ) {
        for (let i = 0; i < 5; i++) {
            if (this.listValue[i].evaluate(valueX, valueY, valueZ) >= this.max.evaluate(valueX, valueY, valueZ))
                this.max = this.listValue[i];
        }
        return this.max.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        let result = "";
        for (let i = 0; i < 5; i++) {
            result += this.listValue[i].toString() + " ";
        }
        result += "max5";
        return result;
    }
}
//============================

//=====PARSERS=====
function parse(source) {

    let tokens = source.split(" ").filter(function (t) {
        return t.length > 0;
    });

    let stack = [];

    const VARIABLES = {
        "x": new Const("x"),
        "y": new Const("y"),
        "z": new Const("z"),
    };

    const OPERATIONS = {
        "+": Add,
        "-": Subtract,
        "*": Multiply,
        "/": Divide,
        "negate": Negate,
        "min3": Min3,
        "max5": Max5,
    };

    const ARGS_SIZE = {
        "+": 2,
        "-": 2,
        "*": 2,
        "/": 2,
        "negate": 1,
        "min3": 3,
        "max5": 5
    };

    for (let i = 0; i < tokens.length; i++) {
        if (tokens[i] in VARIABLES) {
            stack.push(new Variable(tokens[i]));
        } else if (tokens[i] in OPERATIONS) {
            let args = [];
            for (let j = 0; j < ARGS_SIZE[tokens[i]]; j++) {
                args.push(stack.pop());
            }
            args.reverse();
            switch (tokens[i]) {
                case "+":
                    stack.push(new Add(args[0], args[1]));
                    break;
                case "-":
                    stack.push(new Subtract(args[0], args[1]));
                    break;
                case "*":
                    stack.push(new Multiply(args[0], args[1]));
                    break;
                case "/":
                    stack.push(new Divide(args[0], args[1]));
                    break;
                case "negate":
                    stack.push(new Negate(args[0]));
                    break;
                case "min3":
                    stack.push(new Min3(args[0], args[1], args[2]));
                    break;
                case "max5":
                    stack.push(new Max5(args[0], args[1], args[2], args[3], args[4]));
                    break;
            }
        } else {
            stack.push(new Const(Number(tokens[i])));
        }
        //console.log(stack);
    }
    return stack.pop();
}

function ParserError(message) {
    this.name = "ParserError";
    this.message = message;
}

ParserError.prototype = Error.prototype;

function parsePrefix(source) {

    const VARIABLES = {
        "x": new Const("x"),
        "y": new Const("y"),
        "z": new Const("z"),
    };

    const BINARY_OPERATIONS = {
        "+": Add,
        "-": Subtract,
        "*": Multiply,
        "/": Divide,
    };

    const UNARY_OPERATIONS = {
        "negate": Negate,
        "atan": ArcTan,
        "exp": Exp
    };

    let tokens = source.match(/\(|\)|[^)(\s]+/g);

    try {
        tokens.pos = 0;
        tokens.level = 0;
    } catch (e) {
        throw new ParserError("Empty expression entered");
    }


    tokens.next = function(){
        if (tokens.pos === tokens.length){
            throw new ParserError("Not enough arguments in operation");
        }
        return tokens[tokens.pos++];
    };

    if(tokens.length === 0){
        throw new ParserError("Empty expression entered");
    }

    if (tokens[0] !== "(" && tokens.length > 1) {
        throw new ParserError("Opening parenthesis expected, found " + tokens[0]);
    }

    function getExpr(next) {
        let token = next();
        if (token in UNARY_OPERATIONS) {
            let a = getExpr(next);
            if (tokens[tokens.pos] === ")") {
                tokens.level--;
                tokens.pos++;
                return new UNARY_OPERATIONS[token](a);
            } else
                throw new ParserError("No closing parenthesis in operation " + token);
        } else if (token in BINARY_OPERATIONS) {
            let a = getExpr(next);
            let b = getExpr(next);
            if (tokens[tokens.pos] === ")") {
                tokens.level--;
                tokens.pos++;
                return new BINARY_OPERATIONS[token](a, b);
            } else
                throw new ParserError("No closing parenthesis in operation " + token);
        } else if (token in VARIABLES) {
            return new Variable(token);
        } else if (/^-?[0-9]\d{0,9}(\.\d*)?$/.test(token)) {
            return new Const(parseInt(token));
        } else if (token === '(') {
            tokens.level++;
            return getExpr(next);
        } else if (token === ')') {
            tokens.level--;
            if (tokens.pos > 1) {
                let temp = tokens.pos - 2;
                if (tokens[temp] in BINARY_OPERATIONS || tokens[temp] in UNARY_OPERATIONS)
                    throw new ParserError("Invalid expression entered");
            }
            if (tokens.level < 0) {
                throw new ParserError("Parenthesis not opened");
            }
            return getExpr(next);
        } else {
            throw new ParserError("Unexpected token: " + token);
        }
    }

    let result = getExpr(tokens.next);
    if (tokens.level === 0)
        if (tokens.pos === tokens.length)
            return result;
        else
            throw new ParserError("Unnecessary symbols found at the end of expression");
    else
        if (tokens.level > 0)
            throw new ParserError("Not enough closing parenthesis");
        else
            throw new ParserError("Not enough opening parenthesis");
}