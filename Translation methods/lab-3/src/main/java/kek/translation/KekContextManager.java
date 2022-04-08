package kek.translation;

import kek.grammar.KekBaseListener;
import kek.grammar.KekParser;

import java.util.Collection;

public class KekContextManager extends KekBaseListener {
    private final KekGlobalContext globalContext = new KekGlobalContext();
    private KekContext context = globalContext;

    @Override
    public void enterBlock(KekParser.BlockContext ctx) {
        context = context.newChildContext();
    }

    @Override
    public void exitBlock(KekParser.BlockContext ctx) {
        context = context.getParent();
    }

    public void addFunc(KekFunc kekFunc) {
        globalContext.addFunc(kekFunc);
    }

    public void addDeferFunc(KekFunc kekFunc) {
        globalContext.addDeferFunc(kekFunc);
    }

    public void addExternFunc(KekFunc kekFunc) {
        globalContext.addExternFunc(kekFunc);
    }

    public void addFuncContent(KekFunc func, KekBlock content) {
        globalContext.addFuncContent(func, content);
    }

    public void ensureFunc(String name) {
        globalContext.ensureFunc(name);
    }

    public KekFunc getFunc(String name) {
        ensureFunc(name);
        return globalContext.getFunc(name);
    }

    public void addVar(KekVar var) {
        context.addVar(var);
    }

    public void ensureVar(String name) {
        context.ensureVar(name);
    }

    public KekVar getVar(String name) {
        ensureVar(name);
        return context.getVar(name);
    }

    public Collection<KekVar> getGlobalVars() {
        return globalContext.getVars();
    }

    public Collection<KekFunc> getGlobalFuncs() {
        return globalContext.getFuncs();
    }

    public KekGlobalContext getGlobalContext() {
        return globalContext;
    }

    public KekContext getContext() {
        return context;
    }
}
