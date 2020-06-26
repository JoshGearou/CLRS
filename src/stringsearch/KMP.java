package stringsearch;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    public List<Integer> KMPSearch(String str, String pattern) {
        List<Integer> indices = new ArrayList<>();
        if (pattern.isEmpty()) {
            return indices;
        }
        int[] lps = createLps(pattern);
        int i = 0;
        int j = 0;
        while (i < str.length() && j < lps.length) {
            if (str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            }

            if (j == lps.length) {
                indices.add(i - lps.length);
                j = lps[j - 1];

            }
        }
        return indices;
    }

    public int[] createLps(String pattern) {
        char[] pat = pattern.toCharArray();
        int[] lps = new int[pat.length];

        int i = 1;
        int len = 0;
        while (i < pat.length) {
            if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len == 0) {
                    lps[i] = 0;
                    i++;
                } else {
                    len = lps[len - 1];
                }
            }
        }
        return lps;
    }
}
