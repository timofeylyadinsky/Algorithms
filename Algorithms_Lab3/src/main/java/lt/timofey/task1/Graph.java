package lt.timofey.task1;

import lt.timofey.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    public int countOddVertices()
    {
        int count = 0;
        for (List<Integer>list : adjacencyLists)
        {
            if ((list.size() & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    public boolean isEulerGraph(){
        if ((countOddVertices() == 0 || countOddVertices() == 2) && getConnectedCompenents().size() == 1){
            return true;
        }else {
            return false;
        }
    }
    public boolean hasEulerCycle(){
        if (countOddVertices() == 0 && getConnectedCompenents().size() == 1){
            return true;
        }else {
            return false;
        }
    }
    public int countAllEdges(){
        int count = 0;
        for(List<Integer> i : adjacencyLists){
            count+=i.size();
        }
        return count;
    }
    public int countEdges(int vertex){
        return adjacencyLists[vertex].size();
    }
    public ArrayList<Integer> findEulerCycle(){
        if(hasEulerCycle()){
            ArrayList<Integer> cycle = new ArrayList<>();
            ArrayList<Integer> tempCycle = new ArrayList<>();
            int currentVertex = (int)(Math.random() * (numVertices-1));
            tempCycle.add(currentVertex);
            Graph g = new Graph(this.numVertices);
            g.copy(this);
            while(g.countAllEdges()!=0){
                int nextVertex;
                if(g.countEdges(currentVertex)!=0){
                    nextVertex = g.adjacencyLists[currentVertex].get(0);
                    g.removeEdge(currentVertex,nextVertex);
                    currentVertex = nextVertex;
                    tempCycle.add(currentVertex);
                }else{
                    int i = tempCycle.size()-1;
                    while(g.countEdges(tempCycle.get(i))==0){
                        cycle.add(tempCycle.get(i));
                        tempCycle.remove(i);
                        i--;
                    }
                    currentVertex = tempCycle.get(i);
                }
            }
            if(tempCycle.size()!=0){
                while(tempCycle.size()!=0){
                    cycle.add(tempCycle.get(tempCycle.size()-1));
                    tempCycle.remove(tempCycle.size()-1);
                }
            }
            return cycle;
        }
        System.out.println("None euler cycle in graph");
        return new ArrayList<Integer>(0);
    }
    public boolean isBiPartited(){
        int[] visited = new int[numVertices];
        return isBiPartitedRec(0, visited, 1);
        //return true;
    }
    public boolean isBiPartitedRec(int vertex, int[] visited, int currentNum){
        visited[vertex] = currentNum;
        //System.out.print(vertex + " ");
        Iterator<Integer> ite = adjacencyLists[vertex].listIterator();
        while (ite.hasNext()) {
            int adj = ite.next();
            if (visited[adj] == 0)
                isBiPartitedRec(adj, visited, (currentNum==1) ? 2 : 1);
            else if(visited[adj] == currentNum)
                return false;
        }
        return true;
    }
    public ArrayList<Integer>[] findPartition(){
        if (this.isBiPartited()){
            int[] visited = new int[numVertices];
            isBiPartitedRec(0, visited, 1);
            ArrayList<Integer> fractional[] = new ArrayList[2];
            fractional[0] = new ArrayList<>();
            fractional[1] = new ArrayList<>();
            for (int i = 0; i<visited.length; i++){
                if(visited[i]==1){
                    fractional[0].add(i);
                }else{
                    fractional[1].add(i);
                }
            }
            return fractional;
        }else{
            return new ArrayList[2];
        }
    }
    public void printGraph(){
        System.out.println("\n--------");
        for(int i = 0; i < numVertices; i++){
            System.out.println(i+": " + adjacencyLists[i].toString());
        }
        System.out.println("--------");
    }
    public void copy(Graph g){
        this.numVertices = g.numVertices;
        for (int i = 0; i < numVertices;i++){
            for (int j : g.adjacencyLists[i]){
                this.adjacencyLists[i].add(j);
            }
        }
        //return this;
    }
}
