package Graphs.BFSAndDFS;

import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        int[][] result = updateMatrix(grid);

        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] updateMatrix(int[][] grid){
        int m = grid.length;
        int n =  grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int[][] distance = new int[m][n];
        Queue<Node> q = new LinkedList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    q.add(new Node(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[] rowOps = {-1, 0, 1, 0};
        int[] colOps = {0, 1, 0, -1};

        while(!q.isEmpty()){
            Node removedPair = q.poll();
            int removedRow = removedPair.row;
            int removedCol = removedPair.col;
            int steps = removedPair.distance;

            distance[removedRow][removedCol] = steps;

            for(int i = 0; i < 4; i++){
                int neighborRow = removedRow + rowOps[i];
                int neighborCol = removedCol + colOps[i];

                if(neighborRow >= 0 && neighborRow < m && neighborCol >= 0 && neighborCol < n
                && visited[neighborRow][neighborCol] == false){
                        visited[neighborRow][neighborCol] = true;
                        q.add(new Node(neighborRow, neighborCol, steps + 1));
                }
            }
        }

        return distance;
    }
}


class Node {
    int row;
    int col;
    int distance;

    public Node(int r, int c, int d){
        this.row = r;
        this.col = c;
        this.distance = d;
    }
}