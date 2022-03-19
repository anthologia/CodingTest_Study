package solutions.그래프.영역구하기_2583.경현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_2583 {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int [N][M];
        visited = new boolean [N][M];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int Lx = Integer.parseInt(st.nextToken());
            int Ly = Integer.parseInt(st.nextToken());
            int Rx = Integer.parseInt(st.nextToken());
            int Ry = Integer.parseInt(st.nextToken());

            for (int y = Ly; y < Ry + 1; y++) {
                for(int x = Lx; x < Rx + 1; x++){
                    map[y][x] = 1;
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++){
                if(map[i][j] != 1 && !visited[i][j]){
                    arr.add(bfs(i, j));
                    visited[i][j] = true;
                    count++;
                }
            }
        }
        System.out.println(count);
        for (Integer integer : arr) {
            System.out.print(integer + " ");
        }

    }

    public static int bfs(int i, int j){
        int size = 1;
        Queue<Point> que = new LinkedList<>();
        que.offer(new Point(i, j));

        while(!que.isEmpty()) {
            Point newPoint = que.poll();

            for(int n = 0; n<4; n++){
                int x = newPoint.x + dx[n];
                int y = newPoint.y + dy[n];

                if(x >= 0 && y >= 0 && x < map.length && y < map[0].length) {
                    if (map[x][y] != 1 && !visited[x][y]) {
                        que.offer(new Point(x, y));
                        visited[i][j] = true;
                        size++;
                    }
                }
            }
        }
        return size;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
