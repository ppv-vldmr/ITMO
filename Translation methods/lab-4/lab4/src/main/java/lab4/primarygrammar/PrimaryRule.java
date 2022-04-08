package lab4.primarygrammar;

import java.util.List;

public record PrimaryRule(String name,
                          List<String> ruleArgs,
                          List<String> ruleReturns,
                          String initCode,
                          List<PrimaryRuleContent> ruleContents) {

}
