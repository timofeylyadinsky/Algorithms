package lt.timofey.task1;

import java.util.ArrayList;
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
    public ArrayList<Integer> doDFS(int vertex, boolean[] visited){
        //boolean[] visited = new boolean[numVertices];
        ArrayList<Integer> component = new ArrayList<>();
        DFS(vertex, visited, component);
        return component;
    }
    private ArrayList<Integer> DFS(int vertex, boolean[] visited, ArrayList<Integer> component) {
        visited[vertex] = true;
        //System.out.print(vertex + " ");
        component.add(vertex);
        Iterator<Integer> ite = adjacencyLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj, visited, component);
        }
        return component;
    }
    public  ArrayList<ArrayList<Integer>> getConnectedCompenents(){
        ArrayList<ArrayList<Integer>> components = new ArrayList<ArrayList<Integer>>();
        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i< numVertices; i++){
            if(!visited[i]){
                components.add(doDFS(i, visited));
            }
        }
        return components;
    }
    public void printGraph(){
        System.out.println("\n--------");
        for(int i = 0; i < numVertices; i++){
            System.out.println(i+": " + adjacencyLists[i].toString());
        }
        System.out.println("--------");
    }
}
