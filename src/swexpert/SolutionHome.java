package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionHome {
	static int Answer, N, M;
	static int map[][];
	static boolean fmap[][];
	static int MAXHome;
	static int Hcnt;

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String args[]) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int T, i, j;
		T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			Answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					MAXHome = map[i][j] == 1 ? ++MAXHome : MAXHome;
				}
			}

			int k = (int) Math.sqrt(MAXHome * M);

			Answer = checkMap(k - 1);
			System.out.println("#" + test_case + " " + Answer);
		}
	}

	public static int checkMap(int k) {
		if (k < 1)
			return 0;

		int maxCnt = 0;
		int cnt = 0;

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				cnt = checkService(k - 1, x, y);
				if (maxCnt < cnt) {
					maxCnt = cnt;
				}
			}
		}
		int service = (maxCnt * M) - (k * k + ((k - 1) * (k - 1)));
		if (service < 0) {
			k--;
			maxCnt = checkMap(k);
		}

		return maxCnt;
	}

	public static int checkService(int k, int x, int y) {
		Hcnt = 0;
		fmap = new boolean[N][N];

		dfs(k, 0, x, y);
		/*for (boolean[] a : fmap) {
			for (boolean b : a) {
				System.out.print(b + " ");
			}
			System.out.println();
		}
		System.out.println();*/
		return Hcnt;
	}

	public static void dfs(int k, int l, int x, int y) {
		if (l == k) {
			return;
		}

		fmap[x][y] = true;
		if (map[x][y] == 1)
			Hcnt++;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];

			if (newX >= 0 && newX < N && newY >= 0 && newY < N && fmap[newX][newY] == false) {
				dfs(k, l + 1, newX, newY);
			}
		}
	}

}