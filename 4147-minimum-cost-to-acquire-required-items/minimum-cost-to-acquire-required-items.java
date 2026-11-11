class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;
        
        long res1 = n1 * c1 + n2 * c2;
        
        long min = Math.min(n1, n2);
        long res2 = min * cb + (n1 - min) * c1 + (n2 - min) * c2;
        
        long max = Math.max(n1, n2);
        long res3 = max * cb;
        
        return Math.min(res1, Math.min(res2, res3));
    }
}