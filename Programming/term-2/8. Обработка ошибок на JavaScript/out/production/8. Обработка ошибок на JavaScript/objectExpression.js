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
function Negate(value) {
    this.value = value;

    Negate.prototype.toString = function() {
        return this.value.toString() + " negate";
    };

    this.evaluate = function(valueX, valueY, valueZ) {
        return -this.value.evaluate(valueX, valueY, valueZ);
    };

    this.prefix = function() {
        return "(negate " + this.value.prefix() + ")";
    }
}
//============================

//=====BINARY===OPERATIONS=====
function AbstractBinaryOperation(left, right, operationType) {
    this.left = left;
    this.right = right;
    this.operationType = operationType;

    AbstractBinaryOperation.prototype.toString = function() {
        return this.left.toString() + " " + this.right.toString() + " " + this.operationType;
    };

    AbstractBinaryOperation.prototype.evaluate = function(valueX, valueY, valueZ) {
        switch (this.operationType) {
            case "+":
                return this.left.evaluate(valueX, valueY, valueZ) + this.right.evaluate(valueX, valueY, valueZ);
                case "-":
                    return this.left.evaluate(valueX, valueY, valueZ) - this.right.evaluate(valueX, valueY, valueZ);
                case "*":
                    return this.left.evaluate(valueX, valueY, valueZ) * this.right.evaluate(valueX, valueY, valueZ);
                case "/":
                    return this.left.evaluate(valueX, valueY, valueZ) / this.right.evaluate(valueX, valueY, valueZ);
            }
    };

    AbstractBinaryOperation.prototype.prefix = function() {
        return "(" + this.operationType + " " + this.left.prefix() + " " + this.right.prefix() + ")";
    }
}

function Add(left, right) {
    this.add = Object.create(AbstractBinaryOperation.prototype);
    AbstractBinaryOperation.call(this.add, left, right, "+");

    this.evaluate = function(valueX, valueY, valueZ) {
        return this.add.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function() {
        return this.add.toString();
    };

    this.prefix = function() {
        return this.add.prefix();
    };
}

function Subtract(left, right) {
    this.subtract = Object.create(AbstractBinaryOperation.prototype);
    AbstractBinaryOperation.call(this.subtract, left, right, "-");

    this.evaluate = function (valueX, valueY, valueZ) {
        return this.subtract.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        return this.subtract.toString();
    };

    this.prefix = function() {
        return this.subtract.prefix();
    };
}

function Multiply(left, right) {
    this.multiply = Object.create(AbstractBinaryOperation.prototype);
    AbstractBinaryOperation.call(this.multiply, left, right, "*");

    this.evaluate = function (valueX, valueY, valueZ) {
        return this.multiply.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        return this.multiply.toString();
    };

    this.prefix = function() {
        return this.multiply.prefix();
    };
}

function Divide(left, right) {
    this.divide = Object.create(AbstractBinaryOperation.prototype);
    AbstractBinaryOperation.call(this.divide, left, right, "/");

    this.evaluate = function (valueX, valueY, valueZ) {
        return this.divide.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        return this.divide.toString();
    };

    this.prefix = function() {
        return this.divide.prefix();
    };
}
//=============================

//=====OTHER===OPERATIONS=====
function Min3(value1, value2, value3) {
    this.listValue = [];
    this.listValue[0] = value1;
    this.listValue[1] = value2;
    this.listValue[2] = value3;
    this.min = value1;

    this.evaluate = function(valueX, valueY, valueZ) {
        for (let i = 0; i < 3; i++) {
            if (this.listValue[i].evaluate(valueX, valueY, valueZ) <= this.min.evaluate(valueX, valueY, valueZ))
                this.min = this.listValue[i];
        }
        return this.min.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function() {
        let result = "";
        for (let i = 0; i < 3; i++) {
            result += this.listValue[i].toString() + " ";
        }
        result += "min3";
        return result;
    };

    this.prefix = function() {
        let result = "(min3";
        for (let i = 0; i < 3; i++) {
            result += " " + this.listValue[i].prefix();
        }
        result += ")";
        return result;
    }
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
    };

    this.prefix = function() {
        let result = "(max5";
        for (let i = 0; i < 5; i++) {
            result += " " + this.listValue[i].prefix();
        }
        result += ")";
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
        // if (tokens.level !== 0) {
        //     throw new ParserError("Parenthesis not closed");
        // }
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

//console.log(parsePrefix("(- x 2))").prefix());