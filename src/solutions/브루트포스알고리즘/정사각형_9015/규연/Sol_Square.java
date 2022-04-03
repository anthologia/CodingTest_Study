package solutions.브루트포스알고리즘.정사각형_9015.규연;

import java.util.*;
import java.io.*;
public class Sol_Square {
    static class Source {
        int N;
        Point[] points;
        public Source(BufferedReader br) throws IOException {
            N = Integer.parseInt(br.readLine());
            points = new Point[N];
            for (int i = 0; i < N; i++) {
                points[i] = new Point(convertInputToArray(br));
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
    }

    static class Solution {

        public void solution(int n, Point[] points) {

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
