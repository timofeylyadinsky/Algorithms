package by.bsu.sorts.handler;

public class HybridMergeInsertion {

    public static int[] hybridMergeInsertionSort(int[] array, int k) {
        if (array == null) {
            return null;
        }
        if (array.length < 2) {
            return array;
        }
        int[] arrayB = new int[array.length / 2];
        System.arraycopy(array, 0, arrayB, 0, array.length / 2);

        int[] arrayC = new int[array.length - arrayB.length];
        System.arraycopy(array, arrayB.length, arrayC, 0, array.length - arrayB.length);

        if(arrayB.length < k) {
            InsertionSort.insertionSort(arrayB, 0 , arrayB.length - 1);
        }else{
            hybridMergeInsertionSort(arrayB, k);
        }
        if(arrayC.length < k) {
            InsertionSort.insertionSort(arrayC, 0 , arrayC.length - 1);
        }else{
            hybridMergeInsertionSort(arrayC, k);
        }
        mergeArray(array, arrayB, arrayC);

        return array;
    }

    private static void mergeArray(int[] array, int[] arrayB, int[] arrayC) {

        int positionB = 0;
        int positionC = 0;

        for (int c = 0; c < array.length; c++) {
            if (positionB == arrayB.length) {
                array[c] = arrayC[positionC];
                positionC++;
            } else if (positionC == arrayC.length) {
                array[c] = arrayB[positionB];
                positionB++;
            } else if (arrayB[positionB] < arrayC[positionC]) {
                array[c] = arrayB[positionB];
                positionB++;
            } else {
                array[c] = arrayC[positionC];
                positionC++;
            }
        }
    }
}
