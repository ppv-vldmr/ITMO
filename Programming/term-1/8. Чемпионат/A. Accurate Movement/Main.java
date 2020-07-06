import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    double n, a, b;
	    Scanner sc = new Scanner(System.in);
	    a = sc.nextInt();
	    b = sc.nextInt();
	    n = sc.nextInt();
	    System.out.println((int) (2 * Math.ceil((n - b) / (b - a)) + 1));
	}
}
