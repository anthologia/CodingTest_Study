package solutions.브루트포스알고리즘.정사각형_9015.주영;

import java.io.*;
import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Solution9015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            double ans = 0;
            Set<Point> set = new HashSet<>();
            ArrayList<Point> arr = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                Point p = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                set.add(p);
                arr.add(p);
            }

            for (int i = 0; i < n; i++) {
                Point p1 = arr.get(i);

                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }

                    Point p2 = arr.get(j);
                    double l = Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);

                    if (ans >= l) {
                        continue;
                    }

                    int xDiff = p1.x - p2.x;
                    int yDiff = p1.y - p2.y;

                    Point expectedP1 = new Point(p1.x - yDiff, p1.y + xDiff);
                    Point expectedP2 = new Point(p2.x - yDiff, p2.y + xDiff);

                    if (set.contains(expectedP1) && set.contains(expectedP2)) {
                        ans = Math.max(ans, l);
                    }
                }
            }
            sb.append((int) ans).append('\n');
        }
        System.out.println(sb);
    }
}
