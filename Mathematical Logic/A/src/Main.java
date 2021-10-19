import parser.Expression;
import parser.ExpressionParser;
import parser.PerfectParser;

import java.util.Scanner;

/**
 * @author Popov Vladimir (vova_57@bk.ru)
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
	    String input = scanner.nextLine();
//        Expression ep = new ExpressionParser().parse(input);
        Expression pp = new PerfectParser().parse(input);
//        System.out.println(ep.toStringPrefix());
        System.out.println(pp.toStringPrefix());
//        System.out.println(expression.toStringInfix());

//        String input;
//        ExpressionParser parser = new ExpressionParser();
//        while ((input = scanner.nextLine()) != null) {
//            System.out.println(parser.parse(input).toStringInfix());
//        }
    }
}
