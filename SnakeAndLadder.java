//The idea here is to check minimum between all the possible paths to the end of the board. so we check all the possibilities and we traverse using BFS.
//To optimize, we only go to the children of the numbers which are not at the current level.
//We mark the one's visited as -2
//To solve this, we flatten the matrix to a 1-d array
//Time Complexity: O(n^2) where n is the side of the square
//Space Complexity: O(n^2)
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        boolean dir = true;
        int r = n-1; int c = 0;
        int idx = 0; //idx is the index of arr
        while(idx < n*n){
            if(board[r][c] == -1){
                arr[idx] = board[r][c];
            } else {
                arr[idx] = board[r][c] - 1;
            }
            idx++;
            //r c is used..move to next r, c
            if(dir){
                //left to right
                c++;
                if(c == n){
                    dir = false;
                    r--;
                    c--;
                }
            } else {
                c--;
                if(c==-1){
                    dir = true;
                    r--;
                    c++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                int curr = q.poll();
                if(curr == n*n - 1) return moves;
                for(int j = 1; j<=6; j++){
                    int newIdx = curr + j;
                    if(newIdx > n*n - 1) break;
                    if(arr[newIdx] == -1){
                        q.add(newIdx);
                    } else{
                        if(arr[newIdx] != -2){
                            q.add(arr[newIdx]);
                        }                        
                    }
                    arr[newIdx] = -2;
                }
            }
            moves++;
        }
        return -1;
    }
}
