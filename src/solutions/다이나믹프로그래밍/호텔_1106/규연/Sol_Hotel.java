package solutions.다이나믹프로그래밍.호텔_1106.규연;

import java.util.*;
import java.io.*;

public class Sol_Hotel {
    static class Condition {
        int cost;
        int client;

        public Condition(StringTokenizer st) {
            this.cost = Integer.parseInt(st.nextToken());
            this.client = Integer.parseInt(st.nextToken());
        }
    }

    static class Source {
        int C, N;
        Condition[] conditions;

        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            conditions = new Condition[N];
            for (int i = 0; i < N; i++) {
                conditions[i] = new Condition(new StringTokenizer(br.readLine()));
            }
        }
    }

    public static int[] dp;
    public static int MaxValue = 987654321;

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        dp = new int[s.C + 101];
        Arrays.fill(dp, MaxValue);
        dp[0] = 0;
        for (Condition condition : s.conditions) {
            for (int i = condition.client; i < s.C + 101; i++) {
                dp[i] = Math.min(dp[i], condition.cost + dp[i - condition.client]);
            }
        }
        System.out.println(Arrays.stream(dp).skip(s.C).min().getAsInt());
    }

}
