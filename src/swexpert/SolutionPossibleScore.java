package swexpert;

import java.util.Scanner;
import java.util.StringTokenizer;

public class SolutionPossibleScore {
	private static int[] scorelist;
	private static boolean[] flag;
	private static int Answer;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int T, N;
		T = Integer.parseInt(sc.nextLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			Answer = 1;
			N = Integer.parseInt(sc.nextLine());
			scorelist = new int[N];
			flag = new boolean[N * 100];
			StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
			for (int i = 0; i < N; i++) {
				scorelist[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0);

			System.out.print("#" + test_case + " " + Answer);
		}
	}

	public static void dfs(int node, int sum) {
		if (node >= scorelist.length) {
			return;
		}

		dfs(node + 1, sum);
		sum += scorelist[node];

		if (flag[sum] == false) {
			flag[sum] = true;
			Answer++;
		}
		dfs(node + 1, sum);
	}
}