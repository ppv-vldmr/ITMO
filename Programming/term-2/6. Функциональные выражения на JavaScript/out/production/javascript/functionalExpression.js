"use strict"

let VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2,
};

let operation = function (action) {
    return function () {
        let operands = arguments;
        return function () {
            let result = [];
            for (let i = 0; i < operands.length; i++) {
                result.push(operands[i].apply(null, arguments));
            }
            return action.apply(null, result);
        }
    }
};

let variable = function (name) {
    return function () {
        return arguments[VARIABLES[name]];
    }
};

let cnst = function (value) {
    return function () {
        return value;
    };
};

const pi = cnst(Math.PI);

const e = cnst(Math.E);

let add = operation(function (a, b) {
    return a + b;
});

let subtract = operation(function (a, b) {
    return a - b;
});

let multiply = operation(function (a, b) {
    return a * b;
});

let divide = operation(function (a, b) {
    return a / b;
});

let negate = operation(function (a) {
    return -a;
});

let cube = operation(function (a) {
    return Math.pow(a, 3);
});

let cuberoot = operation(function (a) {
    return Math.pow(a, 1 / 3);
});

let sin = operation(function (a) {
    return Math.sin(a);
});

let cos = operation(function (a) {
    return Math.cos(a);
});

let parse = function (s) {
    let tokens = s.split(" ").filter(function (t) {
        return t.length > 0;
    });
    let stack = [];
    let OP = {
        "+": add,
        "-": subtract,
        "*": multiply,
        "/": divide,
        "negate": negate,
        "cube": cube,
        "cuberoot": cuberoot,
        "sin": sin,
        "cos": cos,
    };
    let ARGS_SIZE = {
        "+": 2,
        "-": 2,
        "*": 2,
        "/": 2,
        "negate": 1,
        "cube": 1,
        "cuberoot": 1,
        "sin": 1,
        "cos": 1,
    };
    let MATH_CONSTANTS = {
      "pi": pi,
      "e": e,
    };
    for (let i = 0; i < tokens.length; i++) {
        if (tokens[i] in VARIABLES) {
            stack.push(variable(tokens[i]));
        } else if (tokens[i] in OP) {
            let args = [];
            for (let j = 0; j < ARGS_SIZE[tokens[i]]; j++) {
                args.push(stack.pop());
            }
            args.reverse();
            stack.push(OP[tokens[i]].apply(null, args));
        } else if (tokens[i] in MATH_CONSTANTS) {
            stack.push(MATH_CONSTANTS[tokens[i]]);
        } else {
            stack.push(cnst(Number(tokens[i])));
        }
    }
    return stack.pop();
}