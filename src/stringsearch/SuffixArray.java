package stringsearch;

import java.util.Arrays;

class Suffix {
    int index;
    int rank;
    int next;

    public Suffix(int index, int rank, int next) {
        this.index = index; // the original array index of the suffix
        this.rank = rank; // the current ordering of this suffix.
        this.next = next; // Used to compare the next characters of the suffix.
    }
}

public class SuffixArray {

    public int[] buildSuffixArray(String text) {
        int n = text.length();
        Suffix[] su = new Suffix[n];
        // Store the initial suffixes.  The class is
        // needed to sort the suffixes alphabetically
        // and maintain their original indexes while sorting.
        for (int i=0; i<n; i++) {
            su[i] = new Suffix(i, text.charAt(i) - '$', 0);
        }

        // fill out the next character for
        // comparison for each suffix.
        for (int i=0; i<n; i++) {
            su[i].next = (i+1) >= n ? -1 : su[i+1].rank;
        }
        // sort the suffixes according to the first two characters in each string.
        // rank is the first character and next is the second.
        Arrays.sort(su, (x,y) -> x.rank != y.rank ? x.rank - y.rank : x.next - y.next);

        // used to get the index in su[] from the original index in text.
        int[] indices = new int[n];

        for (int len=4; len<2*n; len*=2) {
            int rank = 0;
            int prev = su[0].rank;
            su[0].rank = 0;
            indices[su[0].index] = 0;
            for (int i=1; i<n; i++) {
                if (su[i].rank == prev && su[i].next == su[i-1].next) {
                    prev = su[i].rank;
                    su[i].rank = rank;
                } else {
                    rank++;
                    prev = su[i].rank;
                    su[i].rank = rank;
                }
                indices[su[i].index] = i;
            }

            for (int i=0; i<n; i++) {
                int nextP = su[i].index + (len/2);
                su[i].next = nextP < n ? su[indices[nextP]].rank : -1;
            }

            Arrays.sort(su, (x,y) -> x.rank != y.rank ? x.rank - y.rank : x.next - y.next);
        }

        int[] suf = new int[n];
        for (int i=0; i<suf.length; i++) {
            suf[i] = su[i].index;
        }

        return suf;
    }

    public static void main(String[] args) {
        int[] suf = new SuffixArray().buildSuffixArray("banana");

        for (int index: suf) {
            System.out.println("index: " + index);
        }
    }
}
