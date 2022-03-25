package solutions.구현.참외밭_2477.주영;

import java.io.*;
import java.util.StringTokenizer;

public class Solution2477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int minWidth = 500, maxWidth = 0;
        int minHeight = 500, maxHeight = 0;
        int[][] arr = new int[6][2];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int direction = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            arr[i][0] = direction;
            arr[i][1] = length;

            if (direction == 1 || direction == 2) {
                if (maxWidth < length) maxWidth = length;
            } else {
                if (maxHeight < length) maxHeight = length;
            }
        }

        for (int i = 0; i < 6; i++) {
            int prevAngle = (i + 5) % 6;
            int nextAngle = (i + 1) % 6;

            if (arr[prevAngle][0] == arr[nextAngle][0]) {
                if (arr[i][0] == 1 || arr[i][0] == 2) minWidth = arr[i][1];
                else minHeight = arr[i][1];
            }
        }

        System.out.println((maxHeight * maxWidth - minHeight * minWidth) * k);
    }
}

