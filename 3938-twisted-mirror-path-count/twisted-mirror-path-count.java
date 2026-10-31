class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1000000007;
        
        int[][][] dest = new int[m][n][2];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    dest[i][j][0] = i * n + j;
                    dest[i][j][1] = i * n + j;
                } else {
                    dest[i][j][0] = (i + 1 < m) ? dest[i + 1][j][1] : -1;
                    dest[i][j][1] = (j + 1 < n) ? dest[i][j + 1][0] : -1;
                }
            }
        }
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;
                
                if (j + 1 < n) {
                    int nxt = dest[i][j + 1][0];
                    if (nxt != -1) {
                        dp[nxt / n][nxt % n] = (dp[nxt / n][nxt % n] + dp[i][j]) % mod;
                    }
                }
                
                if (i + 1 < m) {
                    int nxt = dest[i + 1][j][1];
                    if (nxt != -1) {
                        dp[nxt / n][nxt % n] = (dp[nxt / n][nxt % n] + dp[i][j]) % mod;
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1];
    }
}