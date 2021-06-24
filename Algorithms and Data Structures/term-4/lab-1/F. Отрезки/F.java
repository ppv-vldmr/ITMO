import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class F {
    StreamTokenizer scanner = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter writer = new PrintWriter(System.out);
    int[] px;
    int[] py;
    boolean[] visited;
    boolean[] visitedVertical;
    boolean[] visitedHorizontal;
    BipartiteGraph graph;

    public static void main(String[] args) {
        F F = new F();
        F.run();
    }

    public void run() {
        graph = new BipartiteGraph();
        fordFulkerson();
        visitedVertical = new boolean[graph.sizeVertical];
        visitedHorizontal = new boolean[graph.sizeHorizontal];

        TreeSet<Integer> Vertical = new TreeSet<>();
        TreeSet<Integer> Horizontal = new TreeSet<>();

        for (int i = 0; i < graph.sizeHorizontal; i++) {
            if (px[i] == -1) {
                dfsHorizontal(i);
            }
        }

        for (int i = 0; i < graph.sizeHorizontal; i++) {
            if (visitedHorizontal[i]) {
                Horizontal.add(i);
            }
        }

        for (int i = 0; i < graph.sizeVertical; i++) {
            if (!visitedVertical[i]) {
                Vertical.add(i);
            }
        }

        writer.println(Vertical.size() + Horizontal.size());
        writer.flush();
        writer.close();
    }

    public void fordFulkerson() {
        px = new int[graph.sizeHorizontal];
        Arrays.fill(px, -1);
        py = new int[graph.sizeVertical];
        Arrays.fill(py, -1);
        visited = new boolean[graph.sizeHorizontal];
        boolean isPath = true;
        while (isPath) {
            isPath = false;
            Arrays.fill(visited, false);
            for (int i = 0; i < graph.sizeHorizontal; i++) {
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

        for (int y: graph.edgesHorizontal.get(x)) {
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

    public void dfsVertical(int x) {
        visitedVertical[x] = true;
        for (int y: graph.edgesVertical.get(x)) {
            if (py[x] == y) {
                if (!visitedHorizontal[y]) {
                    dfsHorizontal(y);
                }
            }
        }
    }

    public void dfsHorizontal(int x) {
        visitedHorizontal[x] = true;
        for (int y: graph.edgesHorizontal.get(x)) {
            if (px[x] != y) {
                if (!visitedVertical[y]) {
                    dfsVertical(y);
                }
            }
        }
    }

    class BipartiteGraph {
        int sizeHorizontal = 0, sizeVertical = 0, size;
        TreeSet<Integer> partHorizontal = new TreeSet<>();
        TreeSet<Integer> partVertical = new TreeSet<>();

        ArrayList<ArrayList<Integer>> edgesHorizontal;
        ArrayList<ArrayList<Integer>> edgesVertical;

        BipartiteGraph() {
            int n = nextInt();

            int[] x1 = new int[n];
            int[] y1 = new int[n];
            int[] x2 = new int[n];
            int[] y2 = new int[n];
            HashMap<Integer, Integer> mapVertical = new HashMap<>();
            HashMap<Integer, Integer> mapHorizontal = new HashMap<>();

            for (int i = 0; i < n; i++) {
                int tmpx1 = nextInt();
                int tmpy1 = nextInt();
                int tmpx2 = nextInt();
                int tmpy2 = nextInt();

                if ((tmpx1 == tmpx2 && tmpy1 > tmpy2) || (tmpy1 == tmpy2 && tmpx1 > tmpx2)) {
                    x1[i] = tmpx2;
                    y1[i] = tmpy2;
                    x2[i] = tmpx1;
                    y2[i] = tmpy1;
                } else {
                    x1[i] = tmpx1;
                    y1[i] = tmpy1;
                    x2[i] = tmpx2;
                    y2[i] = tmpy2;
                }



                if (x1[i] == x2[i]) {
                    partVertical.add(i);
                    mapVertical.put(i, sizeVertical);
                    sizeVertical++;
                } else if (y1[i] == y2[i]) {
                    partHorizontal.add(i);
                    mapHorizontal.put(i, sizeHorizontal);
                    sizeHorizontal++;
                }
            }

            size = sizeHorizontal + sizeVertical;
            edgesVertical = new ArrayList<>(sizeVertical);
            edgesHorizontal = new ArrayList<>(sizeHorizontal);
            for (int i = 0; i < Math.max(sizeVertical, sizeHorizontal); i++) {
                if (i < sizeVertical) {
                    edgesVertical.add(new ArrayList<>());
                }
                if (i < sizeHorizontal) {
                    edgesHorizontal.add(new ArrayList<>());
                }
            }

            for (int i: partHorizontal) {
                int hx1 = x1[i];
                int hx2 = x2[i];
                int hy1 = y1[i];
                int hy2 = y2[i];
                for (int j: partVertical) {
                    int vx1 = x1[j];
                    int vx2 = x2[j];
                    int vy1 = y1[j];
                    int vy2 = y2[j];
                    if (isLinesCross(hx1, hy1, hx2, hy2, vx1, vy1, vx2, vy2)) {
                        edgesHorizontal.get(mapHorizontal.get(i)).add(mapVertical.get(j));
                        edgesVertical.get(mapVertical.get(j)).add(mapHorizontal.get(i));
                    }
                }
            }
        }

        boolean isLinesCross(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
            if ((y3 <= y1) && (y1 <= y4) && (x1 <= x3) && (x3 <= x2)) {
                return true;
            } else {
                return false;
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
