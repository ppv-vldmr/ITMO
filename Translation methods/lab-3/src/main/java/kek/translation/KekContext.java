package kek.translation;

import java.util.*;

public class KekContext {
    private final KekContext parent;
    private final Map<String, KekVar> vars = new HashMap<>();
    private final Map<String, KekVar> additionalVars = new HashMap<>();
    private final List<KekContext> children = new LinkedList<>();
    private final int depth;

    protected KekContext(KekContext parent) {
        this.parent = parent;
        if (parent != null)
            this.depth = parent.depth + 1;
        else
            this.depth = 0;
    }

    public boolean containsName(String name) {
        return vars.containsKey(name) || (parent != null && parent.containsName(name)) || additionalVars.containsKey(name);
    }

    public void addAdditionalVar(KekVar kekVar) {
        if (containsName(kekVar.name()))
            throw new RuntimeException("Var '" + kekVar.name() + "' is already declared");
        additionalVars.put(kekVar.name(), kekVar);
    }

    public void addVar(KekVar kekVar) {
        if (containsName(kekVar.name()))
            throw new RuntimeException("Var '" + kekVar.name() + "' is already declared");
        vars.put(kekVar.name(), kekVar);
    }

    public KekContext newChildContext() {
        KekContext kekContext = new KekContext(this);
        children.add(kekContext);
        return kekContext;
    }

    public KekContext getParent() {
        return parent;
    }

    public void ensureVar(String name) {
        if (!containsName(name))
            throw new RuntimeException("Var '" + name + "' is not declared");
    }

    public KekVar getVar(String name) {
        KekVar var = vars.get(name);
        if (var == null && parent != null)
            var = parent.getVar(name);
        if (var == null)
            var = additionalVars.get(name);
        return var;
    }

    public Collection<KekVar> getVars() {
        return vars.values();
    }

    public int getDepth() {
        return depth;
    }
}
