package solutions.문자열.contact.주영;

import java.io.*;
import static java.util.regex.Pattern.*;

public class Solution1013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        String pattern = "(100+1+|01)+";

        for (int i = 0; i < t; i++) {
            sb.append(matches(pattern, br.readLine()) ? "YES" : "NO").append('\n');
        }
        System.out.println(sb);
    }
}
