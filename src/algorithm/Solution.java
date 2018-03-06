package algorithm;

import java.util.Scanner;

public class Solution {
	static int Answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		while (T-- > 0) {
			int N = Integer.parseInt(sc.nextLine());

			System.out.println("#" + T + " " + Answer);

		}
		sc.close();
	}
}