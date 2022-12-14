package by.bsu.sorts.handler;



public class HybridQuickInsertion {

    public static int[] hybridQuickInsertionSort(int[] arr, int low, int high, int k){

        if(high - low <= k){
            new InsertionSort().insertionSort(arr, low, high);
        }else {
            if (low < high) {
                int divideIndex = partition(arr, low, high);
                hybridQuickInsertionSort(arr, low, (divideIndex - 1),k);
                hybridQuickInsertionSort(arr, divideIndex, high,k);
            }
        }
        return arr;
    }

    private static int partition(int[] arr, int from, int to){
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from + (to - from) / 2];
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

    private static void swap(int[] array, int index1, int index2) {
        int tmp  = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

}
