package solutions.누적합.블로그_21921.주영;

import java.io.*;
import java.util.*;

public class Solve21921 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());
        int[] visits = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            visits[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int j = 0; j < x; j++) {
            sum += visits[j];
        }

        int max = sum;
        int cnt = 1;

        for (int i = x; i < n; i++) {
            sum += visits[i];
            sum -= visits[i - x];

            if (sum > max) {
                max = sum;
                cnt = 1;
            } else if (sum == max) {
                cnt++;
            }
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
