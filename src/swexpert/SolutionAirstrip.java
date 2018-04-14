package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionAirstrip {
	static int Answer, N, x;
	private static int map[][];
	private static boolean flag[];

	public static void main(String args[]) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int T, i, j;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			Answer = 0;

			map = new int[N][N];
			flag = new boolean[N];
			for (i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			checkRow();
			checkCol();
			System.out.println("#" + test_case + " " + Answer);
		}
	}

	private static void checkRow() {
		int[] checkline = new int[N];

		boolean p;// = false;
		for (int i = 0; i < N; i++) {

			// initialize
			Arrays.fill(checkline, 0);
			Arrays.fill(flag, false);
			p = true;

			for (int j = 0; j < N - 1; j++) {
				checkline[j] = map[i][j] - map[i][j + 1];
				// 경사가 2 이상이면 종료
				if (Math.abs(checkline[j]) > 1) {
					p = false;
					break;
				}
			}

			if (p == false)
				continue;

			check : for (int j = 0; j < N - 1; j++) {
				if (checkline[j] > 0) {
					if (j + x >= N) {
						p = false;
						break check;
					}
               
                    flag[j + 1] = true;
					for (int k = 1; k < x; k++) {
						if (checkline[j + k] != 0 || flag[j + k+1] == true) {
							p = false;
							break check;
						} else {
							flag[j + k + 1] = true;
						}
					}
				} else if (checkline[j] < 0) {
					flag[j] = true;
					if (j - x + 1 < 0) {
						p = false;
						break check;
					}
					flag[j] = true;
					for (int k = 1; k < x; k++) {
						if (flag[j - k] == true || checkline[j - k] != 0 ) {
							p = false;
							break check;
						} else {
							flag[j-k] = true;
						}
					}
				}
			}

			if (p == true) {
				Answer++;
            }
		}
	}

	private static void checkCol() {
		int[] checkline = new int[N];

		boolean p;
		line : for (int i = 0; i < N; i++) {

			Arrays.fill(checkline, 0);
			Arrays.fill(flag, false);
			p = true;

			for (int j = 0; j < N - 1; j++) {
				checkline[j] = map[j][i] - map[j + 1][i];
				if (Math.abs(checkline[j]) > 1) {
					continue line;
				}
			}

			check : for (int j = 0; j < N - 1; j++) {
				if (checkline[j] > 0) {
					if (j + x >= N) {
						p = false;
						break check;
					}
               
                    flag[j + 1] = true;
					for (int k = 1; k < x; k++) {
						if (checkline[j + k] != 0 || flag[j + k+1] == true) {
							p = false;
							break check;
						} else {
							flag[j + k + 1] = true;
						}
					}
				} else if (checkline[j] < 0) {
					flag[j] = true;
					if (j - x + 1 < 0) {
						p = false;
						break check;
					}
					flag[j] = true;
					for (int k = 1; k < x; k++) {
						if (flag[j - k] == true || checkline[j - k] != 0 ) {
							p = false;
							break check;
						} else {
							flag[j-k] = true;
						}
					}
				}
			}

			if (p == true) {
				Answer++;
            }
		}
	}

}