package ticTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    private boolean checkInput(int n, int m, int k) {
        return true;
    }

    public static void main(String[] args) {
        final int n;
        final int m;
        final int k;
        Scanner sc = new Scanner(System.in);
        try {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input parametres.");
            return;
        }
        if (k > m || k > n) {
            System.out.println("K should be greater in value than M or N.");
            return;
        }
        final Game game = new Game(false, new HumanPlayer(), new HumanPlayer());
        int result;
        do {
            result = game.play(new MNKBoard(n, m, k));
            System.out.println("Game result: " + result);
        } while (result != 0);
    }
}
