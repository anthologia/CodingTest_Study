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
        int smellCount;

        public Fish(int[] info) {
            this.x = info[0];
            this.y = info[1];
            if (info.length > 2) this.d = info[2];
            this.smellCount = 0;
        }
    }

    static class Solution {
        List<Fish>[][] board;
        Fish shark;
        int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] sx = {-1, 0, 1, 0};
        int[] sy = {0, -1, 0, 1};
        int fishCount = 0;
        int ate = Integer.MAX_VALUE;

        public void solution(int m, int s, Fish[] fishInfo, Fish shark) {
            this.shark = shark;
            board = initBoard(fishInfo);
            for (int i = 0; i < s; i++) {
                List<Fish> movedFishInfo = new ArrayList<>();
                for (Fish fish : fishInfo) {
                    int[] info = new int[]{fish.x, fish.y, rotateDirection(fish, shark)};
                    movedFishInfo.add(new Fish(info));
                }
                List<Fish>[][] movedBoard = initBoard(movedFishInfo.toArray(Fish[]::new));
                findPossibleToEat(movedBoard, shark, 0, "");
//                eatFish(movedBoard);

//                deleteSmell(board);
//                deleteSmell(movedBoard);
//
//                copyBoard(movedBoard);
            }
            int result = 0;
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    for (Fish fish : board[i][j]) {
                        if (fish.smellCount == 0) result++;
                    }
                }
            }
            System.out.println(result);
        }

        private void eatFish(List<Fish>[][] movedBoard) {
            for (int i = 0; i < 3; i++) {
                int d = (int) (ate / Math.pow(10, 2 - i));
                for (Fish fish : movedBoard[shark.x + sx[d - 1]][shark.y + sy[d - 1]]) {
                    fish.smellCount++;
                }
            }
        }

        private void copyBoard(List<Fish>[][] movedBoard) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    board[i][j].addAll(movedBoard[i][j]);
                }
            }
        }

        private void deleteSmell(List<Fish>[][] board) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    board[i][j].removeIf(fish -> fish.smellCount == 2);
                }
            }
        }

        private void findPossibleToEat(List<Fish>[][] board, Fish shark, int fishCnt, String directions) {
            if (directions.length() == 3) {
                if (fishCnt > fishCount) {
                    fishCount = fishCnt;
                    ate = Integer.parseInt(directions);
                } else if (fishCnt == fishCount) {
                    System.out.println("ate = " + ate);
                    System.out.println("Integer.parseInt(directions) = " + Integer.parseInt(directions));
                    ate = Math.min(ate, Integer.parseInt(directions));
                }
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = shark.x + sx[i];
                int ny = shark.y + sy[i];
                if (!isIn(nx, ny)) continue;
                int possibleToEat = board[nx][ny].size();

                fishCnt += possibleToEat;
                directions += (i + 1);

                findPossibleToEat(board, shark, fishCnt, directions);

                fishCnt -= possibleToEat;
                directions.substring(0, directions.length() - 1);
            }
        }

        private int rollBack(List<Fish>[][] board, int fishCnt, int di) {

            return fishCnt;
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
            System.out.println(direction);
            return direction;
        }

        private boolean isIn(int x, int y) {
            return x >= 1 && x < 5 && y >= 1 && y < 5;
        }

        private boolean isShark(int x, int y, Fish shark) {
            return x == shark.x && y == shark.y;
        }

        private boolean isSmell(int x, int y) {
            return board[x][y].size() != 0 && board[x][y].get(0).smellCount != 0;
        }

        private List<Fish>[][] initBoard(Fish[] fishInfo) {
            List<Fish>[][] board = new ArrayList[5][5];
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    board[i][j] = new ArrayList<>();
                }
            }
            for (Fish fish : fishInfo) {
                board[fish.x][fish.y].add(fish);
            }
            board[shark.x][shark.y].add(shark);
            return board;
        }
    }

    public static void main(String[] args) throws IOException {
        Source s = new Source(new BufferedReader(new InputStreamReader(System.in)));
        new Solution().solution(s.M, s.S, s.fishInfo, s.shark);
    }
}
