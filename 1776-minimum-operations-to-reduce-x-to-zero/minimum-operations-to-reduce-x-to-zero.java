class Solution {
    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        
        int maxLen = -1;
        int curr = 0;
        int i = 0;
        int n = nums.length;
        
        for (int j = 0; j < n; j++) {
            curr += nums[j];
            
            while (curr > target && i <= j) {
                curr -= nums[i];
                i++;
            }
            
            if (curr == target) {
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        
        if (maxLen != -1) {
            return n - maxLen;
        }
        return -1;
    }
}