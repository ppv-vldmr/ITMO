import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] A = new int[n][n];
        int[][] res = new int[n][n];
        for (int i = 0; i <n; i++) {
            String str = sc.nextLine();
            for(int j = 0; j < str.length(); j++) {
                A[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }

        for (int i = 0; i <n; i++) {
            for (int j = i + 1; j <n; j++) {
                if (A[i][j] > 0) {
                    res[i][j] = 1;
                    for (int k = j + 1; k <n; k++) {
                        A[i][k] = (A[i][k] - A[j][k] + 10) % 10;
                    }
                }
            }
        }

        for (int i = 0; i <n; i++) {
            for (int j = 0; j <n; j++) {
                System.out.print(res[i][j]);
            }
            System.out.println("");
        }
    }
}