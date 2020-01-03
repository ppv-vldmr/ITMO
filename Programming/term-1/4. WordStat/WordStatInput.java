import java.io.*;
import java.util.*;

public class WordStatInput {

	public static String Normalize(String st) {
		st = st.toLowerCase();
		String temp = "";
		for (int i = 0; i < st.length(); i++) {
			if (Character.getType(st.charAt(i)) == Character.DASH_PUNCTUATION ||
				st.charAt(i) == '\'' || 
				Character.isLetter(st.charAt(i))) { 
				temp += st.substring(i, i + 1);
			} else {
				temp += " ";
			}
		}
		return(temp);
	}

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf-8"), 1_000_000);
			try {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"), 1_000_000);
				String[] arr = new String[1_000_000];
				int[] cnt = new int[1_000_000];
				int k = 0;
				String line;
				while ((line = reader.readLine()) != null) {
					line = " " + line + " ";
					line = Normalize(line);
					int l = line.length(), ns = -1;
					for (int i = 0; i < l - 1; i++) {
						if (line.charAt(i) == ' ' && line.charAt(i + 1) != ' ') ns = i + 1;
						if (line.charAt(i) != ' ' && line.charAt(i + 1) == ' ') {
							String word = line.substring(ns, i + 1);
							word = Normalize(word);
							if (word.length() > 0) {
								int j = 0;
								while (j < k && !word.equals(arr[j])) j++;
									if (j < k) cnt[j]++; else {
									arr[k] = word;
		    						cnt[k] = 1;
		    						k++;
								}
							}
						}
					}
				}
				for (int i = 0; i < k; i++) {
	    			writer.write(arr[i] + " " + cnt[i] + "\n");
				}
				writer.close();
			} catch (IOException e) {
				System.out.println("Произошла IOException " + e.getMessage());
			}
			reader.close();	
		} catch (IOException e) {
			System.out.println("Произошла IOException " + e.getMessage());
		}
	}
}