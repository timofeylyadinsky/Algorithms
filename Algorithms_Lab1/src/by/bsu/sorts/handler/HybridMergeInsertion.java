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

    /*
    public static int[] mergeSort(int[] array) {
        int[] tmp;
        int[] currentSrc = array;
        int[] currentDest = new int[array.length];

        int size = 1;
        while (size < array.length) {
            for (int i = 0; i < array.length; i += 2 * size) {
                merge(currentSrc, i, currentSrc, i + size, currentDest, i, size);
            }

            tmp = currentSrc;
            currentSrc = currentDest;
            currentDest = tmp;

            size = size * 2;
        }
        return currentSrc;
    }

    private static void merge(int[] src1, int src1Start, int[] src2, int src2Start, int[] dest,
                              int destStart, int size) {
        int index1 = src1Start;
        int index2 = src2Start;

        int src1End = Math.min(src1Start + size, src1.length);
        int src2End = Math.min(src2Start + size, src2.length);

        if (src1Start + size > src1.length) {
            for (int i = src1Start; i < src1End; i++) {
                dest[i] = src1[i];
            }
            return;
        }

        int iterationCount = src1End - src1Start + src2End - src2Start;

        for (int i = destStart; i < destStart + iterationCount; i++) {
            if (index1 < src1End && (index2 >= src2End || src1[index1] < src2[index2])) {
                dest[i] = src1[index1];
                index1++;
            } else {
                dest[i] = src2[index2];
                index2++;
            }
        }
    }*/
}
