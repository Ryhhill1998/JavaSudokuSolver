public class Main {

    public static void main(String[] args) {
//        int[][] gameboard = {
//                {},{},{},
//                {},{},{},
//                {},{},{}
//        };

        int[][] gameboard = {
                {1,0,0,6,0,0,0,8,0},{0,4,0,0,0,0,0,2,0},{5,0,0,0,9,0,4,0,6},
                {0,5,0,2,0,0,7,0,1},{9,0,0,0,0,3,0,0,0},{0,0,0,0,0,0,0,4,0},
                {0,0,1,0,2,0,0,0,0},{0,0,0,0,0,0,0,0,8},{0,7,0,1,0,0,6,0,5}
        };

        SudokuSolver sudokuSolver = new SudokuSolver(gameboard);

        sudokuSolver.printGameboard();
        System.out.println();

        if (sudokuSolver.solvePuzzle(0, 0)) {
            sudokuSolver.printGameboard();
        } else {
            System.out.println("No solution possible.");
        }
    }
}
