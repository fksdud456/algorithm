package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainStart {
	private static int N;
	private static int min;
	private static int sum;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		min = Integer.MAX_VALUE;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int i, j;
		StringTokenizer stringTokenizer;
		for (i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(br.readLine(), " ");
			for (j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		
		sum = 0;
		for (i = 0; i < N; i++) {
			for (j = i; j < N; j++) {
				map[i][j] += map[j][i];
				sum += map[i][j];
			}
		}
		
		dfs(0, 0, sum);
		System.out.println(min);
	}

	public static void dfs(int index, int cnt, int cur_sum) {
		if (index > N-1)
			return;

		if (cnt == (N/2)) {
			min = min < Math.abs(cur_sum) ? min : Math.abs(cur_sum);
			return;
		}

		dfs(index + 1, cnt, cur_sum);
		
		int j;
		for(j = index+1; j <N; j++) 
			cur_sum-= map[index][j];					
		for(j = 0; j < index; j++)
			cur_sum-= map[j][index];
		
		dfs(index + 1, cnt + 1, cur_sum);
	}
}
