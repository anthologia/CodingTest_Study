package solutions.구현.마법사_상어와_복제_23290.주영;

import java.io.*;
import java.util.*;

class Shark implements Comparable<Shark> {
    int x, y, fishCnt;
    String str;

    public Shark(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Shark(int x, int y, int fishCnt, String str) {
        this.x = x;
        this.y = y;
        this.fishCnt = fishCnt;
        this.str = str;
    }

    @Override
    public int compareTo(Shark o) {
        if(o.fishCnt == this.fishCnt) {
            return this.str.compareTo(o.str);
        }
        return o.fishCnt - this.fishCnt;
    }
}

public class Solution23290 {
    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; // 12345678
    public static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1}; // 12345678
    public static int[] sharkDx = {0, -1, 0, 1}; // 상좌하우
    public static int[] sharkDy = {-1, 0, 1, 0}; // 상좌하우
    public static int[][] fishSmell = new int[4][4];
    public static boolean[][] visited;
    public static ArrayList<Integer>[][] aqua = new ArrayList[4][4];
    public static ArrayList<Integer>[][] fishes = new ArrayList[4][4];
    public static ArrayList<Shark> sharks;
    public static Shark curShark;
    public static int m;
    public static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x, y, d;

        m = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                aqua[i][j] = new ArrayList<>();
            }
        }

        // 물고기 init
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken()) - 1;
            y = Integer.parseInt(st.nextToken()) - 1;
            d = Integer.parseInt(st.nextToken());

            aqua[x][y].add(d);
        }

        // 상어 init
        st = new StringTokenizer(br.readLine(), " ");
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;
        curShark = new Shark(x, y);

        for (int i = 1; i <= s; i++) {
            copyFish();

            moveFish();

            sharks = new ArrayList<>();
            visited = new boolean[4][4];
            moveShark(curShark.x, curShark.y, 0, 0, "");

            makeSmell(i);

            removeSmell(i);

            cloneFishMagic();
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ans += aqua[i][j].size();
            }
        }
        System.out.println(ans);
    }

    public static void copyFish() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishes[i][j] = new ArrayList<>();
                for (int d : aqua[i][j]) {
                    fishes[i][j].add(d);
                }
            }
        }
    }

    public static void moveFish() {
        ArrayList<Integer>[][] newAqua = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newAqua[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : aqua[i][j]) {
                    int tmpDirect = d;
                    int cnt = 0;

                    do {
                        tmpDirect = tmpDirect == 0 || tmpDirect == 8 ? 8 : Math.abs(tmpDirect) % 8;
                        int mx = i + dy[tmpDirect - 1];
                        int my = j + dx[tmpDirect - 1];

                        if (isPossible(mx, my)) {
                            newAqua[mx][my].add(tmpDirect);
                            break;
                        }

                        if (++cnt == 8) {
                            newAqua[i][j].add(d);
                            break;
                        }
                    } while (Math.abs(--tmpDirect) % 8 != d);
                }
            }
        }
        aqua = newAqua.clone();
    }

    public static void moveShark(int x, int y, int cnt, int fishCnt, String s) {
        if (cnt == 3) {
            Shark shark = new Shark(x, y, fishCnt, s);
            sharks.add(shark);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int mx = x + sharkDy[i];
            int my = y + sharkDx[i];

            if (!isInsideRange(mx, my)) continue;

            if (!visited[mx][my]) {
                visited[mx][my] = true;
                moveShark(mx, my, cnt + 1, fishCnt + aqua[mx][my].size(), s + i);
                visited[mx][my] = false;
            } else {
                moveShark(mx, my, cnt + 1, fishCnt, s + i);
            }
        }
    }

    public static void makeSmell(int time) {
        Collections.sort(sharks);
        Shark shark = sharks.get(0);
        int x = curShark.x;
        int y = curShark.y;

        for (int i = 0; i < 3; i++) {
            int direction = shark.str.charAt(i) - '0';
            x += sharkDy[direction];
            y += sharkDx[direction];

            if (aqua[x][y].size() > 0) {
                fishSmell[x][y] = time + 2;
                aqua[x][y].clear();
            }
        }
        curShark = shark;
    }

    public static void removeSmell(int time) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (fishSmell[i][j] == time) {
                    fishSmell[i][j] = 0;
                }
            }
        }
    }

    public static void cloneFishMagic() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int d : fishes[i][j]) {
                    aqua[i][j].add(d);
                }
            }
        }
    }

    public static boolean isPossible(int x, int y) {
        return isInsideRange(x, y) && fishSmell[x][y] == 0 && !(curShark.x == x && curShark.y == y);
    }

    public static boolean isInsideRange(int x, int y) {
        return (x >= 0 && x < 4) && (y >= 0 && y < 4);
    }
}
