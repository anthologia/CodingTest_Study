package solutions.문자열.contact.규연;

import java.util.*;
import java.io.*;

public class Sol_Concat {
    static class Source {
        List<String> patterns;

        public Source(BufferedReader br) throws IOException {
            int n = Integer.parseInt(br.readLine());
            patterns = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                patterns.add(br.readLine());
            }
        }
    }

    static class Solution {
        public void solution(List<String> patterns) {
            String pattern = "(100+1+|01)+";
            for (String s : patterns) {
                if (s.matches(pattern)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.patterns);

    }
}
