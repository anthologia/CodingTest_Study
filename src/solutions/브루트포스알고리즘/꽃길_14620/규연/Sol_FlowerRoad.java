package solutions.브루트포스알고리즘.꽃길_14620.규연;

import algorithm.b2583;
import solutions.그래프.영역구하기_2583.규연.Sol_CalcArea;;

import java.util.*;
import java.io.*;

public class Sol_FlowerRoad {

    static class Source {
        int N;
        int[][] board;

        public Source(BufferedReader br) throws IOException {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }
    }

    static class Solution {
        int N;
        int[][] board;
        boolean[][] visited;
        int ans;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        public void solution(int N, int[][] board) {
            this.N = N;
            this.board = board;
            ans = Integer.MAX_VALUE;
            visited = new boolean[N][N];

            dfs(0, 0);
            System.out.println(ans);
        }

        private void dfs(int seedCount, int price) {
            if (seedCount == 3) { // 3개 심은 경우
                ans = Math.min(ans, price); // 최저가 계산
                return;
            }

            /**
             * 테두리 부분은 제외 -> 씨앗 심기 불가능
             */
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < N - 1; j++) {
                    // 방문 하지 않았거나, 가능한 위치인 경우
                    if (!visited[i][j] && isPossible(j, i)) {
                        int sum = calcPrice(j, i); // 해당 위치 땅값 계산 & 방문 처리
                        dfs(seedCount + 1, sum + price); // 다음 씨앗 위치 탐색
                        rollBack(j, i); // 이전 상태로 백트래킹 (방문 처리 X)
                    }
                }
            }
        }

        private Boolean isPossible(int x, int y) {
            for (int i = 0; i < 4; i++) {
                if (visited[y + dy[i]][x + dx[i]]) return false;
            }
            return true;
        }

        private int calcPrice(int x, int y) {
            int price = board[y][x];
            visited[y][x] = true;
            for (int i = 0; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                price += board[cy][cx];
                visited[cy][cx] = true;
            }
            return price;
        }

        private void rollBack(int x, int y) {
            visited[y][x] = false;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                visited[ny][nx] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.N, s.board);
    }
}
