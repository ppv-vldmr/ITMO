"use strict";

const cnst = value => x => value;
const variable = () => x => x;
const parse = input => input.trim() === "x" ? variable("x") : cnst(+input);

function add(comparator, init = Infinity) {
    return (...args) => {
        let result = init;
        for (const arg of args) {
            if (comparator(result, arg) > 0) {
                result = arg;
            }
        }
        return result;
    }
}

function comparing(f) {
    return (a, b) => f(a) - f(b);
}