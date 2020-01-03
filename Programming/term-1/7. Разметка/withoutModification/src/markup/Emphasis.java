package markup;
import java.util.List;
 
public class Emphasis extends MyAbstractClass {
    public void toMarkdown(StringBuilder str){
        in(str, "*");
    }
    public Emphasis(List <CanMarkdown> list){
        this.list = list;
    }
}