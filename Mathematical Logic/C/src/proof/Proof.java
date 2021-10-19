package proof;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import operation.BinaryOperation;
import operation.Implication;
import operation.ModusPonensInfo;
import operation.Predicate;
import operation.UnaryOperation;
import operation.WithQuantifier;
import parser.Expression;
import parser.ExpressionParser;
import token.Variable;

public class Proof {
    private Expression statement;
    private final List<Expression> proofSteps;
    private final ExpressionParser parser;
    private final AxiomMatcher axiomMatcher;
    private final ModusPonensMatcher modusPonensMatcher;
    private final QuantifierRulesMatcher quantifierRulesMatcher;

    public Proof() {
        proofSteps = new ArrayList<>();
        parser = new ExpressionParser();
        axiomMatcher = new AxiomMatcher();
        modusPonensMatcher = new ModusPonensMatcher();
        quantifierRulesMatcher = new QuantifierRulesMatcher();
    }

    public void setStatementAndHypothesis(String input) {
        this.statement = parser.parse(input.substring(2));
    }

    public boolean tryAddStep(String input) {
        Expression newStep = this.parser.parse(input);
        return checkNewStep(newStep);
    }

    private boolean checkNewStep(Expression newStep) {
        if (axiomMatcher.isAxiom(newStep)) {
            this.proofSteps.add(newStep);
            return true;
        }
        ModusPonensInfo newStepMPInfo = modusPonensMatcher.isModusPonens(newStep, proofSteps);
        if (newStepMPInfo.isModusPonens()) {
            try {
                BinaryOperation bo = (BinaryOperation) newStep;
                bo.setModusPonens(true);
                bo.setRightMP(newStepMPInfo.getRightMP());
                bo.setLeftMP(newStepMPInfo.getLeftMP());
                this.proofSteps.add(bo);
                System.out.println("[" + proofSteps.size() + ". M.P. " + (newStepMPInfo.getLeftMP() + 1) + ", " + (newStepMPInfo.getRightMP() + 1) + "] " + newStep.toStringInfix());
                return true;
            } catch (Exception ignored) {}
            try {
                UnaryOperation uo = (UnaryOperation) newStep;
                uo.setModusPonens(true);
                uo.setRightMP(newStepMPInfo.getRightMP());
                uo.setLeftMP(newStepMPInfo.getLeftMP());
                this.proofSteps.add(uo);
                System.out.println("[" + proofSteps.size() + ". M.P. " + (newStepMPInfo.getLeftMP() + 1) + ", " + (newStepMPInfo.getRightMP() + 1) + "] " + newStep.toStringInfix());
                return true;
            } catch (Exception ignored) {}
            try {
                Variable var = (Variable) newStep;
                var.setModusPonens(true);
                var.setLeftMP(newStepMPInfo.getLeftMP());
                var.setRightMP(newStepMPInfo.getRightMP());
                this.proofSteps.add(var);
                System.out.println("[" + proofSteps.size() + ". M.P. " + (newStepMPInfo.getLeftMP() + 1) + ", " + (newStepMPInfo.getRightMP() + 1) + "] " + newStep.toStringInfix());
                return true;
            } catch (Exception ignored) {}
            try {
                WithQuantifier wq = (WithQuantifier) newStep;
                wq.setModusPonens(true);
                wq.setLeftMP(newStepMPInfo.getLeftMP());
                wq.setRightMP(newStepMPInfo.getRightMP());
                this.proofSteps.add(wq);
                System.out.println("[" + proofSteps.size() + ". M.P. " + (newStepMPInfo.getLeftMP() + 1) + ", " + (newStepMPInfo.getRightMP() + 1) + "] " + newStep.toStringInfix());
                return true;
            } catch (Exception ignored) {}
            try {
                Predicate p = (Predicate) newStep;
                p.setModusPonens(true);
                p.setLeftMP(newStepMPInfo.getLeftMP());
                p.setRightMP(newStepMPInfo.getRightMP());
                this.proofSteps.add(p);
                System.out.println("[" + proofSteps.size() + ". M.P. " + (newStepMPInfo.getLeftMP() + 1) + ", " + (newStepMPInfo.getRightMP() + 1) + "] " + newStep.toStringInfix());
                return true;
            } catch (Exception ignored) {}
        }
        int existsQuan = quantifierRulesMatcher.isRuleWithQuantifierExists(newStep, proofSteps);
        if (existsQuan != -1) {
            this.proofSteps.add(newStep);
            System.out.println("[" + proofSteps.size() + ". ?-intro " + existsQuan + "] " + newStep.toStringInfix());
            return true;
        }
        int anyQuan = quantifierRulesMatcher.isRuleWithQuantifierAny(newStep, proofSteps);
        if (anyQuan != -1) {
            this.proofSteps.add(newStep);
            System.out.println("[" + proofSteps.size() + ". @-intro " + anyQuan + "] " + newStep.toStringInfix());
            return true;
        }
        return false;
    }

    public List<Expression> getProofSteps() {
        return proofSteps;
    }

    public Expression getStatement() {
        return statement;
    }
}
