package solutions.그래프.촌수계산_2644.경현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S3_2644 {
	public static int arr[][];
	public static boolean visited[];
	public static int target1, target2;
	public static int ans = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		target1 = Integer.parseInt(st.nextToken());
		target2 = Integer.parseInt(st.nextToken());
		
		arr = new int [N+1][N+1];
		visited = new boolean[N+1];
		
		int size = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < size; i++) {
			StringTokenizer ar = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(ar.nextToken());
			int b = Integer.parseInt(ar.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		//System.out.println(bfs(target1, target2));
		dfs(target1, 0);
		System.out.println(ans);
	}
	
	public static int bfs(int a, int b) {
		Queue <Node> que = new LinkedList<>();
		visited[a] = true;
		que.offer(new Node(a, 0));

		while(!que.isEmpty()) {
			Node node = que.poll();
			int p = node.n;

			for(int i = 1; i < arr.length; i++) {				
				if(arr[p][i] == 1 && visited[i] != true) {
					if(i == b) {						
						return node.count+1;
					}					
					que.offer(new Node(i, node.count+1));
					visited[i] = true;
				}
			}
		}
		return -1;	
	}
	
	public static void dfs(int start, int depth) {
		for(int i = 0; i<arr.length; i++) {
			if(arr[start][i] == 1 && visited[i] != true) {	
				if(i == target2) {
					ans = depth+1;
					return;
				}				
				visited[i] = true;
				dfs(i, depth+1);
			}
		}
	}
	
	static class Node {
		int n;
		int count;
		
		public Node(int n, int count){
			this.n = n;
			this.count = count;
		}
	}

}
