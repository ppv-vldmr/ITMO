//
//  main.swift
//  Homework-2
//
//  Created by Владимир Попов on 25.09.2020.
//  Copyright © 2020 Vladimir Popov. All rights reserved.
//

import Foundation

protocol Calculatable: Numeric, Comparable {
    init?(_ string: String)

    static func add(args: [Self]) throws -> Self
    static func subtract(args: [Self]) throws -> Self
    static func multiply(args: [Self]) throws -> Self
    static func divide(args: [Self]) throws  -> Self
    static func mod(args: [Self]) throws -> Self
    static func power(args: [Self]) throws -> Self
    static func factorial(args: [Self]) throws -> Self
    static func negate(args: [Self]) throws -> Self
    static func abs(args: [Self]) throws -> Self
}

extension Int: Calculatable {
    static func add(args: [Int]) throws -> Int {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        return args.reduce(0, +)
    }
    static func subtract(args: [Int]) throws -> Int {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected:2, actual: args.count)
        }
        return args.reduce(2 * args[0], -)
    }
    static func multiply(args: [Int]) throws -> Int {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        return args.reduce(1, *)
    }
    static func divide(args: [Int]) throws -> Int {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        guard args[1] != 0 else {
            throw EvaluationError.divisionByZero
        }
        return args[0] / args[1]
    }
    static func mod(args: [Int]) throws -> Int {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        guard args[1] != 0 else {
            throw EvaluationError.divisionByZero
        }
        return args[0] % args[1]
    }
    static func power(args: [Int]) throws -> Int {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        return Int(pow(Double(args[0]), Double(args[1])))
    }
    static func factorial(args: [Int]) throws -> Int {
        guard args.count == 1 else {
            throw EvaluationError.arityError(expected: 1, actual: args.count)
        }
        return (1...args[0]).reduce(1, *)
    }
    static func negate(args: [Int]) throws -> Int {
        guard args.count == 1 else {
            throw EvaluationError.arityError(expected: 1, actual: args.count)
        }
        return -args[0]
    }
    static func abs(args: [Int]) throws -> Int {
        guard args.count == 1 else {
            throw EvaluationError.arityError(expected: 1, actual: args.count)
        }
        return args[0] >= 0 ? args[0] : try negate(args: [args[0]])
    }
}

extension Double: Calculatable {
    static func add(args: [Double]) throws -> Double {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        return args.reduce(0, +)
    }
    static func subtract(args: [Double]) throws -> Double {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected:2, actual: args.count)
        }
        return args.reduce(2 * args[0], -)
    }
    static func multiply(args: [Double]) throws -> Double {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        return args.reduce(1, *)
    }
    static func divide(args: [Double]) throws -> Double {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        guard args[1] != 0 else {
            throw EvaluationError.divisionByZero
        }
        return args[0] / args[1]
    }
    static func mod(args: [Double]) throws -> Double {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        guard args[1] != 0 else {
            throw EvaluationError.divisionByZero
        }
        return Double(Int(args[0]) % Int(args[1]))
    }
    static func power(args: [Double]) throws -> Double {
        guard args.count == 2 else {
            throw EvaluationError.arityError(expected: 2, actual: args.count)
        }
        return Double(pow(Double(args[0]), Double(args[1])))
    }
    static func factorial(args: [Double]) throws -> Double {
        guard args.count == 1 else {
            throw EvaluationError.arityError(expected: 1, actual: args.count)
        }
        return Double((1...Int(args[0])).reduce(1, *))
    }
    static func negate(args: [Double]) throws -> Double {
        guard args.count == 1 else {
            throw EvaluationError.arityError(expected: 1, actual: args.count)
        }
        return -args[0]
    }
    static func abs(args: [Double]) throws -> Double {
        guard args.count == 1 else {
            throw EvaluationError.arityError(expected: 1, actual: args.count)
        }
        return args[0] >= 0 ? args[0] : try negate(args: [args[0]])
    }
}

enum EvaluationError: Error, CustomStringConvertible {
    case invalidToken(token: String)
    case arityError(expected: Int, actual: Int)
    case divisionByZero
    case rpnError
    case bracketError
    case expressionError
    case unknownError
    case unknownMode(mode: String)

