import java.io.*;
import java.util.*;

public class ReverseTranspose {
	public static void main(String[] args) {
    	MyScanner s = new MyScanner(System.in);
    	ArrayList<ArrayList<Integer>> nums = new ArrayList<>(); 
        String line;
        int word; 
        int k = 0, h = 0; 
        try {
            while (s.hasNextLine()) { 
                line = s.nextLine(); 
                k = 0; 
                MyScanner s2 = new MyScanner(line); 
                while(s2.hasNextInt()) { 
                    k++; 
                    if (k > h) 
                        nums.add(new ArrayList<Integer>()); 
                    word = s2.nextInt(); 
                    nums.get(k-1).add(word); 
                if (k > h) 
                    h = k; 
                } 
    	    }
            for (int i = 0; i < nums.size(); i++) {
                for (int j = 0; j < nums.get(i).size(); j++) {
                    System.out.print(nums.get(i).get(j) + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("An error has occured with scanner: " + e.getMessage());
        }
    }
}