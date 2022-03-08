package solutions.그리디알고리즘.기타줄_1049.규연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sol_GuitarString {
    static int N, M;
    static int minPack = 1001;
    static int minPiece = 1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        initialize(br);
        System.out.println(solve());
    }

    private static void initialize(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int pack = Integer.parseInt(st.nextToken());
            int piece = Integer.parseInt(st.nextToken());
            if (minPack > pack) {
                minPack = pack;
            }
            if (minPiece > piece) {
                minPiece = piece;
            }
        }
    }

    private static int solve() {
        return Collections.min(Arrays.asList(
                N * minPiece,
                ((N / 6) + 1) * minPack,
                (N / 6) * minPack + (N % 6) * minPiece));
    }

}
