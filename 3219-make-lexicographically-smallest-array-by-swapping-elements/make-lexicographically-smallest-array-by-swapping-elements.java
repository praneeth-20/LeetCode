class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        
        Arrays.sort(arr, (x, y) -> Integer.compare(x[0], y[0]));
        
        int[] ans = new int[n];
        int l = 0;
        
        while (l < n) {
            int r = l + 1;
            while (r < n && arr[r][0] - arr[r - 1][0] <= limit) {
                r++;
            }
            
            int[] pos = new int[r - l];
            for (int k = 0; k < r - l; k++) {
                pos[k] = arr[l + k][1];
            }
            
            Arrays.sort(pos);
            
            for (int k = 0; k < r - l; k++) {
                ans[pos[k]] = arr[l + k][0];
            }
            
            l = r;
        }
        
        return ans;
    }
}