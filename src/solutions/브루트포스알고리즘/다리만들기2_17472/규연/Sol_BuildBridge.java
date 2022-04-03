package solutions.브루트포스알고리즘.다리만들기2_17472.규연;

import java.util.*;
import java.io.*;
public class Sol_BuildBridge {
    static class Source {
        int N;
        int M;
        int[][] board;
        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
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

        public void solution(int n, int m, int[][] board) {

        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.N, s.M, s.board);

    }
}
