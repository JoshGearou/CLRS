package stringsearch;

public class RabinKarp {
    int prime = 101;

    private int findSubstring(String str, String pattern) {
        if (str == null || pattern == null || pattern.length() > str.length()) {
            return -1;
        }

        int n = str.length();
        int m = pattern.length();
        long strHash = calculateHash(str, m-1);
        long patternHash = calculateHash(pattern, m-1);
        for (int i=1; i<=n-m+1; i++) {
            if (strHash == patternHash && pattern.equals(str.substring(i-1, i+m-1))) {
                return i-1;
            } else if (i < n-m+1) {
                strHash = recalculateHash(str, i-1, strHash, i+m-1, m);
            }
        }
        return -1;
    }

    private long calculateHash(String str, int end) {
        long hash = 0;
        for (int i=0; i<=end; i++) {
            hash+=(str.charAt(i) * Math.pow(prime, i));
        }
        return hash;
    }

    private long recalculateHash(String str, int oldIndex, long oldHash, int newIndex, int patternLen) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash = newHash / prime;
        newHash+=(str.charAt(newIndex) * Math.pow(prime, patternLen-1));
        return newHash;
    }
}
