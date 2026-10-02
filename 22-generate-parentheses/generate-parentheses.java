import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String currentString, int openCount, int closeCount, int max) {
        if (currentString.length() == max * 2) {
            result.add(currentString);
            return;
        }

        if (openCount < max) {
            generate(result, currentString + "(", openCount + 1, closeCount, max);
        }
        
        if (closeCount < openCount) {
            generate(result, currentString + ")", openCount, closeCount + 1, max);
        }
    }
}