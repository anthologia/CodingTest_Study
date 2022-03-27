package solutions.정렬.센서_2212.규연;

import java.util.*;
import java.io.*;

public class Sol_Sensor {
    static class Source {

        int N;
        int K;
        int[] coordinates;

        public Source(BufferedReader br) throws IOException {
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());
            coordinates = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    static class Solution {
        int[] dist;

        public void solution(int N, int K, int[] coordinates) {
            if (N < K) System.out.println(0);
            else {
                Arrays.sort(coordinates);
                dist = new int[N - 1];
                for (int i = 1; i < N; i++) {
                    dist[i - 1] = coordinates[i] - coordinates[i - 1];
                }
                System.out.println(Arrays.stream(dist).sorted().limit(N - K).sum());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.N, s.K, s.coordinates);

    }
}
