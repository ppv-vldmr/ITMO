package lab.four.algorithm.newton;

import lab.four.util.DoubleMultiFunction;
import lab.four.util.LinearUtils;

public class DescentDirectionNewton extends UnarySearchNewton {
    public DescentDirectionNewton(DoubleMultiFunction function, double[] x) {
        super(function, x);
    }

    @Override
    protected void updateX() {
        if (LinearUtils.scalar(p, grad) < 0) {
            p = LinearUtils.negate(grad);
        }
        super.updateX();
    }
}
