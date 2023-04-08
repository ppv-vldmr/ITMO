package ru.akirakozov.sd.refactoring.utils;

public class HtmlBuilder {
    public static String addHtml(String renderTag) {
        return addTag(HtmlTag.HTML, renderTag);
    }

    public static String addBody(String renderTag) {
        return addTag(HtmlTag.BODY, renderTag);
    }

    public static String addH1(String text) {
        return addTag(HtmlTag.H1, text);
    }

    public static String addBr(String text) {
        return addTag(HtmlTag.BR, text);
    }

    private static String addTag(HtmlTag tag, String innerRenderTag) {
        return (tag.hasStartTag() ? getOpenTag(tag) : "") + innerRenderTag + getCloseTag(tag);
    }

    private static String getOpenTag(HtmlTag tag) {
        return String.format(tag.needStartLineBreak() ? "<%s>\n" : "<%s>", tag.getExternalName());
    }

    private static String getCloseTag(HtmlTag tag) {
        return String.format(tag.needEndLineBreak() ? "</%s>\n" : "</%s>", tag.getExternalName());
    }
}
