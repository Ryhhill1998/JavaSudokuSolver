import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        int[][] gameboard = getGameBoardFromInput();

//        int[][] gameboard = {
//                {1,0,6,0,7,8,0,3,0},{0,5,7,3,4,9,0,6,2},{2,9,3,6,0,5,0,0,7},
//                {0,0,0,8,0,1,6,0,4},{4,6,1,0,0,0,2,8,9},{5,8,0,0,6,4,3,0,0},
//                {7,3,4,1,9,6,5,0,8},{6,1,8,7,0,2,4,9,3},{0,0,0,0,8,0,0,1,0}
//        };

        int[][] gameboard = {
                {0,0,4,1,0,0,0,0,0},{0,0,0,2,9,0,3,0,1},{0,9,0,0,7,0,8,6,0},
                {1,3,0,9,0,7,5,8,2},{9,4,8,5,0,6,1,0,7},{0,0,2,8,0,3,0,0,0},
                {6,1,0,7,0,0,0,0,0},{4,0,7,0,0,0,2,1,0},{0,0,0,0,8,0,0,0,6}
        };

        SudokuSolver sudokuSolver = new SudokuSolver(gameboard);

        sudokuSolver.printGameboard();
        System.out.println();
        sudokuSolver.solvePuzzle();
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
