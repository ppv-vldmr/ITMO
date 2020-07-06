package markup;
import java.util.List;
 
public class Strikeout extends MyAbstractClass {
    public void toMarkdown(StringBuilder str){
        in(str, "~");
    }

    @Override
    public void toTex(StringBuilder str) {
        in2(str, "\\textst{", "}");
    }

    public Strikeout(List <CanMarkdown> list){
        this.list = list;
    }
}