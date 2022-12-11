package lt.timofey.task4;

import java.util.ArrayList;
import java.util.Arrays;

public class HungarianAlgorithm {
    private int[][] matrix;

    public HungarianAlgorithm(int size){
        this.matrix = new int[size][size];
    }
    public HungarianAlgorithm(int[][] newMatrix){
        this.matrix = new int[newMatrix.length][newMatrix.length];
        if(newMatrix.length == matrix.length){
            this.matrix = newMatrix;
        }
    }

    public int[][] findOptimalAssigment(){
        reduceMatrix();
        //Arrays.stream(matrix).toList().forEach(i -> System.out.println(Arrays.toString(i)));
        return null;
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
}
