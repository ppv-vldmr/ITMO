import java.io.*;
import java.util.*;

public class WordStatWords {

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
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf-8"), 1_000_000);
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"), 1_000_000);
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
				for (int i = 0; i < k - 1; i++) {
					for (int j = 0; j < k - 1; j++) {
						if (arr[j].compareTo(arr[j + 1]) > 0) {
							String temp1 = arr[j];
							arr[j] = arr[j + 1];
							arr[j + 1] = temp1;
							int temp2 = cnt[j];
							cnt[j] = cnt[j + 1];
							cnt[j + 1] = temp2;
						}
					}
				}
				for (int i = 0; i < k; i++) {
	    			writer.write(arr[i] + " " + cnt[i] + "\n");
				}
			} catch (IOException e) {
				System.out.println("Произошла IOException на выходе" + e.getMessage());
			} finally {
				writer.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Не найден файл для входа " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Произошла IOException на входе" + e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {

			}
		}
	}
}