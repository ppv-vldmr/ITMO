package lab.three.tests;

import lab.three.methods.*;
import lab.three.structures.LUData;
import lab.three.structures.ProfileMatrix;
import lab.three.utils.Generator;

import java.io.File;

import static lab.three.methods.LUMethod.readLU;
import static lab.three.utils.MatrixUtils.*;

public class Test3 {
    public static void main(String[] args) {
        Generator generator = new Generator();
        int k = 0;
        for (int n = 3; n <= 10; n ++) {
            String filename = getFileName(n);
            generator.generateGAndWrite(n, filename);
            LUData luData = readLU(filename);
            assert luData != null;
            double[] x = luData.getX();
            ProfileMatrix profileMatrix = luData.getMatrix();
            double a00 = profileMatrix.getFromL(0, 0);
            for (int i = 0; i < 3; i++) {
                ProfileMatrix profileMatrix1 = new ProfileMatrix(copy(profileMatrix.getD()), copy(profileMatrix.getAl()), copy(profileMatrix.getAu()), copy(profileMatrix.getIa()));
                profileMatrix1.setToL(0, 0, a00 + Math.pow(10, -k));
                double[][] a = profileMatrix1.convertToNormalMatrix();
                double[] b = profileMatrix1.getAx(x);
                double[] res = LUMethod.solve(profileMatrix1, b);
                double delta = module(subtract(x, res));
                double mx = delta / module(x);
                System.out.println(n + " " + k + " " + delta + " " + mx );
                double[] res1 = GaussMethod.solve(a, b);
                double delta1 = module(subtract(x, res1));
                double mx1 = delta1 / module(x);
                System.out.println(n + " " + k + " " + delta1 + " " + mx1 + "\n");
                k++;
            }
        }
    }


    private static final String dirName = "test3";

    private static String getFileName(int k) {
        return dirName + File.separatorChar + "t" + "_" + k;
    }
}

