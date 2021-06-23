package lab.three.tests;

import lab.three.structures.ProfileMatrix;
import lab.three.utils.Generator;

public class TestT {
    public static void main(String[] args) {
        Generator generator = new Generator();
        double[][] gilbert = generator.generateGilbertMatrix(100);
        ProfileMatrix matrix = generator.generateProfileMatrixFromA(gilbert);

    }
}
