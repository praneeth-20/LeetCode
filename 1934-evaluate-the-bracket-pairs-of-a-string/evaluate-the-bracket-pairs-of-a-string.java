import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> k : knowledge) {
            map.put(k.get(0), k.get(1));
        }
        
        StringBuilder res = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            if (s.charAt(i) == '(') {
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                String key = s.substring(i + 1, j);
                if (map.containsKey(key)) {
                    res.append(map.get(key));
                } else {
                    res.append("?");
                }
                i = j + 1;
            } else {
                res.append(s.charAt(i));
                i++;
            }
        }
        
        return res.toString();
    }
}