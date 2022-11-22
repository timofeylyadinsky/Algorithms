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
        int[] a = new int[size];

        a = QuickSort.createRandomArray(a, size, seed);
        System.out.println(Arrays.toString(a));
        int[] resultsForSortArrayA = QuickSort.quickSortSecondKey(a);
        System.out.println("Sort with second key: " + Arrays.toString(resultsForSortArrayA));
        resultsForSortArrayA = a;
        resultsForSortArrayA = QuickSort.quickSortMiddleKey(a);
        System.out.println("Sort with Middle key: " + Arrays.toString(resultsForSortArrayA));
        resultsForSortArrayA = a;
        resultsForSortArrayA = QuickSort.quickSortRandomKey(a);
        System.out.println("Sort with Random key: " + Arrays.toString(resultsForSortArrayA));
        resultsForSortArrayA = a;
        resultsForSortArrayA = QuickSort.quickSortMedianOfThreeKey(a);
        System.out.println("Sort with median three key: " + Arrays.toString(resultsForSortArrayA));
        resultsForSortArrayA = a;
        resultsForSortArrayA = QuickSort.quickSortHoar(a,0,a.length-1);
        System.out.println("Sort with Hoar Partition: " + Arrays.toString(resultsForSortArrayA));
        resultsForSortArrayA = a;
        resultsForSortArrayA = QuickSort.quickSortLomuto(a,0,a.length-1);
        System.out.println("Sort with Lomuto Partition: " + Arrays.toString(resultsForSortArrayA));

    }
}





