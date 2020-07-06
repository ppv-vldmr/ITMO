package markup;

import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TexTest extends MarkdownTest {
    private static final Map<String, String> TEX = Map.of(
            "*<", "\\emph{",
            "__<", "\\textbf{",
            "~<", "\\textst{",
            "*>", "}",
            "__>", "}",
            "~>", "}"
    );

    public static void main(String[] args) {
        new TexTest().run();
    }

    @Override
    protected void test(final Paragraph paragraph, final String expected) {
        super.test(paragraph, expected);
        test(paragraph::toTex, expected, TEX);
    }
}
