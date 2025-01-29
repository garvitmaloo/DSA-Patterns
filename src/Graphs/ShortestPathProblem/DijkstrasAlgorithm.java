package Graphs.ShortestPathProblem;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class DijkstrasAlgorithm {
    static class Edge {
        int source;
        int destination;
        int edgeWeight;

        public Edge(int s, int d, int w){
            this.source = s;
            this.destination = d;
            this.edgeWeight = w;
        }
    }

    static class Pair implements Comparable<Pair>{
        int node;
        int distance;

        public Pair(int node, int distance){
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pair o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        int numberOfNodes = 6;

        for(int i = 0; i < numberOfNodes; i++){
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(0, 1, 2));
        graph.get(0).add(new Edge(0, 2, 4));

        graph.get(1).add(new Edge(1,2,1));
        graph.get(1).add(new Edge(1,3,7));

        graph.get(2).add(new Edge(2,4,3));

        graph.get(3).add(new Edge(3, 5, 1));

        graph.get(4).add(new Edge(4, 3, 2));
        graph.get(4).add(new Edge(4, 5, 5));

        int[] distances = shortestPaths(graph, 0);
        for(int distance : distances){
            System.out.print(distance + " ");
        }
    }

    public static int[] shortestPaths(ArrayList<ArrayList<Edge>> graph, int sourceNode){
        int size = graph.size();
        boolean[] visited = new boolean[size];
        int[] distances = new int[size];

        for(int i = 0; i < size; i++){
            distances[i] = (int)(1e9);
        }
        distances[sourceNode] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(sourceNode, 0));

        while(!pq.isEmpty()){
            Pair removedNode = pq.poll();
            int currentNode = removedNode.node;

            if(visited[currentNode] == false){
                visited[currentNode] = true;

                for(Edge neighbor : graph.get(currentNode)){
                    int u = neighbor.source;
                    int v = neighbor.destination;
                    int w = neighbor.edgeWeight;

                    if(distances[u] + w < distances[v]){
                        distances[v] = distances[u] + w;
                        pq.add(new Pair(v, distances[v]));
                    }
                }
            }
        }

        return distances;
    }
}
