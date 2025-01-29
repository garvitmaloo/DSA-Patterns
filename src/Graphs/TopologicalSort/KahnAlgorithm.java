package Graphs.TopologicalSort;

import java.util.*;

public class KahnAlgorithm {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int numberOfNodes = 6;

        for(int i = 0; i < numberOfNodes; i++){
            graph.add(new ArrayList<>());
        }
        graph.get(2).add(3);
        graph.get(3).add(1);
        graph.get(4).add(0);
        graph.get(4).add(1);
        graph.get(5).add(0);
        graph.get(5).add(2);

        List<Integer> sort = topologicalSort(graph);
        System.out.println(sort.toString());
    }

    public static List<Integer> topologicalSort(ArrayList<ArrayList<Integer>> graph){
        int size = graph.size();
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[size];

        // Initialize inDegree array
        for(int i = 0; i < size; i++){
            for(int neighbor : graph.get(i)){
                inDegree[neighbor]++;
            }
        }

        // Add nodes with inDegree 0 to the queue
        for(int i = 0; i < inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            } 
        }

        List<Integer> topoSort = new ArrayList<>();
        int count = 0;

        // start processing the queue
        while(!queue.isEmpty()){
            int removedNode = queue.remove();
            topoSort.add(removedNode);
            count++;

            for(int neighbor : graph.get(removedNode)){
                inDegree[neighbor]--;

                if(inDegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }

        // Check if there is a cycle in the graph
        if(count != size){
            System.out.println("There is a cycle in the graph");
            return new ArrayList<>();
        }

        return topoSort;
    }
}
