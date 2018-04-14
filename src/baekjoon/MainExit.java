import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Ball {
	int Rx;
	int Ry;
	int Bx;
	int By;
	int dir;
	int cnt;

	public Ball(int rx, int ry, int bx, int by, int dir, int cnt) {
		Rx = rx;
		Ry = ry;
		Bx = bx;
		By = by;
		this.dir = dir;
		this.cnt = cnt;
	}
}
public class Main {
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int N, M;
	static int min;
	static char map[][];
	static char END, EMPTY, HOLE, R, B;
	static int RX, RY, Bx, By;

	public static void main(String[] args) throws Exception {
		END = '#';
		EMPTY =  '.';
		HOLE = 'O';
		R = 'R';
		B =  'B';
		min = -1;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		String tmp = "";
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] =  tmp.charAt(j);
				if (map[i][j] == R) {
					RX = i;
					RY = j;
					map[i][j] = EMPTY;
				} else if (map[i][j] == B) {
					Bx = i;
					By = j;
					map[i][j] = EMPTY;
				}
			}
		}

		bfs();
		System.out.println(min);
	}

	static void bfs() {
		Queue<Ball> q = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			Ball b = new Ball(RX, RY, Bx, By, i, 0);
			q.add(b);
		}

		Ball tmp = null;
		boolean res;
		while (!q.isEmpty()) {
			tmp = q.poll();
            
	        if (isRedFirst(tmp)) {
				moveRed(tmp);
			} else {
				moveBlue(tmp);
			}
            
			if (map[tmp.Rx][tmp.Ry] == HOLE) {
				if (map[tmp.Bx][tmp.By] == HOLE)
					continue;
				min = tmp.cnt+1;
				return;
			}
            
			if (map[tmp.Bx][tmp.By] == HOLE)
				continue;
		
			if (tmp.cnt >= 10) {
				return;
			}

			for (int i = 0; i < 4; i++) {
				q.add(new Ball(tmp.Rx, tmp.Ry, tmp.Bx, tmp.By, i, tmp.cnt + 1));
			}
		}
	}

	private static void moveBlue(Ball tmp) {
		int tx = tmp.Bx + (dx[tmp.dir]);
		int ty = tmp.By + (dy[tmp.dir]);
		
		while (tx >= 1 && tx < N-1 && ty >= 1 && ty < M-1) {
			if (map[tx][ty] == EMPTY) {
				tmp.Bx = tx;
				tmp.By = ty;
				tx = tmp.Bx + (dx[tmp.dir]);
				ty = tmp.By + (dy[tmp.dir]);
			} else if (map[tx][ty] == HOLE) {
				tmp.Bx = tx;
				tmp.By = ty;
				break;
			} else {
				break;
			}
		}

		tx = tmp.Rx + (dx[tmp.dir]);
		ty = tmp.Ry + (dy[tmp.dir]);
		while (tx >= 1 && tx < N-1 && ty >= 1 && ty < M-1) {
			if (tx == tmp.Bx && ty == tmp.By) {
				if (map[tx][ty] == HOLE) {
					tmp.Rx = tx;
					tmp.Ry = ty;
				}
				break;
			}
			if (map[tx][ty] == EMPTY) {
				tmp.Rx = tx;
				tmp.Ry = ty;
				tx = tmp.Rx + (dx[tmp.dir]);
				ty = tmp.Ry + (dy[tmp.dir]);
			} else if (map[tx][ty] == HOLE) {
				tmp.Rx = tx;
				tmp.Ry = ty;
				break;
			} else {
				break;
			}
		}
	}

	private static void moveRed(Ball tmp) {
		int tx = tmp.Rx + (dx[tmp.dir]);
		int ty = tmp.Ry + (dy[tmp.dir]);
		while (tx >= 1 && tx < N-1 && ty >= 1 && ty < M-1) {
			if (map[tx][ty] == EMPTY) {
				tmp.Rx = tx;
				tmp.Ry = ty;

				tx = tmp.Rx + (dx[tmp.dir]);
				ty = tmp.Ry + (dy[tmp.dir]);
			} else if (map[tx][ty] == HOLE) {
				tmp.Rx = tx;
				tmp.Ry = ty;
				break;
			} else {
				break;
			}
		}

		tx = tmp.Bx + (dx[tmp.dir]);
		ty = tmp.By + (dy[tmp.dir]);

		while (tx >= 1 && tx < N-1 && ty >= 1 && ty < M-1) {
			if (tx == tmp.Rx && ty == tmp.Ry) {
				if (map[tx][ty] == HOLE) {
					tmp.Bx = tx;
					tmp.By = ty;
				}
				break;
			}
			if (map[tx][ty] == EMPTY) {
				tmp.Bx = tx;
				tmp.By = ty;
				tx = tmp.Bx + (dx[tmp.dir]);
				ty = tmp.By + (dy[tmp.dir]);
			} else if (map[tx][ty] == HOLE) {
				tmp.Bx = tx;
				tmp.By = ty;
				break;
			} else {
				break;
			}
		}
	}
	
	static boolean isRedFirst(Ball tmp) {
		boolean res = false;
		if (tmp.dir == 0) {
			if (tmp.Rx > tmp.Bx) {
				res = true;
			}
		} else if (tmp.dir == 1) {
			if (tmp.Rx < tmp.Bx) {
				res = true;
			}

		} else if (tmp.dir == 2) {
			if (tmp.Ry > tmp.By) {
				res = true;
			}
		} else {
			if (tmp.Ry < tmp.By) {
				res = true;
			}
		}
		return res;
	}
}

