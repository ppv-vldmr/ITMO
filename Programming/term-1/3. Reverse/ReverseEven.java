import java.util.Arrays;
import java.util.Scanner;

public class ReverseEven {
    public static void main(String[] agrs) {
	Scanner sc = new Scanner(System.in);
	int[][] arr = new int[1_000_000][];
	int[] temp = new int[1_000_000];
	int k = 0 ;
	while ( sc.hasNextLine() ) {
	    Scanner line = new Scanner( sc.nextLine() );
	    int k1 = 0;
	    while (line.hasNextInt()) {
		temp[k1] = line.nextInt();
		k1++;
	    }
	    arr[k] = new int[k1];
	    arr[k] = Arrays.copyOf( temp, k1 );
	    k++;
	}
	sc.close();
	for ( int i = k - 1 ; i >= 0 ; i-- ) {
	    for ( int j = arr[i].length - 1 ; j >= 0 ; j-- ) {
		/*if ( arr[i][j] % 2 == 0 )*/ System.out.print( arr[i][j] + " " );
	    }
	    System.out.println();
	}
    }
}