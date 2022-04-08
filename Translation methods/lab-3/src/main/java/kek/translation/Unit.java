package kek.translation;

import java.util.Collections;
import java.util.List;

import static kek.util.Util.join;

public interface Unit {
    default String getCString() {
        return join(getC());
    }

    default List<String> getC() {
        return Collections.singletonList(getCString());
    }
}
