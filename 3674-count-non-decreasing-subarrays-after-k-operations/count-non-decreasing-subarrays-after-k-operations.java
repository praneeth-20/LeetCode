class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] q = new int[n];
        int head = 0, tail = -1;
        long ans = 0, cost = 0;
        int r = n - 1;
        
        for (int l = n - 1; l >= 0; l--) {
            while (tail >= head && nums[l] >= nums[q[tail]]) {
                int poppedIdx = q[tail--];
                int nextIdx = (tail >= head) ? q[tail] : r + 1;
                cost += (long) (nums[l] - nums[poppedIdx]) * (nextIdx - poppedIdx);
            }
            q[++tail] = l;
            
            while (cost > k) {
                int last = q[head];
                cost -= (nums[last] - nums[r]);
                if (r == last) {
                    head++;
                }
                r--;
            }
            ans += (r - l + 1);
        }
        
        return ans;
    }
}