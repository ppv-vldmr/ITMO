package markup;
import java.util.List;

public class Markdown {
    public static void main (String[] args) {
        StringBuilder br = new StringBuilder();
        Paragraph paragraph = new Paragraph(List.of(
            new Strong(List.of(
                new Text ("1"),
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
        paragraph.toTex(br);
        System.out.println(br.toString());
    }
}