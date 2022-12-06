package lt.timofey.task1;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private int numVertices;
    private LinkedList<Integer> adjacencyLists[];

    public Graph(int vertices)
    {
        numVertices = vertices;
        adjacencyLists = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjacencyLists[i] = new LinkedList();
    }

    public void addEdge(int src, int dest)
    {
        adjacencyLists[src].add(dest);
        adjacencyLists[dest].add(src);
    }
    public void removeEdge(int src, int dest){
        adjacencyLists[src].remove((Integer) dest);
        adjacencyLists[dest].remove((Integer) src);
    }
    public void doDFS(int vertex){
        boolean[] visited = new boolean[numVertices];
        DFS(vertex, visited);
    }
    private void DFS(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> ite = adjacencyLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj, visited);
        }
    }
    public void printGraph(){
        System.out.println("\n--------");
        for(int i = 0; i < numVertices; i++){
            System.out.println(i+": " + adjacencyLists[i].toString());
        }
        System.out.println("--------");
    }
}
