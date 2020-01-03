package markup;
import java.util.List;
 
public class Strikeout extends MyAbstractClass {
    public void toMarkdown(StringBuilder str){
        in(str, "~");
    }
    public Strikeout(List <CanMarkdown> list){
        this.list = list;
    }
}