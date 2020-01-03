import java.util.*;
import java.io.*;

class Word {
    public final String word;
    public int frequency;
    public ArrayList<Integer> lineNumbers;
    public ArrayList<Integer> wordNumbers;
 
    public Word(String word, int lnum, int wnum) {
        this.word = word;
        frequency = 1;
        lineNumbers = new ArrayList<Integer>();
        wordNumbers = new ArrayList<Integer>();
        lineNumbers.add(lnum);
        wordNumbers.add(wnum);
    }
 
    public ArrayList<Integer> getLineNumbers() {
        return lineNumbers;
    }
 
    public ArrayList<Integer> getWordNumbers() {
        return wordNumbers;
    }
 
    public void increment(int lnum, int wnum) {
        frequency += 1;
        lineNumbers.add(lnum);
        wordNumbers.add(wnum);
    }
 
    public String getString() {
        return word;
    }
 
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(word + " " + frequency);
        for (int i = 0; i < frequency; i++) {
            result.append(" " + lineNumbers.get(i) + ":" + wordNumbers.get(i));
        }
        return result.toString();
    }
}
 
public class WordStatLineIndex {
    private static ArrayList<Word> words;

    private static void lineProcessing(String line, int lnum) {
        int wnum = 1;
        for (int i = 0; i < line.length(); i++) {
            int idxStart = i;
            while (i < line.length() && isWordChar(line.charAt(i))) {
                i++;
            }
 
            if (idxStart < i) {
                addWord(line.substring(idxStart, i).toLowerCase(), lnum, wnum);
                wnum += 1;
            }
        }
    }
 
    private static Boolean isWordChar(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION || Character.isLetter(c) || c == '\'';
    }
 
    private static void addWord(String wordString, int lnum, int wnum) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getString().equals(wordString)) {
                words.get(i).increment(lnum, wnum);
                return;
            }
        }
        words.add(new Word(wordString, lnum, wnum));
    }
 
    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("Input and output file names not found");
            return;
        }
 
        String inputFileName = args[0];
        String outputFileName = args[1];
        BufferedWriter writer = null;
        words = new ArrayList<>();
        MyScanner sc = null;
        try {
            sc = new MyScanner(new File(inputFileName), "UTF-8");
            String line = new String();
            int lnum = 1;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                lineProcessing(line, lnum);
                lnum += 1;
            }
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF8"));

                Collections.sort(words, new Comparator<Word>() {
                    @Override
                    public int compare(Word lhs, Word rhs) {
                        return lhs.getString().compareTo(rhs.getString());
                    }
                });
 
                for (int i = 0; i < words.size(); i++) {
                    writer.write(words.get(i) + System.lineSeparator());
                }
            } catch (FileNotFoundException e) {
                System.err.println("Output File not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.err.println("An error has occured with output");
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                	System.out.println("An error has occured with closing writer");
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("An error has occured with input");
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}