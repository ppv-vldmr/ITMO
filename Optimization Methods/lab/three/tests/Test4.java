package lab.three.tests;

import lab.three.methods.*;
import lab.three.structures.SparseMatrix;
import lab.three.utils.Generator;

import java.util.stream.IntStream;

import static lab.three.utils.MatrixUtils.*;

public class Test4 {

    public static void main(String[] args) {
        Generator generator = new Generator();
        int k = 0;
        for (int n = 3; n <= 20; n++) {
            GradientMethod conjugate = new GradientMethod();
            SparseMatrix sparseMatrix = generator.generateSparseMatrix(n);
            double[][] A = sparseMatrix.convertToNormalMatrix();

            double[] x = IntStream.range(1, n + 1).mapToDouble(i -> (double) i).toArray();
            double[] b = sparseMatrix.getAx(x);
            //printMatrix(A, b);

            double[] res = conjugate.solve(sparseMatrix, b);
            double delta = module(subtract(x, res));
            double mx = delta / module(x);
            System.out.println(n + " " + k++ + " " + delta + " " + mx);
        }

    }
}

