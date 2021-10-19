package proof;

import operation.BinaryOperation;
import operation.Conjunction;
import operation.Implication;
import operation.Increment;
import operation.Multiplication;
import operation.Negation;
import operation.Predicate;
import operation.Summation;
import operation.UnaryOperation;
import operation.WithQuantifier;
import parser.Expression;
import token.Variable;

public class AxiomMatcher {
    private static final Variable ZERO = new Variable("0");
    private int stepNumber = 0;

    public boolean isAxiom(Expression expression) {
        this.stepNumber++;
        int matchedAxiom = matchesToAxiom(expression);
        if (matchedAxiom >= 1 && matchedAxiom <= 12) {
            System.out.println("[" + stepNumber + ". Ax. sch. " + matchedAxiom + "] " + expression.toStringInfix());
        } else if (matchedAxiom == 13) {
            System.out.println("[" + stepNumber + ". Ax. sch. A9] " + expression.toStringInfix());
        } else if (matchedAxiom > 13) {
            System.out.println("[" + stepNumber + ". Ax. A" + (matchedAxiom - 13) + "] " + expression.toStringInfix());
        }
        return matchedAxiom != -1;
    }

    public int matchesToAxiom(Expression expression) {
        if (matchesToAxiomScheme1(expression)) {
            return 1;
        }
        if (matchesToAxiomScheme2(expression)) {
            return 2;
        }
        if (matchesToAxiomScheme3(expression)) {
            return 3;
        }
        if (matchesToAxiomScheme4(expression)) {
            return 4;
        }
        if (matchesToAxiomScheme5(expression)) {
            return 5;
        }
        if (matchesToAxiomScheme6(expression)) {
            return 6;
        }
        if (matchesToAxiomScheme7(expression)) {
            return 7;
        }
        if (matchesToAxiomScheme8(expression)) {
            return 8;
        }
        if (matchesToAxiomScheme9(expression)) {
            return 9;
        }
        if (matchesToAxiomScheme10(expression)) {
            return 10;
        }
        if (matchesToAxiomScheme11(expression)) {
            return 11;
        }
        if (matchesToAxiomScheme12(expression)) {
            return 12;
        }
        if (matchesToAxiomSchemeA9(expression)) {
            return 13;
        }
        if (matchesToAxiom1(expression)) {
            return 14;
        }
        if (matchesToAxiom2(expression)) {
            return 15;
        }
        if (matchesToAxiom3(expression)) {
            return 16;
        }
        if (matchesToAxiom4(expression)) {
            return 17;
        }
        if (matchesToAxiom5(expression)) {
            return 18;
        }
        if (matchesToAxiom6(expression)) {
            return 19;
        }
        if (matchesToAxiom7(expression)) {
            return 20;
        }
        if (matchesToAxiom8(expression)) {
            return 21;
        }
        return -1;
    }

    // a=b->a'=b'
    private boolean matchesToAxiom1(Expression expression) {
        try {
            Implication whole = (Implication) expression;
            Predicate left = (Predicate) whole.getLeft();
            Predicate right = (Predicate) whole.getRight();
            Variable a = (Variable) left.getLeftTerm();
            Variable b = (Variable) left.getRightTerm();
            Increment incA = (Increment) right.getLeftTerm();
            Increment incB = (Increment) right.getRightTerm();

            return left.getCapitalVariable() == null
                    && right.getCapitalVariable() == null
                    && a.equals(incA.getVal())
                    && b.equals(incB.getVal())
                    && a.getName().equals("a")
                    && b.getName().equals("b");
        } catch (Exception e) {
            return false;
        }
    }

    // a=b->(a=c->b=c)
    private boolean matchesToAxiom2(Expression expression) {
        try {
            Implication whole = (Implication) expression;
            Predicate left = (Predicate) whole.getLeft();
            Implication rImpl = (Implication) whole.getRight();
            Predicate middle = (Predicate) rImpl.getLeft();
            Predicate right = (Predicate) rImpl.getRight();
            Variable a = (Variable) left.getLeftTerm();
            Variable b = (Variable) left.getRightTerm();
            Variable c = (Variable) middle.getRightTerm();

            return left.getCapitalVariable() == null
                    && middle.getCapitalVariable() == null
                    && right.getCapitalVariable() == null
                    && a.equals(middle.getLeftTerm())
                    && b.equals(right.getLeftTerm())
                    && c.equals(right.getRightTerm())
                    && a.getName().equals("a")
                    && b.getName().equals("b")
                    && c.getName().equals("c");
        } catch (Exception e) {
            return false;
        }
    }

