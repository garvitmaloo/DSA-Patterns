package Graphs.BFSAndDFS;

public class FloodFill {
    public static void main(String[] args) {
        int[][] image = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr = 1;
        int sc = 1;
        int newColor = 2;
        int[][] answer = floodFill(image, sr, sc, newColor);
        for(int i = 0; i < answer.length; i++){
            for(int j = 0; j < answer[0].length; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor){
        int answer[][] = image;
        int[] nearbyRows = {-1, 0, 1, 0};
        int[] nearbyCols = {0, 1, 0, -1};
        int initialColor = image[sr][sc];

        dfs(image, sr, sc, newColor, answer, nearbyRows, nearbyCols, initialColor);

        return answer;
    }

    private static void dfs(int[][] image, int sr, int sc, int newColor, int[][] answer, int[] nearbyRows, int[] nearbyCols, int initialColor){
        answer[sr][sc] = newColor;
        int totalRows = image.length;
        int totalCols = image[0].length;

        for(int i = 0; i < 4; i++){
            int neighborRow = sr + nearbyRows[i];
            int neighborCol = sc + nearbyCols[i];

            // check if the neighbor is valid and if it is the same color as the initial color
            if(neighborRow >= 0 && neighborCol >= 0 && neighborRow < totalRows && neighborCol < totalCols
            && image[neighborRow][neighborCol] == initialColor && answer[neighborRow][neighborCol] != newColor){
                dfs(image, neighborRow, neighborCol, newColor, answer, nearbyRows, nearbyCols, initialColor);
            }
        }
    }
}
