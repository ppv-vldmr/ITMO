package proof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import operation.BinaryOperation;
import operation.ModusPonensInfo;
import operation.UnaryOperation;
import parser.Expression;
import parser.ExpressionParser;
import token.Variable;

public class Proof {
    private List<Expression> hypothesis;
    private Expression statement;
    private final List<Expression> proofSteps;
    private final ExpressionParser parser;
    private final AxiomMatcher axiomMatcher;
    private final ModusPonensMatcher modusPonensMatcher;

    public Proof() {
        hypothesis = new ArrayList<>();
        proofSteps = new ArrayList<>();
        parser = new ExpressionParser();
        axiomMatcher = new AxiomMatcher();
        modusPonensMatcher = new ModusPonensMatcher();
    }

    public void setStatementAndHypothesis(String input) {
        int turnstileIndex = input.indexOf("|-");

        if (turnstileIndex > 0) {
            this.hypothesis =
                    Arrays.stream(input.substring(0, turnstileIndex).split(","))
                            .map(this.parser::parse)
                            .collect(Collectors.toList());
        } else {
            this.hypothesis = new ArrayList<>();
        }

        this.statement = parser.parse(input.substring(turnstileIndex + 2));
    }

    public boolean tryAddStep(String input) {
        Expression newStep = this.parser.parse(input);
        if (checkNewStep(newStep)) {
            return true;
        }
        return false;
    }

    private boolean checkNewStep(Expression newStep) {
        if (axiomMatcher.isAxiom(newStep)) {
            this.proofSteps.add(newStep);
            return true;
        }
        if (inHypothesis(newStep)) {
            return true;
        }
        ModusPonensInfo newStepMPInfo = modusPonensMatcher.isModusPonens(newStep, proofSteps); //
        if (newStepMPInfo.isModusPonens()) {
            try {
                BinaryOperation bo = (BinaryOperation) newStep;
                bo.setModusPonens(true);
                bo.setRightMP(newStepMPInfo.getRightMP());
                bo.setLeftMP(newStepMPInfo.getLeftMP());
                this.proofSteps.add(bo);
                return true;
            } catch (Exception ignored) {}
            try {
                UnaryOperation uo = (UnaryOperation) newStep;
                uo.setModusPonens(true);
                uo.setRightMP(newStepMPInfo.getRightMP());
                uo.setLeftMP(newStepMPInfo.getLeftMP());
                this.proofSteps.add(uo);
                return true;
            } catch (Exception ignored) {}
            try {
                Variable var = (Variable) newStep;
                var.setModusPonens(true);
                var.setLeftMP(newStepMPInfo.getLeftMP());
                var.setRightMP(newStepMPInfo.getRightMP());
                this.proofSteps.add(var);
                return true;
            } catch (Exception ignored) {}
        }
        return false;
    }

    private boolean inHypothesis(Expression newStep) {
        for (Expression hypothese : hypothesis) {
            if (hypothese.equals(newStep)) {
                this.proofSteps.add(newStep);
                return true;
            }
        }
        return false;
    }

    public List<Expression> getProofSteps() {
        return proofSteps;
    }

    public Expression getStatement() {
        return statement;
    }

    public List<Expression> getHypothesis() {
        return hypothesis;
    }
}
