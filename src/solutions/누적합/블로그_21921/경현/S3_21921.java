package solutions.누적합.블로그_21921.경현;

import java.io.*;
import java.util.*;

public class S3_21921 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String [] rs = br.readLine().split(" ");
		int N = Integer.parseInt(rs[0]);
		int X = Integer.parseInt(rs[1]);
		
		int [] visitedList = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int k = 0;				
		while(st.hasMoreTokens()) {
			visitedList[k] = Integer.parseInt(st.nextToken());
			k++;
		}
		
		ArrayList <Integer> list = new ArrayList<>();
		Queue <Integer> que = new LinkedList<>();
		int sum = 0;

		for(int i = 0; i < N; i++) {
			que.add(visitedList[i]);
			sum += visitedList[i];

			if(que.size() == X) {
				list.add(sum);
				sum -= que.poll();
			}
		}
		
		int maxSum = Collections.max(list);
		
		if(maxSum == 0) {
			System.out.println("SAD");
		}else {
			int count = Collections.frequency(list, maxSum);
			System.out.println(maxSum);
			System.out.println(count);
		}
	}

}
