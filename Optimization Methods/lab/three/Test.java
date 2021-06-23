package lab.three;

import lab.three.methods.GaussMethod;
import lab.three.methods.GradientMethod;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        GaussMethod gauss = new GaussMethod();
        GradientMethod conjugate = new GradientMethod();

        System.out.println("Choose N: ");
        int N = scan.nextInt();

        double[] B = new double[N];
        double[][] A = new double[N][N];

        System.out.println("Choose matrix A: ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = scan.nextDouble();
            }
        }

        System.out.println("Choose matrix B: ");
        for (int i = 0; i < N; i++) {
            B[i] = scan.nextDouble();
        }
    }
}
