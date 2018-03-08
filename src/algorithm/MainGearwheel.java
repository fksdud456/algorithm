package algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class MainGearwheel {
	private static ArrayList<LinkedList<Integer>> q;
	private static final int CHAIN = 8;
	private static final int S = 1;
	private static final int l = 6;
	private static final int r = 2;

	public static void main(String[] args) throws Exception {
		int i;
		Scanner sc = new Scanner(System.in);

		q = new ArrayList<>();
		LinkedList<Integer> a;

		for (i = 0; i < 4; i++) {
			a = new LinkedList<>();
			String temp = sc.nextLine();
			for (int j = 0; j < CHAIN; j++) {
				a.add(temp.charAt(j) - 48);
			}
			q.add(a);
		}

		int K = Integer.parseInt(sc.nextLine());
		int num, dir;
		for (i = 0; i < K; i++) {
			num = sc.nextInt();
			dir = sc.nextInt();

			num = num - 1;
			rotateLeft(num, dir);
			rotateRight(num, dir);
			int peekNum;
			if (dir == 1) {
				peekNum = q.get(num).removeLast();
				q.get(num).addFirst(peekNum);
			} else {
				peekNum = q.get(num).removeFirst();
				q.get(num).addLast(peekNum);
			}
		}

		LinkedList<Integer> temp;
		int sum = 0;
		for (i = 0; i < 4; i++) {
			temp = q.get(i);
			if (temp.peek() == S)
				sum += Math.pow(2, i);
		}

		System.out.print(sum);
	}

	public static void rotateLeft(int num, int dir) {
		if (num - 1 < 0)
			return;

		if (q.get(num).get(l) != q.get(num - 1).get(r)) {
			dir = dir == 1 ? -1 : 1;
			rotateLeft(num - 1, dir);
			int peekNum;
			if (dir == 1) {
				peekNum = q.get(num - 1).removeLast();
				q.get(num - 1).addFirst(peekNum);
			} else {
				peekNum = q.get(num - 1).removeFirst();
				q.get(num - 1).addLast(peekNum);
			}
		}
	}

	public static void rotateRight(int num, int dir) {
		if ((num + 1) >= 4)
			return;

		if (q.get(num).get(r) != q.get(num + 1).get(l)) {
			dir = dir == 1 ? -1 : 1;
			rotateRight(num + 1, dir);
			int peekNum;
			if (dir == 1) {
				peekNum = q.get(num + 1).removeLast();
				q.get(num + 1).addFirst(peekNum);
			} else {
				peekNum = q.get(num + 1).removeFirst();
				q.get(num + 1).addLast(peekNum);
			}
		}
	}

}