package by.bsu.sorts.handler;

import by.bsu.sorts.reader.InfoReader;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Menu {
    public static void firstTaskMenu(){
        int R = 0, N = 0, M = 0;
        System.out.println("R - number of array");
        R = new InfoReader().oneIntRead(System.in);

        System.out.println("N - size of array");
        N = new InfoReader().oneIntRead(System.in);

        System.out.println("M - number of Random");
        M = new InfoReader().oneIntRead(System.in);

        int[][] arr = new int[R][N];

        new InfoReader().inputRandomToArray(arr, M);
        int[] kRez = new int[R];



        System.out.println("Arrays.toString(arr[i])");
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }

        for(int i = 0; i < arr.length; i++){
            //int[] arr1 = arr[i];
            for(int k = 0 ; k < arr[i].length; k++){
                int[] arr1 = arr[i].clone();
                //long start = System.currentTimeMillis();
                Instant start = Instant.now();
                arr1 =  new HybridQuickInsertion().hybridQuickInsertionSort(arr1,  0, arr1.length-1, k);
                Instant finish = Instant.now();
            }


        }
    }


    public static void secondTaskMenu(){
        int R = 0, N = 0, M = 0;
        System.out.println("R - number of array");
        R = new InfoReader().oneIntRead(System.in);

        System.out.println("N - size of array");
        N = new InfoReader().oneIntRead(System.in);

        System.out.println("M - number of Random");
        M = new InfoReader().oneIntRead(System.in);

        int[][] arr = new int[R][N];

        new InfoReader().inputRandomToArray(arr, M);

        System.out.println("Arrays.toString(arr[i])");
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }


        for(int i = 0; i < arr.length; i++){
            for(int k = 0 ; k < arr[i].length; k++){
                int[] arr1 = arr[i].clone();
                //long start = System.currentTimeMillis();
                Instant start = Instant.now();
                arr1 =  new HybridMergeInsertion().hybridMergeInsertionSort(arr1, k);
                Instant finish = Instant.now();
                //long end = System.currentTimeMillis();
                System.out.println(Arrays.toString(arr1) + "\n When k = " + k + "\nTimes = " + Duration.between(start, finish).toMillis() +"ms\n\n");
            }


        }
    }


}
