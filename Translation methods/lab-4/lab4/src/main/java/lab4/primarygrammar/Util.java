package lab4.primarygrammar;

import java.util.List;

public class Util {
    private Util() {}

    public static String getJava(String content) {
        if (content.charAt(0) == '[' && content.charAt(content.length() - 1) == ']') {
            return content.substring(1, content.length() - 1);
        }

        if (content.charAt(0) == '{' && content.charAt(content.length() - 1) == '}') {
            return content.substring(1, content.length() - 1);
        }

        return content;
    }

    public static <T> T get(List<T> list) {
        if (list == null || list.isEmpty())
            return null;
        if (list.size() > 1)
            throw new RuntimeException();
        return list.get(0);
    }
}
