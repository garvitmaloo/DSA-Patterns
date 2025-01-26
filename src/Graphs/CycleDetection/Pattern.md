# Cycle detection in undirected graph using BFS

Detecting a cycle in an undirected graph using BFS is an interesting problem. Here, I'll walk you through the approach and then provide you with the corresponding Java code.

When performing BFS, we will track the parent of each node. The parent helps ensure that we don’t consider the edge leading back to the parent node as part of a cycle.

## Step by step approach:

1. Initialize a visited array to keep track of visited nodes.
2. Iterate over all nodes in the graph:
   - If the node has not been visited, start BFS from this node.
3. For each node in BFS:
   - Mark it as visited.
   - For each of its neighbors:
     - If the neighbor has not been visited, continue BFS by adding it to the queue.
     - If the neighbor is already visited and is not the parent of the current node, a cycle is detected.
4. If BFS finishes without detecting a cycle, continue with other nodes until all are processed.
5. If no cycle is found, the graph is acyclic.

Check code in the file named UndirectedGraph_BFS.java

## Time complexity:

- BFS Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
  - Each node and edge is processed once during the BFS traversal.
- Space Complexity: O(V) for storing the adjacency list and the visited[] and parent[] arrays.

# Cycle detection in directed graph using DFS

The intuition is that we start from a source and go in-depth, and reach any node that has been previously visited in the past; it means there's a cycle.

In an undirected graph, when we perform DFS, we explore as deeply as possible along each branch before backtracking. The challenge in detecting cycles in undirected graphs using DFS is that a node may be revisited via the edge leading to its parent node. To avoid this false cycle detection, we need to differentiate between revisiting the parent node and revisiting a node that is part of a cycle.

# Cycle detection in directed graph using DFS

## Why the above approach wont work for directed graphs?

Simple answer: Edges have directions. You might come to a node that has been visited before and is not the parent node as well (the above algorithm will mark it as a cycle), but it might not be a part of cycle.

In directed graphs, we say a cycle exists if we come to the same node from the same path. If we are coming to the same node from a different path, we cannot say it will certainly have a cycle.

In such scenarios, we use another array, called path array whose size is same as the size of the graph.

The algorithm is as follows:

1. Initialize a visited array to keep track of visited nodes.
2. Initialize a path array to keep track of nodes in the current path.
3. Iterate over all nodes in the graph:

   - If the node has not been visited, start DFS from this node.
   - If DFS returns true, a cycle is detected.
     For each node in DFS:
   - Mark it as visited.
   - Add it to the path array.

   - For each of its neighbors:
   - If the neighbor has not been visited, continue DFS by adding
   - If the neighbor is already visited and is in the path array, a cycle is detected.
     If DFS finishes without detecting a cycle, continue with other nodes until all are processed.
     If no cycle is found, the graph is acyclic.

NOTE: Here backtracking is involved. When the recursion is returning, we need to set the path visited array back to its original value.

We need not to continue recursion if we have visited the array but it has not been path visited.
