package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainHide {
	private static int visit[];
	private static int answer;
	private static int N, K;
	private final static int MAXDIS = 100000;

	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visit = new int[MAXDIS + 1];
		answer = 0;

		bfs(N);
		System.out.println(answer);
	}

	//qCnt 
	public static void bfs(int cur) {
		Queue<Integer> q = new LinkedList<>();
		q.add(cur);
		int tmp, tmpCnt;
		while (!q.isEmpty()) {
			tmp = q.remove();
			tmpCnt = visit[tmp];

			if (tmp == K) {
				answer = tmpCnt;
				return;
			}

			if (tmp + 1 >= 0 && tmp + 1 <= MAXDIS && visit[tmp + 1] == 0) {
				q.add(tmp + 1);
				visit[tmp + 1] = (tmpCnt + 1);
			}

			if (tmp - 1 >= 0 && tmp - 1 <= MAXDIS && visit[tmp - 1] == 0) {
				q.add(tmp - 1);
				visit[tmp - 1] = (tmpCnt + 1);
			}

			if (tmp * 2 >= 0 && tmp * 2 <= MAXDIS && visit[tmp * 2] == 0) {
				q.add(tmp * 2);
				visit[tmp * 2] = (tmpCnt + 1);
			}
		}
	}
}