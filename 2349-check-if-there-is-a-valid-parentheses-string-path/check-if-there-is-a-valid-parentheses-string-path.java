class Solution {
    int m, n;
    int[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        
        dp = new int[m][n][m + n];
        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int i, int j, int bal) {
        if (grid[i][j] == '(') {
            bal++;
        } else {
            bal--;
        }
        
        if (bal < 0) return false;
        
        int rem = (m - 1 - i) + (n - 1 - j);
        if (bal > rem) return false;
        
        if (i == m - 1 && j == n - 1) {
            return bal == 0;
        }
        
        if (dp[i][j][bal] != 0) {
            return dp[i][j][bal] == 1;
        }
        
        boolean res = false;
        if (i < m - 1) {
            res = dfs(grid, i + 1, j, bal);
        }
        if (!res && j < n - 1) {
            res = dfs(grid, i, j + 1, bal);
        }
        
        dp[i][j][bal] = res ? 1 : -1;
        return res;
    }
}