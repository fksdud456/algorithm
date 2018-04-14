package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionAirstrip2 {
	private static int Answer, N, X;
	private static int map[][];
	private static int line[];

	public static void main(String args[]) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for (int testcase = 1; testcase <= T; testcase++) {
			Answer = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			line = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// row
			for (int i = 0; i < N; i++) {
				line = map[i];
				if (check()) {
					Answer++;
					System.out.println("row : " + i);
				}
			}

			// col
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					line[j] = map[j][i];
				}
				if (check()) {
					
					Answer++;
					System.out.println("col : " + i);
				}
			}

			System.out.println("#" + testcase + " " + Answer);
		}
	}

	public static boolean check() {
		boolean resLine[] = new boolean[N];
		boolean result = true;

		lineStart: for (int i = 0; i < N - 1; i++) {
			if (Math.abs(line[i + 1] - line[i]) > 1) {
				result = false;
				break lineStart;
			}

			if (line[i] < line[i + 1]) {
				if (i - X < 0) {
					result = false;
					break lineStart;
				}
				// i+1
				for (int k = i + 1; k > i - X; k--) {
					if (line[k] != line[i] || resLine[k]) {
						result = false;
						break lineStart;
					}
				}
				if (result) {
					for (int k = i; k > i - X; k--) {
						resLine[k] = true;
					}
				}
			} else if(line[i] > line[i + 1]){
				if (i + X >= N) {
					result = false;
					break lineStart;
				}

				for (int k = i + 2; k <= i + X; k++) {
					if (line[k] != line[i + 1] || resLine[k]) {
						result = false;
						break lineStart;
					}
				}
				if (result) {
					for (int k = i + 1; k <= i + X; k++) {
						resLine[k] = true;
					}
//					i = i + X;
				}
			}
		}

		return result;
	}

}
