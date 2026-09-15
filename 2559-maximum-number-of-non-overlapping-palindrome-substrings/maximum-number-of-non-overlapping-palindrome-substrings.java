class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];
        
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            
            for (int j = 0; i - j >= 0 && i + j < n; j++) {
                if (s.charAt(i - j) != s.charAt(i + j)) break;
                if (2 * j + 1 >= k) {
                    dp[i + j + 1] = Math.max(dp[i + j + 1], dp[i - j] + 1);
                    break;
                }
            }
            
            for (int j = 0; i - j >= 0 && i + j + 1 < n; j++) {
                if (s.charAt(i - j) != s.charAt(i + j + 1)) break;
                if (2 * j + 2 >= k) {
                    dp[i + j + 2] = Math.max(dp[i + j + 2], dp[i - j] + 1);
                    break;
                }
            }
        }
        
        return dp[n];
    }
}