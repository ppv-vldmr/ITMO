public class Main {
    private static final Parser parser = new Parser();
    private static final String outputFile = "out.txt";
    private static final String expr = "unsigned short int func(unsigned short int a, signed char b, signed short int c);";

    public static void main(String[] args) {
        parser.parseAndGenerate(expr, outputFile);
    }
}
