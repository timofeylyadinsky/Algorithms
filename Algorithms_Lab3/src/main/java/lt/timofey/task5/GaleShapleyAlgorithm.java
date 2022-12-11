package lt.timofey.task5;

import java.util.ArrayList;
import java.util.Arrays;

public class GaleShapleyAlgorithm {
   /* private int[][] employee;
    private int[][] tasks;

    private int[] effectiveTaskList;
    private int[] interestingTaskList;

    private int[] employesTaskList;

    public GaleShapleyAlgorithm(int[][] e, int[][] t){
        this.employee = new int[e.length][e[0].length];
        this.tasks = new int[t.length][t[0].length];
        employee = e;
        tasks = t;
        effectiveTaskList = new int[t.length];
        interestingTaskList = new int[t.length];
        employesTaskList = new int[employee.length];
        Arrays.fill(employesTaskList,-1);
        Arrays.fill(effectiveTaskList, -1);
        Arrays.fill(interestingTaskList, -1);
    }*/
    public GaleShapleyAlgorithm(){

    }
    public void moreEffectiveEmployeeForTask(int[][] arrayOfFirstTable, int[][] arrayOfSecondTable){
        int[] listForFirst = new int[arrayOfFirstTable.length];
        int[] listForSecond = new int[arrayOfSecondTable.length];
        fillArray(listForFirst, listForSecond);
        while(!coverAllArray(listForSecond) && !coverAllArray(listForFirst)){
            for(int i = 0; i < arrayOfFirstTable.length; i++){
                if (listForFirst[i] == -1){
                    if(listForSecond[arrayOfFirstTable[i][0]] == -1){
                        listForSecond[arrayOfFirstTable[i][0]] = i;
                        listForFirst[i] = arrayOfFirstTable[i][0];
                    } else {
                        int iterator = 0;
                        while(listForFirst[i] == -1){
                            /*if(getPosition(arrayOfSecondTable[i], i) > getPosition(arrayOfSecondTable[i],   listForSecond[arrayOfSecondTable[i][0]])){
                                listForFirst[listForSecond[arrayOfSecondTable[i][0]]] = -1;
                                listForSecond[arrayOfSecondTable[i][0]] = arrayOfFirstTable[i][0];
                                listForFirst[i] = arrayOfSecondTable[i][0];
                            }
                            else{

                            }*/
                            if((listForSecond[arrayOfFirstTable[i][iterator]]!=-1)) {
                                if (getPosition(arrayOfSecondTable[arrayOfFirstTable[i][iterator]], i) < getPosition(arrayOfSecondTable[arrayOfFirstTable[i][iterator]], listForSecond[arrayOfFirstTable[i][iterator]])) {
                                    listForFirst[listForSecond[arrayOfFirstTable[i][iterator]]] = -1;
                                    listForSecond[arrayOfFirstTable[i][iterator]] = i;
                                    listForFirst[i] = arrayOfFirstTable[i][iterator];
                                }
                            }else{
                                listForSecond[arrayOfFirstTable[i][iterator]] = i;
                                listForFirst[i] = arrayOfFirstTable[i][iterator];
                            }
                            iterator++;
                        }
                    }
                }
            }
        }
        System.out.println("Array first to second: " + Arrays.toString(listForFirst));
        System.out.println("Array second to first: " + Arrays.toString(listForSecond));
    }
private int getPosition(int[] arr, int element) {
    for (int i = 0; i < arr.length; i++){
        if(arr[i]==element) {
            //System.out.println(i);
            return i;
        }
    }
        return -1;
}

    private void fillArray(int[] arr1, int[] arr2){
        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);
    }
    private boolean coverAllArray(int[] arr){
        for (int i : arr){
            if(i==-1) return false;
        }
        return true;
    }

}
