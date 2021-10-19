package proof;

import operation.BinaryOperation;
import operation.ModusPonensInfo;
import parser.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NaturalProofConverter {
    private Proof proof;
    private List<String> naturalProof;

    public List<String> getNaturalProof(Proof proof) {
        this.proof = proof;
        this.naturalProof = new ArrayList<>();
        buildNaturalProof(proof.getProofSteps().get(proof.getProofSteps().size() - 1), 0);
        return naturalProof;
    }

    private void buildNaturalProof(Expression statement, int lvl) {
        if (new AxiomMatcher().isAxiom(statement)) {
            getToExpression(statement, lvl);
        } else {
            if (proof.getHypothesis().stream().anyMatch(hypothese -> hypothese.equals(statement))) {
                naturalProof.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + statement.toStringInfix() + " [Ax]");
            } else {
                try {
                    ModusPonensInfo info = (ModusPonensInfo) statement;
                    buildNaturalProof(proof.getProofSteps().get(info.getLeftMP()), lvl + 1);
                    buildNaturalProof(proof.getProofSteps().get(info.getRightMP()), lvl + 1);
                    naturalProof.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + statement.toStringInfix() + " [E->]");
                } catch (Exception ignored) {}
            }
        }
    }

    private String writeHypotheses(List<Expression> hypotheses) {
        return hypotheses.stream().map(Expression::toStringInfix).collect(Collectors.joining(","));
    }

    private void getToExpression(Expression expression, int treeLevel) {
        try {
            BinaryOperation bo = (BinaryOperation) expression;
            switch (new AxiomMatcher().matchesToAxiom(expression)) {
                case 1:
                    get1(bo, treeLevel);
                    break;
                case 2:
                    get2(bo, treeLevel);
                    break;
                case 3:
                    get3(bo, treeLevel);
                    break;
                case 4:
                    get4(bo, treeLevel);
                    break;
                case 5:
                    get5(bo, treeLevel);
                    break;
                case 6:
                    get6(bo, treeLevel);
                    break;
                case 7:
                    get7(bo, treeLevel);
                    break;
                case 8:
                    get8(bo, treeLevel);
                    break;
                case 9:
                    get9(bo, treeLevel);
                    break;
                case 10:
                    get10(bo, treeLevel);
                    break;
                default:
            }
        } catch (Exception ignored) {}
    }

    private void get1(BinaryOperation expression, int lvl) {
        BinaryOperation inner = (BinaryOperation) expression.getRight();

        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" +inner.getRight().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [I->]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get2(BinaryOperation expression, int lvl) {
        BinaryOperation inner = (BinaryOperation) expression.getRight();
        BinaryOperation inner1 = (BinaryOperation) inner.getLeft();
        BinaryOperation inner11 = (BinaryOperation) inner1.getRight();
        BinaryOperation inner12 = (BinaryOperation) expression.getLeft();
        BinaryOperation inner2 = (BinaryOperation) inner.getRight();
        String beforeTurnstile = expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "," + inner2.getLeft().toStringInfix() + "|-";

        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner12.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner11.toStringInfix() + " [E->]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner12.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner12.getRight().toStringInfix() + " [E->]");
        result.add("[" + (lvl + 3) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner2.getRight().toStringInfix() + " [E->]");
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" + inner.getRight().toStringInfix() + " [I->]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [I->]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get3(BinaryOperation expression, int lvl) {
        BinaryOperation inner = (BinaryOperation) expression.getRight();
        String beforeTurnstile = expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-";

        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 3) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 3) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + inner.getRight().toStringInfix() + " [I&]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [I->]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get4(BinaryOperation expression, int lvl) {
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [El&]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get5(BinaryOperation expression, int lvl) {
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [Er&]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get6(BinaryOperation expression, int lvl) {
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [Il|]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get7(BinaryOperation expression, int lvl) {
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [Ir|]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get8(BinaryOperation expression, int lvl) {
        BinaryOperation inner = (BinaryOperation) expression.getRight();
        BinaryOperation inner2 = (BinaryOperation) inner.getRight();
        BinaryOperation inner3 = (BinaryOperation) inner.getLeft();
        BinaryOperation inner1 = (BinaryOperation) expression.getLeft();
        String beforeTurnstile = expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "," + inner2.getLeft().toStringInfix();
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "," + inner1.getLeft().toStringInfix() + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "," + inner1.getLeft().toStringInfix() + "|-" + inner1.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "," + inner1.getLeft().toStringInfix() + "|-" + inner2.getRight().toStringInfix() + " [E->]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "," + inner3.getLeft().toStringInfix() + "|-" + inner.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "," + inner3.getLeft().toStringInfix() + "|-" + inner3.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "," + inner3.getLeft().toStringInfix() + "|-" + inner2.getRight().toStringInfix() + " [E->]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner2.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 3) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner2.getRight().toStringInfix() + " [E|]");
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" + inner.getRight().toStringInfix() + " [I->]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [I->]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get9(BinaryOperation expression, int lvl) {
        BinaryOperation inner = (BinaryOperation) expression.getRight();
        BinaryOperation inner1 = (BinaryOperation) expression.getLeft();
        BinaryOperation inner2 = (BinaryOperation) inner.getLeft();
        String beforeTurnstile = expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "," + inner1.getLeft().toStringInfix();
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner1.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner2.getRight().toStringInfix() + " [E->]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 5) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner1.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-" + inner1.getRight().toStringInfix() + " [E->]");
        result.add("[" + (lvl + 3) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + beforeTurnstile + "|-_|_ [E->]");
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" + inner.getRight().toStringInfix() + " [I->]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [I->]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }

    private void get10(BinaryOperation expression, int lvl) {
        BinaryOperation inner = (BinaryOperation) expression.getRight();
        ArrayList<String> result = new ArrayList<>();
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" + inner.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 4) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" + expression.getLeft().toStringInfix() + " [Ax]");
        result.add("[" + (lvl + 3) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-_|_ [E->]");
        result.add("[" + (lvl + 2) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "," + inner.getLeft().toStringInfix() + "|-" + inner.getRight().toStringInfix() + " [E_|_]");
        result.add("[" + (lvl + 1) + "] " + writeHypotheses(proof.getHypothesis()) + (proof.getHypothesis().size() > 0 ? "," : "") + expression.getLeft().toStringInfix() + "|-" + expression.getRight().toStringInfix() + " [I->]");
        result.add("[" + lvl + "] " + writeHypotheses(proof.getHypothesis()) + "|-" + expression.toStringInfix() + " [I->]");
        
        naturalProof.addAll(result);
    }
}
