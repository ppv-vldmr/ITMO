'use strict';

/**
 * Складывает два целых числа
 * @param {Number} a Первое целое
 * @param {Number} b Второе целое
 * @throws {TypeError} Когда в аргументы переданы не числа
 * @returns {Number} Сумма аргументов
 */
function abProblem(a, b) {
    if (typeof a === "string" || typeof b === "string") {
        throw TypeError("Expected two numbers");
    }
    return a.valueOf() + b.valueOf();
}

/**
 * Определяет век по году
 * @param {Number} year Год, целое положительное число
 * @throws {TypeError} Когда в качестве года передано не число
 * @throws {RangeError} Когда год – отрицательное значение
 * @returns {Number} Век, полученный из года
 */
function centuryByYearProblem(year) {
    if (typeof year !== "number") {
        throw TypeError("Expected one number")
    }
    if (year <= 0) {
        throw RangeError("Expected number greater than 0")
    }
    return Math.ceil(year / 100);
}

/**
 * Переводит цвет из формата HEX в формат RGB
 * @param {String} hexColor Цвет в формате HEX, например, '#FFFFFF'
 * @throws {TypeError} Когда цвет передан не строкой
 * @throws {RangeError} Когда значения цвета выходят за пределы допустимых
 * @returns {String} Цвет в формате RGB, например, '(255, 255, 255)'
 */
function colorsProblem(hexColor) {
    if (typeof hexColor !== "string") {
        throw TypeError("Expected one string argument")
    }
    let result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hexColor);
    if (result === null) {
        throw RangeError("Not a valid value")
    }
    let rgb =  {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16),
    };
    for (let rgbKey in rgb) {
        if (rgbKey < 0 || rgbKey > 255) {
            throw RangeError("Not a valid value")
        }
    }
    return '(' + rgb.r + ', ' + rgb.g + ', ' + rgb.b + ')'
}

/**
 * Находит n-ое число Фибоначчи
 * @param {Number} n Положение числа в ряде Фибоначчи
 * @throws {TypeError} Когда в качестве положения в ряде передано не число
 * @throws {RangeError} Когда положение в ряде не является целым положительным числом
 * @returns {Number} Число Фибоначчи, находящееся на n-ой позиции
 */
function fibonacciProblem(n) {
    if (typeof n !== "number") {
        throw TypeError("Expected one number")
    }
    if (n % 1 !== 0 || n <= 0) {
        throw RangeError("Not a valid value")
    }
    var f1 = 0, f2 = 1
    for (var i = 1; i < n; i++) {
        if (i % 2 === 1) {
            f2 += f1
        } else {
            f1 += f2
        }
    }
    return f2 + f1
}

/**
 * Транспонирует матрицу
 * @param {(Any[])[]} matrix Матрица размерности MxN
 * @throws {TypeError} Когда в функцию передаётся не двумерный массив
 * @returns {(Any[])[]} Транспонированная матрица размера NxM
 */
function matrixProblem(matrix) {
    const is2dArray = array =>  array.every(item => Array.isArray(item));
    if (matrix.constructor !== Array  || matrix.length <= 0 || !is2dArray(matrix)) {
        throw new TypeError("Not a valid value")
    }
    matrix = matrix[0].map((_, colIndex) => matrix.map(row => row[colIndex]));
    return matrix
}

/**
 * Переводит число в другую систему счисления
 * @param {Number} n Число для перевода в другую систему счисления
 * @param {Number} targetNs Система счисления, в которую нужно перевести (Число от 2 до 36)
 * @throws {TypeError} Когда переданы аргументы некорректного типа
 * @throws {RangeError} Когда система счисления выходит за пределы значений [2, 36]
 * @returns {String} Число n в системе счисления targetNs
 */
function numberSystemProblem(n, targetNs) {
    if (typeof n !== "number" || typeof targetNs !== "number") {
        throw TypeError("Expected two numbers")
    }
    if (targetNs < 2 || targetNs > 36) {
        throw RangeError("Expected base of new numerical system from 2 to 36")
    }
    return n.toString(targetNs)
}

/**
 * Проверяет соответствие телефонного номера формату
 * @param {String} phoneNumber Номер телефона в формате '8–800–xxx–xx–xx'
 * @throws {TypeError} Когда в качестве аргумента передаётся не строка
 * @returns {Boolean} Если соответствует формату, то true, а иначе false
 */
function phoneProblem(phoneNumber) {
    if (typeof phoneNumber !== "string") {
        throw TypeError("Expected one string argument")
    }
    let result = /^8-800-?([0-9]{3})-?([0-9]{2})-?([0-9]{2})$/i.exec(phoneNumber);
    return result != null
}

/**
 * Определяет количество улыбающихся смайликов в строке
 * @param {String} text Строка в которой производится поиск
 * @throws {TypeError} Когда в качестве аргумента передаётся не строка
 * @returns {Number} Количество улыбающихся смайликов в строке
 */
function smilesProblem(text) {
    if (typeof text !== "string") {
        throw TypeError("Expected one string argument")
    }
    const count = (str) => {
        const re = /[:][-][)]/g
        return ((str || '').match(re) || []).length
    }
    const count2 = (str) => {
        const re = /[(][-][:]/g
        return ((str || '').match(re) || []).length
    }
    const count3 = (str) => {
        const re = /[(][-][:][-][)]/g
        return ((str || '').match(re) || []).length
    }
    return count(text) + count2(text) - count3(text)
}

// console.log(smilesProblem("amcdasbszkjzbdv:-)jhsvbjdbsbjz(-:-)"))

/**
 * Определяет победителя в игре "Крестики-нолики"
 * Тестами гарантируются корректные аргументы.
 * @param {(('x' | 'o')[])[]} field Игровое поле 3x3 завершённой игры
 * @returns {'x' | 'o' | 'draw'} Результат игры
 */
function ticTacToeProblem(field) {
    if (
        field[0][0] + field[0][1] + field[0][2] === 'xxx'
        ||
        field[1][0] + field[1][1] + field[1][2] === 'xxx'
        ||
        field[2][0] + field[2][1] + field[2][2] === 'xxx'
        ||
        field[0][0] + field[1][0] + field[2][0] === 'xxx'
        ||
        field[0][1] + field[1][1] + field[2][1] === 'xxx'
        ||
        field[0][2] + field[1][2] + field[2][2] === 'xxx'
        ||
        field[0][0] + field[1][1] + field[2][2] === 'xxx'
        ||
        field[0][2] + field[1][1] + field[2][0] === 'xxx'
    ) {
        return 'x'
    }
    if (
        field[0][0] + field[0][1] + field[0][2] === 'ooo'
        ||
        field[1][0] + field[1][1] + field[1][2] === 'ooo'
        ||
        field[2][0] + field[2][1] + field[2][2] === 'ooo'
        ||
        field[0][0] + field[1][0] + field[2][0] === 'ooo'
        ||
        field[0][1] + field[1][1] + field[2][1] === 'ooo'
        ||
        field[0][2] + field[1][2] + field[2][2] === 'ooo'
        ||
        field[0][0] + field[1][1] + field[2][2] === 'ooo'
        ||
        field[0][2] + field[1][1] + field[2][0] === 'ooo'
    ) {
        return 'o'
    }
    return "draw"
}

module.exports = {
    abProblem,
    centuryByYearProblem,
    colorsProblem,
    fibonacciProblem,
    matrixProblem,
    numberSystemProblem,
    phoneProblem,
    smilesProblem,
    ticTacToeProblem
};