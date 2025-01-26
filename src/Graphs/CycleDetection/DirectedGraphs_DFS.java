package Graphs.CycleDetection;

import java.util.ArrayList;

public class DirectedGraphs_DFS {
    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        System.out.println(isCyclic(adj));
    }

    public static boolean isCyclic(ArrayList<ArrayList<Integer>> graph){
        int size = graph.size();
        boolean[] visited = new boolean[size];
        boolean[] pathVisited = new boolean[size];

        for(int i = 0; i < size; i++){
            if(visited[i] == false){
                if(dfs(graph, i, visited, pathVisited) == true) return true;
            }
        }

        return false;
    }

    private static boolean dfs(ArrayList<ArrayList<Integer>> graph, int node, boolean[] visited, boolean[] pathVisited){
        visited[node] = true;
        pathVisited[node] = true;

        for(int neighbor : graph.get(node)){
            if(visited[neighbor] == false){
                if(dfs(graph, neighbor, visited, pathVisited) == true) return true;
            }else if(pathVisited[neighbor] == true){
                return true;
            }
        }

        pathVisited[node] = false;
        return false;
    }
}
