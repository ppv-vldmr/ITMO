package md2html;

import java.util.Arrays;

public class MyList {
    private int size = 0;
    private int[] arr = new int[5];

    public void add(int value) {
        checkSize();
        arr[size++] = value;
    }

    private void checkSize() {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    public int get(int position) {
        return arr[position];
    }

    public void pop() {
        size--;
    }

    public int getSize() {
        return size;
    }
}