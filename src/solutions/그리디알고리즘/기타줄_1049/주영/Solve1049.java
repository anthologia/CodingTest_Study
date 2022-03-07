package solutions.그리디알고리즘.기타줄_1049.주영;

import java.io.*;
import java.util.*;

public class Solve1049 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        int minPackagePrice = 1000, minEPiecePrice = 1000;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int packagePrice = Integer.parseInt(st.nextToken());
            int piecePrice = Integer.parseInt(st.nextToken());

            if (packagePrice < minPackagePrice) minPackagePrice = packagePrice;
            if (piecePrice < minEPiecePrice) minEPiecePrice = piecePrice;
        }

        System.out.println(Math.min(Math.min(minPackagePrice * (n / 6) + minEPiecePrice * (n % 6), minPackagePrice * ((n / 6) + 1)), minEPiecePrice * n));
    }

}
