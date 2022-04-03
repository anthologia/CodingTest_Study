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
        int n;
        int m;
        int[][] board;
        boolean[][] visited;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        public void solution(int n, int m, int[][] board) {
            this.n = n;
            this.m = m;
            this.board = board;

            int cnt = 2;
            visited = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        bfs(board, j, i, cnt);
                        cnt++;
                    }
                }
            }
        }

        private void bfs(int[][] board, int x, int y, int idx) {
            Queue<int[]> q = new LinkedList<>();

            q.offer(new int[]{x, y});
            board[y][x] = idx;
            visited[y][x] = true;

            while (!q.isEmpty()) {
                int[] point = q.poll();
                int px = point[0];
                int py = point[1];
                for (int i = 0; i < 4; i++) {
                    int nx = px + dx[i];
                    int ny = py + dy[i];

                    if (isOut(nx, ny) || visited[y][x]) continue;

                    if (board[ny][nx] == 1) {
                        board[ny][nx] = idx;
                        visited[ny][nx] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }

        private boolean isOut(int x, int y) {
            return x < 0 || y < 0 || x > m - 1 || y > n - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.N, s.M, s.board);

    }
}
