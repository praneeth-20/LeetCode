import java.util.TreeSet;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] costs = new long[n];
        
        TreeSet<int[]> left = new TreeSet<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        TreeSet<int[]> right = new TreeSet<>((a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        
        long lSum = 0;
        long rSum = 0;

        for (int i = 0; i < n; i++) {
            int[] curr = {nums[i], i};
            
            if (left.isEmpty() || nums[i] <= left.last()[0]) {
                left.add(curr);
                lSum += nums[i];
            } else {
                right.add(curr);
                rSum += nums[i];
            }

            if (i >= x) {
                int[] old = {nums[i - x], i - x};
                if (left.remove(old)) {
                    lSum -= nums[i - x];
                } else {
                    right.remove(old);
                    rSum -= nums[i - x];
                }
            }

            while (left.size() > (x + 1) / 2) {
                int[] last = left.pollLast();
                lSum -= last[0];
                right.add(last);
                rSum += last[0];
            }
            
            while (left.size() < (x + 1) / 2 && !right.isEmpty()) {
                int[] first = right.pollFirst();
                rSum -= first[0];
                left.add(first);
                lSum += first[0];
            }

            if (i >= x - 1) {
                long median = left.last()[0];
                long leftCost = median * left.size() - lSum;
                long rightCost = rSum - median * right.size();
                costs[i] = leftCost + rightCost;
            }
        }

        long[][] dp = new long[k + 1][n + 1];
        
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = (long) 1e18;
            }
        }

        for (int i = 1; i <= k; i++) {
            for (int j = x; j <= n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j - x] + costs[j - 1]);
            }
        }

        return dp[k][n];
    }
}