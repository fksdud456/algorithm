package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainExamsupervisor {
	public static void main(String[] args) throws Exception {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			int A[] = new int[Integer.parseInt(br.readLine())];
			
			String[] a = br.readLine().split(" ");
			for (int i = 0; i < a.length; i++) 
				A[i] = Integer.parseInt(a[i]);
			
			a = br.readLine().split(" ");
			int B = Integer.parseInt(a[0]);
			int C = Integer.parseInt(a[1]);
			
			long count = A.length;
			int cur;
			for (int i = 0; i < A.length; i++) {
				A[i] -= B;
				if(A[i]<0)
					continue;
				cur = A[i] / C;
				count += cur;
				if (A[i] % C == 0)
					continue;
				count++;
			}

			System.out.println(count);
		}
	}
