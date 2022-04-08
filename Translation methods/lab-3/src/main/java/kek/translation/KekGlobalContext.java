package kek.translation;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class KekGlobalContext extends KekContext {
    private final Map<String, KekFunc> deferFuncs = new LinkedHashMap<>();
    private final Map<String, KekFunc> externFuncs = new LinkedHashMap<>();
    private final Map<String, KekFunc> funcs = new LinkedHashMap<>();
    private final Map<String, Block> funcsContent = new LinkedHashMap<>();

    public KekGlobalContext() {
        super(null);
    }

    @Override
    public boolean containsName(String name) {
        return super.containsName(name) || funcs.containsKey(name);
    }

    public void addFunc(KekFunc kekFunc) {
        if (deferFuncs.containsKey(kekFunc.name())) {
            deferFuncs.remove(kekFunc.name());
        } else {
            if (containsName(kekFunc.name()))
                throw new RuntimeException("Redeclaration of the function '" + kekFunc.name() + "'");
            funcs.put(kekFunc.name(), kekFunc);
        }
    }

    public void addDeferFunc(KekFunc kekFunc) {
        addFunc(kekFunc);
        deferFuncs.put(kekFunc.name(), kekFunc);
    }

    public void addExternFunc(KekFunc kekFunc) {
        externFuncs.put(kekFunc.name(), kekFunc);
    }

    public void addFuncContent(KekFunc func, Block content) {
        if (!containsName(func.name()))
            throw new RuntimeException("Impossible case 1");
        funcsContent.put(func.name(), content);
    }

    public void ensureFunc(String name) {
        if (!funcs.containsKey(name) && !externFuncs.containsKey(name))
            throw new RuntimeException("Could not find definition of the function '" + name + "'");
    }

    public KekFunc getFunc(String name) {
        var result = funcs.get(name);
        if (result == null)
            result = externFuncs.get(name);
        return result;
    }

    public Collection<KekFunc> getFuncs() {
        return funcs.values();
    }

    public Map<String, Block> getFuncsContent() {
        return funcsContent;
    }
}
