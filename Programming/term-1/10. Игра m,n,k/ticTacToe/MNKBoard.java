package ticTacToe;

import java.util.Arrays;
import java.util.Map;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private final int n;
    private final int m;
    private final int k;
    private Cell turn;
    private int moveCounter;

    public MNKBoard(int m, int n, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.moveCounter = m * n;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        cells[move.getRow()][move.getColumn()] = move.getValue();
        moveCounter--;
        turn = turn == Cell.X ? Cell.O : Cell.X;
        if (check(move) == Result.UNKNOWN)
            if (moveCounter == 0)
                return Result.DRAW;
            else
                return Result.UNKNOWN;
        return check(move);
    }

    private Result check(Move move) {
        if (count(1, 0, move) + count(-1, 0, move) + 1 >= k) {
            return Result.WIN;
        } else if (count(0, 1, move) + count(0, -1, move) + 1 >= k) {
            return Result.WIN;
        } else if (count(1, 1, move) + count(-1, -1, move) + 1 >= k) {
            return Result.WIN;
        } else if (count(-1, 1, move) + count(1, -1, move) + 1 >= k) {
            return Result.WIN;
        } else {
            return Result.UNKNOWN;
        }
    }

    private int count(int x, int y, Move move) {
        int cnt = 0, param1 = 0, param2 = 0;

        cycle:
        for (int i = 1; i < k; i++) {
            for (int j = 1; j < k; j++) {
                param1 += x;
                param2 += y;
                if (move.getRow() + param1 < n && move.getRow() + param1 >= 0 &&
                        move.getColumn() + param2 < m && move.getColumn() + param2 >= 0) {
                    if (cells[move.getRow() + param1][move.getColumn() + param2] == move.getValue()) {
                        cnt++;
                    } else {
                        break cycle;
                    }
                } else {
                    break cycle;
                }
            }
        }
        return cnt;
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(int r, int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < m; i++) {
            sb.append(i);
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n" + r + " ");
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
