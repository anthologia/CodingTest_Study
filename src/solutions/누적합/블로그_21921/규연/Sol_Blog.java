package solutions.누적합.블로그_21921.규연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_Blog {

    static class Source {
        int N, X;
        int[] visitors;

        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            visitors = Arrays
                    .stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        int maxVisit = Arrays.stream(s.visitors).limit(s.X).sum();
        int maxVisitCount = 1;
        int result = maxVisit;
        for (int i = s.X; i < s.N; i++) {
            maxVisit = maxVisit + s.visitors[i] - s.visitors[i - s.X];
            if (result == maxVisit) maxVisitCount++;
            else if (result < maxVisit) {
                result = maxVisit;
                maxVisitCount = 1;
            }
        }

        if (maxVisit == 0) System.out.println("SAD");
        else {
            System.out.println(result);
            System.out.println(maxVisitCount);
        }
    }
}
