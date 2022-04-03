package solutions.구현.마법사_상어와_복제_23290.규연;

import java.io.*;
import java.util.*;

public class Sol_MS_Copy {
    static class Source {
        int M;
        int S;
        Fish[] fishInfo;
        Fish shark;

        public Source(BufferedReader br) throws IOException {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            fishInfo = new Fish[M];
            for (int i = 0; i < M; i++) {
                fishInfo[i] = new Fish(convertInputToArray(br));
            }

            shark = new Fish(convertInputToArray(br));
        }

        private int[] convertInputToArray(BufferedReader br) throws IOException {
            return Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

    }

    static class Fish {
        int x;
        int y;
        int d;
        boolean smell;

        public Fish(int[] info) {
            this.x = info[0];
            this.y = info[1];
            if (info.length > 2) this.d = info[2];
            this.smell = false;
        }
    }

    static class Solution {
        List<Fish>[][] board;
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

        public void solution(int m, int s, Fish[] fishInfo, Fish shark) {
            board = initBoard(fishInfo, shark);
            for (int i = 0; i < s; i++) {
                List<Fish> movedFishInfo = new ArrayList<>();
                for (Fish fish : fishInfo) {
                    int[] info = new int[]{fish.x, fish.y, rotateDirection(fish, shark)};
                    movedFishInfo.add(new Fish(info));
                }

                List<Fish>[][] movedBoard = initBoard(movedFishInfo.toArray(Fish[]::new), shark);


            }

        }

        private int rotateDirection(Fish fish, Fish shark) {
            int direction = fish.d;
            for (int i = 0; i < 7; i++) {
                int nx = fish.x + dx[direction - 1];
                int ny = fish.y + dy[direction - 1];
                if (isIn(nx, ny) && !isShark(nx, ny, shark) && !isSmell(nx, ny)) {
                    break;
                }
                if (direction == 1) direction = 8;
                else direction--;
            }
            return direction;
        }

        private boolean isIn(int x, int y) {
            return x >= 0 && x < 4 && y >= 0 && y < 4;
        }

        private boolean isShark(int x, int y, Fish shark) {
            return x == shark.x && y == shark.y;
        }

        private boolean isSmell(int x, int y) {
            return board[x][y].size() != 0 && board[x][y].get(0).smell;
        }

        private List<Fish>[][] initBoard(Fish[] fishInfo, Fish shark) {
            List<Fish>[][] board = new ArrayList[4][4];
            Arrays.fill(board, new ArrayList<>());
            Arrays.stream(fishInfo).forEach(fish -> board[fish.x][fish.y].add(fish));
            board[shark.x][shark.y].add(shark);
            return board;
        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.M, s.S, s.fishInfo, s.shark);
    }
}
