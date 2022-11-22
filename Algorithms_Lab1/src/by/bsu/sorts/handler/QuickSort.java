package by.bsu.sorts.handler;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static int[] createRandomArray(int[] arr, int size, int seed){
        for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) Math.round(Math.random() * seed);
        }
        return arr;
    }

    public static int[] createRandomArrayByGauss(int[] arr, int size, int seed){
        double mean = 1.0, std = 0.5;
        Random rng = new Random(seed);
        for(int i = 0;i<arr.length;i++) {
            arr[i] = (int)( (mean + (std * rng.nextGaussian()))*seed);
        }
        /*for(int i = 0;i<arr.length;i++) {
            int phi = (int) Math.round(Math.random() * seed);
            //System.out.println(phi);
            int r = (int) Math.round(Math.random() * seed);
            //System.out.println(r);
            double z0 =  Math.cos(2 * Math.PI * phi) * StrictMath.sqrt(-2 * StrictMath.log(r));
            //System.out.println(Math.cos(2 * Math.PI * phi));
            System.out.println(StrictMath.sqrt(-2 * StrictMath.log(r)));
            //System.out.println(z0);
            arr[i] = (int) (z0 * seed);
        }
*/
        return arr;
    }

    //Sort using second key of array
    public static int[] quickSortSecondKey(int[] arr){
            if (arr.length <= 1) {
                return arr;
            }
            int partition = arr[1];
            int[] lessArray = Arrays.stream(arr)
                        .filter(i -> i < partition)
                        .toArray();
            int[] equalArray = Arrays.stream(arr)
                        .filter(i -> i == partition)
                        .toArray();
            int[] greaterArray = Arrays.stream(arr)
                        .filter(i -> i > partition)
                        .toArray();
            int[][] arrayForConcat = new int[][]{quickSortSecondKey(lessArray),equalArray,quickSortSecondKey(greaterArray)};
        return Arrays.stream(arrayForConcat)
                .flatMapToInt(i->Arrays.stream(i))
                .toArray();
    }







    //Sort using middle key of array
    public static int[] quickSortMiddleKey(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }
        int partition = arr[arr.length/2];
        int[] lessArray = Arrays.stream(arr)
                .filter(i -> i < partition)
                .toArray();
        int[] equalArray = Arrays.stream(arr)
                .filter(i -> i == partition)
                .toArray();
        int[] greaterArray = Arrays.stream(arr)
                .filter(i -> i > partition)
                .toArray();
        int[][] arrayForConcat = new int[][]{quickSortMiddleKey(lessArray),equalArray,quickSortMiddleKey(greaterArray)};
        return Arrays.stream(arrayForConcat)
                .flatMapToInt(i->Arrays.stream(i))
                .toArray();
    }


//quick sort with random key
    public static int[] quickSortRandomKey(int[] arr){
        if (arr.length <= 1) {
            return arr;
        }
        int partition = arr[(int)Math.random()*(arr.length-1)];
        int[] lessArray = Arrays.stream(arr)
                .filter(i -> i < partition)
                .toArray();
        int[] equalArray = Arrays.stream(arr)
                .filter(i -> i == partition)
                .toArray();
        int[] greaterArray = Arrays.stream(arr)
                .filter(i -> i > partition)
                .toArray();
        int[][] arrayForConcat = new int[][]{quickSortRandomKey(lessArray),equalArray,quickSortRandomKey(greaterArray)};
        return Arrays.stream(arrayForConcat)
                .flatMapToInt(i->Arrays.stream(i))
                .toArray();
    }




//Median of three
public static int[] quickSortMedianOfThreeKey(int[] arr){
    if (arr.length <= 1) {
        return arr;
    }
    int partition = findMedianOfThree(arr[0],arr[(arr.length-1)/2],arr[arr.length-1]);
    int[] lessArray = Arrays.stream(arr)
            .filter(i -> i < partition)
            .toArray();
    int[] equalArray = Arrays.stream(arr)
            .filter(i -> i == partition)
            .toArray();
    int[] greaterArray = Arrays.stream(arr)
            .filter(i -> i > partition)
            .toArray();
    int[][] arrayForConcat = new int[][]{quickSortMedianOfThreeKey(lessArray),equalArray,quickSortMedianOfThreeKey(greaterArray)};
    return Arrays.stream(arrayForConcat)
            .flatMapToInt(i->Arrays.stream(i))
            .toArray();
}

public static int findMedianOfThree(int a, int b, int c){
       if(a > b && a < c) return a;
       if(a < b && a > c) return a;
       if(c > b && a > c) return c;
       if(c > a && b > c) return c;
       if(c < b && a > b) return b;
       if(b > a && c > b) return b;
       return a;
}



    public static int[] quickSortHoar(int[] arr, int low, int high){
        if (high - low > 1) {
            int divideIndex = partitionHoar(arr, low, high);
            quickSortHoar(arr, low, (divideIndex - 1));
            quickSortHoar(arr, divideIndex, high);
        }
        return arr;
    }
    private static int partitionHoar(int[] arr, int from, int to){
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[(from + (to)) / 2];
        while (leftIndex <= rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }

            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }




    //Lomuto
    public static int[] quickSortLomuto(int[] arr, int low, int high){
        if (high - low > 1) {
            int divideIndex = partitionLomuto(arr, low, high);
            quickSortLomuto(arr, low, (divideIndex - 1));
            quickSortLomuto(arr, divideIndex, high);
        }
        return arr;
    }
    private static int partitionLomuto(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++){
            if(arr[j]<=pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,high);
        return i;
    }



    private static void swap(int[] array, int index1, int index2) {
        int tmp  = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
