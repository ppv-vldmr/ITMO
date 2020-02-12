package expression.parser;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
import expression.*;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser implements Parser {

    @Override
    public CommonExpression parse(String expression) {
        return new Parse(expression).parseExpression();
    }

    private static class Parse extends BaseParser {
        public Parse(Source source) {
            super(source);
            nextChar();
        }

        public Parse(String source) {
            super(new StringSource(source));
            nextChar();
        }

        public CommonExpression parseExpression() {
            List<CommonExpression> operands = new ArrayList<>();
            List<Character> operators = new ArrayList<>();
            while (!test('\0')) {
                skipWhitespace();
                if (between('0', '9') || isVariable()) {
                    operands.add(parseTerm(false));
                } else if (test('(')) {
                    operands.add(parseExpression());
                } else if (test(')')) {
                    return wrap(operands, operators);
                } else if (test('<')) {
                    if (test('<')) {
                        operators.add('<');
                    } else {
                        throw  new IllegalArgumentException("Expected <<");
                    }
                } else if (test('>')) {
                    if (test('>')) {
                        operators.add('>');
                    } else {
                        throw  new IllegalArgumentException("Expected >>");
                    }
                } else {
                    if (operands.size() <= operators.size()) {
                        boolean isMinus = false;
                        while (c == '-') {
                            isMinus = !isMinus;
                            nextChar();
                            skipWhitespace();
                        }
                        if (isMinus) {
                            CommonExpression value =  (test('(') ? new UnaryMinus(parseExpression()) : parseTerm(true));
                            operands.add(value);
                        }
                    } else {
                        operators.add(c);
                        nextChar();
                    }
                }
                skipWhitespace();
            }
            return wrap(operands, operators);
        }

        private CommonExpression wrap(List<CommonExpression> operands, List<Character> operators) {
            List<CommonExpression> secondOrderOperands = new ArrayList<>();
            List<Character> secondOrderOperators = new ArrayList<>();
            secondOrderOperands.add(operands.get(0));
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i).equals('*') || operators.get(i).equals('/')) {
                    secondOrderOperands.set(secondOrderOperands.size() - 1, operators.get(i).equals('*') ?
                            new Multiply(
                                    secondOrderOperands.get(secondOrderOperands.size() - 1), operands.get(i + 1)
                            ) :
                            new Divide(
                                    secondOrderOperands.get(secondOrderOperands.size() - 1), operands.get(i + 1)
                            )
                    );
                } else {
                    secondOrderOperands.add(operands.get(i + 1));
                    secondOrderOperators.add(operators.get(i));
                }
            }

            List<CommonExpression> lastOrderOperands = new ArrayList<>();
            List<Character> lastOrderOperators = new ArrayList<>();
            lastOrderOperands.add(secondOrderOperands.get(0));
            for (int i = 0; i < secondOrderOperators.size(); i++) {
                if (secondOrderOperators.get(i).equals('+') || secondOrderOperators.get(i).equals('-')) {
                    lastOrderOperands.set(lastOrderOperands.size() - 1, secondOrderOperators.get(i).equals('+') ?
                            new Add(
                                    lastOrderOperands.get(lastOrderOperands.size() - 1), secondOrderOperands.get(i + 1)
                            ) :
                            new Subtract(
                                    lastOrderOperands.get(lastOrderOperands.size() - 1), secondOrderOperands.get(i + 1)
                            )
                    );
                } else {
                    lastOrderOperands.add(secondOrderOperands.get(i + 1));
                    lastOrderOperators.add(secondOrderOperators.get(i));
                }
            }
            CommonExpression res = lastOrderOperands.get(0);
            for (int i = 0; i < lastOrderOperators.size(); i++) {
                res = lastOrderOperators.get(i).equals('<') ?
                        new LeftShift(res, lastOrderOperands.get(i + 1)) :
                        new RightShift(res, lastOrderOperands.get(i + 1));
            }

            return res;
        }

        private CommonExpression parseTerm(boolean withMinus) {
            return between('0', '9') ? parseConst(withMinus) : parseVariable(withMinus);
        }

        private CommonExpression parseVariable(boolean withMinus) {
            char name = c;
            nextChar();
            if(withMinus) {
                return new UnaryMinus(
                        new Variable(
                                Character.toString(name)
                        )
                );
            }
            return new Variable(Character.toString(name));
        }

        private CommonExpression parseConst(boolean withMinus) {
            StringBuilder sb = new StringBuilder();
            if (withMinus) {
                sb.append('-');
            }
            while (between('0', '9')) {
                sb.append(c);
                nextChar();
            }

            return new Const(Integer.parseInt(sb.toString()));
        }
    }
}
