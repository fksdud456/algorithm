package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class MainOperator {
	private static int N;
	private static int max;
	private static int min;
	private static String[] numArr;
	private static int[] opArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int i;
		N = Integer.parseInt(br.readLine());
		opArr = new int[4];
		
		numArr = br.readLine().split(" ");
		String temp[] = br.readLine().split(" ");
		for (i = 0; i < 4; i++) {
			opArr[i] = Integer.parseInt(temp[i]);
		}
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		bfs(1, Integer.parseInt(numArr[0]));
		System.out.println(max);
		System.out.println(min);
	}

	static void bfs(int index, int result) {
		if (index == N) {
			min = min < result ? min : result;
			max = max > result ? max : result;
			return;
		}

		int temp = Integer.parseInt(numArr[index]);
		for(int i = 0 ; i < 4 ; i ++) {
			if(opArr[i] > 0) {
				opArr[i]--;
				if(i == 0)
					bfs(index+1, result+temp);
				else if(i==1)
					bfs(index+1, result-temp);
				else if(i==2)
					bfs(index+1, result*temp);
				else
					bfs(index+1, result/temp);
				opArr[i]++;
			}
		}
	}
}