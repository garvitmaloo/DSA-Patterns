package Graphs.BFSAndDFS;

import java.util.ArrayList;

public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(isConnected));
    }

    public static int findCircleNum(int[][] isConnected){
        // Convert into adjacency list
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < isConnected.length; i++){
            graph.add(new ArrayList<>()); // 0-indexed
        }

        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < isConnected.length; j++){
                if(isConnected[i][j] == 1 && i != j){
                    graph.get(i).add(j);
                }
            }
        }

        int numberOfNodes = isConnected.length;
        boolean[] visited = new boolean[numberOfNodes]; // 0-indexed

        int numberOfProvinces = 0;

        for(int i = 0; i < numberOfNodes; i++){
            if(visited[i] == false){
                // call dfs
                dfs(graph, i, visited);
                numberOfProvinces++;
            }
        }

        return numberOfProvinces;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int currentNode, boolean[] visited){
        visited[currentNode] = true;

        for(int neighbor : graph.get(currentNode)){
            if(visited[neighbor] == false){
                dfs(graph, neighbor, visited);
            }
        }
    }
}
