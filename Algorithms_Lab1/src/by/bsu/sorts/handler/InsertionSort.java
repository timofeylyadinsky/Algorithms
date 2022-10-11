package by.bsu.sorts.handler;

import java.util.ArrayList;
import java.util.Collections;

public class InsertionSort {
    ArrayList<Integer> insertionSort(ArrayList<Integer> arr){
        for(int i = 1; i < arr.size(); i++){
            int j = i - 1;
            while(j>=0 && arr.get(j) > arr.get(j+1)){
                Collections.swap(arr, i, j);
                j--;
            }
        }
        return arr;
    }
}
