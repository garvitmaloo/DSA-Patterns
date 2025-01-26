package Graphs.Bipartite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UsingBFS {
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
        int[] color = new int[size];

        Arrays.fill(color, -1);

        for(int i = 0; i < size; i++){
            if(color[i] == -1){
                if(!isBipartite(graph, color, i)) return false;
            }
        }

        return true;
    }

    private static boolean isBipartite(ArrayList<ArrayList<Integer>> graph, int[] colors, int startNode){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        colors[startNode] = 0;

        while(!queue.isEmpty()){
            int currentNode = queue.poll();

            for(int neighbor : graph.get(currentNode)){
                if(colors[currentNode] == -1){
                    colors[neighbor] = 1 - colors[currentNode];
                    queue.add(neighbor);
                }else if(colors[currentNode] == colors[neighbor]){
                    return false;
                }
            }
        }

        return true;
    }
}
