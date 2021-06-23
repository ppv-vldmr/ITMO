package latex;

import lab.one.algorithm.Algorithm;
import lab.one.util.Segment;
import java.io.PrintStream;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;

public class TableBuilder {

    public void algoTable(PrintStream out, Algorithm performedAlgo, DoubleFunction<Double> func) {
        List<Segment> segments = performedAlgo.getSegments();
        // & segment & length & x & f(x)
        for (Segment segment : segments) {
            String xs =
                    segment.getComputedXs().stream().map(Object::toString).collect(Collectors.joining(", "));
            String fs =
                    segment.getComputedFs().stream().map(Object::toString).collect(Collectors.joining(", "));
            out.printf("& [%f, %f] & %f & %s & %s \\\\%n",
                    segment.from(), segment.to(), segment.length(), xs, fs);
        }
    }

    public void epsNumItersTable(PrintStream out, List<Integer> numUpdates, List<Double> eps) {
        // (log(eps),numUpdates)(*, *)...
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numUpdates.size(); ++i) {
            builder.append(String.format("(%f,%d)", eps.get(i), numUpdates.get(i)));
        }
        out.println(builder);
    }
}
