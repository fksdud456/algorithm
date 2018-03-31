package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainMakeone {
	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[N + 3];
		count[2] = 1;
		count[3] = 1;
		
		int min = 0;
		for (int i = 4; i < N + 1; i++) {
			min = Integer.MAX_VALUE;
			if (i % 2 == 0 && min > count[i/2])
				min = count[i / 2];
			if(i% 3 ==0 && min > count[i / 3])
				min = count[i / 3];			
			if(min > count[i-1])
				min = count[i-1];
			
			count[i] = min + 1;
		}

		System.out.println(count[N]);
	}
}
