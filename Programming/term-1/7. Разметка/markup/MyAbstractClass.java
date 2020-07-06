package markup;
import java.util.List;

public abstract class MyAbstractClass implements CanMarkdown {
    protected List <CanMarkdown> list;

    protected void in(StringBuilder str, String raz){
        str.append(raz);
        for (int i = 0; i < list.size(); i++){
            list.get(i).toMarkdown(str);
        }
        str.append(raz);
    }

    abstract public void toMarkdown(StringBuilder str);

    abstract public void toTex(StringBuilder str);

    protected void in2(StringBuilder str, String beginTag, String endTag) {
        str.append(beginTag);
        for (int i = 0; i < list.size(); i++){
            list.get(i).toTex(str);
        }
        str.append(endTag);
    }
}
