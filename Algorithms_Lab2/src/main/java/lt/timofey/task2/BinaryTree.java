package lt.timofey.task2;


import java.util.ArrayList;
import java.util.List;


public class BinaryTree {

    private Node rootNode;

    public BinaryTree(){
        rootNode = null;
    }

    public void insertNode(int value) {
        Node newNode = new Node();
        newNode.setValue(value);
        if (rootNode == null) {
            rootNode = newNode;
        }
        else {
            Node currentNode = rootNode;
            Node parentNode;
            while (true)
            {
                parentNode = currentNode;
                if(value == currentNode.getValue()) {
                    return;
                }
                else  if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null){
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                }
                else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }


    public ArrayList<Integer> traversalInOrder(){
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalInOrderRec(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalInOrderRec(Node node, ArrayList<Integer> arrayOfNodes){
        if(node!=null){
            traversalInOrderRec(node.getLeftChild(),arrayOfNodes);
            arrayOfNodes.add(node.getValue());
            traversalInOrderRec(node.getRightChild(),arrayOfNodes);
        }
    }

    public ArrayList<Integer> traversalPreOrder(){
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalPreOrderRec(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalPreOrderRec(Node node, ArrayList<Integer> arrayOfNodes){
        if(node!=null){
            arrayOfNodes.add(node.getValue());
            traversalPreOrderRec(node.getLeftChild(),arrayOfNodes);
            traversalPreOrderRec(node.getRightChild(),arrayOfNodes);
        }
    }

    public ArrayList<Integer> traversalPostOrder(){
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalPostOrderRec(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalPostOrderRec(Node node, ArrayList<Integer> arrayOfNodes){
        if(node!=null){
            traversalPostOrderRec(node.getLeftChild(),arrayOfNodes);
            traversalPostOrderRec(node.getRightChild(),arrayOfNodes);
            arrayOfNodes.add(node.getValue());
        }
    }


    public Node findNodeByValue(int value){
        Node currentNode = rootNode;
        boolean isLeft = false, isRoot = true;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeftChild();
                isRoot = false;
                isLeft = true;
            } else { //движение вправо
                currentNode = currentNode.getRightChild();
                isRoot = false;
                isLeft = false;
            }
            if (currentNode == null) {
                return null;
            }
        }
        if(isRoot) System.out.println("It Root Node");
        if(isLeft) System.out.println("It Left Node");
        else System.out.println("It Right Node");
        return currentNode;
    }

    public int findKeyMin(int key){
        ArrayList<Integer> list = this.traversalInOrder();
        return list.get(key);
    }

    public void balanceTree(){
        ArrayList<Integer> list = this.traversalInOrder();
        this.rootNode = null;
        this.balanceTreeRec(list);
    }
    public void balanceTreeRec(List<Integer> list){
        if(list.isEmpty()) return;
        int middleKey = list.size() / 2;
        this.insertNode(list.get(middleKey));
        this.balanceTreeRec(list.subList(0,middleKey));
        this.balanceTreeRec(list.subList(middleKey + 1,list.size()));
    }

    public boolean deleteNode(int value)
    {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;
        while (currentNode.getValue() != value) {
            parentNode = currentNode;
            if (value < currentNode.getValue()) {
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            }
            else {
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null)
                return false;
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (currentNode == rootNode)
                rootNode = null;
            else if (isLeftChild)
                parentNode.setLeftChild(null);
            else
                parentNode.setRightChild(null);
        }
        else if (currentNode.getRightChild() == null) {
            if (currentNode == rootNode)
                rootNode = currentNode.getLeftChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getLeftChild());
            else
                parentNode.setRightChild(currentNode.getLeftChild());
        }
        else if (currentNode.getLeftChild() == null) {
            if (currentNode == rootNode)
                rootNode = currentNode.getRightChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getRightChild());
            else
                parentNode.setRightChild(currentNode.getRightChild());
        }
        else {
            Node heir = receiveHeir(currentNode);
            if (currentNode == rootNode)
                rootNode = heir;
            else if (isLeftChild)
                parentNode.setLeftChild(heir);
            else
                parentNode.setRightChild(heir);
        }
        return true;
    }
    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRightChild();
        while (currentNode != null)
        {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        if (heirNode != node.getRightChild())
        {
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        return heirNode;
    }


    public boolean isBST(){
        return isBSTRec(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean isBSTRec(Node node, int min, int max){
        if(node == null) return true;
        if(node.getValue()<min || node.getValue() > max) return false;
        return isBSTRec(node.getLeftChild(), min, node.getValue()-1) && isBSTRec(node.getRightChild(), node.getValue()-1, max);
    }
    public BinaryTree insertArrayOfValuesInBinaryTree(int[] array){
        BinaryTree binaryTree = new BinaryTree();
        for(int i : array){
            binaryTree.insertNode(i);
        }
        return binaryTree;
    }

    public void print(){
        printRecursion(rootNode,"",true);
    }
    public void printRecursion(Node current, String prefix, Boolean isLeft){
        if(current!=null){
            if(isLeft){
                printRecursion(current.getRightChild(),prefix + "|   ", false);
            }else{
                printRecursion(current.getRightChild(),prefix + "    ", false);
            }
            if(isLeft){
                System.out.println(prefix + "\\-- " + current.getValue());
            }else{
                System.out.println(prefix + "/-- " + current.getValue());
            }
            if(isLeft){
                printRecursion(current.getLeftChild(),prefix + "    ", true);
            }else{
                printRecursion(current.getLeftChild(),prefix + "|   ", true);
            }
        }
    }
}

/*
[96, 92, 59, 39, 94, 65, 17, 71, 65, 87]
\-- 96
    |   /-- 94
    \-- 92
        |           /-- 87
        |       /-- 71
        |   /-- 65
        \-- 59
            \-- 39
                \-- 17
Traversal in order: [17, 39, 59, 65, 71, 87, 92, 94, 96]
Traversal pre order: [96, 92, 59, 39, 17, 65, 71, 87, 94]
Traversal post order: [17, 39, 87, 71, 65, 59, 94, 92, 96]
Second Min key = 59
Value to search: Node{value=71, leftChild=null, rightChild=Node{value=87, leftChild=null, rightChild=null}}

|       /-- 96
|   /-- 94
|   |   \-- 92
|   |       \-- 87
\-- 71
    |   /-- 65
    \-- 59
        \-- 39
            \-- 17

 */
/*
[49, 96, 39, 65, 73, 31, 1, 10, 44, 98]
|       /-- 98
|   /-- 96
|   |   |   /-- 73
|   |   \-- 65
\-- 49
    |   /-- 44
    \-- 39
        \-- 31
            |   /-- 10
            \-- 1
Traversal in order: [1, 10, 31, 39, 44, 49, 65, 73, 96, 98]
Traversal pre order: [49, 39, 31, 1, 10, 44, 96, 65, 73, 98]
Traversal post order: [10, 1, 31, 44, 39, 73, 65, 98, 96, 49]
Second Min key = 31
Value to search: Node{value=44, leftChild=null, rightChild=null}

|       /-- 98
|   /-- 96
|   |   \-- 73
|   |       \-- 65
\-- 49
    |   /-- 44
    |   |   \-- 39
    \-- 31
        \-- 10
            \-- 1
 */
