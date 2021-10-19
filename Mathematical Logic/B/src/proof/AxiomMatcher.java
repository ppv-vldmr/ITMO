package proof;

import operation.BinaryOperation;
import operation.UnaryOperation;
import parser.Expression;

public class AxiomMatcher {

    public boolean isAxiom(Expression expression) {
        return matchesToAxiom(expression) != -1;
    }

    public int matchesToAxiom(Expression expression) {
        if (matchesToAxiom1(expression)) {
            return 1;
        }
        if (matchesToAxiom2(expression)) {
            return 2;
        }
        if (matchesToAxiom3(expression)) {
            return 3;
        }
        if (matchesToAxiom4(expression)) {
            return 4;
        }
        if (matchesToAxiom5(expression)) {
            return 5;
        }
        if (matchesToAxiom6(expression)) {
            return 6;
        }
        if (matchesToAxiom7(expression)) {
            return 7;
        }
        if (matchesToAxiom8(expression)) {
            return 8;
        }
        if (matchesToAxiom9(expression)) {
            return 9;
        }
        if (matchesToAxiom10(expression)) {
            return 10;
        }
        return -1;
    }

    private boolean matchesToAxiom1(Expression expression) {
        try {
            BinaryOperation bo = (BinaryOperation) expression;
            BinaryOperation bo1 = (BinaryOperation) bo.getRight();

            return bo.getSign().equals(bo1.getSign()) && bo.getSign().equals("->")
                    &&
                    bo.getLeft().equals(bo1.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom2(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation block1 = (BinaryOperation) whole.getLeft();
            BinaryOperation blocks23 = (BinaryOperation) whole.getRight();
            BinaryOperation block2 = (BinaryOperation) blocks23.getLeft();
            BinaryOperation block2_23var = (BinaryOperation) block2.getRight();
            BinaryOperation block3 = (BinaryOperation) blocks23.getRight();

            return whole.getSign().equals("->") && whole.getSign().equals(block1.getSign()) && block1.getSign().equals(blocks23.getSign()) && blocks23.getSign().equals(block2.getSign()) && block2.getSign().equals(block2_23var.getSign()) && block2_23var.getSign().equals(block3.getSign())
                    &&
                    block1.getLeft().equals(block2.getLeft()) && block1.getLeft().equals(block3.getLeft())
                    &&
                    block1.getRight().equals(block2_23var.getLeft())
                    &&
                    block2_23var.getRight().equals(block3.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom3(Expression expression) {
        try{
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation middleAndConj = (BinaryOperation) whole.getRight();
            BinaryOperation conj = (BinaryOperation) middleAndConj.getRight();

            return whole.getSign().equals("->") && whole.getSign().equals(middleAndConj.getSign())
                    &&
                    conj.getSign().equals("&")
                    &&
                    whole.getLeft().equals(conj.getLeft())
                    &&
                    middleAndConj.getLeft().equals(conj.getRight());
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean matchesToAxiom4(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation conj = (BinaryOperation) whole.getLeft();

            return conj.getSign().equals("&")
                    &&
                    whole.getSign().equals("->")
                    &&
                    conj.getLeft().equals(whole.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom5(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation conj = (BinaryOperation) whole.getLeft();;

            return conj.getSign().equals("&")
                    &&
                    whole.getSign().equals("->")
                    &&
                    conj.getRight().equals(whole.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom6(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation disj = (BinaryOperation) whole.getRight();

            return whole.getSign().equals("->")
                    &&
                    disj.getSign().equals("|")
                    &&
                    whole.getLeft().equals(disj.getLeft());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom7(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation disj = (BinaryOperation) whole.getRight();

            return whole.getSign().equals("->")
                    &&
                    disj.getSign().equals("|")
                    &&
                    whole.getLeft().equals(disj.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom8(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation block1 = (BinaryOperation) whole.getLeft();
            BinaryOperation block23 = (BinaryOperation) whole.getRight();
            BinaryOperation block2 = (BinaryOperation) block23.getLeft();
            BinaryOperation block3 = (BinaryOperation) block23.getRight();
            BinaryOperation block3disj = (BinaryOperation) block3.getLeft();

            return whole.getSign().equals("->") && whole.getSign().equals(block23.getSign()) && whole.getSign().equals(block1.getSign()) && whole.getSign().equals(block2.getSign()) && whole.getSign().equals(block3.getSign())
                    &&
                    block3disj.getSign().equals("|")
                    &&
                    block1.getLeft().equals(block3disj.getLeft())
                    &&
                    block1.getRight().equals(block2.getRight()) && block1.getRight().equals(block3.getRight())
                    &&
                    block2.getLeft().equals(block3disj.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom9(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation block1 = (BinaryOperation) whole.getLeft();
            BinaryOperation block23 = (BinaryOperation) whole.getRight();
            BinaryOperation block2 = (BinaryOperation) block23.getLeft();
            UnaryOperation block3 = (UnaryOperation) block23.getRight();
            UnaryOperation block2neg = (UnaryOperation) block2.getRight();

            return whole.getSign().equals("->") && whole.getSign().equals(block23.getSign()) && whole.getSign().equals(block1.getSign()) && whole.getSign().equals(block2.getSign())
                    &&
                    block2neg.getSign().equals("!") && block2neg.getSign().equals(block3.getSign())
                    &&
                    block1.getLeft().equals(block2.getLeft()) && block1.getLeft().equals(block3.getVal())
                    &&
                    block1.getRight().equals(block2neg.getVal());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchesToAxiom10(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation block23 = (BinaryOperation) whole.getRight();
            UnaryOperation block2neg = (UnaryOperation) block23.getLeft();

            return whole.getSign().equals("->") && whole.getSign().equals(block23.getSign())
                    &&
                    whole.getLeft().equals(block2neg.getVal());
        } catch (Exception e) {
            return false;
        }
    }
}
