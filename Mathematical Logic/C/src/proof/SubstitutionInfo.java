package proof;

import java.util.HashSet;

import javax.xml.validation.Validator;

import operation.BinaryOperation;
import operation.Predicate;
import operation.UnaryOperation;
import operation.WithQuantifier;
import parser.Expression;
import token.Variable;

public class SubstitutionInfo {
    public SubstitutionStatus status = SubstitutionStatus.CONTINUE;
    public Expression theta = null;
    public String var = null;
    public HashSet<String> bound = new HashSet<>();
    public boolean not_free = false;
}
