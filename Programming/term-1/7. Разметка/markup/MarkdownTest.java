package markup;

import java.util.List;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MarkdownTest extends AbstractTest {
    private static final Map<String, String> MARKDOWN = Map.of(
            "<", "",
            ">", ""
    );

    public static void main(String... args) {
        new MarkdownTest().run();
    }

    @Override
    protected void test() {
        test(new Paragraph(List.of(new Text("Hello"))), "Hello");
        test(new Paragraph(List.of(new Emphasis(List.of(new Text("Hello"))))), "*<Hello*>");
        test(new Paragraph(List.of(new Strong(List.of(new Text("Hello"))))), "__<Hello__>");
        test(new Paragraph(List.of(new Strikeout(List.of(new Text("Hello"))))), "~<Hello~>");
        final Paragraph paragraph = new Paragraph(List.of(
                new Strong(List.of(
                        new Text("1"),
                        new Strikeout(List.of(
                                new Text("2"),
                                new Emphasis(List.of(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));
        test(
            paragraph,
            "__<1~<2*<34*>5~>6__>"
        );
        test(new Paragraph(List.of(
                new Strong(List.of(new Text("sdq"), new Strikeout(List.of(new Emphasis(List.of(new Text("r"))), new Text("vavc"))), new Text("zg"))))),
                "__<sdq~<*<r*>vavc~>zg__>"
        );
    }

    protected void test(Paragraph paragraph, final String expected) {
        test(paragraph::toMarkdown, expected, MARKDOWN);
    }
}
