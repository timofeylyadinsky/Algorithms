package lt.timofey.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColorGraph {

/*
//Greedy algorithm result
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


//DSATUR Algorithm result
Graph dsatur coloringGraph{vertexCount=5, adjancencyList=
0 -> [1, 2]
1 -> [0, 2, 4]
2 -> [0, 1, 4]
3 -> [4]
4 -> [1, 2, 3]
}
Vertex 0 --->  Color 0
Vertex 1 --->  Color 1
Vertex 2 --->  Color 2
Vertex 3 --->  Color 1
Vertex 4 --->  Color 0


 */
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(4, 3);

        System.out.println("Graph greedy coloring" + graph);
        graph.greedyColoring(graph);
        System.out.println("Graph dsatur coloring" + graph);
        graph.dsaturImpl(graph);

        Graph g2 = new Graph();
        g2.generateRandomGraph(g2);
        System.out.println("Graph greedy coloring"+g2);
        g2.greedyColoring(g2);
        System.out.println("Graph dsatur coloring"+g2);
        g2.dsaturImpl(g2);
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
        int vertex = 15;
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

    public void dsaturImpl(Graph graph) {
        int[] saturationDegrees = new int[graph.vertexCount];
        Arrays.fill(saturationDegrees, 0);
        List<Integer> fillingNodes = new ArrayList<>();
        int[] result = new int[graph.vertexCount];
        Arrays.fill(result, -1);
        boolean[] colorAvailable = new boolean[graph.vertexCount];
        Arrays.fill(colorAvailable, true);
        while (fillingNodes.size() < graph.vertexCount) {
            int maxSaturIndex = getMaxSaturation(saturationDegrees);
            for (Integer vertex : graph.adjancencyList[maxSaturIndex]) {
                if (result[vertex]!=-1) {
                    colorAvailable[result[vertex]] = false;
                }
            }
            int b;
            for (b = 0; b < colorAvailable.length; b++) {
                if(colorAvailable[b]) break;
            }
            result[maxSaturIndex] = b;
            Arrays.fill(colorAvailable, true);
            fillingNodes.add(maxSaturIndex);
            saturationDegrees[maxSaturIndex] = -1;
            for (Integer vertex : graph.adjancencyList[maxSaturIndex]) {
                if (saturationDegrees[vertex]!=-1) saturationDegrees[vertex] += 1;
            }
        }

        for (int i = 0; i < graph.vertexCount; i++){
            System.out.println("Vertex " + i + " --->  Color "
                    + result[i]);
        }
    }

    public int getMaxSaturation(int[] s) {
        int max = 0;
        for (int i = 0; i < s.length; i++){
            if(s[i] > s[max]) max = i;
        }
        return max;
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

/*

Graph greedy coloringGraph{vertexCount=15, adjancencyList=
0 -> [9, 5, 11]
1 -> [12]
2 -> [4]
3 -> [14]
4 -> [2, 9, 13]
5 -> [0, 10]
6 -> [9]
7 -> [13, 10]
8 -> []
9 -> [4, 0, 10, 6]
10 -> [9, 5, 7]
11 -> [0]
12 -> [1, 14]
13 -> [7, 14, 4]
14 -> [3, 13, 12]
}
Vertex 0 --->  Color 0
Vertex 1 --->  Color 0
Vertex 2 --->  Color 0
Vertex 3 --->  Color 0
Vertex 4 --->  Color 1
Vertex 5 --->  Color 1
Vertex 6 --->  Color 0
Vertex 7 --->  Color 0
Vertex 8 --->  Color 0
Vertex 9 --->  Color 2
Vertex 10 --->  Color 3
Vertex 11 --->  Color 1
Vertex 12 --->  Color 1
Vertex 13 --->  Color 2
Vertex 14 --->  Color 3
Graph dsatur coloringGraph{vertexCount=15, adjancencyList=
0 -> [9, 5, 11]
1 -> [12]
2 -> [4]
3 -> [14]
4 -> [2, 9, 13]
5 -> [0, 10]
6 -> [9]
7 -> [13, 10]
8 -> []
9 -> [4, 0, 10, 6]
10 -> [9, 5, 7]
11 -> [0]
12 -> [1, 14]
13 -> [7, 14, 4]
14 -> [3, 13, 12]
}
Vertex 0 --->  Color 0
Vertex 1 --->  Color 0
Vertex 2 --->  Color 1
Vertex 3 --->  Color 1
Vertex 4 --->  Color 0
Vertex 5 --->  Color 1
Vertex 6 --->  Color 0
Vertex 7 --->  Color 1
Vertex 8 --->  Color 0
Vertex 9 --->  Color 1
Vertex 10 --->  Color 0
Vertex 11 --->  Color 1
Vertex 12 --->  Color 1
Vertex 13 --->  Color 2
Vertex 14 --->  Color 0

Process finished with exit code 0

 */
