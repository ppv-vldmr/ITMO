package testLab4Calc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;

public class MainTestLab4Calc {
    public static void main(String[] args) throws IOException {
        String prefix = "generated/testLab4Calc/";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(prefix + "test_input.txt")));
        var parser = new Parser(new Lexer((String.join("\n", IOUtils.readLines(reader)))));

        var a = parser.parse();
        System.out.println(a.result);
        Visualizer.visualize(a, prefix + "visualized");
    }
}
