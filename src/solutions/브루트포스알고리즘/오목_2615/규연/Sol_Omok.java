package solutions.브루트포스알고리즘.오목_2615.규연;

import java.io.*;
import java.util.*;

public class Sol_Omok {

    static int MAX_VALUE = 19;
    static int OMOK_COUNT = 5;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 1};
    static int[] dy = {1, 1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[MAX_VALUE][MAX_VALUE];
        for (int i = 0; i < MAX_VALUE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX_VALUE; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    public static String solution() {
        for (int y = 0; y < MAX_VALUE; y++) {
            for (int x = 0; x < MAX_VALUE; x++) {
                if (board[y][x] == 0) continue; // 돌이 없으면 continue
                int curr = board[y][x]; // current 돌 색깔
                for (int d = 0; d < 4; d++) { // 4가지 방향 비교
                    int count = 0;
                    int checkX = x - dx[d];
                    int checkY = y - dy[d];  // 반대 방향 좌표
                    // 반대 방향 돌이 바둑판 안에 있을 때 색이 같으면 continue -> 이미 방문한 좌표
                    if (!isOut(checkX, checkY) && board[checkY][checkX] == curr) continue;

                    // 제일 끝 돌이 오목 가능한 위치일 때
                    checkX += dx[d] * OMOK_COUNT;
                    checkY += dy[d] * OMOK_COUNT;
                    if (!isOut(checkX, checkY)) {
                        for (int i = 0; i < OMOK_COUNT; i++) {
                            int nx = x + dx[d] * i;
                            int ny = y + dy[d] * i;
                            if (board[ny][nx] != curr) break;
                            count++;
                        }
                    }

                    // 오목일 때 다음 돌 색깔 확인
                    if (count == OMOK_COUNT) {
                        checkX += dx[d];
                        checkY += dy[d];
                        if (isOut(checkX, checkY) || board[checkY][checkX] != curr) {
                            return curr + "\n" + (y + 1) + " " + (x + 1);
                        }
                    }
                }
            }
        }
        return "0";
    }

    static boolean isOut(int x, int y) {
        return x < 0 || x >= MAX_VALUE || y < 0 || y >= MAX_VALUE;
    }
}