    // a'=b'->a=b
    private boolean matchesToAxiom3(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            Predicate left = (Predicate) whole.getLeft();
            Predicate right = (Predicate) whole.getRight();
            Increment incA = (Increment) left.getLeftTerm();
            Increment incB = (Increment) left.getRightTerm();
            Variable a = (Variable) right.getLeftTerm();
            Variable b = (Variable) right.getRightTerm();

            return left.getCapitalVariable() == null
                    && right.getCapitalVariable() == null
                    && a.equals(incA.getVal())
                    && b.equals(incB.getVal())
                    && a.getName().equals("a")
                    && b.getName().equals("b");
        } catch (Exception e) {
            return false;
        }
    }

    // !a'=0
    private boolean matchesToAxiom4(Expression expression) {
        try {
            Negation negation = (Negation) expression;
            Predicate predicate = (Predicate) negation.getVal();
            Increment inc = (Increment) predicate.getLeftTerm();
            Variable la = (Variable) inc.getVal();
            Variable zero = (Variable) predicate.getRightTerm();

            return predicate.getCapitalVariable() == null
                    && zero.equals(ZERO) && la.getName().equals("a");
        } catch (Exception e) {
            return false;
        }
    }

    // a+b'=(a+b)'
    private boolean matchesToAxiom5(Expression expression) {
        try {
            Predicate whole = (Predicate) expression;
            Summation leftSum = (Summation) whole.getLeftTerm();
            Variable la = (Variable) leftSum.getLeft();
            Increment incB = (Increment) leftSum.getRight();
            Variable lb = (Variable) incB.getVal();
            Increment rightInc = (Increment) whole.getRightTerm();
            Summation rightSum = (Summation) rightInc.getVal();
            Variable ra = (Variable) rightSum.getLeft();
            Variable rb = (Variable) rightSum.getRight();

            return whole.getCapitalVariable() == null
                    && la.equals(ra)
                    && lb.equals(rb)
                    && la.getName().equals("a")
                    && lb.getName().equals("b");
        } catch (Exception e) {
            return false;
        }
    }

    // a+0=a
    private boolean matchesToAxiom6(Expression expression) {
        try {
            Predicate whole = (Predicate) expression;
            Variable ra = (Variable) whole.getRightTerm();
            Summation leftSum = (Summation) whole.getLeftTerm();
            Variable la = (Variable) leftSum.getLeft();
            Variable zero = (Variable) leftSum.getRight();

            return whole.getCapitalVariable() == null
                    && la.equals(ra)
                    && zero.equals(ZERO)
                    && la.getName().equals("a");
        } catch (Exception e) {
            return false;
        }
    }

    // a*0=0
    private boolean matchesToAxiom7(Expression expression) {
        try {
            Predicate whole = (Predicate) expression;
            Variable rz = (Variable) whole.getRightTerm();
            Multiplication mul = (Multiplication) whole.getLeftTerm();
            Variable a = (Variable) mul.getLeft();
            Variable lz = (Variable) mul.getRight();

            return whole.getCapitalVariable() == null
                    && lz.equals(ZERO)
                    && rz.equals(ZERO)
                    && a.getName().equals("a");
        } catch (Exception e) {
            return false;
        }
    }

    // a*b'=a*b+a
    private boolean matchesToAxiom8(Expression expression) {
        try {
            Predicate whole = (Predicate) expression;
            Multiplication lMul = (Multiplication) whole.getLeftTerm();
            Variable la = (Variable) lMul.getLeft();
            Increment incB = (Increment) lMul.getRight();
            Summation sum = (Summation) whole.getRightTerm();
            Variable ra = (Variable) sum.getRight();
            Multiplication rMul = (Multiplication) sum.getLeft();
            Variable ma = (Variable) rMul.getLeft();
            Variable b = (Variable) rMul.getRight();

            return whole.getCapitalVariable() == null
                    && la.equals(ma)
                    && la.equals(ra)
                    && b.equals(incB.getVal())
                    && la.getName().equals("a")
                    && b.getName().equals("b");
        } catch (Exception e) {
            return false;
        }
    }

    // A->(B->A)
    private boolean matchesToAxiomScheme1(Expression expression) {
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

    // (A->B)->(A->B->C)->(A->C)
    private boolean matchesToAxiomScheme2(Expression expression) {
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

    // A->B->A&B
    private boolean matchesToAxiomScheme3(Expression expression) {
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

    // A&B->A
    private boolean matchesToAxiomScheme4(Expression expression) {
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

    // A&B->B
    private boolean matchesToAxiomScheme5(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            BinaryOperation conj = (BinaryOperation) whole.getLeft();

            return conj.getSign().equals("&")
                    &&
                    whole.getSign().equals("->")
                    &&
                    conj.getRight().equals(whole.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    // A->A|B
    private boolean matchesToAxiomScheme6(Expression expression) {
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

    // B->A|B
    private boolean matchesToAxiomScheme7(Expression expression) {
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

    // (A->C)->(B->C)->(A|B->C)
    private boolean matchesToAxiomScheme8(Expression expression) {
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

    // (A->B)->(A->!B)->!A
    private boolean matchesToAxiomScheme9(Expression expression) {
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

    // !!A->A
    private boolean matchesToAxiomScheme10(Expression expression) {
        try {
            BinaryOperation whole = (BinaryOperation) expression;
            UnaryOperation left = (UnaryOperation) whole.getLeft();
            UnaryOperation leftInner = (UnaryOperation) left.getVal();
            return whole.getSign().equals("->")
                    &&
                    left.getSign().equals("!") && leftInner.getSign().equals("!")
                    &&
                    leftInner.getVal().equals(whole.getRight());
        } catch (Exception e) {
            return false;
        }
    }

    // @x.(P)->(P[x:=a])
    private boolean matchesToAxiomScheme11(Expression expression) {
        try {
            Implication whole = (Implication) expression;
            WithQuantifier left = (WithQuantifier) whole.getLeft();
            Expression right = whole.getRight();
            if (!left.getQuantifier().equals("@")) {
                return false;
            }
            if (left.getExpr().equals(right)) {
                return true;
            }
//            if (!right.isFreeForV(left.getVar())) {
//                return false;
//            }
            SubstitutionInfo info = new SubstitutionInfo();
            info.var = left.getVar().toStringInfix();
            left.getExpr().substitution(right, info);
            if (info.not_free) {
                if (!right.isFreeForV(new Variable("x"))) {
                    return false;
                }
                System.out.println("Expression " + stepNumber + ": variable " + info.var + " is not free for term " + info.theta.toStringInfix() + " in @-axiom.");
                System.exit(0);
            }
            return info.status == SubstitutionStatus.CONTINUE;
        } catch (Exception e) {
            return false;
        }
    }

    // (P[x:=a])->?x.(P)
    private boolean matchesToAxiomScheme12(Expression expression) {
        try {
            Implication whole = (Implication) expression;
            Expression left = whole.getLeft();
            WithQuantifier right = (WithQuantifier) whole.getRight();
            if (!right.getQuantifier().equals("?")) {
                return false;
            }
            if (left.equals(right.getExpr())) {
                return true;
            }
//            if (!left.isFreeForV(right.getVar())) {
//                return false;
//            }
            SubstitutionInfo info = new SubstitutionInfo();
            right.getExpr().substitution(left, info);
            if (info.not_free) {
                if (!left.isFreeForV(new Variable("x"))) {
                    return false;
                }
                System.out.println("Expression " + stepNumber + ": variable " + info.var + " is not free for term " + info.theta.toStringInfix() + " in ?-axiom.");
                System.exit(0);
            }
            return info.status == SubstitutionStatus.CONTINUE;
        } catch (Exception e) {
            return false;
        }
    }

    // (P[x:=a])&(@x.P->(P[x:=x']))->P
    private boolean matchesToAxiomSchemeA9(Expression expression) {
        try {
            Implication whole = (Implication) expression;
            Expression rp = whole.getRight();
            Conjunction conj = (Conjunction) whole.getLeft();
            Expression pa = conj.getLeft();
            WithQuantifier quant = (WithQuantifier) conj.getRight();
            if (!quant.getQuantifier().equals("@")) {
                return false;
            }
            Implication impl = (Implication) quant.getExpr();
            Expression lp = impl.getLeft();
            if (!lp.equals(rp)) {
                return false;
            }
            Expression px = impl.getRight();
            Variable x = (Variable) quant.getVar();

            SubstitutionInfo si = new SubstitutionInfo();
            // функция проверяет что мы заменили все только свободные вхождения x и находит theta
            boolean res = lp.correctSubstIn(px, x, si);
            return res && si.theta.equals(new Increment(x)) && lp.correctSubstIn(pa, x, new SubstitutionInfo());
        } catch (Exception e) {
            return false;
        }
    }
}
