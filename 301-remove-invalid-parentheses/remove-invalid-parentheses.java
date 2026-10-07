class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        int left = 0;
        int right = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if (left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }

        dfs(s, 0, left, right, res);
        return res;
    }

    private void dfs(String s, int start, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            if (isValid(s)) {
                res.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                String nextStr = s.substring(0, i) + s.substring(i + 1);

                if (right > 0 && s.charAt(i) == ')') {
                    dfs(nextStr, i, left, right - 1, res);
                } else if (left > 0 && s.charAt(i) == '(') {
                    dfs(nextStr, i, left - 1, right, res);
                }
            }
        }
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}