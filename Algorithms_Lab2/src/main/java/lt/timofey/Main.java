package lt.timofey;



//Задание 1:
//
//        Реализовать алгоритмы бинарного и интерполяционного поиска числа x в массиве длины N, элементы которого - случайные целые числа в диапазоне от 0 до M. Вывести число операций сравнения, выполненных алгоритмом для заданных величин.
//
//        Задание 2:
//
//        Реализовать алгоритмы построения, обхода и балансировки дерева бинарного поиска. На вход алгоритма подается последовательность целых положительных чисел. Программа должна строить BST, добавляя узлы в порядке последовательности. Реализовать обходы дерева по возрастанию и по убыванию узлов. Реализовать алгоритм нахождения k-го минимального ключа в дереве. На его основе сбалансировать построенное дерево (ротациями вправо и влево (n/2)-ый минимальный элемент помещается в корень и это повторяется рекурсией в дочерних узлах).
//
//        Задание 3:
//
//        Реализовать хэширование умножением с разрешением коллизий цепочками переполнения, линейным зондированием и двойным хэшированием. Провести вычислительный эксперимент: подобрать константу для метода умножения, сравнить ее с константой Кнута по наибольшей длине цепочек коллизий (например, проитерировать константу Кнута, уменьшая или увеличивая с очень малым шагом)

import lt.timofey.task1.BinarySearch;
import lt.timofey.task1.InterpolationSearch;
import lt.timofey.reader.InfoReader;
import lt.timofey.task2.BinaryTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int size = 100;
        int seed = 100;
        int[] array = new int[size];
        array = InfoReader.createRandomArray(array, size, seed);
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println(BinarySearch.binarySearchInArray(array, 10));
        System.out.println(InterpolationSearch.interpolationSearchInArray(array, 10));




        int[] secondArray = new int[10];
        secondArray = InfoReader.createRandomArray(secondArray, 10, seed);
        System.out.println(Arrays.toString(secondArray));
        BinaryTree tree = new BinaryTree().insertArrayOfValuesInBinaryTree(secondArray);
        // отображение дерева:
        tree.print();
    }
}