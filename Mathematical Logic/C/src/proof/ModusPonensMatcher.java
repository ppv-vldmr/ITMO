package proof;

import java.util.List;
import java.util.Objects;

import operation.BinaryOperation;
import operation.ModusPonensInfo;
import parser.Expression;

public class ModusPonensMatcher {
    public ModusPonensInfo isModusPonens(Expression expression, List<Expression> steps) {
        return findRightPart(expression, steps);
    }

    private ModusPonensInfo findRightPart(Expression expression, List<Expression> steps) {
        int counter = 0;
        ModusPonensInfo result = new ModusPonensInfo();
        result.setModusPonens(false);
        for (Expression step : steps) {
            counter++;
            try {
                BinaryOperation bo = (BinaryOperation) step;
                if (bo.getSign().equals("->") && bo.getRight().equals(expression)) {
                    int l = findLeftPart(expression, bo.getLeft(), steps);
                    if (l != -1) {
                        if (result.getLeftMP() == -1 || result.getLeftMP() > l) {
                            result.setModusPonens(true);
                            result.setLeftMP(l);
                            result.setRightMP(counter - 1);
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return result;
    }

    private int findLeftPart(Expression modusPonensResult, Expression leftPart, List<Expression> steps) {
        int counter = 0;
        for (Expression step : steps) {
            counter++;
            if (step.equals(leftPart)) {
                return counter - 1;
            }
        }
        return -1;
    }
}
