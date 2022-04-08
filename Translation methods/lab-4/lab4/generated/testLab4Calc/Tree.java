package testLab4Calc;


import java.util.Collections;
import java.util.List;

public interface Tree {
    default List<Tree> getChildren() {
        return Collections.emptyList();
    }
}
