package markup;
import java.util.List;
 
public class Paragraph extends MyAbstractClass {
    public void toMarkdown(StringBuilder str){
        in(str, "");
    }
    public Paragraph(List <CanMarkdown> list){
        this.list = list;
    }
}