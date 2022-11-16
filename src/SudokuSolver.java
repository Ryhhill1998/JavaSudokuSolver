import java.util.Arrays;

public class SudokuSolver {

    private int[][] gameboard;
    private int[][] columns;

    public SudokuSolver(int[][] gameboard) {
        this.gameboard = gameboard;
        columns = new int[gameboard.length][gameboard.length];
        setColumns();
    }

    private void setColumns() {
        for (int i = 0; i < gameboard.length; i++) {
            int[] column = new int[gameboard[i].length];

            for (int j = 0; j < gameboard[i].length; j++) {
                column[j] = gameboard[j][i];
                System.out.println(gameboard[j][i]);
            }

            columns[i] = column;
            System.out.println(Arrays.toString(column));
        }
    }

    public void printGameboard() {
        for (int i = 0; i < gameboard.length; i++) {
            System.out.printf("Row %d: %s\n", i + 1, Arrays.toString(gameboard[i]));
        }
    }

    public void printColumns() {
        for (int i = 0; i < columns.length; i++) {
            System.out.printf("Column %d: %s\n", i + 1, Arrays.toString(columns[i]));
        }
    }
}
