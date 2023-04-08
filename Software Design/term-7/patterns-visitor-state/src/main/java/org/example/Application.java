package org.example;

import org.example.evaluator.EvaluatorVisitor;
import org.example.parser.ParserVisitor;
import org.example.printer.PrinterVisitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final String text = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        work(text, System.out, System.err);
    }

    public static void work(String expression, PrintStream printStream, PrintStream errorStream) {
        try {
            final ParserVisitor parser = new ParserVisitor(expression);

            final PrinterVisitor printer = new PrinterVisitor(parser, printStream);
            printer.print();

            final EvaluatorVisitor evaluator = new EvaluatorVisitor(parser);
            printStream.println(evaluator.evaluate());
        } catch (Exception e) {
            errorStream.println(e.getMessage());
        }
    }
}
