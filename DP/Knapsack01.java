package DP;

import java.util.Scanner;

public class Knapsack01 {
    static int n, w;
    static int[] val, wt;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), w = sc.nextInt();
        val = new int[n];
        wt = new int[n];
        dp = new int[n + 1][w + 1];
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();
        Kanpsack(val, wt, n, w, dp);
    }

    static void Kanpsack(int[] val, int[] wt, int n, int w, int[][] dp) {
        for (int i = 0; i <= n; i++)
            dp[i][0] = 0;
        for (int i = 0; i <= w; i++)
            dp[0][i] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (wt[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(val[i - 1] + dp[i - 1][j - wt[i - 1]], dp[i - 1][j]);
                }
            }
        }
        System.out.println(dp[n][w]);
    }
}
