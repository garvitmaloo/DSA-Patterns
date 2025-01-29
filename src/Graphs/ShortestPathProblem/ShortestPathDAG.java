package Graphs.ShortestPathProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ShortestPathDAG {
    public static void main(String[] args) {
        // The only problem in this code is that the source node has to be the first node in topological sequence. Source node cannot be any random node
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        int numberOfNodes = 6;
        int numberOfEdges = 7;
        int sourceNode = 0;
        int[][] edges = {{0,1,2},{0,4,1},{4,5,4},{4,2,2},{1,2,3},{2,3,6},{5,3,1}};

        for(int i = 0; i < numberOfNodes; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numberOfEdges; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            graph.get(u).add(new Pair(v, wt));
        }

        int[] distances = shortestPaths(graph, sourceNode);
        for(int dist : distances){
            System.out.print(dist + " ");
        }
    }

    public static int[] shortestPaths(ArrayList<ArrayList<Pair>> graph, int sourceNode){
        int size = graph.size();
        boolean[] visited = new boolean[size];
        Stack<Integer> stack = new Stack<>();
        int[] topSort = new int[size];

        for(int i = 0; i < size; i++){
            if(visited[i] == false){
                dfs(graph, i, visited, stack);
            }
        }

        int count = 0;
        while(!stack.isEmpty()){
            topSort[count] = stack.pop();
            count++;
        }

        int[] distances = new int[size];
        for(int i = 0; i < distances.length; i++){
            if(topSort[i] == sourceNode){
                distances[i] = 0;
            }else{
                distances[i] = (int)(1e9);
            }
        }

        for(int element : topSort){
            edgeRelaxation(graph, distances, element);
        }

        return distances;
    }

    private static void dfs(ArrayList<ArrayList<Pair>> graph, int node, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;

        for(Pair neighbor : graph.get(node)){
            if(visited[neighbor.destNode] == false){
                dfs(graph, neighbor.destNode, visited, stack);
            }
        }

        stack.push(node);
    }

    private static void edgeRelaxation(ArrayList<ArrayList<Pair>> graph, int[] distances, int currentNode){
        int currentDistanceFromSource = distances[currentNode];
        Arrays.toString(distances);

        for(Pair neighbor : graph.get(currentNode)){
            int currentToNeighborDist = neighbor.edgeWeight;
            int sourceToNeighborDistance = currentToNeighborDist + currentDistanceFromSource;

            distances[neighbor.destNode] = Math.min(distances[neighbor.destNode], sourceToNeighborDistance);
        }
    }
}

class Pair {
    int destNode;
    int edgeWeight;

    public Pair(int node, int weight){
        this.destNode = node;
        this.edgeWeight = weight;
    }
}