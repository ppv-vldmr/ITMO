import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import proof.NaturalProofConverter;
import proof.Proof;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Main {

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("stress.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Proof proof = new Proof();
        proof.setStatementAndHypothesis(reader.readLine());

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
                System.out.println("Proof is incorrect at line " + lineCounter);
                System.exit(0);
            }
        }
        if (!proof.getProofSteps().get(proof.getProofSteps().size() - 1).equals(proof.getStatement())) {
            System.out.println("The proof does not prove the required expression");
            System.exit(0);
        }
        List<String> naturalProof = new NaturalProofConverter().getNaturalProof(proof);
//        BufferedWriter writer = new BufferedWriter(new FileWriter("stress_output_my.txt"));
        for (String line : naturalProof) {
//            writer.write(line + System.lineSeparator());
//            writer.flush();
            System.out.println(line);
            System.out.flush();
        }
    }
}
