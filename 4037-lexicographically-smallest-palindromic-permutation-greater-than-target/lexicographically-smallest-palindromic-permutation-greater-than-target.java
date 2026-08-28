import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int oddCount = 0;
        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                mid = (char) (i + 'a');
            }
        }
        
        if (oddCount > 1) {
            return "";
        }
        
        int[] M = new int[26];
        for (int i = 0; i < 26; i++) {
            M[i] = count[i] / 2;
        }
        
        int n = s.length();
        int half = n / 2;
        
        int max_i = 0;
        int[] tempM = Arrays.copyOf(M, 26);
        while (max_i < half) {
            char c = target.charAt(max_i);
            if (tempM[c - 'a'] > 0) {
                tempM[c - 'a']--;
                max_i++;
            } else {
                break;
            }
        }
        
        for (int i = max_i; i >= 0; i--) {
            if (i == half) {
                String left = target.substring(0, half);
                StringBuilder sb = new StringBuilder(left);
                if (n % 2 != 0) {
                    sb.append(mid);
                }
                for (int j = half - 1; j >= 0; j--) {
                    sb.append(left.charAt(j));
                }
                String P = sb.toString();
                if (P.compareTo(target) > 0) {
                    return P;
                }
            } else {
                int[] rem = Arrays.copyOf(M, 26);
                for (int j = 0; j < i; j++) {
                    rem[target.charAt(j) - 'a']--;
                }
                
                char tc = target.charAt(i);
                char nextC = 0;
                for (int c = tc - 'a' + 1; c < 26; c++) {
                    if (rem[c] > 0) {
                        nextC = (char) (c + 'a');
                        break;
                    }
                }
                
                if (nextC != 0) {
                    StringBuilder left = new StringBuilder(target.substring(0, i));
                    left.append(nextC);
                    rem[nextC - 'a']--;
                    
                    for (int c = 0; c < 26; c++) {
                        while (rem[c] > 0) {
                            left.append((char) (c + 'a'));
                            rem[c]--;
                        }
                    }
                    
                    StringBuilder sb = new StringBuilder(left);
                    if (n % 2 != 0) {
                        sb.append(mid);
                    }
                    for (int j = half - 1; j >= 0; j--) {
                        sb.append(left.charAt(j));
                    }
                    return sb.toString();
                }
            }
        }
        
        return "";
    }
}