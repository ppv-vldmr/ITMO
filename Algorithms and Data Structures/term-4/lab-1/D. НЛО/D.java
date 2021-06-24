import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class D {
    Scanner scanner = new Scanner(System.in);
    PrintWriter writer = new PrintWriter(System.out);
    int[] px;
    int[] py;
    boolean[] visited;
    BipartiteGraph graph;

    public static void main(String[] args) {
        D D = new D();
        D.run();
    }

    public void run() {
        graph = new BipartiteGraph();
        fordFulkerson();
        int count = 0;
        for (int i = 0; i < graph.size; i++) {
            if (px[i] != -1) {
                count++;
            }
        }
        writer.println(graph.size - count);
        writer.flush();
        writer.close();
    }

    public void fordFulkerson() {
        px = new int[graph.size];
        Arrays.fill(px, -1);
        py = new int[graph.size];
        Arrays.fill(py, -1);
        visited = new boolean[graph.size];
        boolean isPath = true;
        while (isPath) {
            isPath = false;
            Arrays.fill(visited, false);
            for (int i = 0; i < graph.size; i++) {
                if (px[i] == -1) {
                    if (dfs(i)) {
                        isPath = true;
                    }
                }
            }
        }
    }

    public boolean dfs(int x) {
        if (visited[x]) {
            return false;
        }
        visited[x] = true;

        for (int y: graph.edges.get(x)) {
            if (py[y] == -1) {
                py[y] = x;
                px[x] = y;
                return true;
            } else {
                if (dfs(py[y])) {
                    py[y] = x;
                    px[x] = y;
                    return true;
                }
            }
        }

        return false;
    }

    class BipartiteGraph {

        int size, maxSpeed;
        int[] time, x, y;

        ArrayList<ArrayList<Integer>> edges;

        BipartiteGraph() {
            size = scanner.nextInt();
            maxSpeed= scanner.nextInt();

            edges = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                edges.add(new ArrayList<>());
            }


            time = new int[size];
            x = new int[size];
            y = new int[size];
            String line = scanner.nextLine();
            for (int i = 0; i < size; i++) {
                line = scanner.nextLine();
                int j = 0;
                while (line.charAt(j) != ':') {
                    j++;
                }
                int hour = Integer.parseInt(line.substring(0, j));
                int k = j;
                while (line.charAt(k) != ' ') {
                    k++;
                }
                int min = Integer.parseInt(line.substring(j + 1, k));
                time[i] = hour * 60 + min;
                j = k + 1;
                while (line.charAt(j) != ' ') {
                    j++;
                 }
                x[i] = Integer.parseInt(line.substring(k + 1, j));
                y[i] = Integer.parseInt(line.substring(j + 1, line.length()));
            }


            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        long dx = x[i] - x[j];
                        long dy = y[i] - y[j];

                        if (Math.sqrt(dx * dx + dy * dy) * 60 <= (time[j] - time[i]) * maxSpeed) {
                            edges.get(i).add(j);
                        }
                    }
                }
            }
        }
    }
}
