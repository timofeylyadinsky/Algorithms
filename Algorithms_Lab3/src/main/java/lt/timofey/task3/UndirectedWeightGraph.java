package lt.timofey.task3;

import lt.timofey.task1.Graph;

import java.util.*;

public class UndirectedWeightGraph {
    private int numVertices;
    private LinkedList<Edge> adjacencyLists[];

    public UndirectedWeightGraph(int vertices)
    {
        numVertices = vertices;
        adjacencyLists = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++)
            adjacencyLists[i] = new LinkedList();
    }

    public void addEdge(Edge edge)
    {
        adjacencyLists[edge.getSrc()].add(edge);
        adjacencyLists[edge.getDest()].add(edge);
    }
    public void removeEdge(Edge edge){
        int i = 0;
        for (Edge e : adjacencyLists[edge.getSrc()]){
            if (e.getDest() == edge.getDest()){
                adjacencyLists[edge.getSrc()].remove(i);
                break;
            }else{
                i++;
            }
        }
        i = 0;
        for (Edge e : adjacencyLists[edge.getDest()]){
            if (e.getSrc() == edge.getSrc()){
                adjacencyLists[edge.getDest()].remove(i);
                break;
            }else{
                i++;
            }
        }
        //adjacencyLists[edge.getSrc()].remove(edge);
        //adjacencyLists[edge.getDest()].remove(edge);
    }
    public void kruskalMST(){
        UndirectedWeightGraph graph = new UndirectedWeightGraph(numVertices);
        graph.copy(this);
        ArrayList<Edge> listOfEdges = sortByWeight(graph);
        DisjointSet nodeSet = new DisjointSet(numVertices);
        ArrayList<Edge> mstEdges = new ArrayList<Edge>();
        for(int i = 0; i<listOfEdges.size() && mstEdges.size()<(numVertices-1);i++){
            Edge currentEdge = listOfEdges.get(i);
            int root1 = nodeSet.find(currentEdge.getSrc());
            int root2 = nodeSet.find(currentEdge.getDest());
            if(root1!=root2){
                mstEdges.add(currentEdge);
                nodeSet.union(root1,root2);
            }
        }
        int totalWeight = 0;
        for(Edge e : mstEdges){
            totalWeight+=e.getWeight();
        }
        System.out.println("Minimal spanning tree: "+ mstEdges.toString() + "\tWeight: " + totalWeight);
    }
    public ArrayList<Edge> sortByWeight(UndirectedWeightGraph g){
        ArrayList<Edge> expected = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            for (Edge e : g.adjacencyLists[i]) {
                expected.add(e);
                //g.removeEdge(e);
            }
            while (g.adjacencyLists[i].size() > 0){
                g.removeEdge(g.adjacencyLists[i].get(0));
            }
        }
        Collections.sort(expected);
        return expected;
    }
    public void printGraph(){
        System.out.println("\n--------");
        for(int i = 0; i < numVertices; i++){
            System.out.println(i+": " + adjacencyLists[i].toString());
        }
        System.out.println("--------");
    }
    public void copy(UndirectedWeightGraph g){
        this.numVertices = g.numVertices;
        for (int i = 0; i < numVertices;i++){
            for (Edge j : g.adjacencyLists[i]){
                this.adjacencyLists[i].add(j);
            }
        }
        //return this;
    }
}
class DisjointSet{
    private int[] set;

    public int[] getSet(){
        return set;
    }

    public DisjointSet(int numElements) {
        set = new int [numElements];
        for(int i = 0; i < set.length; i++){
            set[i] = -1;
        }
    }

    public void union(int root1, int root2) {
        if(set[root2] < set[root1]){
            set[root1] = root2;
        }
        else {
            if(set[root1] == set[root2]){
                set[root1]--;
            }
            set[root2] = root1;
        }
    }

    public int find(int x) {
        if(set[x] < 0){
            return x;
        }
        int next = x;
        while(set[next] > 0){
            next=set[next];
        }
        return next;
    }
}

