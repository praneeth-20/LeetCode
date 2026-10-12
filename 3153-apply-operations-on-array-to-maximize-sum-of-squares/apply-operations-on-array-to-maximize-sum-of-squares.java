class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 31; i++) {
                if ((x & (1 << i)) != 0) {
                    cnt[i]++;
                }
            }
        }
        
        long ans = 0;
        long mod = 1000000007;
        
        while (k > 0) {
            long val = 0;
            for (int i = 0; i < 31; i++) {
                if (cnt[i] > 0) {
                    val |= (1 << i);
                    cnt[i]--;
                }
            }
            ans = (ans + (val * val) % mod) % mod;
            k--;
        }
        
        return (int) ans;
    }
}