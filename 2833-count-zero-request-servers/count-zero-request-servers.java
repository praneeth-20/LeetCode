import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int q = queries.length;
        int[][] sortedQueries = new int[q][2];
        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[q];
        int[] counts = new int[n + 1];
        int active = 0;
        int left = 0;
        int right = 0;
        
        for (int i = 0; i < q; i++) {
            int end = sortedQueries[i][0];
            int start = end - x;
            
            while (right < logs.length && logs[right][1] <= end) {
                if (counts[logs[right][0]]++ == 0) {
                    active++;
                }
                right++;
            }
            
            while (left < logs.length && logs[left][1] < start) {
                if (--counts[logs[left][0]] == 0) {
                    active--;
                }
                left++;
            }
            
            ans[sortedQueries[i][1]] = n - active;
        }
        
        return ans;
    }
}