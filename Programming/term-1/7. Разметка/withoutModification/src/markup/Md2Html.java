package markup;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "utf-8"), 1_000_000);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf-8"), 1_000_000);
            } catch (FileNotFoundException e) {
                System.out.println("Output file not found: " + e.getMessage());
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error has occured with closing output file: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                System.out.println("An error has occured with closing input file: " + e.getMessage());
            }
        }
    }
}
