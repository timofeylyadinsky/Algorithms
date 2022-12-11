package lt.timofey.handler;

import lt.timofey.task1.Graph;
import lt.timofey.task2.Floyd;
import lt.timofey.task3.Edge;
import lt.timofey.task3.UndirectedWeightGraph;
import lt.timofey.task4.HungarianAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

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
    public static void startMenuForTask2(){
        Floyd fl = new Floyd(Floyd.initRandomAdjacencyMatrix(5));
        fl.findShortestPath();
        System.out.println(fl.findVertexWithShortestPath());
    }
    public static void startMenuForTask3(){

        UndirectedWeightGraph uwg = new UndirectedWeightGraph(5);

        uwg.addEdge(new Edge(0,2,3));
        uwg.addEdge(new Edge(0,3,5));
        uwg.addEdge(new Edge(0,1,7));
        uwg.addEdge(new Edge(1,3,4));
        uwg.addEdge(new Edge(1,4,8));
        uwg.addEdge(new Edge(3,4,6));
        //uwg.printGraph();
        //uwg.removeEdge(new Edge(1,3,4));
        uwg.printGraph();
        uwg.kruskalMST();
        uwg.printGraph();
    }
    public static void startMenuForTask4(){
        //int[][] arr = {{1,1,1,0},{0,1,0,0},{1,0,1,0},{0,1,1,0}};
//        int[][] arr = {{1,1,1,3},{3,1,4,5},{2,3,4,5},{0,1,1,4}};
        int[][] arr = {{7,3,6,9,5},{7,5,7,5,6},{7,6,8,8,9},{3,1,6,5,7},{2,4,9,9,5}};
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(arr);
        Arrays.stream(arr).forEach(i -> System.out.println(Arrays.toString(i)));
        hungarianAlgorithm.print(hungarianAlgorithm.findOptimalAssigment());
    }
}
