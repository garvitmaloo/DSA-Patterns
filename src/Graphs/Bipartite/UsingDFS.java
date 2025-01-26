package Graphs.Bipartite;

import java.util.ArrayList;
import java.util.Arrays;

public class UsingDFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i <= 5 ; i++){
            adjList.add(i, new ArrayList<>());
        }

        adjList.get(1).add(2);
        adjList.get(1).add(4);

        adjList.get(2).add(1);
        adjList.get(2).add(3);

        adjList.get(3).add(2);
        adjList.get(3).add(4);
        adjList.get(3).add(5);

        adjList.get(4).add(1);
        adjList.get(4).add(3);

        adjList.get(5).add(3);

        System.out.println(isBipartite(adjList));
    }

    public static boolean isBipartite(ArrayList<ArrayList<Integer>> graph){
        int size = graph.size();
        int[] colors = new int[size];

        Arrays.fill(colors , -1);

        for(int i = 0; i < size; i++){
            if(colors[i] == -1){
                if(isBipartite(graph, colors, 0, 1) == false) return false;
            }
        }

        return true;
    }

    private static boolean isBipartite(ArrayList<ArrayList<Integer>> graph, int[] colors, int currentColor, int currentNode){
        colors[currentNode] = currentColor;

        for(int neighbor : graph.get(currentNode)){
            if(colors[neighbor] != -1){
                return isBipartite(graph, colors, 1 - currentColor, neighbor);
            }else if(colors[neighbor] == colors[currentNode]) return false;
        }

        return true;
    }
}
