package lab.three.methods;

import lab.three.structures.LUData;
import lab.three.structures.ProfileMatrix;
import lab.three.utils.ProjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

public class LUMethod {
    public static double[] solve(final ProfileMatrix matrix, double[] b){
        if(!matrix.isConvertedToLu()){
            matrix.convertToLU();
        }
        int n = b.length;
        double[] y = new double[n];
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            int finalI = i;
            y[i] = (b[i] - IntStream.range(0, i).mapToDouble(j -> matrix.getFromL(finalI, j) * y[j]).sum()) / matrix.getFromL(i, i);
        }
        for (int i = n - 1; i > -1; i--) {
            int finalI = i;
            x[i] = y[i] - IntStream.range(i, n).mapToDouble(j -> matrix.getFromU(finalI, j) * x[j]).sum();
        }
        return x;
    }

    public static double[] solve(String in){
        LUData data = readLU(in);
        assert data != null;
        return solve(data.getMatrix(), data.getB());
    }

    public static LUData readLU(String in) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(in))) {
            int n = Integer.parseInt(reader.readLine());
            ProfileMatrix profileMatrix = new ProfileMatrix(
                    ProjectUtils.readArrayDouble(reader),
                    ProjectUtils.readArrayDouble(reader),
                    ProjectUtils.readArrayDouble(reader),
                    ProjectUtils.readArrayInt(reader));
            double[] b = ProjectUtils.readArrayDouble(reader);
            double[] x = ProjectUtils.readArrayDouble(reader);
            return new LUData(profileMatrix, b, x, n);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
