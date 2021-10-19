import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import operation.BinaryOperation;
import parser.Expression;
import parser.ExpressionParser;
import proof.AxiomMatcher;
import proof.Proof;
import proof.QuantifierRulesMatcher;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        System.out.println(new AxiomMatcher().matchesToAxiom(new ExpressionParser().parse("(((@b.(@c.((((0'' * p) + 0'') = b) -> (((((0'' * p) + 0'') = c) -> (b = c))))))) -> ((@c.((((0'' * p) + 0'') = ((0'' * p) + 0')') -> (((((0'' * p) + 0'') = c) -> (((0'' * p) + 0')' = c)))))))")));
//        System.out.println(new ExpressionParser().parse("(((@b.(@c.((((0'' * p) + 0'') = b) -> (((((0'' * p) + 0'') = c) -> (b = c))))))) -> ((@c.((((0'' * p) + 0'') = ((0'' * p) + 0')') -> (((((0'' * p) + 0'') = c) -> (((0'' * p) + 0')' = c)))))))").toStringInfix());
//        System.out.println(new ExpressionParser().parse("(@c.((((0''*p)+0'')=((0''*p)+0')')->((((0''*p)+0'')=c)->(((0''*p)+0')'=c))))").toStringInfix());
//        System.out.println(new ExpressionParser().parse("((((0''*p)+0'')=c)->(((0''*p)+0')'=c))").toStringInfix());
//        System.out.println(new ExpressionParser().parse("((0''*p)+0')").toStringInfix());
//        System.out.println(new ExpressionParser().parse("((0''*p)+0')'=c").toStringInfix());
//        System.exit(0);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader reader = new BufferedReader(new FileReader("test3_in.txt"));

        Proof proof = new Proof();
        proof.setStatementAndHypothesis(reader.readLine());

        System.out.println("|-" + proof.getStatement().toStringInfix());

        String step;
        int lineCounter = 1;
        while (true) {
            step = reader.readLine();
            if (step == null || step.equals("")) {
                break;
            }
            lineCounter++;
            boolean result = proof.tryAddStep(step);
            if (!result) {
                System.out.println("Expression " + (lineCounter - 1) + " is not proved.");
                System.exit(0);
            }
        }

        if (!proof.getProofSteps().get(proof.getProofSteps().size() - 1).equals(proof.getStatement())) {
            System.out.println("The proof proves different expression.");
            System.exit(0);
        }
    }
}
