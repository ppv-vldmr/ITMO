package md2html;

import java.util.HashMap;
import java.util.Map;

public class ParseTexts {
    private StringBuilder source;
    private static Map<String, Integer> markdownIndex;
    private static String[] htmlTags;
    private static String[] markdownTags;
    private static int tagsCount;

    static {
        htmlTags = new String[]{"em", "strong", "em", "strong", "s", "code", "mark", "a"};
        markdownTags = new String[]{"*", "**", "_", "__", "--", "`", "~", "["};
        tagsCount = markdownTags.length;
        markdownIndex = new HashMap<>();
        for (int i = 0; i < tagsCount; i++) {
            markdownIndex.put(markdownTags[i], i);
        }
        markdownIndex.put("]", tagsCount - 1);
        markdownIndex.put("(", tagsCount - 1);
        markdownIndex.put(")", tagsCount - 1);
    }

    ParseTexts(StringBuilder source) {
        this.source = source;
    }

    private Integer getTagPosition(String cur) {
        Integer res = markdownIndex.get(cur);
        if (res == null) {
            res = markdownIndex.get(Character.toString(cur.charAt(0)));
        }
        return res;
    }

    public void toHtml(StringBuilder result) {
        int tagCount = htmlTags.length;
        MyList[] arr = new MyList[tagCount];
        for (int i = 0; i < tagCount; i++) {
            arr[i] = new MyList();
        }
        for (int i = 0; i < source.length(); i++) {
            String cur = source.substring(i, Math.min(i + 2, source.length()));
            if (source.charAt(i) == '\\') {
                i++;
                continue;
            }
            Integer pos = getTagPosition(cur);
            if (pos != null) {
                arr[pos].add(i);
            }
        }
        for (MyList intList : arr) {
            if (intList.getSize() % 2 == 1) {
                intList.pop();
            }
        }
        int[] pos = new int[tagCount];
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            String cur = source.substring(i, Math.min(i + 2, source.length()));
            switch (c) {
                case '<':
                    result.append("&lt;");
                    continue;
                case '>':
                    result.append("&gt;");
                    continue;
                case '&':
                    result.append("&amp;");
                    continue;
                case '\\':
                    continue;
            }
            Integer needPos = getTagPosition(cur);
            if (needPos == null || arr[needPos].getSize() == 0) {
                result.append(c);
            } else {
                cur = markdownTags[needPos];
                String tagName = htmlTags[needPos];
                int tagSize = cur.length();
                boolean usualChar = false;
                if (tagName.equals("a")) {
                    if (arr[needPos].getSize() - pos[needPos] < 4) {
                        result.append(c);
                        continue;
                    }
                }
                if (usualChar) {
                    result.append(c);
                    continue;
                }
                boolean isOpen = ((arr[needPos].getSize() - pos[needPos]) % 2 == 0);
                String tmp = "<" + (isOpen ? "" : "/") + tagName + ">";
                result.append(tmp);
                i += tagSize - 1;
                pos[needPos]++;
            }
        }
    }
}