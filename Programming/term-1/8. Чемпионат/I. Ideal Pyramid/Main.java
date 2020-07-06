import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    int n, xl = 100000000, xr = -1000000000, yl = 100000000, yr = -1000000000;
	    Scanner sc = new Scanner(System.in);
	    n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x,y,h;
            x = sc.nextInt();
            y = sc.nextInt();
            h = sc.nextInt();
            if (xl > x - h)
                xl = x - h;
            if (xr < x + h)
                xr = x + h;
            if (yl > y - h)
                yl = y - h;
            if (yr < y + h)
                yr = y + h;
        }
        System.out.print((xl + xr)/2);
        System.out.print(" ");
        System.out.print((yl + yr)/2);
        System.out.print(" ");
        System.out.print((int) Math.ceil((double) Math.max(xr - xl, yr - yl) / 2));
    }
}
