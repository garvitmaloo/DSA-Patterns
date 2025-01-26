package Graphs.BFSAndDFS;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        
    }

    public static int orangesRotting(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[n][m];
        int freshCount = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    q.add(new Pair(i, j, 2));
                    vis[i][j] = 2;
                }else{
                    vis[i][j] = 0;
                }

                if(grid[i][j] == 1){
                    freshCount++;
                }
            }
        }

        int rowOperations[] = {-1, 0, 1, 0};
        int colOperations[] = {0, 1, 0, -1};
        int rotCount = 0;
        int totalTime = 0;

        while(!q.isEmpty()){
            Pair removedPair = q.poll();
            int remRow = removedPair.row;
            int remCol = removedPair.col;
            int remTime = removedPair.time;
            totalTime = Math.max(totalTime, remTime);

            for(int i = 0; i < 4; i++){
                int neighborRow = remRow + rowOperations[i];
                int neighborCol = remCol + colOperations[i];

                if(neighborRow >= 0 && neighborRow < n && neighborCol >= 0 && neighborCol < m &&
                    vis[neighborRow][neighborCol] == 0 && grid[neighborRow][neighborCol] == 1
                ){
                    q.add(new Pair(neighborRow, neighborCol, remTime + 1));
                    vis[neighborRow][neighborCol] = 2;
                    rotCount++;
                }
            }
        }

        if(freshCount != rotCount){
            return -1;
        }

        return totalTime;
    }
}

class Pair {
    int row, col, time;

    public Pair(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}