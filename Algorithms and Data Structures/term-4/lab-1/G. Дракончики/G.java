import java.io.*;
import java.util.Arrays;

public class G {
    StreamTokenizer scanner = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter writer = new PrintWriter(System.out);
    boolean[] visited;
    int[] matching;
    BipartiteGraph graph;

    public static void main(String[] args) {
        G G = new G();
        G.run();
    }

    public void run() {
        graph = new BipartiteGraph();
        matching = new int[graph.sizeMatrix];
        visited = new boolean[graph.sizeMatrix];
        Arrays.fill(matching, -1);
        for (int i = 0; i < graph.sizeMatrix; i++) {
            Arrays.fill(visited, false);
            if (!dfs(i)) {
                writer.print("NO");
                writer.flush();
                writer.close();
                return;
            }
        }
        writer.println("YES");
        for (int i = 0; i < graph.sizeYellow; i++) {
            if (matching[i] < graph.sizeGreen) {
                writer.println(matching[i] + 1 + " " + (i + graph.sizeGreen + 1));
            }
        }
        writer.flush();
        writer.close();
    }

    public boolean dfs(int v) {
        if (visited[v]) {
            return false;
        }
        visited[v] = true;
        for (int i = 0; i < graph.sizeMatrix; i++) {
            if (graph.matrix[v][i] != 0) {
                    if (matching[i] == -1 || dfs(matching[i])) {
                    matching[i] = v;
                    return true;
                }
            }
        }
        return false;
    }

    class BipartiteGraph {

        int sizeGreen, sizeYellow, countDoubleAquarium, countPair, countNotSingle, sizeMatrix;
        int[][] matrix;

        /**
         * Будем считать, что зеленые дракончики пронумерованы числами от 1 до m, а желтые — числами от m+1 до m+k.
         */
        BipartiteGraph() {
            sizeGreen = nextInt();
            sizeYellow = nextInt();
            countDoubleAquarium = nextInt();
            sizeMatrix = sizeGreen + sizeYellow - countDoubleAquarium;
            matrix = new int[sizeMatrix][sizeMatrix];

            for (int i = 0; i < sizeMatrix; i++) {
                for (int j = 0; j < sizeMatrix; j++) {
                    matrix[i][j] = 1;
                }
            }
            for (int i = sizeGreen; i < sizeMatrix; i++) {
                for (int j = sizeYellow; j < sizeMatrix; j++) {
                    matrix[i][j] = 0;
                }
            }

            countPair = nextInt();
            for (int i = 0; i < countPair; i++) {
                int x, y;
                x = nextInt() - 1;
                y = nextInt() - 1;
                if (x > y) {
                    int tmp = x;
                    x = y;
                    y = tmp;
                }
                matrix[x][y - sizeGreen] = 0;
            }

            countNotSingle = nextInt();
            for (int i = 0; i < countNotSingle; i++) {
                int x = nextInt() - 1;
                if (x < sizeGreen) {
                    for (int j = sizeYellow; j < sizeMatrix; j++) {
                        matrix[x][j] = 0;
                    }
                } else {
                    for (int j = sizeGreen; j < sizeMatrix; j++) {
                        matrix[j][x - sizeGreen] = 0;
                    }
                }
            }
        }
    }

    int nextInt() {
        try {
            scanner.nextToken();
            return (int) scanner.nval;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -42;
    }
}
