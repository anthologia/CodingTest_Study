package solutions.그래프.퍼즐_1525.주영;

import java.io.*;
import java.util.*;

public class Solution1525 {
    public static int[] dx = {0, 0, -1, 1}; // 상하좌우
    public static int[] dy = {1, -1, 0, 0}; // 상하좌우
    public static Deque<String> deque = new ArrayDeque<>();
    public static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sb.append(br.readLine().replace(" ", ""));
        }

        if (isAnswer(sb.toString())) {
            System.out.println(0);
        } else {
            map.put(sb.toString(), 0);
            deque.add(sb.toString());
            System.out.println(bfs());
        }

    }

    public static int bfs() {
        while (!deque.isEmpty()) {
            String str = deque.poll();
            int zeroIdx = str.indexOf('0');
            int x = zeroIdx / 3;
            int y = zeroIdx % 3;

            for (int k = 0; k < dx.length; k++) {
                int mx = y + dy[k];
                int my = x + dx[k];

                if (!isInsideRange(mx, my)) continue;

                int swapIdx = (3 * my) + mx;

                StringBuilder sb = new StringBuilder(str);

                char ch = sb.charAt(swapIdx);
                sb.setCharAt(swapIdx, '0');
                sb.setCharAt(zeroIdx, ch);

                if (isAnswer(sb.toString())) {
                    return map.get(str) + 1;
                }

                if (!map.containsKey(sb.toString())) {
                    deque.add(sb.toString());
                    map.put(sb.toString(), map.get(str) + 1);
                }
            }
        }
        return -1;
    }

    public static boolean isInsideRange(int x, int y) {
        return (x >= 0 && x < 3) && (y >= 0 && y < 3);
    }

    public static boolean isAnswer(String str) {
        return "123456780".equals(str);
    }

    public static boolean isRightPlace(int x, int y, int val) {
        return ((y * 3) + 1) + x == val;
    }
}
