package solutions.그래프.촌수계산_2644.주영;

import java.io.*;
import java.util.*;

public class Solve2644 {
    static boolean[][] graph;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new boolean[n][n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            graph[x][y] = true;
            graph[y][x] = true;
        }
        System.out.println(bfs(start, end));
    }

    private static int bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        visited[start] = true;
        queue.add(start);

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int k = 0; k < size; k++) {
                int now = queue.remove();
                if (now == end) return result;

                for (int i = 0; i < n; i++) {
                    if (graph[now][i] && !visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
            result++;
        }
        return -1;
    }
}
