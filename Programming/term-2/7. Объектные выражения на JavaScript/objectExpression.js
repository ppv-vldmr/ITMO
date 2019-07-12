"use strict";

//=====VARIABLES=====
function Const(value) {
    this.value = value;

    Const.prototype.toString = function () {
        return this.value.toString();
    };

    this.evaluate = function(valueX, valueY, valueZ) {
        return this.value;
    };

    this.diff = function() {
        return new Const(0);
    };

    this.simplify = function() {
        return this;
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

    Variable.prototype.toString = function () {
        return this.name.toString();
    };

    this.diff = function(variable) {
        if (variable === this.name)
            return new Const(1);
        return new Const(0);
    };

    this.simplify = function() {
        return this;
    };
}
//===================

//=====UNARY===OPERATIONS=====
function Negate(value) {
    this.value = value;

    Negate.prototype.toString = function () {
        return this.value.toString() + " negate";
    };

    this.evaluate = function(valueX, valueY, valueZ) {
        return -this.value.evaluate(valueX, valueY, valueZ);
    };

    this.diff = function(variable) {
        return new Negate(value.diff(variable));
    };

    this.simplify = function() {
        if (typeof this.value.value === "number")
            return new Const(-this.value.value);
        else
            return this;
    };
}
//============================

//=====BINARY===OPERATIONS=====
function abstractBinaryOperation(left, right, operationType) {
    this.left = left;
    this.right = right;
    this.operationType = operationType;

    abstractBinaryOperation.prototype.toString = function () {
        return this.left.toString() + " " + this.right.toString() + " " + this.operationType;
    };

    abstractBinaryOperation.prototype.evaluate = function(valueX, valueY, valueZ) {
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

    abstractBinaryOperation.prototype.diff = function(variable) {
        switch (this.operationType) {
            case "+":
                return new Add(this.left.diff(variable), this.right.diff(variable));
            case "-":
                return new Subtract(this.left.diff(variable), this.right.diff(variable));
            case "*":
                return new Add(
                    new Multiply(this.left.diff(variable), this.right),
                    new Multiply(this.left, this.right.diff(variable))
                );
            case "/":
                return new Divide(
                    new Subtract(
                        new Multiply(this.left.diff(variable), this.right),
                        new Multiply(this.left, this.right.diff(variable))
                    ),
                    new Multiply(this.right, this.right)
                );
        }
    };

    abstractBinaryOperation.prototype.simplify = function() {
        this.left = this.left.simplify();
        this.right = this.right.simplify();
        switch (this.operationType) {
            case "+":
                if (typeof this.left.value === typeof this.right.value && typeof this.left.value === "number")
                    return new Const(this.left.value + this.right.value);
                if (this.right.value === 0)
                    return this.left;
                if (this.left.value === 0)
                    return this.right;
                return this;
            case "-":
                if (typeof this.left.value === typeof this.right.value && typeof this.right.value === "number")
                    return new Const(this.left.value - this.right.value);
                if (this.right.value === 0)
                    return this.left;
                return this;
            case "*":
                 if (typeof this.left.value === typeof this.right.value && typeof this.left.value === "number") {
                    return new Const(this.left.value * this.right.value);
                }
                if (this.left.value === 0 || this.right.value === 0)
                    return new Const(0);
                if (this.left.value === 1)
                    return this.right;
                if (this.right.value === 1)
                    return this.left;
                return this;
            case "/":
                if (typeof this.left.value === typeof this.right.value && typeof this.left.value === "number") {
                    return new Const(this.left.value / this.right.value);
                }
                if (this.left.value === 0)
                    return new Const(0);
                if (this.right.value === 1)
                    return this.left;
                return this;
        }
    }
}

function Add(left, right) {
    this.add = Object.create(abstractBinaryOperation.prototype);
    abstractBinaryOperation.call(this.add, left, right, "+");

    this.evaluate = function(valueX, valueY, valueZ) {
        return this.add.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function() {
        return this.add.toString();
    };

    this.diff = function(variable) {
        return this.add.diff(variable);
    };

    this.simplify = function() {
        return this.add.simplify();
    };
}

function Subtract(left, right) {
    this.subtract = Object.create(abstractBinaryOperation.prototype);
    abstractBinaryOperation.call(this.subtract, left, right, "-");

    this.evaluate = function (valueX, valueY, valueZ) {
        return this.subtract.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        return this.subtract.toString();
    };

    this.diff = function(variable) {
        return this.subtract.diff(variable);
    };

    this.simplify = function() {
        return this.subtract.simplify();
    };
}

function Multiply(left, right) {
    this.multiply = Object.create(abstractBinaryOperation.prototype);
    abstractBinaryOperation.call(this.multiply, left, right, "*");

    this.evaluate = function (valueX, valueY, valueZ) {
        return this.multiply.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        return this.multiply.toString();
    };

    this.diff = function(variable) {
        return this.multiply.diff(variable);
    };

    this.simplify = function() {
        return this.multiply.simplify();
    };
}

function Divide(left, right) {
    this.divide = Object.create(abstractBinaryOperation.prototype);
    abstractBinaryOperation.call(this.divide, left, right, "/");

    this.evaluate = function (valueX, valueY, valueZ) {
        return this.divide.evaluate(valueX, valueY, valueZ);
    };

    this.toString = function () {
        return this.divide.toString();
    };

    this.diff = function(variable) {
        return this.divide.diff(variable);
    };

    this.simplify = function() {
        return this.divide.simplify();
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

const parse = function(source) {

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

    const MATH_CONSTANTS = {
        "pi": new Const(Math.PI),
        "e": new Const(Math.E),
        "one": new Const(1),
        "two": new Const(2),
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
                case "cube":
                    stack.push(new Cube(args[0]));
                    break;
                case "cuberoot":
                    stack.push(new Cuberoot(args[0]));
                    break;
                case "sin":
                    stack.push(new Sin(args[0]));
                    break;
                case "cos":
                    stack.push(new Cos(args[0]));
                    break;
                case "abs":
                    stack.push(new Abs(args[0]));
                    break;
                case "min3":
                    stack.push(new Min3(args[0], args[1], args[2]));
                    break;
                case "max5":
                    stack.push(new Max5(args[0], args[1], args[2], args[3], args[4]));
                    break;
            }
        } else if (tokens[i] in MATH_CONSTANTS) {
            stack.push(MATH_CONSTANTS[tokens[i]]);
        } else {
            stack.push(new Const(Number(tokens[i])));
        }
        //console.log(stack);
    }
    return stack.pop();
};