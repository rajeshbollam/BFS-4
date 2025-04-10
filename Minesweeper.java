//The approach here is to use BFS to traverse the board. At each index, we check if there are any mines as neighbors, if we have any, we update the count of the mines.
//if there are no mines surrounded, then we change it to 'B'. In the end, we return the board.
//Time Complexity: O(m*n) where m and n are the rows and columns of the board
//Space Complexity: O(m*n)
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = new int[][] {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
        Queue<int []> q = new LinkedList<>();
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], dirs);
            if(count == 0){
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr>=0 && nr < board.length && nc >=0 && nc < board[0].length && board[nr][nc] == 'E'){
                        q.add(new int[]{nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char)(count+'0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j, int[][] dirs){
        int count = 0;
        for(int[] dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr>=0 && nr < board.length && nc >=0 && nc < board[0].length && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}