    var description: String {
        switch self {
        case .invalidToken(token: let token):
            return "CustomError --- Invalid token: \"\(token)\""
        case .divisionByZero:
            return "CustomError --- Division By Zero"
        case .arityError(expected: let expected, actual: let actual):
            return "CustomError --- Wrong count of arguments. Expected: \(expected), actual: \(actual)."
        case .rpnError:
            return "CustomError --- Wrong count of tokens in RPN: \(CommandLine.arguments.joined(separator: " "))"
        case .bracketError:
            return "CustomError --- Wrong count of brackets: \(CommandLine.arguments.joined(separator: " "))"
        case .expressionError:
            return "CustomError --- Invalid expression: \(CommandLine.arguments.joined(separator: " "))"
        case .unknownError:
            return "CustomError --- UNKNOWN ERROR"
        case .unknownMode(let mode):
            return "CustomError --- Unknown work mode entered. Expected: \"--infix\"\\\"--postfix\", actual: \"\(mode)\"."
        }
    }
}

extension Optional {
    func unwrap(or error: Error) throws -> Wrapped {
        guard let wrapped = self else {
            throw error
        }
        return wrapped
    }
}

struct Operator<Num: Calculatable>: CustomStringConvertible {
    let name: String
    let precedence: Int
    let numberOfArguments: Int
    let f: ([Num]) throws -> Num
    enum associativity {
        case right, left, non
    }
    let associativity: associativity
    var description: String {
        return self.name
    }
}

enum Token<Num: Calculatable>: CustomStringConvertible, Equatable {
    static func == (lhs: Token<Num>, rhs: Token<Num>) -> Bool {
        return lhs.description == rhs.description
    }

    case value(Num)
    case `operator`(Operator<Num>)
    case openBracket
    case closeBracket

    var description: String {
        switch self {
        case .value(let num): return "\(num)"
        case .operator(let op): return op.description
        case .openBracket: return "("
        case .closeBracket: return ")"
        }
    }
}

func defaultOperators<Num: Calculatable>() -> [Operator<Num>] {
    [
        Operator(name: "+", precedence: 10, numberOfArguments: 2, f: Num.add, associativity: .left),
        Operator(name: "-", precedence: 10, numberOfArguments: 2, f: Num.subtract, associativity: .left),
        Operator(name: "*", precedence: 20, numberOfArguments: 2, f: Num.multiply, associativity: .left),
        Operator(name: "/", precedence: 20, numberOfArguments: 2, f: Num.divide, associativity: .left),
        Operator(name: "%", precedence: 20, numberOfArguments: 2, f: Num.mod, associativity: .left),
        Operator(name: "^", precedence: 30, numberOfArguments: 2, f: Num.power, associativity: .right),
        Operator(name: "negate", precedence: 30, numberOfArguments: 1, f: Num.negate, associativity: .left),
        Operator(name: "abs", precedence: 30, numberOfArguments: 1, f: Num.abs, associativity: .left),
        Operator(name: "!", precedence: 40, numberOfArguments: 1, f: Num.factorial, associativity: .non)
    ]
}

