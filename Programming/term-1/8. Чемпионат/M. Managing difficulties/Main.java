import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.stream.Collectors;
import java.util.*;

public class Main {
    public static void main(String[] agrs) {
        try {
            MyScanner read = new MyScanner(System.in);
            int n = read.nextInt();
            for (int d = 0; d < n; d++) {
                int k = read.nextInt();
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < k; j++) {
                    int tm = read.nextInt();
                    list.add(tm);
                }
                int sum = 0;
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int j = k - 1; j >= 0; j--) {
                    for (int i = 0; i < j; i++) {
                        if (map.get(2 * list.get(j) - list.get(i)) != null) {
                            sum += map.get(2 * list.get(j) - list.get(i));
                            //System.out.println(map.get(2 * list.get(j) - list.get(i)) + " " + i + " " + j);
                        }
                    }
                    if (map.get(list.get(j)) != null) {
                        map.replace(list.get(j), map.get(list.get(j)) + 1);
                    } else {
                        map.put(list.get(j), 1);
                        // System.out.println(map.get(list.get(j)) + " " + j);
                    }
                }
                System.out.println(sum);
            }
            read.close();
        }catch (FileNotFoundException e) {
            System.out.println("Не найден файл для входа " + e.getMessage());
        }catch (IOException e) {
            System.out.println("Произошла IOException на входе" + e.getMessage());
        }
    }
}

class MyScanner {
    private Reader br = null;
    private int pos, len;
    private char[] buffer = new char[4096];
    private boolean EOF = false;
    private StringBuilder sb = new StringBuilder();

    private static boolean isPatOfWord(char c) {
        return Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'' || Character.isLetter(c);
    }

    public boolean isDigit(char c) {
        return Character.isDigit(c) || c == '-';
    }

    public MyScanner (InputStream is) {
        try {
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        } catch(UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException");
        }
    }

    public MyScanner (String line) {
        br = new StringReader(line);
    }
    // public FastScanner(String string)
    private void readBuffer() throws IOException {
        len = br.read(buffer);
        while (len == 0){
            len = br.read(buffer);
        }
        if (len == -1)
        {
            EOF = true;
        }
        pos = 0;
    }

    public String nextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        char c;
        while(hasNextChar()) {
            c = nextChar();
            if (c == '\n') {
                break;
            }
            if (c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public char nextChar() throws IOException {
        if (pos >= len){
            readBuffer();
        }
        return buffer[pos++];
    }

    public String nextWord() throws IOException {
        sb = new StringBuilder();
        while (!EOF && !isPatOfWord(nextChar()));
        pos--;
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (isPatOfWord(c)) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public boolean hasNextWord() throws IOException {
        while (!EOF && !isPatOfWord(nextChar()));
        pos--;
        return !EOF;
    }

    public String next() throws IOException {
        sb = new StringBuilder();
        while (!EOF && Character.isWhitespace(nextChar()));
        pos--;
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
            else {
                break;
            }
        }
        return sb.toString();
    }

    public boolean hasNext() throws IOException {
        while (!EOF && Character.isWhitespace(nextChar())){}
        pos--;
        return !EOF;
    }

    public int nextInt() throws IOException {
        skipBlank();
        sb = new StringBuilder();
        char c;
        while (hasNextChar()){
            c = nextChar();
            if (isDigit(c))
            {
                sb.append(c);
            }
            else {
                break;
            }
        }
        try {
            return Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }

    public boolean hasNextChar() throws IOException {
        nextChar();
        pos--;
        return !EOF;
    }

    public boolean hasNextLine() throws IOException {
        return hasNextChar();
    }

    public boolean hasNextInt() throws IOException {
        skipBlank();
        if (hasNextChar() && isDigit(nextChar())) {
            pos--;
            return true;
        }
        return false;
    }

    private void skipBlank() throws IOException {
        while (true) {
            if (!hasNextChar() || !Character.isWhitespace(nextChar())) break;
        }
        pos--;
    }

    public void close() throws IOException {
        br.close();
    }
}