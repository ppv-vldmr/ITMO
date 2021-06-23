package lab.three.utils;

import lab.three.structures.ProfileMatrix;
import lab.three.structures.SparseMatrix;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

import static lab.three.utils.MatrixUtils.getAx;


public class Generator {

    public SparseMatrix generateSparseMatrix(int n) {
        Random random = new Random();
        int maxL = n / 5;
        int minX;
        int maxX = 5;
        int[] ia = new int[n + 1];
        ia[0] = ia[1] = 1;
        int previous = 1;
        int jIndex = 0;
        for (int i = 2; i < n + 1; i++) {
            previous = Math.max(1, random.nextInt(Math.min(previous + 2, maxL + 1)));
            ia[i] = ia[i - 1] + previous;
        }

        Set<Integer> nonZeroValues = new HashSet<>();

        Arrays.stream(ia).forEach(nonZeroValues::add);

        int k = ia[n] - 1;
        double[] al = new double[k];
        double[] au = new double[k];
        int[] ja = new int[k + 1];
        Set<Integer> columns = new HashSet<>();
        for (int i = 2; i < n + 1; i++) {
            columns.clear();
            for (int j = 0; j < (ia[i] - ia[i - 1]); j++) {
                int col = random.nextInt(i);
                while (columns.contains(col)) {
                    col = random.nextInt(i);
                }
                ja[jIndex++] = col;
                columns.add(col);
            }
        }
        for (int i = 0; i < k; i++) {
            minX = 0;
            if (nonZeroValues.contains(i + 1)) {
                minX = 1;
            }
            al[i] = -(minX + random.nextInt(maxX - minX));
            au[i] = -(minX + random.nextInt(maxX - minX));
        }
        double[] d = new double[n];
        SparseMatrix matrix = new SparseMatrix(d, al, au, ia, ja);
        for (int i = 0; i < n; i++) {
            int finalI = i;
            double val = IntStream.range(0, n).filter(j -> j != finalI).mapToDouble(j -> matrix.get(finalI, j)).sum();
            matrix.getD()[i] = -val + 1;
        }
        return matrix;
    }

    public ProfileMatrix generateProfileMatrix(int n) {
        Random random = new Random();
        int maxL = n / 5;
        int minX;
        int maxX = 5;
        int[] ia = new int[n + 1];
        ia[0] = ia[1] = 1;
        int previous = 1;
        for (int i = 2; i < n + 1; i++) {
            previous = Math.max(1, random.nextInt(Math.min(previous + 2, maxL + 1)));
            ia[i] = ia[i - 1] + previous;
        }
        Set<Integer> nonZeroValues = new HashSet<>();

        Arrays.stream(ia).forEach(nonZeroValues::add);

        int k = ia[n] - 1;
        double[] al = new double[k];
        double[] au = new double[k];
        for (int i = 0; i < k; i++) {
            minX = 0;
            if (nonZeroValues.contains(i + 1)) {
                minX = 1;
            }
            al[i] = -(minX + random.nextInt(maxX - minX));
            au[i] = -(minX + random.nextInt(maxX - minX));
        }
        double[] d = new double[n];
        ProfileMatrix matrix = new ProfileMatrix(d, al, au, ia);
        for (int i = 0; i < n; i++) {
            int finalI = i;
            double val = IntStream.range(0, n).filter(j -> j != finalI).mapToDouble(j -> matrix.get(finalI, j)).sum();
            matrix.setToL(i, i, -val);
        }
        return matrix;
    }

    public ProfileMatrix generateProfileMatrixFromA(double[][] a) {
        int n = a.length;
        double[] d = new double[n];
        ArrayList<Double> aL = new ArrayList<>();
        ArrayList<Double> aU = new ArrayList<>();
        int[] ia = new int[n + 1];
        ia[0] = ia[1] = 1;
        for (int i = 0; i < n; i++) {
            d[i] = a[i][i];
        }
        for (int i = 1; i < n; i++) {
            int j = 0;
            while (a[i][j] == 0 && j < i) {
                j++;
            }
            while (j < i) {
                aL.add(a[i][j]);
                ia[i + 1]++;
                j++;
            }
            ia[i + 1] += ia[i];
        }

        for (int i = 1; i < n; i++) {
            int j = 0;
            while (a[j][i] == 0 && j < i) {
                j++;
            }
            while (j < i) {
                aU.add(a[j][i]);
                j++;
            }
        }
        int k = aL.size();
        double[] al = new double[k];
        for (int i = 0; i < k; i++) {
            al[i] = aL.get(i);
        }

        double[] au = new double[k];
        for (int i = 0; i < k; i++) {
            au[i] = aU.get(i);
        }

        return new ProfileMatrix(d, al, au, ia);
    }

    public double[][] generateGilbertMatrix(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = 1 / ((double) (i + j + 1));
            }
        }
        return a;
    }

    public void generateGAndWrite(int n, String out) {
        double[][] a = generateGilbertMatrix(n);
        double[] x = IntStream.range(1, n + 1).mapToDouble(it -> it).toArray();
        double[] b = getAx(a, x);
        ProfileMatrix profileMatrix = generateProfileMatrixFromA(a);
        writeLU(n, profileMatrix, b, x, out);
    }

    public void generatePMandWrite(int n, String out) {
        ProfileMatrix profileMatrix = generateProfileMatrix(n);
        double[] x = IntStream.range(1, n+1).mapToDouble(i -> i).toArray();
        double[] b = profileMatrix.getAx(x);
        writeLU(n, profileMatrix, b, x, out);
    }

    public void writeLU(int n, ProfileMatrix profileMatrix, double[] b, double[] x, String out) {
        Path path = Path.of(out);
        final Path parent = path.getParent();
        if (parent != null && Files.notExists(parent)) {
            try {
                Files.createDirectories(parent);
            } catch (final IOException ignored) {
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(String.valueOf(n));
            writer.newLine();
            ProjectUtils.writeArray(profileMatrix.getD(), writer);
            ProjectUtils.writeArray(profileMatrix.getAl(), writer);
            ProjectUtils.writeArray(profileMatrix.getAu(), writer);
            ProjectUtils.writeArray(profileMatrix.getIa(), writer);
            ProjectUtils.writeArray(b, writer);
            ProjectUtils.writeArray(x, writer);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
