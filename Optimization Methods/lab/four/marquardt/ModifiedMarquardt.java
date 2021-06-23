package lab.four.marquardt;

import lab.four.util.*;

import java.util.ArrayList;
import java.util.List;

public class ModifiedMarquardt extends Marquardt {

    protected List<Integer> choleskyApproximations = new ArrayList<>();

    public ModifiedMarquardt(DoubleMultiFunction function, double[] x) {
        super(function, x, 0);
    }

    @Override
    protected void step() {
        double[] grad = FunctionUtils.gradient(function, x);
        double[][] h = FunctionUtils.hessian(function, x);

        double tauDot = tau * BETA;
        double baseVal = function.apply(x);
        do {
            tauDot /= BETA;
            p = new LES(LinearUtils.sum(h, LinearUtils.I(n, tauDot)), LinearUtils.negate(grad)).solve();
            y = LinearUtils.sum(x, p);
        } while (function.apply(y) > baseVal);
        updateX();

        double[][] right = LinearUtils.sum(h, LinearUtils.I(n, tau));
        double[][] L = new Cholesky(right).decompose();

        int numCholesky = 0;
        while (!LinearUtils.equal(right, LinearUtils.mulMatrixMatrix(L, LinearUtils.transpose(L))) && numCholesky < 10) {
            tau = Math.max(1, 2 * tau);
            right = LinearUtils.sum(h, LinearUtils.I(n, tau));
            L = new Cholesky(right).decompose();
            numCholesky++;
        }

        tau *= BETA;
        tauValues.add(tau);
        choleskyApproximations.add(numCholesky);
    }

    public List<Integer> getCholeskyApproximations() {
        return choleskyApproximations;
    }
}