import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    for (int i = -25000; i < n - 25000; i++) {
            System.out.println(710 * i);
        }
    }
}
