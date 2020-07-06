package markup;
import java.util.List;
 
public class Strong extends MyAbstractClass {
    public void toMarkdown(StringBuilder str) {
        in(str, "__");
    }

    public Strong(List <CanMarkdown> list){
        this.list = list;
    }
    public void toTex(StringBuilder str) {
        in2(str, "\\textbf{", "}");
    }
}