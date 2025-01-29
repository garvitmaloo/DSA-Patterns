# Finding Shortest Path in a DAG

This is the algorithm that we follow -

1. Do a topological sort on the graph. You can do it using any way - BFS or DFS
2. Start taking out the nodes from the stack one by one and perform relaxation of the edges.
   - In order to perform relaxation of each node, make a distances array of size same as that of number of edges.
   - Initialize the array with MAX_INT, except the source node. The source node should have 0 at its index.
   - Pop the stack and find the minimum distance required to reach to this popped node from the source node. If the value at this popped node's index in the distances array is MAX_INT, set it directly to the minimum distance calculated. If it's not MAX_INT, then take the minimum of existing value in the array and the new min distance calculated.

NOTE: Popping out elements from the stack will give the topological sequence. So, you can also perform relaxation of this sequence.

# Dijkstra's Algorithm

Dijkstra's algorithm is used to find the shortest path between two nodes (or vertices) in a graph, where all edges have non-negative weights. It finds the shortest path from a starting node to all other nodes in the graph.

## Algorithm steps -

1. Define a Priority Queue which would contain pairs of the type {dist, node}, where ‘dist’ indicates the currently updated value of the shortest distance from the source to the ‘node’. Check out code to know how to make priority queue in the right way.
2. Define a distances array initialized with a large integer number (except source node, distances [sourceNode] = 0) at the start indicating that the nodes are unvisited initially. This array stores the shortest distances to all the nodes from the source node.
3. We will also need a boolean array of same size as that of the number of nodes.
4. We push the source node to the queue along with its distance which is 0. Be mindful of what source node is being passed, especially if the graph is directed.
5. Start removing the elements from the PQ until its empty. For every removed element, perform relaxation for all its neighbors. Each time you perform a relaxation, add that neighbor in the priority queue and update the distances array for that neighbors distance.

NOTE: Dijkstra's algorithm works only for non-negative weights. It is because at every traversal, the distance will reduce even more (due to negative edge weight) and algorithm will never stop. It will act like an infinite loop.

## Why priority queue instead of a normal queue?

It is because we want to find the minimum distance node from the source node. Priority queue will always give us the minimum distance node. Had we used a normal queue, we would have to traverse the whole queue to find the minimum distance node. So, we would have traversed unnecessary nodes which would have made the algorithm take more time.

## Time and space complexity

Time complexity: O(E log V)
Space complexity: O(V)

NOTE: The only reason why we are using a priority queue is because to take the minimum distance first. If a scenario comes up where the distances are increasing consistently in a way that we will always get the minimum distance first, we can use a normal queue instead of a priority queue. This will reduce the time complexity from O(E log V) to O(E) by removing the need to sort the queue and put the the smallest distance first. One such scenario is the shortest distance in a binary maze. Check out the solution of that question.

**Pattern:** If a question involves a grid and finding shortest path or path with minimum cost or energy and you are given a source and a destination, you can try out Dijkstra's algorithm.

# Bellman Ford algorithm

This algorithm is used in cases where the Dijkstra's algorithm fails, that is when a graph has negative weights or negative weight cycle. It is also used to find the shortest path in a directed graph. In cases of an undirected graph, we will have to convert it into a directed graph as BF algorithm works only for directed graphs.

As per Bellman Ford algorithm, we will have to relax all the edges V-1 times, where V is the number of vertices in the graph. As you know, relaxation means -
For any two node u and v such that u ---> v and has a weight w, we can say that -

```
if(distanceFromSource[u] + w < distanceFromSource[v]){
    distanceFromSource[v] = distanceFromSource[u] + w;
}
```

## Why n - 1 iterations?

It is because the shortest we get from the first iteration is used in the next iteration to update the shortest path of the next node. Because there are N nodes and we already know the shortest path of the source node, that is, 0, so we just perform relaxation for the rest of the nodes, that is n - 1 times.

N - 1 is taken for the worst case scenario, that is, the source node is placed at the bottom in the list of the edges.

## How to detect negative weight cycle using this algorithm?

If there is no negative weight cycle, then the distances array containing the negative weights will be updated to the final answer in exactly n - 1 iterations. If we are able to perform Nth iteration and the distances array is getting updated, then we can say that there is a negative weight cycle.

It can be said so because in the Nth iteration, the distance got reduced even more (due to negative weight cycle) which kept the loop running.

## Time and space complexity

Time complexity: O(V \* E)
Space complexity: O(V)

# Floyd Warshall algorithm

This algorithm is a multi-source shortest path algorithm. It is used to find the shortest path between all the pairs of vertices in a graph. It is also used to detect negative weight cycles.
