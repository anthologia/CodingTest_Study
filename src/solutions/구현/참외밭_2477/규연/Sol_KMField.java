package solutions.구현.참외밭_2477.규연;

import java.io.*;
import java.util.*;

public class Sol_KMField {
    static int maxWidth;
    static int maxHeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int prevLen = 0;
        int startLen = 0;
        int size = 0;
        maxWidth = 0;
        maxHeight = 0;
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int direct = Integer.parseInt(st.nextToken());
            int currLen = Integer.parseInt(st.nextToken());
            compareLength(direct, currLen);
            if (prevLen != 0) size += prevLen * currLen;
            else startLen = currLen;
            prevLen = currLen;
        }
        size += startLen * prevLen;

        System.out.println((size - 2 * maxHeight * maxWidth) * k);
    }

    private static void compareLength(int direct, int len) {
        if (direct < 3 && len > maxWidth) maxWidth = len;
        else if (direct >= 3 && len > maxHeight) maxHeight = len;
    }
}
