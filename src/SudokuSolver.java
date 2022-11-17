import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SudokuSolver {

    private int[][] gameboard;
    private int[][] columns;
    private int[][] gridRow0;
    private int[][] gridRow1;
    private int[][] gridRow2;
    private int spacesLeft = 0;

    public SudokuSolver(int[][] gameboard) {
        this.gameboard = gameboard;
        columns = new int[gameboard.length][gameboard.length];
        setColumns();
        gridRow0 = new int[3][9];
        gridRow1 = new int[3][9];
        gridRow2 = new int[3][9];
        setGrids();
        setSpacesLeft();
    }

    private void setColumns() {
        for (int i = 0; i < gameboard.length; i++) {
            int[] column = new int[gameboard[i].length];

            for (int j = 0; j < gameboard[i].length; j++) {
                column[j] = gameboard[j][i];
            }

            columns[i] = column;
        }
    }

    private void setGrids() {
        setGridRow0();
        setGridRow1();
        setGridRow2();
    }

    private void setGridRow0() {
        for (int n = 0; n < 3; n++) {
            int column = n * 3;
            int[] grid = new int[9];
            int position = 0;

            for (int row = 0; row < 3; row++) {
                for (int i = column; i < column + 3; i++) {
                    grid[position] = gameboard[row][i];
                    position++;
                }
            }

            gridRow0[n] = grid;
        }
    }
    private void setGridRow1() {
        for (int n = 0; n < 3; n++) {
            int column = n * 3;
            int[] grid = new int[9];
            int position = 0;

            for (int row = 3; row < 6; row++) {
                for (int i = column; i < column + 3; i++) {
                    grid[position] = gameboard[row][i];
                    position++;
                }
            }

            gridRow1[n] = grid;
        }
    }

    private void setGridRow2() {
        for (int n = 0; n < 3; n++) {
            int column = n * 3;
            int[] grid = new int[9];
            int position = 0;

            for (int row = 6; row < 9; row++) {
                for (int i = column; i < column + 3; i++) {
                    grid[position] = gameboard[row][i];
                    position++;
                }
            }

            gridRow2[n] = grid;
        }
    }

    public void printGrids() {
        for (int[] grid : gridRow0) System.out.println(Arrays.toString(grid));
        for (int[] grid : gridRow1) System.out.println(Arrays.toString(grid));
        for (int[] grid : gridRow2) System.out.println(Arrays.toString(grid));
    }

    public void printGameboard() {
        for (int i = 0; i < gameboard.length; i++) {
            System.out.printf("Row %d: %s\n", i, Arrays.toString(gameboard[i]));
        }
    }

    public void printColumns() {
        for (int i = 0; i < columns.length; i++) {
            System.out.printf("Column %d: %s\n", i + 1, Arrays.toString(columns[i]));
        }
    }

    private void setSpacesLeft() {
        for (int[] row : gameboard) {
            for (int space : row) {
                if (space == 0) spacesLeft++;
            }
        }
    }

    public void solvePuzzle() {
        int number = 1;

        while (spacesLeft > 0) {
            if (number == 10) number = 1;
            solveForNumber(number);
            number++;
        }

        printGameboard();
    }

    public void solveForNumber(int number) {
        boolean added = true;
        ArrayList<ArrayList<Integer>> possiblePositions;

        while (added) {
            possiblePositions = getPossiblePositions(number);
            added = addNumberToBestRow(possiblePositions, number);
            setColumns();
        }
    }

    public boolean addNumberToBestRow(ArrayList<ArrayList<Integer>> possiblePositions, int number) {
        HashSet<Integer> rowsSeen = new HashSet<>();
        HashSet<Integer> uniqueRows = new HashSet<>();

        for (ArrayList<Integer> position : possiblePositions) {
            int row = position.get(0);

            if (rowsSeen.contains(row)) {
                uniqueRows.remove(row);
            } else {
                uniqueRows.add(row);
            }

            rowsSeen.add(row);
        }

        for (ArrayList<Integer> position : possiblePositions) {
            int row = position.get(0);
            if (!uniqueRows.contains(row)) continue;
            int column = position.get(1);
            gameboard[row][column] = number;
            spacesLeft--;
//            System.out.printf("%d added to row: %d, col: %d\n", number, row, column);
            return true;
        }

        return false;
    }

    public boolean positionGridContainsNumber(ArrayList<Integer> position, int number) {
        int row = position.get(0);
        int column = position.get(1);

        int gridRowNumber = row / 3;
        int[][] gridRow = gridRow0;

        switch (gridRowNumber) {
            case 1:
                gridRow = gridRow1;
                break;
            case 2:
                gridRow = gridRow2;
                break;
        }

        int gridIndex = column / 3;
        int[] grid = gridRow[gridIndex];

        return sectionContainsElement(grid, number);
    }

    public ArrayList<ArrayList<Integer>> getPossiblePositions(int number) {
        ArrayList<ArrayList<Integer>> possiblePositions = new ArrayList<>();

        for (int i = 0; i < gameboard.length; i++) {
            int[] row = gameboard[i];
            if (sectionContainsElement(row, number)) continue;

            for (int j = 0; j < row.length; j++) {
                if (row[j] != 0) continue;
                int[] column = columns[j];
                if (sectionContainsElement(column, number)) continue;
                ArrayList<Integer> position = new ArrayList<>();
                position.add(i);
                position.add(j);
                if (!positionGridContainsNumber(position, number)) possiblePositions.add(position);
            }
        }

        return possiblePositions;
    }

    private boolean sectionContainsElement(int[] section, int element) {
        for (int j : section) {
            if (j == element) return true;
        }

        return false;
    }
}
