package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SolutionHome {
    static int Answer, N, M;
    static int map[][];
    static int hCnt;
    public static void main(String args[]) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
 
        int T, i, j;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            Answer = 0;
            int MAXHome = 0;
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
 
            checkMap(k-1);
            System.out.println("#" + test_case + " " + Answer);
        }
    }
 
    private static void checkMap(int k) {
        if (k < 1)
            return;
 
        Answer = 0;
        int cnt;
        for (int x = 1; x < N-1; x++) {
            for (int y = 1; y < N-1; y++) {
                cnt = checkService(k - 1, x, y);
                if (Answer < cnt)
                    Answer = cnt;
            }
        }
        
        if ((Answer * M) - (k * k + ((k - 1) * (k - 1))) < 0) {
            checkMap(--k);
        } 
    }
 
    private static int checkService(int k, int x, int y) {
       hCnt = 0;
        for (int i = -k; i <= k; i++) {
            for (int j = -k; j <= k; j++) {
                if (x + i < 0 || x + i >= N || y + j < 0 || y + j >= N || Math.abs(i) + Math.abs(j) > k)
                    continue;
                if (map[x + i][y + j] == 1) 
                    hCnt++;                
            }
        }
        return hCnt;
    } 
}