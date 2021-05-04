import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class F {

    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] km = reader.readLine().split(" ");
        List<Long> c = Arrays.stream(reader.readLine().split(" ")).map(Long::parseLong).collect(Collectors.toList());
        long[] res = new long[Integer.parseInt(km[1]) + 1];
        res[0] = 1;
        long[] count = new long[Integer.parseInt(km[1]) + 1];
        count[0] = 1;

        for (int i = 1; i <= Integer.parseInt(km[1]); ++i) {
            for (int j = 0; j < Integer.parseInt(km[0]); ++j) {
                if (i < c.get(j)) {
                    continue;
                }
                res[i] += count[(int) (i - c.get(j))];
                res[i] %= MOD;
            }

            for(int j = 0; j <= i; ++j) {
                count[i] += (res[j] * res[i - j]) % MOD;
                count[i] %= MOD;
            }
        }
        
        for (int i = 1; i <= Integer.parseInt(km[1]); ++i) {
            System.out.print(res[i] + " ");
        }
    }
}
