package by.bsu.sorts.handler;



public class InsertionSort {
    public static void /*int[]*/ insertionSort(int[] arr, int from, int to){
        for(int i = (from+1); i <= to; i++){
            int j = i - 1;
            while(j>=from && arr[j] > arr[j+1]){
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
                j--;
            }
        }
        //return arr;
    }
}
