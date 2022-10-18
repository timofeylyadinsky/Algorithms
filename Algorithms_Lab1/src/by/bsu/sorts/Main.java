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


import by.bsu.sorts.handler.HybridQuickInsertion;
import by.bsu.sorts.handler.InsertionSort;
import by.bsu.sorts.reader.InfoReader;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        int R = 0, N = 0, M = 0;
        System.out.println("R - number of array");
        R = new InfoReader().oneIntRead(System.in);

        System.out.println("N - size of array");
        N = new InfoReader().oneIntRead(System.in);

        System.out.println("M - number of Random");
        M = new InfoReader().oneIntRead(System.in);

        int[][] arr = new int[R][N];

        new InfoReader().inputRandomToArray(arr, M);


        //arr = new InsertionSort().insertionSort(arr);
        //arr = new HybridQuickInsertion().hybridQuickInsertionSort(arr,  0, arr.length-1, 3);
        for(int i = 0; i < arr.length; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}
