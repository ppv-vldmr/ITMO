"use strict"

const VARIABLES = {
    "x": 0,
    "y": 1,
    "z": 2,
};

const operation = function (action) {
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

const variable = function (name) {
    return function () {
        return arguments[VARIABLES[name]];
    }
};

const cnst = function (value) {
    return function () {
        //console.log(typeof this + " cnst(value = " + value + " " + typeof value + ")");
        return value;
    };
};

const pi = cnst(Math.PI);

const e = cnst(Math.E);

const add = operation(function (a, b) {
    return a + b;
});

const subtract = operation(function (a, b) {
    return a - b;
});

const multiply = operation(function (a, b) {
    return a * b;
});

const divide = operation(function (a, b) {
    return a / b;
});

const negate = operation(function (a) {
    return -a;
});

const cube = operation(function (a) {
    return Math.pow(a, 3);
});

const cuberoot = operation(function (a) {
    //console.log(typeof this + " cuberoot(value = " + a + " " + typeof a + ")");
    return Math.cbrt(a);
});

const sin = operation(function (a) {
    return Math.sin(a);
});

const cos = operation(function (a) {
    return Math.cos(a);
});

const parse = function (s) {
    let tokens = s.split(" ").filter(function (t) {
        return t.length > 0;
    });
    let stack = [];
    const OP = {
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
    const ARGS_SIZE = {
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
    const MATH_CONSTANTS = {
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
};

//let expr = cuberoot(cuberoot(cnst(-801815183)));
//console.log((expr)(100,1000,100000));