package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class SolutionProtectfilm {
	static int Answer;
	static int D, W, K;
	static int map[][];
	static int a[];
	static int b[];
	static int MINCNT;
	static boolean finish;

	public static void main(String args[]) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int T;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			finish = false;
			Answer = 0;
			MINCNT = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			a = new int[W];
			b = new int[W];
			Arrays.fill(a, 0);
			Arrays.fill(b, 1);
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (!pass())
				dfs(0, 0);
			else
				MINCNT = 0;

			System.out.println("#" + test_case + " " + MINCNT);
		}
	}

	private static void dfs(int depth, int min) {
		if (min >= MINCNT)
			return;

		if (depth == D) {
			if (min != 0) {
				if (pass() == true) {
					MINCNT = MINCNT < min ? MINCNT : min;
				}
			}
			return;
		}

		dfs(depth + 1, min);

		int tmp[] = map[depth];

		map[depth] = a;

		dfs(depth + 1, min + 1);

		map[depth] = b;

		dfs(depth + 1, min + 1);

		map[depth] = tmp;
	}

	private static boolean pass() {
		boolean isPass = true;
		int passCnt;

		row: for (int j = 0; j < W; j++) {
			passCnt = 0;
			for (int i = 0; i < D - 1; i++) {
				if (map[i][j] == map[i + 1][j])
					passCnt++;
				else
					passCnt = 0;

				if (passCnt >= K - 1)
					continue row;
			}

			isPass = false;
			break;
		}
		return isPass;
	}
}