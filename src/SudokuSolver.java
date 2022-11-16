import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

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
            }

            columns[i] = column;
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

    public void solveForNumber(int number) {
        int i = 0;
        ArrayList<ArrayList<Integer>> possiblePositions;

        while (i < 2) {
            possiblePositions = getPossiblePositions(number);
            addNumberToBestRow(possiblePositions, number);
            setColumns();
            i++;
        }

        printGameboard();
    }

    public void addNumberToBestRow(ArrayList<ArrayList<Integer>> possiblePositions, int number) {
        HashSet<Integer> uniqueRows = new HashSet<>();

        for (ArrayList<Integer> position : possiblePositions) {
            int row = position.get(0);
            if (uniqueRows.contains(row)) {
                uniqueRows.remove(row);
            } else {
                uniqueRows.add(row);
            }
        }

        for (ArrayList<Integer> position : possiblePositions) {
            int row = position.get(0);
            if (!uniqueRows.contains(row)) continue;
            int column = position.get(1);
            gameboard[row][column] = number;
        }
    }
//
//    public void addNumberToBestColumn(ArrayList<ArrayList<Integer>> possiblePositions, int number) {
//        HashSet<Integer> uniqueColumns = new HashSet<>();
//
//        for (ArrayList<Integer> position : possiblePositions) {
//            int column = position.get(1);
//            if (uniqueColumns.contains(column)) {
//                uniqueColumns.remove(column);
//            } else {
//                uniqueColumns.add(column);
//            }
//        }
//
//        for (ArrayList<Integer> position : possiblePositions) {
//            int column = position.get(1);
//            if (!uniqueColumns.contains(column)) continue;
//            int row = position.get(0);
//            gameboard[row][column] = number;
//        }
//    }

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
                possiblePositions.add(position);
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
