import java.util.Arrays;

public class KMP {
    int count = 0;

    public int KMPSearch(String str, String pattern) {
        int n = str.length();
        int m = pattern.length();
        int [] lps = new int[m];
        createLps(pattern, lps);
        System.out.println("j: " + Arrays.toString(lps));
        int i = 0;
        int j = 0;
        while (i < n) {
            if (str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            else {
                if (j != 0) {
                    j = lps[j-1];
                }
                else {
                    i++;
                }
            }

            if (j == m) {
                System.out.println("starting index: " + (i - j));
                count++;
                j = lps[j-1];
            }
        }
        return count;
    }

    public void createLps(String pattern, int [] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = len + 1;
                len++;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len-1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
