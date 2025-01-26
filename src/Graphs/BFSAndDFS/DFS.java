package Graphs.BFSAndDFS;

import java.util.ArrayList;

public class DFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int numberOfNodes = 8;

        for(int i = 0; i <= numberOfNodes; i++){
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(1);
        graph.get(2).add(5);
        graph.get(2).add(6);
        graph.get(3).add(1);
        graph.get(3).add(4);
        graph.get(3).add(7);
        graph.get(4).add(3);
        graph.get(4).add(8);
        graph.get(5).add(2);
        graph.get(6).add(2);
        graph.get(7).add(3);
        graph.get(7).add(8);
        graph.get(8).add(4);
        graph.get(8).add(7);

        boolean[] visited = new boolean[numberOfNodes + 1];
        visited[0] = true;

        ArrayList<Integer> answerList = new ArrayList<>();

        dfs(graph, answerList, visited, 1);

        System.out.println(answerList.toString());
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> answerList, boolean visited[], Integer currentNode){
        visited[currentNode] = true;
        answerList.add(currentNode);

        for(Integer neighbor : graph.get(currentNode)){
            if(visited[neighbor] == false){
                dfs(graph, answerList, visited, neighbor);
            }
        }
    }
}