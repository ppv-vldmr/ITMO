"use strict";

const variables = ['x', 'y', 'z'];

function Const(value) {
    this.value = value;
}

Const.prototype.toString = function () {
    return this.value.toString();
};

Const.prototype.evaluate = function() {
    return this.value;
};

Const.prototype.prefix = function() {
    return this.value.toString();
}

function Variable(name) {
    this.name = name;
}

const VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2
}

Variable.prototype.evaluate = function(...values) {
    return values[VARIABLES[this.name]];
};

Variable.prototype.toString = function() {
    return this.name.toString();
};

Variable.prototype.prefix = function() {
    return this.name.toString();
}

const Operation = function (...operands) {
    this.getOperands = function () {
        return operands;
    }
    this.evaluate = function (...values) {
        const result = operands.map(function (operand) {
            return operand.evaluate(...values);
        });
        return this.procedure(...result);
    };
    this.toString = function () {
        return operands.join(" ") + " " + this.symbol;
    }
};

Operation.prototype.prefix = function () {
    return "(" + this.symbol + " " + this.getOperands().map(function (operand) {
        return operand.prefix();
    }).join(" ") + ")";
}

const createOperation = function (procedure, symbol) {
    const result = function (...operands) {
        Operation.apply(this, operands);
    };
    result.prototype = new Operation;
    result.prototype.procedure = procedure;
    result.prototype.symbol = symbol;
    return result;
}

const Add = createOperation((a,b) => a + b, "+");
const Subtract = createOperation((a,b) => a - b, "-");
const Multiply = createOperation((a,b) => a * b, "*");
const Divide = createOperation((a,b) => a / b, "/");
const Negate = createOperation((a) => -a, "negate");
const Min3 = createOperation((a,b,c) => Math.min(a,b,c), "min3");
const Max5 = createOperation((a,b,c,d,e) => Math.max(a,b,c,d,e), "max5");
const Exp = createOperation((a) => Math.exp(a), "exp");
const ArcTan = createOperation((a) => Math.atan(a), "atan");

const showPos = function (expression, index) {
    let res = "\n" + expression + "\n";
    for (let i = 0; i < index; i++) {
        res += "-";
    }
    res += "^\n"
    return res;
}

const Exception = function (name, message) {
    const e = function (...args) {
        if (args[0] === "()")
            this.message = message("Empty expression entered");
        else
            this.message = message(...args);
        this.name = name;
    };
    e.prototype = new Error;
    return e;
};

Exception.prototype = Error.prototype;

const UnexpectedSymbolException = Exception(
    "UnexpectedSymbolException",
    (expr, ind) => "Unexpected symbol : " + ind + showPos(expr, ind)
);

const TooMuchOperandsException = Exception(
    "TooMuchOperandsException",
    (expr, ind) => "Too much operands for operation " + showPos(expr, ind)
);

const MissingOperandException = Exception(
    "MissingOperandException",
    (expr, ind) => "Not enough operands for operation " + showPos(expr, ind)
);

const MissingOperationException = Exception(
    "MissingOperationException",
    (expr, ind) => "Expected operation at position " + ind + " but it not found" + showPos(expr, ind)
);

const MissingCloseBracketException = Exception(
    "MissingCloseBracketException",
    (expr, ind) => "Expected close bracket at index " + ind + " but it not found" + showPos(expr, ind)
);



// PARSING

const isDigit = function (suspect) {
    return suspect >= "0" && suspect <= "9";
}

const isNumber = function (suspect) {
    if (suspect.length === 0) return false;

    let i = 0;
    if (suspect[i] === "-") {
        if (suspect.length === 1) {
            return false;
        }
        i++;
    }

    while (i < suspect.length) {
        if (!isDigit(suspect[i])) {
            return false;
        }
        i++;
    }
    return true;
}

const isWhitespace = function (suspect) {
    return /\s/.test(suspect);
}

const operations = {
    "+": [Add, 2],
    "-": [Subtract, 2],
    "/": [Divide, 2],
    "*": [Multiply, 2],
    "negate": [Negate, 1],
    "min3": [Min3, 3],
    "max5": [Max5, 5],
    "atan": [ArcTan, 1],
    "exp": [Exp, 1]
};

const Parser = function (expression, parse) {
    this.parse = parse;
    this.expression = expression;
    this.ind = 0;
    this.token = '';
};

Parser.prototype.nextToken = function () {
    let res = '';
    if (this.expression[this.ind] === "(" || this.expression[this.ind] === ")") {
        res = this.expression[this.ind];
        this.ind++;
    } else {
        let i = this.ind;
        while (this.expression[i] !== "(" && this.expression[i] !== ")" &&
        !isWhitespace(this.expression[i]) && i < this.expression.length) {
            i++;
        }
        res = this.expression.slice(this.ind, i);
        this.ind = i;
    }
    this.skipWhitespace();
    this.token = res;
};

Parser.prototype.skipWhitespace = function () {
    while (this.ind < this.expression.length && isWhitespace(this.expression[this.ind])) {
        this.ind++;
    }
};

const parsePrefix = function (expression) {
    let parser = new Parser(expression, function () {
            if (variables.indexOf(this.token) !== -1) {
                return new Variable(this.token);
            } else if (isNumber(this.token)) {
                return new Const(parseInt(this.token));
            } else if (this.token === "(") {
                this.nextToken();
                if (this.token in operations) {
                    let args = [];
                    let op = this.token;
                    this.nextToken();
                    while (this.token !== ")" && this.ind < expression.length) {
                        args.push(this.parse());
                        this.nextToken();
                    }
                    if (this.token !== ")") {
                        throw new MissingCloseBracketException(expression, this.ind);
                    } else if (operations[op][1] > args.length) {
                        throw new MissingOperandException(expression, this.ind);
                    } else if (operations[op][1] !== 0 && operations[op][1] < args.length) {
                        throw new TooMuchOperandsException(expression, this.ind);
                    }
                    return new operations[op][0](...args);
                } else {
                    throw new MissingOperationException(expression, this.ind);
                }
            } else {
                throw new UnexpectedSymbolException(expression, this.ind);
            }
        }
    );

    parser.skipWhitespace();
    parser.nextToken();
    let result = parser.parse();
    if (parser.ind < expression.length) {
        throw new UnexpectedSymbolException(expression, parser.ind);
    }
    return result;
};

//console.log(parsePrefix("()").toString());