package org.example.profiler;

import one.util.streamex.EntryStream;
import org.aspectj.lang.Signature;

import java.io.PrintStream;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Profiler {
    private static final Profiler INSTANCE = new Profiler();

    private String packagePrefix = null;

    public static Profiler getInstance() {
        return INSTANCE;
    }

    private final Map<String, DoubleSummaryStatistics> pathToStatisticsMap = new HashMap<>();

    synchronized void register(Signature signature, long executionTime) {
        final String path = signature.getDeclaringTypeName() + "." + signature.getName();
        pathToStatisticsMap.computeIfAbsent(path, x -> new DoubleSummaryStatistics())
                .accept(0.000_001 * executionTime);
    }

    public synchronized void clear() {
        pathToStatisticsMap.clear();
    }

    public void stop() {
        packagePrefix = null;
    }

    public synchronized void printStats(PrintStream printStream) {
        final SignatureTree<String> signatureTree = new SignatureTree<>("=== Profiling ===");
        EntryStream.of(pathToStatisticsMap)
                .mapValues(DoubleSummaryStatistics::toString)
                .mapValues(stats -> stats.replace(DoubleSummaryStatistics.class.getSimpleName(), ""))
                .forKeyValue(signatureTree::add);
        signatureTree.print(printStream);
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }
}
