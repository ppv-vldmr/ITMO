package ticTacToe;

import java.util.ArrayList;

public class Tournament {
    private final boolean log;
    private final int c;
    private final int[] points;
    private final int cntPlayers;
    private int lap;
    private ArrayList<Player> players;

    public Tournament(final boolean log, int c, ArrayList<Player> players) {
        this.log = log;
        this.c = c;
        this.lap = 1;
        this.players = players;
        this.cntPlayers = this.players.size();
        this.points = new int[cntPlayers];
        for (int i = 0; i < cntPlayers; i++) {
            this.points[i] = 0;
        }
    }

    private void getTable() {
        log("Круг: " + lap + "\n" + "Таблица баллов игроков:" + "\n" + "Игрок: ");
        for (int i = 0; i < cntPlayers; i++)
            log(i + " ");
        log("\n" + "Баллы: ");
        for (int i = 0; i < cntPlayers; i++)
            log(points[i] + " ");
        log("\n");
    }

    private void getWinner() {
        int max = -1;
        int id = -1;
        for (int i = 0; i < cntPlayers; i++)
            if (points[i] > max) {
                id = i;
                max = points[i];
            }
        for (int i = 0; i < cntPlayers; i++)
            if (points[i] == max && i != id) {
                log("There's no winner!" + "\n");
                return;
            }
        log("Winner player " + id + "!" + "\n");
    }

    public void runTournament(int n, int m, int k) {
        while (lap <= c) {
            for (int i = 0; i < cntPlayers - 1; i++) {
                for (int j = i + 1; j < cntPlayers; j++) {
                    log("Lap: " + lap + ". " + "Round between Player " + i + " and Player " + j + "\n");
                    final Game game = new Game(true, players.get(i), players.get(j));
                    int result = game.play(new MNKBoard(n, m, k));
                    switch (result) {
                        case 1: {
                            points[i] += 3;
                            break;
                        }
                        case 2: {
                            points[j] += 3;
                            break;
                        }
                        case 0: {
                            points[i]++;
                            points[j]++;
                            break;
                        }
                    }
                    getTable();
                }
            }
            this.lap++;
        }
        getWinner();
    }

    private void log(final String message) {
        if (log) {
            System.out.print(message);
        }
    }
}
