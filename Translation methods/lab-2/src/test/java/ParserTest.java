import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {

    private Parser parser;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUp() {
        parser = new Parser();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void checkEmpty() {
        parser.parseAndGenerate("", "checkEmpty");
    }

    @Test
    public void checkVoidWithoutArguments() {
        parser.parseAndGenerate("void _func();", "checkVoidWithoutArguments");
        Assert.assertEquals("", errContent.toString());
    }

    @Test
    public void checkPtrFloatWithPtrArgument() {
        parser.parseAndGenerate("float **func8(int **** a);",
                "checkPtrFloatWithPtrArgument");
        Assert.assertEquals("", errContent.toString());
    }

    @Test
    public void checkVoidWithSimpleArguments() {
        parser.parseAndGenerate("int func(float a, double b, char c);",
                "checkIntWithSimpleArguments");
        Assert.assertEquals("", errContent.toString());
    }

    @Test
    public void checkVoidWithComplicatedArgument() {
        parser.parseAndGenerate("char * func(unsigned short int ** a);",
                "checkCharWithComplicatedArgument");
        Assert.assertEquals("", errContent.toString());
    }

    @Test
    public void checkVoidWithComplicatedArguments() {
        parser.parseAndGenerate("double func(unsigned short int a, signed char b, signed short int c);",
                "checkDoubleWithComplicatedArguments");
        Assert.assertEquals("", errContent.toString());
    }

    @Test
    public void checkComplicatedTypeWithComplicatedArguments() {
        parser.parseAndGenerate("unsigned short int func(unsigned short int a, signed char b, signed short int c);",
                "checkComplicatedTypeWithComplicatedArguments");
        Assert.assertEquals("", errContent.toString());
    }

    @Test
    public void checkInvalidFunctionNameStartingWithNumber() {
        String expr = "void 9a ( int a);";
        parser.parseAndGenerate(expr, "checkInvalidFunctionNameStartingWithNumber");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nIllegal character 9 at position 6\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidFunctionNameWithNotAllowedChar() {
        String expr = "void a&(int a);";
        parser.parseAndGenerate(expr, "checkInvalidFunctionNameWithNotAllowedChar");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nIllegal character & at position 7\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidFunctionNameWithReservedName() {
        String expr = "void int ( int a );";
        parser.parseAndGenerate(expr, "checkInvalidFunctionNameWithReservedName");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nUnexpected token at position 4\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidMissingLeftBracket() {
        String expr = "void aint a);";
        parser.parseAndGenerate(expr, "checkInvalidMissingLeftBracket");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nExpected token LEFT_PAREN but got NAME at position 7\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidMissingRightBracket() {
        String expr = "void func(int a";
        parser.parseAndGenerate(expr, "checkInvalidMissingRightBracket");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nUnexpected token at position 12\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidMissingSemicolon() {
        String expr = "void a(int a)";
        parser.parseAndGenerate(expr, "checkInvalidMissingSemicolon");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nIllegal character "+ ((char) -1) + " at position 14\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidMissingComa() {
        String expr = "void a(int a int b);";
        parser.parseAndGenerate(expr, "checkInvalidMissingSemicolon");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nUnexpected token at position 12\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidType() {
        String expr = "void a(long char a, int b);";
        parser.parseAndGenerate(expr, "checkInvalidType");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nIllegal character   at position 13\n\n", errContent.toString());
    }

    @Test
    public void checkInvalidPtr() {
        String expr = "void *a(long * char a, int b);";
        parser.parseAndGenerate(expr, "checkInvalidType");
        Assert.assertEquals("Parse exception in expression: "+ expr +
                "\nIllegal character * at position 14\n\n", errContent.toString());
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }
}
