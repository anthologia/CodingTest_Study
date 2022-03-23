package BaekJoonGold;

import java.util.Scanner;

//Contact
public class G5_1013 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String reg = "(100+1+|01)+";
		int n = sc.nextInt();
		
		for(int i = 0; i< n; i++) {
			String spread = sc.next();
			if(spread.matches(reg)) System.out.println("YES");
			else System.out.println("NO");
		}
	}
}
