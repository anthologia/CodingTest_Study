package solutions.그리디알고리즘.기타줄_1049.경현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_1049 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int size = Integer.parseInt(st.nextToken());
		int Min = Integer.MAX_VALUE;

		int pack[] = new int [size];
		int one[] = new int [size];
		
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			pack[i] = Integer.parseInt(st.nextToken());
			one[i] = Integer.parseInt(st.nextToken());		
		}
		
		Arrays.sort(pack);
		Arrays.sort(one);

		Min = Math.min(pack[0]*((N/6)+1), one[0]*N);
		Min = Math.min(pack[0]*(N/6) + one[0]*(N%6), Min);
		
		System.out.println(Min);
	}

}
