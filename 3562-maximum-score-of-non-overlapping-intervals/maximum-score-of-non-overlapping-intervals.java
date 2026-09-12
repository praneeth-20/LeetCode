class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }
        
        Arrays.sort(arr, (a, b) -> Integer.compare(a[1], b[1]));
        
        long[][] dpW = new long[n + 1][5];
        int[][][] dpC = new int[n + 1][5][0];
        
        for (int i = 1; i <= n; i++) {
            int start = arr[i - 1][0];
            int weight = arr[i - 1][2];
            int id = arr[i - 1][3];
            
            int l = 0, r = i - 2, p = 0;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (arr[mid][1] < start) {
                    p = mid + 1;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            
            for (int k = 1; k <= 4; k++) {
                long skipW = dpW[i - 1][k];
                int[] skipC = dpC[i - 1][k];
                
                long takeW = dpW[p][k - 1] + weight;
                int[] prevC = dpC[p][k - 1];
                
                int[] takeC = new int[prevC.length + 1];
                System.arraycopy(prevC, 0, takeC, 0, prevC.length);
                takeC[takeC.length - 1] = id;
                Arrays.sort(takeC);
                
                if (takeW > skipW) {
                    dpW[i][k] = takeW;
                    dpC[i][k] = takeC;
                } else if (skipW > takeW) {
                    dpW[i][k] = skipW;
                    dpC[i][k] = skipC;
                } else {
                    dpW[i][k] = takeW;
                    if (isSmaller(takeC, skipC)) {
                        dpC[i][k] = takeC;
                    } else {
                        dpC[i][k] = skipC;
                    }
                }
            }
        }
        
        return dpC[n][4];
    }
    
    private boolean isSmaller(int[] a, int[] b) {
        int minLen = Math.min(a.length, b.length);
        for (int i = 0; i < minLen; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}