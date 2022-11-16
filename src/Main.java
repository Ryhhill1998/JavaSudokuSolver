import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        int[][] gameboard = getGameBoardFromInput();

        int[][] gameboard = {{1,0,3,0},{0,4,2,1},{0,0,0,2},{0,0,4,0}};

        SudokuSolver sudokuSolver = new SudokuSolver(gameboard);

        sudokuSolver.printGameboard();
        System.out.println();

        sudokuSolver.printColumns();
        System.out.println();

        sudokuSolver.solveForNumber(1);
        System.out.println();
        sudokuSolver.solveForNumber(2);
        System.out.println();
        sudokuSolver.solveForNumber(3);
        System.out.println();
        sudokuSolver.solveForNumber(4);
    }

    public static int[][] getGameBoardFromInput() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter the size of the gameboard (e.g. if you have a 4x4 board, please enter 4): ");
        int boardSize = scan.nextInt();
        System.out.println();

        int[][] gameboard = new int[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            System.out.printf("Please enter the numbers in row %d separated by a space " +
                    "(use 0 to signify an empty space):\n", i + 1);

            int[] row = new int[boardSize];

            for (int n = 0; n < boardSize; n++) {
                row[n] = scan.nextInt();
            }

            gameboard[i] = row;
            System.out.println(Arrays.toString(row));
        }

        return gameboard;
    }
}
