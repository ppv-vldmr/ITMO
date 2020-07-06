package md2html;

class ParseParagraphs {
    private StringBuilder source;

    public ParseParagraphs(StringBuilder source) {
        this.source = source;
    }

    public void toHtml(StringBuilder result) {
        result.append("<p>");
        new ParseTexts(source).toHtml(result);
        result.append("</p>");
    }
}