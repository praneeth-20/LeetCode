class Solution {
    public int scoreOfParentheses(String s) {
        int res = 0;
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                count--;
                if (s.charAt(i - 1) == '(') {
                    res += 1 << count;
                }
            }
        }
        
        return res;
    }
}