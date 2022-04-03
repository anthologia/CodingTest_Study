package solutions.그래프.퍼즐_1525.규연;
import java.util.*;
import java.io.*;

public class Sol_Puzzle {
    static class Source {
        int[][] board;
        public Source(BufferedReader br) throws IOException {
            for (int i = 0; i < 3; i++) {
                board[i] = convertInputToArray(br);
            }
        }

        private int[] convertInputToArray(BufferedReader br) throws IOException {
            return Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    static class Solution {
        String result = "123456780";
        public void solution(int[][] board) {

        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.board);

    }
}
