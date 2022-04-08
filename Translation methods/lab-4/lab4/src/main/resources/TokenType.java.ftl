<#if package?has_content>package ${package};

</#if>import java.util.regex.Pattern;

public enum TokenType {
    <#list tokenTypes as tokenType>
        ${tokenType.name()}(${tokenType.regex()}, ${tokenType.skip()?c}),
    </#list>
    EOF("$", false);

    private final Pattern pattern;
    private final boolean skip;

    TokenType(String pattern, boolean skip) {
        this.pattern = Pattern.compile(pattern);
        this.skip = skip;
    }

    public Pattern pattern() {
        return pattern;
    }

    public boolean skip() {
        return skip;
    }
}
