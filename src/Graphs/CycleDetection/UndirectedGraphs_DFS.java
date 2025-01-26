package Graphs.CycleDetection;

import java.util.ArrayList;

public class UndirectedGraphs_DFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i <= 7; i++){
            adjList.add(new ArrayList<>());
        }

        adjList.get(1).add(2);
        adjList.get(1).add(3);

        adjList.get(2).add(1);
        adjList.get(2).add(5);

        adjList.get(3).add(1);
        adjList.get(3).add(4);
        adjList.get(3).add(6);

        adjList.get(4).add(3);

        adjList.get(5).add(2);
        adjList.get(5).add(7);

        adjList.get(6).add(3);
        adjList.get(6).add(7);

        adjList.get(7).add(5);
        adjList.get(7).add(6);

        System.out.println(hasCycle(adjList));
    }

    public static boolean hasCycle(ArrayList<ArrayList<Integer>> graph){
        int size = graph.size();
        boolean[] visited = new boolean[size];

        for(int i = 1; i <= size; i++){
            if(visited[i] == false){
                if(hasCycleHelper(graph, visited, i, -1)) return true;
            }
        }

        return false;
    }

    private static boolean hasCycleHelper(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int currentNode, int parentNode){
        visited[currentNode] = true;

        for(Integer neighbor : graph.get(currentNode)){
            if(visited[neighbor] == false){
                return hasCycleHelper(graph, visited, neighbor, currentNode);
            }

            else if(neighbor != parentNode){
                return true;
            }
        }

        return false;
    }
}
