package by.bsu.sorts;

/*
Задание 1.1. Реализовать один из гибридных алгоритмов, сочетающий быструю
        сортировку и сортировку вставками следующим образом: в алгоритме быстрой
        сортировки, участки массива длины меньшей некоторого параметра k сортировать
        сортировкой вставками, не используя для них рекурсию быстрой сортировки.
        Проделать вычислительный эксперимент. Подобрать оптимальное k для сортировки R
        массивов длины N, элементы которых - случайные целые числа в диапазоне от 0 до M.
        Дать возможность пользователю задавать параметры R,N и M.



        Задание 1.2. Реализовать один из гибридных алгоритмов, сочетающий сортировку
            слиянием и сортировку вставками следующим образом: в алгоритме сортировки
        слиянием, участки массива длины меньшей некоторого параметра k сортировать
        сортировкой вставками, не используя для них рекурсию сортировки слиянием.
        Проделать вычислительный эксперимент. Подобрать оптимальное k для сортировки R
        массивов длины N, элементы которых - случайные целые числа в диапазоне от 0 до M.
        Дать возможность пользователю задавать параметры R,N и M.


        Задание 1.3. Подсчитать число элементарных операций в вашей реализации сортировки
        вставками.
*/


import by.bsu.sorts.handler.*;
import by.bsu.sorts.reader.InfoReader;

import java.util.Arrays;


public class Main {


    public static void main(String[] args) {

        int size = 21;
        int seed = 200;
        int[] a = new int[20];

        a = QuickSort.createRandomArrayByGauss(a, size, seed);
        System.out.println(Arrays.toString(a));
        a = QuickSort.quickSortSecondKey(a);
        System.out.println(Arrays.toString(a));

//        int[] b = new int[]{1,2,3,4,5};
//        int[][] common = new int[][]{a,b};
//        int[] c = Arrays.stream(common)
//                .flatMapToInt(i->Arrays.stream(i))
//                .toArray();
//
//
//        System.out.println(Arrays.toString(Arrays.stream(c)
//                .filter(i -> i<c[1])
//                .toArray()));
//
//
//        System.out.println(c.length);
//        System.out.println(Arrays.toString(QuickSort.quickSortSecondKey(c)));
        //System.out.println(Arrays.toString(a));
        //a = QuickSort.quickSort(a,0,a.length-1);
        //System.out.println(Arrays.toString(a));


        //Menu menu = new Menu();
        //menu.firstTaskMenu();
        // menu.secondTaskMenu();

        //TestMenu.testTimeTask11();

        //int[][] arr = new int[1][10000];
        //long tmp = 500;
        //new InfoReader().inputRandomToArray(arr, 10000);
        //System.out.println(Arrays.toString(arr[0]));


    }
}





    /*private static void testTime(){

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
        measureTime(() -> new HybridMergeInsertion().hybridMergeInsertionSort(arr[0], 5));
    }

    private static void measureTime(Runnable task) {
        long startTime = System.currentTimeMillis();
        task.run();
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Затраченное время: " + elapsed + " ms");
    }*/
