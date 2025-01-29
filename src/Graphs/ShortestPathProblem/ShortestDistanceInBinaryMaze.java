package Graphs.ShortestPathProblem;

import java.util.LinkedList;
import java.util.Queue;

class Tuple{
    int distance;
    int row;
    int col;

    public Tuple(int distance, int row, int col){
        this.distance = distance;
        this.row = row;
        this.col = col;
    }
}

public class ShortestDistanceInBinaryMaze {
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 0, 1}};
            int[] source = {0,1};
            int[] destination = {2,2};

        int ans = shortestPath(grid, source, destination);
        System.out.println(ans);
    }

    public static int shortestPath(int[][] grid, int[] source, int[] destination){
        if(source[0] == destination[0] && 
           source[1] == destination[1]) return 0;

        int n = grid.length;
        int m = grid[0].length;

        Queue<Tuple> queue = new LinkedList<>();

        int[][] distances = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                distances[i][j] = (int)(1e9);
            }
        }
        distances[source[0]][source[1]] = 0;

        int[] rowOps = {-1, 0, 1, 0};
        int[] colOps = {0, 1, 0, -1};

        queue.add(new Tuple(0, source[0], source[1]));

        while(!queue.isEmpty()){
            Tuple removedTuple = queue.remove();
            int removedCellDistance = removedTuple.distance;
            int removedCellRow = removedTuple.row;
            int removedCellCol = removedTuple.col;

            for(int i = 0; i < 4; i++){
                int newRow = removedCellRow + rowOps[i];
                int newCol = removedCellCol + colOps[i];

                if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1
                && removedCellDistance + 1 < distances[newRow][newCol]){
                    distances[newRow][newCol] = removedCellDistance + 1;
                    if(newRow == destination[0] && newCol == destination[1]){
                        return removedCellDistance + 1;
                    }
                    queue.add(new Tuple(removedCellDistance + 1, newRow, newCol));
                }
            }
        }

        return -1;
    }
}
