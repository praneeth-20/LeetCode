class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        
        // Step 1: Count character frequencies in s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Step 2: Greedily match target as far as possible
        int matchLen = 0;
        while (matchLen < n && count[target.charAt(matchLen) - 'a'] > 0) {
            count[target.charAt(matchLen) - 'a']--;
            matchLen++;
        }

        // If we matched the entire target, we still need a STRICTLY greater string.
        // So we must step back one character to find a point to increase.
        if (matchLen == n) {
            matchLen--;
            count[target.charAt(matchLen) - 'a']++;
        }

        // Step 3: Backtrack to find the rightmost split point
        for (int i = matchLen; i >= 0; i--) {
            char tChar = target.charAt(i);
            char bestC = '\0';
            
            // Look for the smallest available character strictly greater than tChar
            for (int j = tChar - 'a' + 1; j < 26; j++) {
                if (count[j] > 0) {
                    bestC = (char) (j + 'a');
                    break;
                }
            }

            if (bestC != '\0') {
                // We found a valid divergence point! 
                StringBuilder sb = new StringBuilder();
                
                // 1. Add the matching prefix
                for (int k = 0; k < i; k++) {
                    sb.append(target.charAt(k));
                }
                
                // 2. Add the diverging character
                sb.append(bestC);
                count[bestC - 'a']--;

                // 3. Append the remaining characters in sorted (lexicographically smallest) order
                for (int j = 0; j < 26; j++) {
                    while (count[j] > 0) {
                        sb.append((char) (j + 'a'));
                        count[j]--;
                    }
                }
                return sb.toString();
            }

            // If we can't diverge at 'i', we must backtrack. 
            // Put target[i-1] back into the available pool to evaluate the previous index.
            if (i > 0) {
                count[target.charAt(i - 1) - 'a']++;
            }
        }

        // Step 4: No valid permutation exists
        return "";
    }
}