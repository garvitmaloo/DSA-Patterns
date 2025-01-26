package Graphs.BFSAndDFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int numberOfNodes = 5;

        for(int i = 0; i <= numberOfNodes; i++){
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);
        graph.get(1).add(5);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(5);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(3).add(5);
        graph.get(4).add(3);
        graph.get(4).add(5);
        graph.get(5).add(1);
        graph.get(5).add(2);
        graph.get(5).add(3);
        graph.get(5).add(4);

        System.out.println(bfs(graph).toString());
    }

    public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> graph){
        int numberOfNodes = graph.size() - 1;
        ArrayList<Integer> answerList = new ArrayList<>();
        boolean[] visited = new boolean[numberOfNodes + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){
            Integer removed = queue.poll();
            answerList.add(removed);

            for(Integer neighbor : graph.get(removed)){
                if(visited[neighbor] == false){
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        return answerList;
    }
}
