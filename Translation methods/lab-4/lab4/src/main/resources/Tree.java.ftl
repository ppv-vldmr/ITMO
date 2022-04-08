<#if package?has_content>package ${package};

</#if>

import java.util.Collections;
import java.util.List;

public interface Tree {
    default List<Tree> getChildren() {
        return Collections.emptyList();
    }
}
