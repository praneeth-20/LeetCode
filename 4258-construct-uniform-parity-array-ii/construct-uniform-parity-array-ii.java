class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        
        for(int val : nums1) {
            if(val % 2 == 0) {
                if(val < minEven) minEven = val;
            } else {
                if(val < minOdd) minOdd = val;
            }
        }
        
        if(minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }
        
        return minOdd < minEven;
    }
}