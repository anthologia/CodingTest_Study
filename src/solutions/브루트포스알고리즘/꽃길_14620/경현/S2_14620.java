package solutions.브루트포스알고리즘.꽃길_14620.경현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//꽃길
public class S2_14620 {
    static int [] dx = {0, 0, 1, -1, 0};
    static int [] dy = {0, 1, 0, 0, -1};
    static int N;
    static int [][] map;
    static boolean [][] check;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int [N][N];
        check = new boolean [N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(result);
    }

    public static void dfs(int cost, int depth) {
        if(depth == 3) {
            result = Math.min(result, cost);
            return;
        }

        for(int i = 1; i < N-1; i++) {
            for(int j = 1; j < N-1; j++) {
                if(!check[i][j] && isPossible(i, j)) {
                    dfs(sum(i,j) + cost, depth+1);
                    setting(i, j);
                }
            }
        }
    }

    public static int sum(int x, int y) {
        int sum = 0;
        for(int n = 0; n < 5; n++) {
            int nx = x+dx[n];
            int ny = y+dy[n];
            check[nx][ny] = true;
            sum += map[nx][ny];
        }
        return sum;
    }
    // 좌표 벗어나는지와 방문 여부 체크
    public static boolean isPossible(int x, int y) {
        for(int n = 0; n < 5; n++) {
            int nx = x+dx[n];
            int ny = y+dy[n];

            if(check[nx][ny]) {
                return false;
            }
        }
        return true;
    }

    public static void setting(int x, int y) {
        for(int n = 0; n < 5; n++) {
            int nx = x+dx[n];
            int ny = y+dy[n];
            check[nx][ny] = false;
        }
    }
}
