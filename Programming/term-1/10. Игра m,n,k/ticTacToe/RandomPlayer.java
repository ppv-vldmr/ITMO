package ticTacToe;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {
    private final Random random;
    private final int w;
    private final int h;

    public RandomPlayer(final Random random, int w, int h) {
        this.random = random;
        this.w = w;
        this.h = h;
    }

    public RandomPlayer(int w, int h) {
        this(new Random(), w, h);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(h);
            int c = random.nextInt(w);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                System.out.println("Position");
                System.out.println(position);
                System.out.println(cell + "'s move");
                return move;
            }
        }
    }
}
