package lt.timofey.handler;

import java.util.Arrays;

public class InterpolationSearch {

    public static int interpolationSearchInArray(int[] array, int key){
        Arrays.sort(array);
        int leftIndex = 0;
        int rightIndex = array.length-1;
        int compareOperations = 0;
        int position = -1;

        while (array[leftIndex]<=key && array[rightIndex]>=key){
            int middleIndex = leftIndex + ((key-array[leftIndex])*(rightIndex-leftIndex)) / (array[rightIndex]-array[leftIndex]);
            if(array[middleIndex] == key){
                compareOperations++;
                position = middleIndex;
                break;
            }else if(array[middleIndex] < key){
                leftIndex = middleIndex + 1;
                compareOperations++;
            }else if(array[middleIndex] > key){
                rightIndex = middleIndex - 1;
                compareOperations++;
            }
        }
        System.out.println("Number of compare operations = " + compareOperations);
        if(position==-1){
            System.out.println("None in array");
        }
        return position;
    }
}
