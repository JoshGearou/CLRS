import java.util.HashMap;

public class DailyCoding2 {

    public int longestSubstring(String s, int k) {
        HashMap<Character, Integer> seen = new HashMap<>();
        if (s == null || s.length() == 0) {
            return 0;
        }
        else if (s.length() < k) {
            return s.length();
        }
        else if (k == 0) {
            return 0;
        }
        int startIndex = 0;
        int endIndex = 0;
        int max = 0;
        int count = 0;
        while (startIndex < s.length()) {
            
            count-=seen.get(s.charAt(startIndex));
            seen.remove(s.charAt(startIndex));
            startIndex = nextIndex(s, startIndex);
        }
        return max;
    }

    public int nextIndex(String s, int index) {
        int next = index + 1;
        while (next < s.length() && s.charAt(next) == s.charAt(index)) {
            next++;
        }
        return next;
    }



    public static void main(String[] args) {
        System.out.println(new DailyCoding2().longestSubstring("abcba", 2));
    }
}
