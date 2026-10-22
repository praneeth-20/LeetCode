class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos = -1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos = i;
                break;
            }
        }
        
        boolean isInc = true;
        boolean isDec = true;
        
        for (int i = 1; i < n; i++) {
            if (nums[(pos + i) % n] != i) {
                isInc = false;
            }
            if (nums[(pos + i) % n] != n - i) {
                isDec = false;
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        if (isInc) {
            if (pos == 0) {
                ans = 0;
            } else {
                ans = Math.min(pos, n - pos + 2);
            }
        }
        
        if (isDec) {
            ans = Math.min(ans, Math.min(n - pos, (pos + 1) % n + 1));
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}