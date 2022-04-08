package kek.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Util {
    public static <T> List<T> nullToEmpty(List<T> list) {
        return Objects.requireNonNullElse(list, Collections.emptyList());
    }

    public static String indent(String s, int tabs) {
        return "\t".repeat(tabs) + s;
    }

    public static List<String> indent(List<String> s, int tabs) {
        return s.stream().map(line -> indent(line, tabs)).collect(Collectors.toList());
    }

    public static String indent(String s) {
        return indent(s, 1);
    }

    public static List<String> indent(List<String> s) {
        return indent(s, 1);
    }

    public static String join(Iterable<String> s) {
        return String.join("\n", s);
    }
}
