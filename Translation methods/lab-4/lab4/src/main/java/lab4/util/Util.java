package lab4.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

public class Util {
    public static void ensureDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String removeExt(String fileName) {
        int i = fileName.lastIndexOf('.');
        return (i < 0 ? fileName : fileName.substring(0, i));
    }

    public static <T extends Collection<?>> boolean isEmpty(T t) {
        return t == null || t.isEmpty();
    }

    public static <T extends Collection<?>> boolean isNotEmpty(T t) {
        return !isEmpty(t);
    }

    public static <T extends CharSequence> boolean isEmpty(T t) {
        return t == null || t.isEmpty();
    }

    public static <T extends CharSequence> boolean isNotEmpty(T t) {
        return !isEmpty(t);
    }
}
