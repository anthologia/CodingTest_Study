package solutions.다이나믹프로그래밍.호텔_1106.주영;

import java.io.*;
import java.util.*;

public class Solve1106 {
    static int MAX = 100 * 1000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int c = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        int[] dp = new int[MAX];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int promotionPrice = Integer.parseInt(st.nextToken());
            int client = Integer.parseInt(st.nextToken());

            for (int j = promotionPrice; j < MAX; j++) {
                dp[j] = Math.max(dp[j - promotionPrice] + client, dp[j]);
                if (dp[j] >= c) break;
            }
        }

        for (int i = 1; i < MAX; i++) {
            if (dp[i] >= c) {
                System.out.println(i);
                break;
            }
        }
    }
}
