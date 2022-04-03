package solutions.브루트포스알고리즘.정사각형_9015.규연;

import java.util.*;
import java.io.*;

public class Sol_Square {
    static class Source {
        int N;
        List<Point> points;

        public Source(BufferedReader br) throws IOException {
            N = Integer.parseInt(br.readLine());
            points = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                points.add(new Point(convertInputToArray(br)));
            }
        }

        private int[] convertInputToArray(BufferedReader br) throws IOException {
            return Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int[] info) {
            this.x = info[0];
            this.y = info[1];
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }

    static class Solution {
        List<List<Point>> combs;
        int ans;
        public void solution(int n, List<Point> points) {
            combs = new ArrayList<>();
            int ans = 0;
            combination(points, new boolean[n], 0, n, 2);

            System.out.println(ans);
        }

        private void combination(List<Point> points, boolean[] visited, int start, int n, int r) {
            if (r == 0) {
                List<Point> temp = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if (visited[i]) temp.add(points.get(i));
                }
                Point p1 = temp.get(0);
                Point p2 = temp.get(1);
                int dx = p2.x - p1.x;
                int dy = p2.y - p1.y;
                Point p3 = new Point(p2.x - dy, p2.y + dx);
                Point p4 = new Point(p1.x - dy, p1.y + dx);

                if (points.contains(p3) && points.contains(p4)) {
                    ans = Math.max(ans, dx * dx + dy * dy);
                }
                return;
            }
            for (int i = start; i < n; i++) {
                visited[i] = true;
                combination(points, visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            Source s = new Source(br);
            new Solution().solution(s.N, s.points);
        }
    }
}
