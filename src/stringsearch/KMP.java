package stringsearch;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    public List<Integer> KMPSearch(String text, String pattern) {
        int[] lps = createLps(pattern);
        List<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int i = 0;
        int j = 0;
        while (i < n) {
            while (j >= 0 && text.charAt(i) != pattern.charAt(j)) {
                j = lps[j];
            }
            i++;
            j++;
            if (j == m) {
                indices.add(i - j);
                j = lps[j];
            }
        }
        return indices;
    }

    public int[] createLps(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m+1];
        int i = 0;
        int j = -1;
        lps[0] = -1;

        while (i < m) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j];
            }
            i++;
            j++;
            lps[i] = j;
        }
        return lps;
    } 
}
