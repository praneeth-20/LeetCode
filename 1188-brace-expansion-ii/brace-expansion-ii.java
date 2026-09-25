class Solution {
    public List<String> braceExpansionII(String expression) {
        Queue<String> q = new LinkedList<>();
        Set<String> result = new TreeSet<>();
        Set<String> seen = new HashSet<>();
        
        q.offer(expression);
        seen.add(expression);
        
        while (!q.isEmpty()) {
            String curr = q.poll();
            
            if (curr.indexOf('{') == -1) {
                result.add(curr);
                continue;
            }
            
            int left = 0;
            int right = 0;
            
            while (curr.charAt(right) != '}') {
                if (curr.charAt(right) == '{') {
                    left = right;
                }
                right++;
            }
            
            String before = curr.substring(0, left);
            String after = curr.substring(right + 1);
            String[] parts = curr.substring(left + 1, right).split(",");
            
            for (String part : parts) {
                String nextStr = before + part + after;
                if (seen.add(nextStr)) {
                    q.offer(nextStr);
                }
            }
        }
        
        return new ArrayList<>(result);
    }
}