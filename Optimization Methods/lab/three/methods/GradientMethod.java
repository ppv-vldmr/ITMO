package lab.three.methods;

import lab.three.structures.SparseMatrix;

import java.util.Arrays;

import static lab.three.utils.MatrixUtils.*;

public class GradientMethod {
    private static final double EPS = 10E-5;

    public double[] solve(SparseMatrix A, double[] B) {
        int N = A.getD().length, k = 1;
        double[] x = Arrays.stream(new double[N]).map(i -> 0).toArray();
        double[] r = subtract(B, A.getAx(x));
        double[] z = r;
        double alpha, betta;
        double[] xk, rk, zk;
        while (true) {
            alpha = scalar(r, r) / scalar(A.getAx(z), z);
            xk = add(x, multiply(z, alpha));
            rk = subtract(r, multiply(A.getAx(z), alpha));
            betta = scalar(rk, rk) / scalar(r, r);
            zk = add(rk, multiply(z, betta));

            if (module(rk) / module(B) < EPS || k > 1000) {
                break;
            }
            x = xk; r = rk; z = zk; k++;
        }
        //printSolution(xk);
        //System.out.println("Iterations: " + k);
        return  xk;
    }
}
