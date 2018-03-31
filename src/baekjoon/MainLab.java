package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainLab {
	static int vX[];
	static int vY[];
	static int virus_cnt;
	static int boundary_cnt;
	static int map[][];
	static boolean c_visit[][];

	static int N, M, Answer, min, calc_min;
	public static void main(String[] args) throws Exception {
		min = Integer.MAX_VALUE;
		Answer = 3;
		virus_cnt = 0;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String[] strs = br.readLine().split(" ");
		N = Integer.parseInt(strs[0]);
		M = Integer.parseInt(strs[1]);

		map = new int[N][M];
		c_visit = new boolean[N][M];
		vX = new int[10];
		vY = new int[10];

		int i, j;
		for (i = 0; i < N; i++) {
			strs = br.readLine().split(" ");
			for (j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(strs[j]);
				if (map[i][j] == 2) {
					vX[virus_cnt] = i;
					vY[virus_cnt] = j;
					virus_cnt++;
				} else if (map[i][j] == 1) {
					boundary_cnt++;
				}
			}
		}

		if (boundary_cnt != 0) {
			dfs(0, 0);
			Answer = N * M - boundary_cnt - 3 - min - virus_cnt;
		}

		System.out.println(Answer);
	}

	public static void dfs(int cnt, int index) {
		if (cnt == 3) {
			for (int i = 0; i < N; i++) {
				Arrays.fill(c_visit[i], false);
			}
			calc_min = 0;
			for (int i = 0; i < virus_cnt; i++) {
				c_visit[vX[i]][vY[i]] = true;
				calc(vX[i], vY[i]);
				if(min <= calc_min){
					return;
				}
			}
			min = min > calc_min ? calc_min : min;
			return;
		}

		for (int i = index; i < N * M; i++) {
			if (map[i / M][i % M] == 0) {
				map[i / M][i % M] = 1;
				dfs(cnt + 1, i);
				map[i / M][i % M] = 0;
			}
		}
	}

	public static void calc(int x, int y) {
		if (x + 1 < N) {
			if (map[x + 1][y] == 0) {
				if (c_visit[x + 1][y] == false) {
					c_visit[x + 1][y] = true;
					calc_min++;
					calc(x + 1, y);
				}
			}
		}

		if (y + 1 < M) {
			if (map[x][y + 1] == 0) {
				if (c_visit[x][y + 1] == false) {
					c_visit[x][y + 1] = true;
					calc_min++;
					calc(x, y + 1);
				}
			}
		}

		if (x - 1 >= 0) {
			if (map[x - 1][y] == 0) {
				if (c_visit[x - 1][y] == false) {
					c_visit[x - 1][y] = true;
					calc_min++;
					calc(x - 1, y);
				}
			}
		}
		if (y - 1 >= 0) {
			if (map[x][y - 1] == 0) {
				if (c_visit[x][y - 1] == false) {
					c_visit[x][y - 1] = true;
					calc_min++;
					calc(x, y - 1);
				}
			}
		}
	}
}
