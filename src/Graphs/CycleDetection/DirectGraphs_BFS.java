package Graphs.CycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DirectGraphs_BFS {
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

        System.out.println(isCyclic(graph));
    }

    public static boolean isCyclic(ArrayList<ArrayList<Integer>> graph){
        int size = graph.size();
        int[] inDegree = new int[size];

        // Initialize inDegree array
        for(int i = 0; i < size; i++){
            for(int neighbor : graph.get(i)){
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }

        int count = 0;

        while(!queue.isEmpty()){
            int removedNode = queue.remove();
            count++;

            for(int neighbor : graph.get(removedNode)){
                inDegree[neighbor]--;

                if(inDegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }

        return count != size;
    }
}
