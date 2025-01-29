package Graphs.ShortestPathProblem;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    public static void main(String[] args) {
        int numberOfNodes = 6, source = 0;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(){
            {
                add(new ArrayList<>(Arrays.asList(3,2,6)));
                add(new ArrayList<>(Arrays.asList(5,3,1)));
                add(new ArrayList<>(Arrays.asList(0,1,5)));
                add(new ArrayList<>(Arrays.asList(1,5,-3)));
                add(new ArrayList<>(Arrays.asList(1,2,-2)));
                add(new ArrayList<>(Arrays.asList(3,4,-2)));
                add(new ArrayList<>(Arrays.asList(2,4,3)));
            }
        };
        System.out.println(Arrays.toString(bellmanFord(graph, source, numberOfNodes)));
    }

    public static int[] bellmanFord(ArrayList<ArrayList<Integer>> graph, int source, int numberOfNodes){
        int[] distances = new int[numberOfNodes];

        for(int i = 0; i < numberOfNodes; i++){
            distances[i] = (int)(1e9);
        }
        distances[source] = 0;

        for(int i = 0; i < numberOfNodes - 1; i++){
            for(ArrayList<Integer> edge : graph){
                int u = edge.get(0);
                int v = edge.get(1);
                int w = edge.get(2);

                if(distances[u] != (int)(1e9) && distances[u] + w < distances[v]){
                    distances[v] = distances[u] + w;
                }
            }
        }

        for(ArrayList<Integer> edge : graph){
            int u = edge.get(0);
            int v = edge.get(1);
            int w = edge.get(2);

            if(distances[u] != (int)(1e9) && distances[u] + w < distances[v]){
                return new int[]{-1};
            }
        }

        return distances;
    }
}
