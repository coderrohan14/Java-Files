package DP;

import java.util.*;

/*
    Contains two functions for getting the Longest Increasing Subsequence(LIS) in an array.
    DP approach is O(n^2) and binary search approach which uses tail array is O(n*logn).
*/
public class LIS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int ans = lis(arr, n);
        System.out.println(ans);
    }

    static int lisDP(int[] arr, int n) { // O(n^2)
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = Integer.MIN_VALUE;
        for (Integer e : dp) {
            res = Math.max(res, e);
        }
        return res;
    }

    static int lis(int[] arr, int n) { // O(n*logn)
        int[] tail = new int[n];
        int len = 1;
        tail[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > tail[len - 1]) {
                tail[len] = arr[i];
                len++;
            } else {
                int c = ceilIdx(tail, 0, len - 1, arr[i]);
                tail[c] = arr[i];
            }
        }
        return len;
    }

    static int ceilIdx(int[] tail, int b, int e, int x) { // function for finding ceil of x
        while (e > b) {
            int m = (e + b) / 2;
            if (tail[m] >= x)
                e = m;
            else
                b = m + 1;
        }
        return e;
    }
}
