import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C {
    private static List<Long> a = new ArrayList<>(1000);
    private static final List<Long> q = new ArrayList<>(1000);
    private static final List<Long> ans = new ArrayList<>(1000);

    private static List<Long> multiply() {
        for (int i = 0; i < a.size() * q.size() + 1; i++) {
            ans.add(0L);
        }
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < q.size(); j++) {
                ans.set(i + j, ans.get(i + j) + a.get(i) * q.get(j));
            }
        }
        return ans;
    }

    private static void trimAndPrint(int l, List<Long> p) {
        while (l > 0 && p.get(l) == 0) {
            l--;
        }
        System.out.println(l);
        for (int i = 0; i <= l; i++) {
            System.out.print(p.get(i) + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        a = Arrays.stream(reader.readLine().split(" ")).map(Long::parseLong).collect(Collectors.toList());
        List<Long> c = Arrays.stream(reader.readLine().split(" ")).map(Long::parseLong).collect(Collectors.toList());
        q.add(1L);
        for (long i : c) {
            q.add(-i);
        }
        List<Long> p = multiply();
        trimAndPrint(Math.min(k - 1, p.size() - 1), p);
        System.out.println();
        trimAndPrint(q.size() - 1, q);
    }
}
