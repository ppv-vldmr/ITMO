package ticTacToe;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        final int n;
        final int m;
        final int k;
        final int laps;
        Scanner sc = new Scanner(System.in);
        try {
            n = sc.nextInt();
            m = sc.nextInt();
            k = sc.nextInt();
            laps = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong input parametres.");
            return;
        } catch (NoSuchElementException e) {
            System.out.println("Wrong input parametres.");
            return;
        }
        if (checkInput(n, m, k)) {
            ArrayList<Player> players = new ArrayList<>(); //random && seq with (n, m), human with ()
            players.add(new HumanPlayer());
            players.add(new RandomPlayer(n, m));
            Tournament tournament = new Tournament(true, laps, players);
            tournament.runTournament(n, m, k);
        }
    }

    private static boolean checkInput(int n, int m, int k) {
        if (k <= 0 || n <= 0 || m <= 0) {
            System.out.println("K should be greater in value than M or N.");
            return false;
        }
        return true;
    }
}
