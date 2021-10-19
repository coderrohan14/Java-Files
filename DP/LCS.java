package DP;

import java.util.*;
import java.lang.*;

public class LCS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] a = new int[n + 1], b = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            b[i] = sc.nextInt();
        }
        int ans = lcs(a, b, n, m);
        System.out.println(ans);
    }

    public static int lcs(int[] a, int[] b, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            dp[i][0] = 0;
        for (int i = 1; i <= m; i++)
            dp[0][i] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a[i] == b[j]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[n][m];
    }
}
