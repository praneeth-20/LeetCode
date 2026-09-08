class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1000000007;
        int[] dp = new int[26];
        int total = 0;
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            int current = (total + 1 - dp[index]) % mod;
            
            if (current < 0) {
                current += mod;
            }
            
            dp[index] = (dp[index] + current) % mod;
            total = (total + current) % mod;
        }
        
        return total;
    }
}