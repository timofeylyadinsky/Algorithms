package lt.timofey.task2;


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
