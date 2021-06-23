package lab.three.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectUtils {
    public static void writeArray(double[] arr, BufferedWriter writer) throws IOException {
        for (double v : arr) {
            writer.write(v + " ");
        }
        writer.newLine();
    }

    public static void writeArray(int[] arr, BufferedWriter writer) throws IOException {
        for (int v : arr) {
            writer.write(v + " ");
        }
        writer.newLine();
    }

    public static double[] readArrayDouble(BufferedReader reader) throws IOException {
        String s = reader.readLine();
        List<Double> list = Arrays.stream(s.split(" ")).map(Double::parseDouble).collect(Collectors.toList());
        double[] arr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static int[] readArrayInt(BufferedReader reader) throws IOException {
        String s = reader.readLine();
        List<Integer> list = Arrays.stream(s.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
