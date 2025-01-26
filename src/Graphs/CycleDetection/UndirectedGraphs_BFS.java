package Graphs.CycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraphs_BFS {
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
        int numberOfNodes = graph.size();
        boolean[] visited = new boolean[numberOfNodes];
        int startNode = 1;

        for(int i = startNode; i <= numberOfNodes; i++){
            if(visited[i] == false){
                if(hasCycleHelper(graph, visited, i)) return true;
            }
        }

        return false;
    }

    private static boolean hasCycleHelper(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int startNode){
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startNode, -1));
        visited[startNode] = true;

        while(!queue.isEmpty()){
            Pair removedPair = queue.remove();
            int removedNode = removedPair.currentNode;
            int parentNode = removedPair.parentNode;

            for(Integer neighbor : graph.get(removedNode)){
                if(visited[neighbor] == false){
                    queue.add(new Pair(neighbor, removedNode));
                    visited[neighbor] = true;
                }

                // Node is visited. Check if parent node is same as neighbor node
                else if(parentNode != neighbor) return true;
            }
        }

        return false;
    }
}

class Pair {
    int currentNode; 
    int parentNode;

    public Pair(int currentNode, int parentNode){
        this.currentNode = currentNode;
        this.parentNode = parentNode;
    }
}