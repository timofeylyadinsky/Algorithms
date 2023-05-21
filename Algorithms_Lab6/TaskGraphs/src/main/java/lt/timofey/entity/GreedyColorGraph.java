package lt.timofey.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GreedyColorGraph {

/*
0 -> [1, 2]
1 -> [0, 2, 4]
2 -> [0, 1, 4]
3 -> [4]
4 -> [1, 2, 3]
}
Vertex 0 --->  Color 0
Vertex 1 --->  Color 1
Vertex 2 --->  Color 2
Vertex 3 --->  Color 0
Vertex 4 --->  Color 3
GraphGraph{vertexCount=10, adjancencyList=
0 -> [2, 1, 3, 4]
1 -> [3, 0]
2 -> [0, 5]
3 -> [1, 5, 0]
4 -> [6, 8, 5, 0]
5 -> [2, 3, 4]
6 -> [4]
7 -> []
8 -> [4]
9 -> []
}
Vertex 0 --->  Color 0
Vertex 1 --->  Color 1
Vertex 2 --->  Color 1
Vertex 3 --->  Color 2
Vertex 4 --->  Color 1
Vertex 5 --->  Color 0
Vertex 6 --->  Color 0
Vertex 7 --->  Color 0
Vertex 8 --->  Color 0
Vertex 9 --->  Color 0

Process finished with exit code 0



 */
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(4, 3);

        System.out.println("Graph" + graph);
        graph.greedyColoring(graph);

        Graph g2 = new Graph();
        g2.generateRandomGraph(g2);
        System.out.println("Graph"+g2);
        g2.greedyColoring(g2);
    }


}

class Graph {
    private int vertexCount;
    private ArrayList<Integer> adjancencyList[];


    public Graph(){}
    public Graph(int v) {
        vertexCount = v;
        adjancencyList = new ArrayList[v];
        for (int i = 0; i < v; i++){
            adjancencyList[i] = new ArrayList<>();
        }
    }
    public void addEdge(int v, int w) {
        adjancencyList[v].add(w);
        adjancencyList[w].add(v);
    }
    public void greedyColoring(Graph graph) {
        int[] result = new int[graph.vertexCount];
        Arrays.fill(result, -1);

        boolean[] colorAvailable = new boolean[graph.vertexCount];
        Arrays.fill(colorAvailable, true);

        for (int i = 0; i < graph.vertexCount; i++) {
            for (Integer vertex : graph.adjancencyList[i]) {
                if (result[vertex]!=-1) {
                    colorAvailable[result[vertex]] = false;
                }
            }
            int b;
            for (b = 0; b < colorAvailable.length; b++) {
                if(colorAvailable[b]) break;
            }
            result[i] = b;
            Arrays.fill(colorAvailable, true);
        }

        for (int i = 0; i < graph.vertexCount; i++){
            System.out.println("Vertex " + i + " --->  Color "
                    + result[i]);
        }
    }

    public void generateRandomGraph(Graph graph) {
        int vertex = 10;
        graph.vertexCount = vertex;
        graph.adjancencyList = new ArrayList[graph.vertexCount];
        for (int i = 0; i < graph.vertexCount; i++){
            adjancencyList[i] = new ArrayList<>();
        }
        Random random = new Random();
        for (int i = 0; i < vertexCount; i++) {
            // Randomly select two vertices to
            // create an edge between them
            int v = random.nextInt(graph.vertexCount);
            int w = random.nextInt(graph.vertexCount);

            if ((v == w)
                    || graph.adjancencyList[v].contains(w)) {
                i = i - 1;
                continue;
            }

            addEdge(v, w);
        }

    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Graph{" +
                "vertexCount=" + vertexCount +
                ", adjancencyList=\n");
        for (int i = 0; i < vertexCount; i++){
           string.append(i).append(" -> ").append(adjancencyList[i].toString()).append("\n");
        }

        string.append("}");
        return string.toString();
    }
}
