package solutions.브루트포스알고리즘.꽃길_14620.주영;

import java.io.*;
import java.util.*;

class Seed {
    int x, y;

    public Seed(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Sovle14620 {
    static ArrayList<Seed> candidateSeeds;
    static boolean[][] visited;
    static int[][] garden;
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우
    static int n;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        garden = new int[n][n];
        visited = new boolean[n][n];

        candidateSeeds = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                garden[i][j] = Integer.parseInt(st.nextToken());

                // 가장 바깥 테두리는 돌지 않으면서 5평의 합을 가진 Seed 객체를 candidateSeeds에 담기
                if ((i > 0 && i < n - 1) && (j > 0 && j < n - 1)) {
                    candidateSeeds.add(new Seed(i, j));
                }
            }
        }

        dfs(0, 0);
        System.out.println(min);

    }

    public static void dfs(int depth, int sum) {
        if (depth == 3) {
            min = Math.min(min, sum);
            return;
        }

        for (Seed seed : candidateSeeds) {
            if (isPossible(seed)) {
                setVisited(seed, true);
                dfs(depth + 1, sum + calc5LandsPrice(seed));
                setVisited(seed, false);
            }
        }
    }

    private static void setVisited(Seed seed, boolean flag) {
        visited[seed.x][seed.y] = flag;

        for (int i = 0; i < dx.length; i++) {
            int mX = seed.x + dx[i];
            int mY = seed.y + dy[i];

            visited[mX][mY] = flag;
        }
    }

    public static boolean isPossible(Seed seed) {
        if (visited[seed.x][seed.y]) {
            return false;
        }

        for (int i = 0; i < dx.length; i++) {
            int mX = seed.x + dx[i];
            int mY = seed.y + dy[i];

            if (visited[mX][mY]) {
                return false;
            }
        }
        return true;
    }

    public static int calc5LandsPrice(Seed seed) {
        int sum = garden[seed.x][seed.y];

        for (int i = 0; i < dx.length; i++) {
            int mX = seed.x + dx[i];
            int mY = seed.y + dy[i];

            sum += garden[mX][mY];
        }
        return sum;
    }
}
