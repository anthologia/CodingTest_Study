package solutions.그래프.영역구하기_2583.규연;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Sol_CalcArea {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Source {
        int M, N, K;
        int[][] boxPoints;

        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            boxPoints = new int[K][4];
            for (int i = 0; i < K; i++) {
                boxPoints[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }
    }

    static class Solution {
        int M, N;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int[][] board;
        List<Integer> result = new ArrayList<>();

        public void solution(int M, int N, int[][] points) {
            this.M = M;
            this.N = N;
            board = new int[M][N];
            for (int[] point : points) {
                checkBoxArea(point);
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0) result.add(bfs(new Point(j, i)));
                }
            }
            System.out.println(result.size());
            result.stream().sorted().forEach(s -> System.out.print(s + " "));
        }

        private void checkBoxArea(int[] point) {
            for (int i = point[1]; i < point[3]; i++) {
                for (int j = point[0]; j < point[2]; j++) {
                    board[i][j] = 1;
                }
            }
        }

        private int bfs(Point point) {
            Queue<Point> queue = new LinkedList<>();
            queue.offer(point);
            board[point.y][point.x] = -1;
            int size = 1;
            while (!queue.isEmpty()) {
                Point p = queue.poll();
                int x = p.x;
                int y = p.y;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isIn(nx, ny) && board[ny][nx] == 0) {
                        board[ny][nx] = -1;
                        size += 1;
                        queue.add(new Point(nx, ny));
                    }
                }
            }
            return size;
        }

        private boolean isIn(int x, int y) {
            return 0 <= x && x < N && 0 <= y && y < M;
        }
    }


    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        Solution solution = new Solution();
        solution.solution(s.M, s.N, s.boxPoints);
    }
}
