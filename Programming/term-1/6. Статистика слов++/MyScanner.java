import java.util.*;
import java.io.*;
 
class MyScanner {
    private int position = 0;
    private int readerResult = 0;
    private boolean eof = false;
    private char[] cbuf;
    private final int blockSize;
    private final Reader reader;
    private Integer nextInt = null;
 
    public MyScanner(InputStream in) {
        this(in, 1024);
    }
 
    public MyScanner(InputStream in, int blockSize) {
        this.reader = new InputStreamReader(in);
        this.blockSize = blockSize;
        readInput();
    }
 
    public MyScanner(String string) throws UnsupportedEncodingException {
        this(string, 1024);
    }
 
    public MyScanner(String string, int blockSize) throws UnsupportedEncodingException {
        this.reader = new InputStreamReader(new ByteArrayInputStream((string + " ").getBytes("utf8")));
        this.blockSize = blockSize;
        readInput();
    }
 
    public MyScanner(File file, String charset) throws FileNotFoundException, UnsupportedEncodingException {
        this(file, 1024, charset);
    }
 
    public MyScanner(File file, int blockSize, String charset)
            throws FileNotFoundException, UnsupportedEncodingException {
        this.reader = new InputStreamReader(new FileInputStream(file), charset);
        this.blockSize = blockSize;
        readInput();
    }
 
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error in QuickScanner.close() method: " + e.getMessage());
            e.printStackTrace();
        }
    }
 
    public boolean hasNextLine() {
        return !eof;
    }
 
    public String nextLine() {
        StringBuilder builder = new StringBuilder();
        while (cbuf[position] != '\n') {
            builder.append(cbuf[position]);
            move();
        }
        move();
        return builder.toString();
    }
 
    public boolean hasNextInt() {
        StringBuilder builder = new StringBuilder();
        while ((Character.isWhitespace(cbuf[position])) && !eof) {
            move();
        }
        while (((Character.isDigit(cbuf[position])) || cbuf[position] == '-') && !eof) {
            builder.append(cbuf[position]);
            move();
        }
        String suspect = builder.toString();
        if (isInt(suspect)) {
            return true;
        } else {
            return false;
        }
    }
 
    public Integer nextInt() throws NoSuchElementException {
        if (nextInt != null) {
            return nextInt;
        } else {
            throw new NoSuchElementException();
        }
    }
 
    private boolean isInt(String suspect) {
        try {
            nextInt = Integer.parseInt(suspect);
            return true;
        } catch (NumberFormatException e) {
            nextInt = null;
            return false;
        }
    }
 
    private void readInput() {
        if (!eof) {
            try {
                cbuf = new char[blockSize];
                readerResult = reader.read(cbuf, 0, blockSize);
            } catch (IOException e) {
                System.out.println("Error in QuickScanner.readInput local method: " + e.getMessage());
                e.printStackTrace();
                eof = true;
            }
        }
    }
 
    private void move() {
        if (!eof) {
            position += 1;
            if (position == readerResult) {
                position = 0;
                readInput();
                if (readerResult == -1) {
                    eof = true;
                }
            }
        }
    }
}