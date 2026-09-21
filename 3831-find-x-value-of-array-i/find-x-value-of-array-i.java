class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] count = new long[k];
        
        for (int x : nums) {
            int rem = x % k;
            long[] nextCount = new long[k];
            
            for (int i = 0; i < k; i++) {
                if (count[i] > 0) {
                    nextCount[(i * rem) % k] += count[i];
                }
            }
            
            nextCount[rem]++;
            
            for (int i = 0; i < k; i++) {
                count[i] = nextCount[i];
                ans[i] += count[i];
            }
        }
        
        return ans;
    }
}