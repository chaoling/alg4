/***********************************************************************************
 * Modification of Quick 3way circular suffix array sort
 *
 ***********************************************************************************/

public class Quick3CSA {
    private static final int CUTOFF =  16;   // cutoff to insertion sort

    // sort the array indices[] of suffix indices for string
    public static void sort(String a, int[] indices) {
        sort(a, indices, 0, indices.length-1, 0);
    }

    // return the dth character of suffix starting at s[sid],
    // -1 if d = length of s
    private static int charAt(String s, int sid, int d) {
        int N = s.length();
        assert d >= 0 && d <= N; //[0, N-1]
        if (d == N) return -1;
        int idx = sid + d;
        if (idx >= N) {
            //wrap around
            idx = idx - N;
        }
        return s.charAt(idx);
    }


    // 3-way string quicksort inds[lo..hi] starting at dth character
    private static void sort(String a, int[] indices, int lo, int hi, int d) {
        // int[] indices is a symble table key to the circular strings[] a0 a1 a2 ...
        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, indices, lo, hi, d);
            return;
        }
        // 3 partitions: ...lt...gt...
        // middle region is the equals
        int lt = lo, gt = hi;
        // select the pivot point v
        int v = charAt(a, indices[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int w = charAt(a, indices[i], d);
            if      (w < v) exch(indices, i++, lt++);
            else if (w > v) exch(indices, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
        sort(a, indices, lo, lt-1, d);
        if (v >= 0) sort(a, indices, lt, gt, d+1);
        sort(a, indices, gt+1, hi, d);
    }



    // sort from a[lo] to a[hi], starting at the dth character
    private static void insertion(String a, int[] indices, int lo, int hi, int d) {
        //first two param a and int[] determines the 
        //string[] to be sorted
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a, indices[j], indices[j-1], d); j--)
            //it is already sorted up to position i
                exch(indices, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // compare two suffixes, starting at character d
    private static boolean less(String a, int sid1, int sid2, int d) {
        for (int i = d; i < a.length(); i++) {
            int c1 = charAt(a, sid1, i);
            int c2 = charAt(a, sid2, i);
            if (c1 < c2) {
                return true;
            } else if (c2 < c1) {
                return false;
            } //continue to d+1 if c1==c2
        }
        return false;
    }
}