package lt.timofey.additional;

import lt.timofey.reader.InfoReader;
import lt.timofey.task2.BinaryTree;

import java.util.Arrays;

public class BinaryTreeTest {
    public static void taskBinaryTreeAdditional(){
        int[] secondArray = new int[]{16, 11, 21, 9, 13, 17, 26};
        System.out.println(Arrays.toString(secondArray));
        BinaryTree tree = new BinaryTree().insertArrayOfValuesInBinaryTree(secondArray);
        // отображение дерева:
        tree.print();
        tree.insertNode(17);
        tree.print();
        System.out.println(tree.findNodeByValue(21));

        System.out.println("Traversal in order: " + tree.traversalInOrder().toString());
        System.out.println("Traversal pre order: " + tree.traversalPreOrder().toString());
        System.out.println("Traversal post order: " + tree.traversalPostOrder().toString());
        System.out.println("Second Min key = " + tree.findKeyMin(2));
        System.out.println("Value to search: " + tree.findNodeByValue(tree.findKeyMin(4)));
        System.out.println();
        System.out.println("is bst: " + tree.isBST());
        tree.balanceTree();
        System.out.println("is bst: " + tree.isBST());
        tree.print();


        secondArray = InfoReader.createRandomArray(secondArray, 15, 100);
        System.out.println(Arrays.toString(secondArray));
        tree = new BinaryTree().insertArrayOfValuesInBinaryTree(secondArray);
        // отображение дерева:
        tree.print();
        tree.insertNode(17);
        tree.print();
        System.out.println(tree.findNodeByValue(21));

        System.out.println("Traversal in order: " + tree.traversalInOrder().toString());
        System.out.println("Traversal pre order: " + tree.traversalPreOrder().toString());
        System.out.println("Traversal post order: " + tree.traversalPostOrder().toString());
        System.out.println("Second Min key = " + tree.findKeyMin(2));
        System.out.println("Value to search: " + tree.findNodeByValue(tree.findKeyMin(4)));
        System.out.println();
        System.out.println("is bst: " + tree.isBST());
        tree.balanceTree();
        System.out.println("is bst: " + tree.isBST());
        tree.print();
    }
}
