package markup;
import java.util.List;
 
public class Emphasis extends MyAbstractClass {
    public void toMarkdown(StringBuilder str){
        in(str, "*");
    }

    public void toTex(StringBuilder str) {
        in2(str, "\\emph{", "}");
    }

    public Emphasis(List <CanMarkdown> list) {
        this.list = list;
    }
}