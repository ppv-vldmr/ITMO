package markup;
 
public class Text implements CanMarkdown {
    private String s;
    public Text (String s){
        this.s = s;
    }   
    public void toMarkdown(StringBuilder str){
        str.append(s);  
    }
}