package solutions.이분탐색.드래곤앤던전_16434.주영;

import java.io.*;
import java.util.StringTokenizer;

public class Solution16434 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long hAtk = Long.parseLong(st.nextToken());
        long hCurHp = 0, minHCurHp = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long t = Integer.parseInt(st.nextToken());
            long a = Integer.parseInt(st.nextToken());
            long h = Integer.parseInt(st.nextToken());

            if (t == 1) {
                if (h % hAtk == 0) hCurHp -= a * (h / hAtk - 1);
                else hCurHp -= a * (h / hAtk);
                minHCurHp = Math.min(minHCurHp, hCurHp);
            } else {
                hAtk += a;
                hCurHp = Math.min(hCurHp + h, 0);
            }
        }
        System.out.println(-(--minHCurHp));
    }
}