func eval<Num: Calculatable>(_ input: [String], operators ops: [Operator<Num>] = defaultOperators()) throws -> Num {
    let operators: [String: Operator<Num>] = Dictionary(uniqueKeysWithValues: ops.map { ($0.name, $0) })

    let brackets: [String: Token<Num>] = ["(":Token.openBracket, ")":Token.closeBracket]

    var tokens: [Token<Num>] = try input.map {
        try (Num($0).map(Token.value) ?? brackets[$0] ?? operators[$0].map(Token.operator)).unwrap(or: EvaluationError.invalidToken(token: $0))
    }

    let unaryPlusMinus = ["-": "negate", "+": "abs"]
    
    for i in 0...tokens.count - 1 {
        switch tokens[i].description {
        case "-", "+":
            if (i == 0) {
                tokens[i] = try(operators[unaryPlusMinus[tokens[i].description] ?? ""].map(Token.operator)).unwrap(or: EvaluationError.unknownError)
                continue
            }
            switch tokens[i - 1] {
            case .value, .closeBracket:
                break
            case .operator(let op):
                if op.numberOfArguments == 2 {
                    tokens[i] = try(operators[unaryPlusMinus[tokens[i].description] ?? ""].map(Token.operator)).unwrap(or: EvaluationError.unknownError)
                }
            case .openBracket:
                tokens[i] = try(operators[unaryPlusMinus[tokens[i].description] ?? ""].map(Token.operator)).unwrap(or: EvaluationError.unknownError)
            }
        default:
            continue
        }
    }

    let rpnExt: (rpn: [Token<Num>], opStack: [Token<Num>]) = try tokens.reduce(into: (rpn: [], opStack: [])) { (acc, token) in
        switch token {
        case .value:
            acc.rpn.append(token)
        case .operator(let op):
            var flag = true
            while flag {
                if acc.opStack.count == 0 {
                    break
                }
                switch acc.opStack.last {
                case .operator(let topOp):
                    if acc.opStack.count != 0 && (topOp.precedence > op.precedence || (topOp.precedence == op.precedence && op.associativity == Operator.associativity.left)) && acc.opStack.last != Token.openBracket  {
                        acc.rpn.append(.operator(topOp))
                        acc.opStack.removeLast()
                    } else {
                        flag = false
                    }
                case .openBracket:
                    flag = false
                default:
                    throw EvaluationError.unknownError
                }
            }
            acc.opStack.append(token)
        case .openBracket:
            acc.opStack.append(token)
        case .closeBracket:
            while acc.opStack.last != Token.openBracket {
                guard let last = acc.opStack.last else {
                    throw EvaluationError.expressionError
                }
                acc.rpn.append(last)
                acc.opStack.removeLast()
                guard !acc.opStack.isEmpty else {
                    throw EvaluationError.bracketError
                }
            }
            if acc.opStack.last == Token.openBracket {
                acc.opStack.removeLast()
            }
        }
    }

    let rpn = rpnExt.rpn + rpnExt.opStack.reversed()

    return try getValueRPN(rpn: rpn)
}

func getValueRPN<Num: Calculatable>(rpn: [Token<Num>]) throws -> Num {
    let valStack: [Num] = try rpn.reduce(into: [Num]()) { (valStack, token) in
        switch token {
        case .value(let num):
            valStack.append(num)
        case .operator(let op):
            var args: [Num] = []
            for i in 1...op.numberOfArguments {
                guard let popOp = valStack.popLast() else {
                    throw EvaluationError.arityError(expected: op.numberOfArguments, actual: i - 1)
                }
                args.append(popOp)
            }
            args.reverse()
            valStack.append(try op.f(args))
        case .openBracket, .closeBracket:
            throw EvaluationError.bracketError
        }
    }

    guard let result = valStack.first, valStack.count == 1 else {
        throw EvaluationError.rpnError
    }

    return result
}

func parseInput<Num: Calculatable>() throws -> Num {
    var arg: [String] = []
    let flag: String
    let cnt_args: Int
    if CommandLine.arguments.last == "--postfix" || CommandLine.arguments.last == "--infix" {
        flag = CommandLine.arguments.last ?? "--infix"
        cnt_args = CommandLine.arguments.count - 1
    } else {
        flag = ""
        cnt_args = CommandLine.arguments.count
    }
    for i in 1..<cnt_args {
        if CommandLine.arguments[i] != CommandLine.arguments.first {
            arg.append(CommandLine.arguments[i])
        } else {
            arg.append("*")
        }
    }
    switch flag {
    case "--postfix":
        let operators: [String: Operator<Num>] = Dictionary(uniqueKeysWithValues: defaultOperators().map { ($0.name, $0) })
        let brackets: [String: Token<Num>] = ["(":Token.openBracket, ")":Token.closeBracket]
        
        let tokens: [Token<Num>] = try arg.map {
            try (Num($0).map(Token.value) ?? brackets[$0] ?? operators[$0].map(Token.operator)).unwrap(or: EvaluationError.invalidToken(token: $0))
        }
        
        return try getValueRPN(rpn: tokens)
    case "--infix":
        return try eval(arg)
    default:
        throw EvaluationError.unknownMode(mode: flag)
    }
}

print(try parseInput() as Double)
