package solutions.이분탐색.드래곤앤던전_16434.규연;

import java.io.*;
import java.util.*;

public class Sol_DragonAndDungeon {
    static class Room {
        int t;
        int a;
        int h;

        public Room(int[] info) {
            this.t = info[0];
            this.a = info[1];
            this.h = info[2];
        }
    }

    static class Source {
        int n;
        long hatk;
        Room[] dungeon;

        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            hatk = Long.parseLong(st.nextToken());
            dungeon = new Room[n];
            for (int i = 0; i < n; i++) {
                dungeon[i] = new Room(
                        Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray());
            }
        }
    }

    static class Solution {
        public long solution(int n, long hatk, Room[] dungeon) {
            long left = 0;
            long right = Long.MAX_VALUE;
            while ((right - left) > 1) {
                long mid = (left + right) / 2;
                if (isAvailableHP(dungeon, hatk, mid)) right = mid;
                else left = mid;
            }
            return right;
        }

        public boolean isAvailableHP(Room[] dungeon, long hatk, long maxHp) {
            long currHp = maxHp;
            for (Room room : dungeon) {
                if (room.t == 1) {
                    int attackTime = (int) Math.ceil((double) room.h / hatk) - 1;
                    currHp -= room.a * attackTime;
                    if (currHp >= 0) return false;
                } else {
                    hatk += room.a;
                    currHp += room.h;
                    if (currHp > maxHp) currHp = maxHp;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        long ans = new Solution().solution(s.n, s.hatk, s.dungeon);
        System.out.println(ans);

    }
}