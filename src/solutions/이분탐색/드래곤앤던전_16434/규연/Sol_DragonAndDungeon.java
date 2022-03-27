package solutions.이분탐색.드래곤앤던전_16434.규연;

import java.io.*;
import java.util.*;

public class Sol_DragonAndDungeon {
    static class Source {
        int n;
        int hatk;
        int[] dungeon;
        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            hatk = Integer.parseInt(st.nextToken());
            dungeon = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        }
    }

    static class Solution {
        public void solution(int n, int hatk, int[] dungeon) {
            int HmaxHp = 0;


        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.n, s.hatk, s.dungeon);

    }
}
