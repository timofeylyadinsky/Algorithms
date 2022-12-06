package lt.timofey.handler;

import lt.timofey.task1.Graph;

import java.util.ArrayList;

public class Menu {
    public static void startMenuForTask1(){
        Graph g = new Graph(6);

        /*g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.printGraph();
        g.doDFS(1);
        g.removeEdge(0,2);
        g.printGraph();

        g.doDFS(1);*/
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4,5);

        System.out.println("Following is Depth First Traversal");

        //g.doDFS(4);

        ArrayList<ArrayList<Integer>> a = g.getConnectedCompenents();
        for(ArrayList<Integer> i : a) {
            System.out.println(i.toString());
        }
        g.isEulerGraph();
        g.findEulerCycle();


        Graph graph2 = new Graph(5);
        graph2.addEdge(1-1, 2-1);
        graph2.addEdge(2-1, 3-1);
        graph2.addEdge(3-1, 4-1);
        graph2.addEdge(4-1, 5-1);
        graph2.addEdge(5-1, 1-1);
        graph2.addEdge(1-1, 3-1);
        graph2.addEdge(3-1, 5-1);
        graph2.addEdge(5-1, 2-1);
        graph2.addEdge(2-1, 4-1);
        graph2.addEdge(4-1, 1-1);
        graph2.printGraph();
        System.out.println(graph2.findEulerCycle().toString());
        graph2.printGraph();


        Graph graph3 = new Graph(5);
        graph3.addEdge(0,3);
        graph3.addEdge(0,4);
        graph3.addEdge(1,3);
        graph3.addEdge(1,4);
        graph3.addEdge(2,3);
        graph3.addEdge(2,4);

        graph3.printGraph();
        System.out.println(graph3.isBiPartited() + " "+ graph3.findPartition()[0].toString()+ " "+ graph3.findPartition()[1].toString());
    }
}
