/***********************************************************************************
 *  Compilation: javac LSDCSA.java
 *  Execution:   java LSDCSA < input.txt
 *
 *  LSD radix sort with indices on circular suffix array
 * 
 *  Based on LSD radix sort algs4 
 *
 *    - Sort a circular suffix array of a string of length N 
 *      extended ASCII strings (R = 256), each of length N.
 *
 *    - Sort an int[] array of N 32-bit integers, treating each integer as 
 *      a sequence of W = 4 bytes (R = 256).
 *
 *  Uses extra space proportional to N + R.
 *
 *
 *  % java LSD < circularsuffixarray.txt
 *  abcdefg
 *  bcdefga
 *  cdefgab
 *  defgabc
 *  efgabcd
 *  fgabcde
 *  gabcdef
 *
 ***********************************************************************************/

public class LSDCSA {
    private static final int BITS_PER_BYTE = 8;
    
    //LSD radix sort circular suffix array
    private void csaRadixSort(String a, int[] indices) {
        int N = a.length();
        int R = 256; // extend ASCII alphabet size
        assert indices.length == N;
        int[] aux = new int[N];
        
        for (int d = N-1; d >= 0; d--) {
            //sort by key-indexed counting on dth char
            //compute freq counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) {
                //count[0] is 0
                count[charAt(a, indices[i], d) + 1]++;
            }
            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }
            // move data or indices
            for (int i = 0; i < N; i++) {
                aux[count[charAt(a, indices[i], d)]++] = indices[i];
            }
            
            //copy back
            for (int i = 0; i < N; i++) {
                indices[i] = aux[i];
            }
        }
    }

    // LSD radix sort
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

            // copy back
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    // LSD sort an array of integers, treating each int as 4 bytes
    // assumes integers are nonnegative
    // [ 2-3x faster than Arrays.sort() ]
    public static void sort(int[] a) {
        int BITS = 32;                 // each int is 32 bits 
        int W = BITS / BITS_PER_BYTE;  // each int is 4 bytes
        int R = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        int MASK = R - 1;              // 0xFF

        int N = a.length;
        int[] aux = new int[N];

        for (int d = 0; d < W; d++) {         

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) {           
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == W-1) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }

            // move data
            for (int i = 0; i < N; i++) {
                int c = (a[i] >> BITS_PER_BYTE*d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int N = a.length;

        // check that strings have fixed length
        int W = a[0].length();
        for (int i = 0; i < N; i++)
            assert a[i].length() == W : "Strings must have fixed length";

        // sort the strings
        sort(a, W);

        // print results
        for (int i = 0; i < N; i++)
            StdOut.println(a[i]);
    }
    
    private static int charAt(String a, int sid, int d) {
        //compute char for csa string a
        //sid -> start index of the string
        // d-> dth char starting from the sid
        
        int N = a.length();
        assert d >= 0 && d <= N;
        if (d == N) return -1;
        int idx = sid + d;
        if (idx >= N) {
            idx -= N;
        }
        return a.charAt(idx);
    }
        
}