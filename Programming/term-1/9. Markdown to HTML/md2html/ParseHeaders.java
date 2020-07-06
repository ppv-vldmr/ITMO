package md2html;

class ParseHeaders {
    private StringBuilder source;

    ParseHeaders(StringBuilder source) {
        this.source = source;
    }

    private int levelOfHeader(StringBuilder text) {
        int pos = 0;
        while (pos < text.length() && text.charAt(pos) == '#') {
            pos++;
        }
        return pos;
    }

    public void toHtml(StringBuilder result) {
        int lvl = levelOfHeader(source);
        result.append("<h").append(lvl).append(">");
        new ParseTexts(new StringBuilder(source.substring(lvl + 1))).toHtml(result);
        result.append("</h").append(lvl).append(">");
    }

}