package lt.timofey.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class HungarianAlgorithm {
    private int[][] matrix;
    int[] rowMarkedZeros, columnMarkedZeros, rowIsCovered, columnIsCovered, storageZeros;

    public HungarianAlgorithm(int size){
        this.matrix = new int[size][size];
    }
    public HungarianAlgorithm(int[][] newMatrix){
        this.matrix = new int[newMatrix.length][newMatrix.length];
        if(newMatrix.length == matrix.length){
            this.matrix = newMatrix;
        }
        rowMarkedZeros = new int[matrix.length];
        columnMarkedZeros = new int[matrix[0].length];
        rowIsCovered = new int[matrix.length];
        columnIsCovered = new int[matrix.length];
        storageZeros = new int[matrix.length];

        Arrays.fill(storageZeros, -1);
        Arrays.fill(rowMarkedZeros, -1);
        Arrays.fill(columnMarkedZeros, -1);
    }

    public int[][] findOptimalAssigment(){
        reduceMatrix();
        markIndependentZeros();
        coverColumnsMarkedZero();
        while(!this.allColumnsAreCovered()){
            int[] mainZero = markZeros();
            while (mainZero == null) {
                step7();
                mainZero = markZeros();
            }
            if (rowMarkedZeros[mainZero[0]] == -1) {
                step6(mainZero);
                coverColumnsMarkedZero();
            } else {
                rowIsCovered[mainZero[0]] = 1;
                columnIsCovered[rowMarkedZeros[mainZero[0]]] = 0;
                step7();
            }
        }

        int[][] optimalAssignment = new int[matrix.length][];
        for (int i = 0; i < columnMarkedZeros.length; i++) {
            optimalAssignment[i] = new int[]{i, columnMarkedZeros[i]};
        }
        return optimalAssignment;
    }

    private void reduceMatrix(){
        int currentRowMin = Integer.MAX_VALUE;
        for(int i = 0; i < matrix.length; i++){
            currentRowMin = Integer.MAX_VALUE;
            for(int j : matrix[i]){
                if(currentRowMin > j){
                    currentRowMin = j;
                }
            }
            for (int j = 0; j < matrix[i].length; j++) matrix[i][j] -= currentRowMin;
        }
        int currentColumnMin = Integer.MAX_VALUE;
        for(int i = 0; i < matrix[0].length; i++){
            currentColumnMin = Integer.MAX_VALUE;
            for(int j = 0; j < matrix.length; j++){
                if(currentColumnMin > matrix[j][i]){
                    currentColumnMin = matrix[j][i];
                }
            }
            for (int j = 0; j < matrix.length; j++) matrix[j][i] -= currentColumnMin;
        }
    }
    private void markIndependentZeros(){
        int[] rowHasSquare = new int[matrix.length];
        int[] colHasSquare = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
                    rowHasSquare[i] = 1;
                    colHasSquare[j] = 1;
                    rowMarkedZeros[i] = j;
                    columnMarkedZeros[j] = i;
                    continue;
                }
            }
        }
    }

    private void coverColumnsMarkedZero(){
        for (int i = 0; i < columnMarkedZeros.length; i++) {
            columnIsCovered[i] = columnMarkedZeros[i] != -1 ? 1 : 0;
        }
    }

    private boolean allColumnsAreCovered() {
        for (int i : columnIsCovered) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    private int[] markZeros() {
        for (int i = 0; i < matrix.length; i++) {
            if (rowIsCovered[i] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0 && columnIsCovered[j] == 0) {
                        storageZeros[i] = j;
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }

    private void step7() {
        // Find the smallest uncovered value in the matrix
        int minUncoveredValue = Integer.MAX_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (rowIsCovered[i] == 1) {
                continue;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (columnIsCovered[j] == 0 && matrix[i][j] < minUncoveredValue) {
                    minUncoveredValue = matrix[i][j];
                }
            }
        }

        if (minUncoveredValue > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (rowIsCovered[i] == 1 && columnIsCovered[j] == 1) {
                        // Add min to all twice-covered values
                        matrix[i][j] += minUncoveredValue;
                    } else if (rowIsCovered[i] == 0 && columnIsCovered[j] == 0) {
                        // Subtract min from all uncovered values
                        matrix[i][j] -= minUncoveredValue;
                    }
                }
            }
        }
    }
    private void step6(int[] mainZero) {
        int i = mainZero[0];
        int j = mainZero[1];

        Set<int[]> K = new LinkedHashSet<>();
        K.add(mainZero);
        boolean found = false;
        do {
            if (columnMarkedZeros[j] != -1) {
                K.add(new int[]{columnMarkedZeros[j], j});
                found = true;
            } else {
                found = false;
            }
            if (!found) {
                break;
            }
            i = columnMarkedZeros[j];
            j = storageZeros[i];
            if (j != -1) {
                K.add(new int[]{i, j});
                found = true;
            } else {
                found = false;
            }

        } while (found);

        for (int[] zero : K) {
            if (columnMarkedZeros[zero[1]] == zero[0]) {
                columnMarkedZeros[zero[1]] = -1;
                rowMarkedZeros[zero[0]] = -1;
            }
            if (storageZeros[zero[0]] == zero[1]) {
                rowMarkedZeros[zero[0]] = zero[1];
                columnMarkedZeros[zero[1]] = zero[0];
            }
        }
        Arrays.fill(storageZeros, -1);
        Arrays.fill(rowIsCovered, 0);
        Arrays.fill(columnIsCovered, 0);
    }

    public void print(int[][] arr){
        Arrays.stream(arr)/*.toList()*/.forEach(i -> System.out.println("Employee " + Arrays.toString(i)+ " Work"));
    }
}
