package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MainHide {
	private static boolean visit[];
	private static int answer;
	private static int N, K;
	private final static int MAXDIS = 100000;

	public static void main(String[] args) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visit = new boolean[MAXDIS + 1];
		answer = 0;

		bfs(N);

		System.out.println(answer);
	}

	public static void bfs(int cur) {
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> qCnt = new LinkedList<>();

		q.add(cur);
		qCnt.add(0);

		int tmp, tmpCnt;
		while (!q.isEmpty()) {
			tmp = q.remove();
			tmpCnt = qCnt.remove();

			System.out.println(tmp + ", " + tmpCnt);

			visit[tmp] = true;

			if (tmp == K) {
				answer = tmpCnt;
				return;
			}

			if (tmp + 1 >= 0 && tmp + 1 <= MAXDIS && visit[tmp + 1] == false) {
				q.add(tmp + 1);
				qCnt.add(tmpCnt + 1);
			}

			if (tmp - 1 >= 0 && tmp - 1 <= MAXDIS && visit[tmp - 1] == false) {
				q.add(tmp - 1);
				qCnt.add(tmpCnt + 1);
			}

			if (tmp * 2 >= 0 && tmp * 2 <= MAXDIS && visit[tmp * 2] == false) {
				q.add(tmp * 2);
				qCnt.add(tmpCnt + 1);
			}
		}
	}
}