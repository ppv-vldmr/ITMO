package md2html;

import java.io.*;

public class Md2Html {

    public static void check(String[] args) {
        if (args.length != 2) {
            System.out.println("Enter input and output files");
            return;
        }
    }

    public static void main(String[] args) {
        check(args);
        StringBuilder result = new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), "UTF-8"))) {
                String tempString = "";
                StringBuilder paragraph = new StringBuilder();
                while (tempString != null && (tempString = reader.readLine()) != null) {
                    while (tempString != null && !tempString.equals("")) {
                        paragraph.append(tempString).append('\n');
                        tempString = reader.readLine();
                    }
                    if (paragraph.length() != 0) {
                        paragraph.setLength(paragraph.length() - 1);
                        new ParseBlocks(paragraph).toHtml(result);
                        result.append('\n');
                        paragraph = new StringBuilder();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error has occured with reading from input file: " + e.getMessage());
        }
        try {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1])), "UTF-8"))) {
                writer.write(result.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Write error: " + e.getMessage());
        }
    }
}