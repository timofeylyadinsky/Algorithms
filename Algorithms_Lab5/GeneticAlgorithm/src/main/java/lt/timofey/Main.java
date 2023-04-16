package lt.timofey;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] firstEquationPowers = {{1,0,2,2,2},{0,0,0,1,0},{0,0,1,0,2},{0,0,1,2,2},{1,2,2,1,2}};
        int[][] secondEquationPowers = {{1,1,2,2,2},{2,1,2,2,2},{0,2,1,1,1},{1,2,1,2,0},{0,0,0,0,1}};
        int[][] thirdEquationPowers = {{0,2,1,2,0},{2,0,2,2,0},{1,2,1,1,0},{0,0,2,0,2},{0,0,0,0,1}};
        //Arrays.stream(thirdEquationPowers).forEach(i -> System.out.println(Arrays.toString(i)));
    }
}