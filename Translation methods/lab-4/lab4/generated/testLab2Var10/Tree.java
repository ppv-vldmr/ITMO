package testLab2Var10;


import java.util.Collections;
import java.util.List;

public interface Tree {
    default List<Tree> getChildren() {
        return Collections.emptyList();
    }
}
