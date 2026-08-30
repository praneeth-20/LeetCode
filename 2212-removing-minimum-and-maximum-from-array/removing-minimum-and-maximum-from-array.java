class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        
        int minIndex = 0;
        int maxIndex = 0;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        int first = Math.min(minIndex, maxIndex);
        int second = Math.max(minIndex, maxIndex);
        
        return Math.min(Math.min(second + 1, n - first), first + 1 + n - second);
    }
}