package Graphs.TopologicalSort;

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort_DFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int numberOfNodes = 4;

        for(int i = 0; i < numberOfNodes; i++){
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(2);
        graph.get(2).add(3);

        int[] sort = topologicalSortDFS(graph);
        for(int i = 0; i < sort.length; i++){
            System.out.print(sort[i] + " ");
        }
    }

    public static int[] topologicalSortDFS(ArrayList<ArrayList<Integer>> graph){
        int size = graph.size();
        boolean[] visited = new boolean[size];
        Stack<Integer> stack = new Stack<>();
        int[] topoSort = new int[size];

        for(int i = 0; i < size; i++){
            if(visited[i] == false){
                dfs(graph, visited, i, stack);
            }
        }
        
        int counter = 0;
        while(!stack.isEmpty()){
            topoSort[counter] = stack.pop();
            counter++;
        }

        return topoSort;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int node, Stack<Integer> stack){
        visited[node] = true;

        for(int neighbor : graph.get(node)){
            if(visited[neighbor] == false){
                dfs(graph, visited, neighbor, stack);
            }
        }

        stack.push(node);
    }
}
