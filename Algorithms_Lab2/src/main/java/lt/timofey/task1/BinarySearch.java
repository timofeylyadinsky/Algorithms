package lt.timofey.task1;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySearchInArray(int[] array, int key){
        Arrays.sort(array);
        int leftIndex = 0;
        int rightIndex = array.length-1;
        int compareOperations = 0;
        int position = -1;

        while (leftIndex<=rightIndex){
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
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
