<#if package?has_content>package ${package};

</#if>public record Token(TokenType type, String content) implements Tree {
}
