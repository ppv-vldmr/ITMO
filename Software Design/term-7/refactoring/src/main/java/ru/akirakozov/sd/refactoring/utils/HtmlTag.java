package ru.akirakozov.sd.refactoring.utils;

public enum HtmlTag {
    HTML("html", true, false, false),
    BODY("body", true, true, false),
    H1("h1", true, false, true),
    BR("br", false, false, true);

    private final String externalName;
    private final boolean hasStartTag;
    private final boolean needStartLineBreak;
    private final boolean needEndLineBreak;

    HtmlTag(String externalName, boolean hasStartTag, boolean needStartLineBreak, boolean needEndLineBreak) {
        this.externalName = externalName;
        this.hasStartTag = hasStartTag;
        this.needStartLineBreak = needStartLineBreak;
        this.needEndLineBreak = needEndLineBreak;
    }

    public String getExternalName() {
        return externalName;
    }

    public boolean hasStartTag() {
        return hasStartTag;
    }

    public boolean needStartLineBreak() {
        return needStartLineBreak;
    }

    public boolean needEndLineBreak() {
        return needEndLineBreak;
    }
}
