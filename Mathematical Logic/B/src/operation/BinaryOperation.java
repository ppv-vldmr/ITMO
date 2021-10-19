package operation;

import parser.Expression;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public abstract class BinaryOperation extends ModusPonensInfo implements Expression{
    private final String sign;
    private final Expression left;
    private final Expression right;
//    private boolean isModusPonens;
//    private int leftMP;
//    private int rightMP;

    public BinaryOperation(String sign, Expression left, Expression right) {
        this.sign = sign;
        this.left = left;
        this.right = right;
//        this.isModusPonens = false;
    }

    @Override
    public String toStringPrefix() {
        return "(" + this.sign + "," + left.toStringPrefix() + "," + right.toStringPrefix() + ")";
    }

    @Override
    public String toStringInfix() {
        return "(" + left.toStringInfix() + this.sign + right.toStringInfix() + ")";
    }

    @Override
    public boolean equals(Expression expression) {
        try {
            BinaryOperation bo = (BinaryOperation) expression;
            return this.sign.equals(bo.sign) && this.left.equals(bo.left) && this.right.equals(bo.right);
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public String getSign() {
        return sign;
    }

//    public void setModusPonens(boolean modusPonens) {
//        isModusPonens = modusPonens;
//    }

//    public boolean isModusPonens() {
//        return isModusPonens;
//    }
//
//    public void setLeftMP(int leftMP) {
//        this.leftMP = leftMP;
//    }
//
//    public void setRightMP(int rightMP) {
//        this.rightMP = rightMP;
//    }
//
//    public int getLeftMP() {
//        return leftMP;
//    }
//
//    public int getRightMP() {
//        return rightMP;
//    }
}
