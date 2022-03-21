package solutions.그래프.영역구하기_2583.주영;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution2583 {
    public static int n, m, k;
    public static int area = 0;
    public static int[][] graph;
    public static int[] dx = {0, 0, -1, 1}; // 상하좌우
    public static int[] dy = {1, -1, 0, 0}; // 상하좌우


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[m][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = y1; j < y2; j++) {
                for (int l = x1; l < x2; l++) {
                    graph[j][l] = 1;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0) {
                    area = 0;
                    dfs(i, j);
                    list.add(area);
                }
            }
        }

        sb.append(list.size()).append('\n');
        Collections.sort(list);
        for (int l : list) sb.append(l).append(' ');

        System.out.println(sb);
    }

    public static void dfs(int x, int y) {
        graph[x][y] = 1;
        area++;

        for (int i = 0; i < dx.length; i++) {
            int mx = x + dx[i];
            int my = y + dy[i];

            if (isPossible(mx, my)) {
                dfs(mx, my);
            }
        }
    }

    public static boolean isPossible(int x, int y) {
        return (x >= 0 && x < m && y >= 0 && y < n) && (graph[x][y] == 0);
    }
}
