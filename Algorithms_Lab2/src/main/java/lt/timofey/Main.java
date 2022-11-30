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

import lt.timofey.additional.BinaryTreeTest;
import lt.timofey.task1.BinarySearch;
import lt.timofey.task1.InterpolationSearch;
import lt.timofey.reader.InfoReader;
import lt.timofey.task2.BinaryTree;
import lt.timofey.task3.HashTableChain;

import java.util.Arrays;
import java.util.Hashtable;

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
        System.out.println("Traversal in order: " + tree.traversalInOrder().toString());
        System.out.println("Traversal pre order: " + tree.traversalPreOrder().toString());
        System.out.println("Traversal post order: " + tree.traversalPostOrder().toString());
        System.out.println("Second Min key = " + tree.findKeyMin(2));
        System.out.println("Value to search: " + tree.findNodeByValue(tree.findKeyMin(4)));
        System.out.println();
        tree.balanceTree();
        tree.print();

        BinaryTreeTest.taskBinaryTreeAdditional();


        final HashTableChain table = new HashTableChain();
        table.add(10, 666);
        table.add(20, -12345);
        table.add(3, 245);
        table.add(4, -123);
        table.add(5, 888);
        table.add(6, 777);
        table.add(7, 333);
        table.add(19, 8);
        table.add(16, 77);
        table.add(71, 633);
        System.out.println("Knut\n-------------");
        System.out.println(table.print());
        table.printMaxOfCollision();
        table.printNumOfCollision();
        System.out.println("\n-------------\n");


        for(int i = 0; i < 100; i++) {
            final HashTableChain table2 = new HashTableChain();
            table2.c = table2.c+(double)(i)/1000;
            table2.add(10, 666);
            table2.add(20, -12345);
            table2.add(3, 245);
            table2.add(4, -123);
            table2.add(5, 888);
            table2.add(6, 777);
            table2.add(7, 333);
            table2.add(19, 8);
            table2.add(16, 77);
            table2.add(71, 633);
            System.out.println("\n-------------");
            System.out.println(table2.print());
            table2.printMaxOfCollision();
            table2.printNumOfCollision();
            System.out.println("Constant = "+ table2.c +"\n-------------\n");
        }
    }
}

