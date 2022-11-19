import java.util.Arrays;

public class SudokuSolver {

    private int[][] gameboard;

    public SudokuSolver(int[][] gameboard) {
        this.gameboard = gameboard;
    }

    public void printGameboard() {
        for (int i = 0; i < gameboard.length; i++) {
            System.out.printf("Row %d: %s\n", i, Arrays.toString(gameboard[i]));
        }
    }

    private boolean boardIsFull() {
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                if (gameboard[i][j] == 0) return false;
            }
        }

        return true;
    }

    public boolean solvePuzzle(int row, int col) {
        if (boardIsFull()) return true;

        // check that position is empty
        while (gameboard[row][col] != 0) {
            if (col == 8) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }

        // try adding all numbers from 1-9 to the empty space
        for (int i = 1; i <= 9; i++) {
            // check if it is valid to place this number
            if (canPlaceNumber(row, col, i)) {
                gameboard[row][col] = i;
                if (solvePuzzle(row, col)) {
                    return true;
                } else {
                    gameboard[row][col] = 0;
                }
            }
        }

        return false;
    }

    private boolean canPlaceNumber(int row, int col, int number) {
        // check grid does not contain number
        int[] grid = getGrid(row, col);
        if (sectionContainsNumber(grid, number)) return false;
        // check row does not contain number
        if (sectionContainsNumber(gameboard[row], number)) return false;
        // check col does not contain number
        int[] column = getColumn(col);
        return !sectionContainsNumber(column, number);
    }

    public int[] getGrid(int row, int col) {
        int[] grid = new int[9];

        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        int n = 0;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                grid[n] = gameboard[i][j];
                n++;
            }
        }

        return grid;
    }

    private int[] getColumn(int col) {
        int[] column = new int[9];
        int n = 0;

        for (int[] rows : gameboard) {
            column[n] = rows[col];
            n++;
        }

        return column;
    }

    private boolean sectionContainsNumber(int[] section, int number) {
        for (int n : section) {
            if (n == number) return true;
        }

        return false;
    }
}
