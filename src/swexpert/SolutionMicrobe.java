package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Microbe {
	int count;
	int dir;
	int maxCnt;

	public Microbe(int count, int dir) {
		this.count = count;
		this.maxCnt = count;
		this.dir = dir - 1;
	}
}

public class SolutionMicrobe {
	private static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	private static int N;
	private static Microbe[][] map;
	private static Microbe[][] map_tmp;

	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int answer = 0;
		int T = Integer.parseInt(br.readLine());

		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		for (int testcase = 1; testcase <= T; testcase++) {
			answer = 0;
			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new Microbe[N][N];
			map_tmp = new Microbe[N][N];

			int[] tmp = new int[4];
			int i, j = 0;
			for (i = 0; i < K; i++) {
				j = 0;
				st = new StringTokenizer(br.readLine(), " ");
				while (st.hasMoreTokens()) {
					tmp[j] = Integer.parseInt(st.nextToken());
					j++;
				}
				map[tmp[0]][tmp[1]] = new Microbe(tmp[2], tmp[3]);
			}

			while (M-- > 0) {
				move();
			}

			for (i = 0; i < N; i++) {
				for (j = 0; j < N; j++) {
					if (map[i][j] != null)
						answer += map[i][j].count;
				}
			}
			System.out.println("#" + testcase + " " + answer);
		}

	}

	public static void move() {
		int i, j;
		Microbe t;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (map[i][j] != null) {
					t = map[i][j];
					map[i][j] = null;
					int next_x = i + dir[t.dir][0];
					int next_y = j + dir[t.dir][1];
					if (next_x == 0 || next_y == 0 || next_x == N - 1 || next_y == N - 1) {
						if (t.dir == 0)
							t.dir = 1;
						else if (t.dir == 1)
							t.dir = 0;
						else if (t.dir == 2)
							t.dir = 3;
						else
							t.dir = 2;

						t.count = t.count / 2;
						t.maxCnt = t.count;
						map_tmp[next_x][next_y] = t;
					} else {

						if (map_tmp[next_x][next_y] != null) {
							map_tmp[next_x][next_y].count += t.count;
							if (map_tmp[next_x][next_y].maxCnt < t.count) {
								map_tmp[next_x][next_y].maxCnt = t.count;
								map_tmp[next_x][next_y].dir = t.dir;
							}
						} else {
							map_tmp[next_x][next_y] = t;
						}

					}
				}
			}
		}
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				map[i][j] = map_tmp[i][j];
				if (map[i][j] != null)
					map[i][j].maxCnt = map[i][j].count;
			}
		}

		for (i = 0; i < N; i++)
			Arrays.fill(map_tmp[i], null);
	}
}