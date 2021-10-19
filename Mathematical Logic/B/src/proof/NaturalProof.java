package proof;

import java.util.List;
import java.util.Set;

import parser.Expression;
import parser.ExpressionParser;

public class NaturalProof {
    private Set<Expression> hypothesis;
    private List<Expression> proofSteps;
    private ExpressionParser parser;
    private AxiomMatcher axiomMatcher;
    private ModusPonensMatcher modusPonensMatcher;

    public NaturalProof(
            Set<Expression> hypothesis,
            List<Expression> proofSteps
    ) {
        this.hypothesis = hypothesis;
        this.proofSteps = proofSteps;
        this.parser = new ExpressionParser();
        this.axiomMatcher = new AxiomMatcher();
        this.modusPonensMatcher = new ModusPonensMatcher();

    }
}
