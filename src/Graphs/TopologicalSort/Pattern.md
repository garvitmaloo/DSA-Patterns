# What is Topological Sort?

It is a linear ordering of vertices such that for every directed edge (u, v), vertex u comes before v in the ordering. It is valid only for Directed Acyclic Graphs (DAGs).

For example, consider these edges of a graph -

A ---> B
B ---> C
A ---> C and
C ---> D

The topological ordering of the above graph is - A, B, C, D

Note that in this sequence A always comes before B and C and B always comes before C and C always comes before D.

# Practical use cases of Topological Sort

1. You have a set of tasks, some of which depend on others being completed before they can begin. Topological sorting helps to schedule these tasks in an order that respects the dependencies. Build systems (e.g., Make, Gradle, or Maven) use topological sorting to determine the order in which source files should be compiled, considering the dependencies between them.
2. Package managers (e.g., npm, pip, apt) must resolve dependencies between software packages before installing them. If one package depends on another, it must be installed first.
3. When exporting or importing data that has foreign key constraints between tables, it is crucial to insert or delete records in the right order to maintain referential integrity.

# Why is topological sort valid only for DAGs?

It is because if a graph is undirected, then any node can come before or after any other node in the sequence. Hence, we cannot give a linear ordering.

Similarly, if a graph is cyclic, then there will be a cycle in the sequence. Like 1 2 3 and again 1.

# Topological Sort using DFS

1. Use the DFS approach, along with a stack data structure.
2. The basic idea is to first add all the neighbors (dependencies) of a node in the stack and then the node itself in the stack. This is because once we start popping elements out of the stack, the least dependent node will come out first in the sequence.

The code is given in the file named TopologicalSort_DFS.java in the same directory.

To check the validity of the topological sequence, list down all the edges and check if they are in the order.

# Topological Sort using BFS (Kahn's Algorithm)

Kahn's Algorithm is a topological sorting algorithm used to order the vertices of a Directed Acyclic Graph (DAG) in a linear sequence. It is particularly useful for scheduling tasks, resolving dependencies, and ensuring that prerequisites are met before moving forward.

Kahn’s algorithm also serves to detect cycles in a directed acyclic graph. If there is any cycle in the graph, topological sorting is not possible, so if the algorithm fails to sort the graph, it indicates the presence of a cycle.

## **Algorithm Steps:**

Here’s how Kahn’s algorithm works:

1. **Input**: A Directed Acyclic Graph (DAG), represented by a set of vertices and directed edges.

2. **Process**:

   - First, calculate the **in-degree** (the number of incoming edges) for each vertex.
   - Initialize a queue (or list) and add all vertices with **zero in-degree** (i.e., those with no prerequisites or dependencies).

3. **Processing**:

   - While the queue is not empty:
     1. Dequeue a vertex `v`.
     2. Add `v` to the topological order.
     3. For each outgoing edge from `v` (i.e., for every vertex `u` such that there is an edge `v → u`):
        - Decrease the in-degree of `u`.
        - If the in-degree of `u` becomes zero, enqueue `u`.

4. **Check for Cycles**:
   - If at the end, the topological order doesn’t contain all vertices (i.e., the graph has more vertices than the number of elements in the topological order), then the graph has a cycle, and topological sorting is not possible.

## **Thought process behind the algorithm:**

1. Why calculate in-degree?

   - In-degree represents the number of prerequisites or dependencies a vertex has. By calculating in-degree, we can identify vertices that have no prerequisites, which are the ones that can be processed first.

2. Processing the queue:

   - Only those vertices are added in the queue which have 0 in-degree. It signifies that they have no prerequisites and can be processed first.
   - The algorithm proceeds by dequeueing a vertex from the front of the queue, processing it, and then reducing the in-degree of all its neighbors (the vertices that are dependent on it).
   - Each time you process a vertex, you reduce the in-degree of its neighbors because you’ve "done" one of their prerequisites. If any of these neighbors now have an in-degree of 0, it means they are now free to be processed, so they are added to the queue.

3. Cycle detection:
   - After processing, if the number of vertices processed is less than the total number of vertices, then this means some vertices were never added to the topological order.
   - This indicates the presence of a cycle in the graph, as there is no way to satisfy all prerequisites.

## Time and space complexity:

- Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
- Space Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
