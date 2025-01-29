package Graphs.ShortestPathProblem;

import java.util.PriorityQueue;

public class PathWithMinEffort {
    public static void main(String[] args) {
        int[][] heights={{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(minimumEffort(heights));
    }

    // source will always be (0,0) and destination will always be (n-1, m-1)
    public static int minimumEffort(int[][] heights){
        int n = heights.length;
        int m = heights[0].length;

        int[][] differences = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                differences[i][j] = (int)(1e9);
            }
        }
        differences[0][0] = 0;

        PriorityQueue<Efforts> pq = new PriorityQueue<>();
        pq.add(new Efforts(0, 0, 0));

        int[] rowOps = {-1, 0, 1, 0};
        int[] colOps = {0, 1, 0, -1};

        while(!pq.isEmpty()){
            Efforts removed = pq.poll();
            int row = removed.row;
            int col = removed.col;
            int distance = removed.distance;

            if(row == n-1 && col == m-1) return distance;

            for(int i = 0; i < 4; i++){
                int newRow = row + rowOps[i];
                int newCol = col + colOps[i];
                
                if(newRow >= 0 && newCol >= 0 && newRow < n && newCol < m){
                    int distanceDiff = Math.abs(heights[row][col] - heights[newRow][newCol]);
                    int newEffort = Math.max(distance, distanceDiff);

                    if(newEffort < differences[newRow][newCol]){
                        differences[newRow][newCol] = newEffort;
                        pq.add(new Efforts(newEffort, newRow, newCol));
                    }
                }
            }
        }

        return 0;
    }
}

class Efforts implements Comparable<Efforts> {
    int distance;
    int row;
    int col;

    public Efforts(int distance, int row, int col){
        this.distance = distance;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Efforts o) {
        return this.distance - o.distance;
    }
}
