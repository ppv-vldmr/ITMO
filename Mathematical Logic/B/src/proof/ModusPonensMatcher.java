package proof;

import java.util.List;

import operation.BinaryOperation;
import operation.Implication;
import operation.ModusPonensInfo;
import parser.Expression;

public class ModusPonensMatcher {
    public ModusPonensInfo isModusPonens(Expression expression, List<Expression> steps) {
        return findLeftPart(expression, steps);
    }

    private ModusPonensInfo findLeftPart(Expression expression, List<Expression> steps) {
        int counter = 0;
        ModusPonensInfo result = new ModusPonensInfo();
        result.setModusPonens(false);
        for (Expression step : steps) {
            counter++;
            try {
                BinaryOperation bo = (BinaryOperation) step;
                if (bo.getSign().equals("->") && bo.getRight().equals(expression)) {
                    int l = findRightPart(bo.getLeft(), steps);
                    if (l != -1) {
                        result.setModusPonens(true);
                        result.setLeftMP(counter - 1);
                        result.setRightMP(l);
                        return result;
                    }
                }
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    private int findRightPart(Expression x, List<Expression> steps) {
        int counter = 0;
        for (Expression step : steps) {
            counter++;
            if (step.equals(x)) {
                return counter - 1;
            }
        }
        return -1;
    }
}
