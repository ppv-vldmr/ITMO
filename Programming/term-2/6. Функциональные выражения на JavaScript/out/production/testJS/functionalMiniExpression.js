"use strict";

const cnst = value => x => value;
const variable = () => x => x;
const parse = input => input.trim() === "x" ? variable("x") : cnst(+input);
