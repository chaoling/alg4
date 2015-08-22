import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, 
    // reading from standard input and writing to standard output
    private static CircularSuffixArray mCSA;
    private static final int R = 256;
    
    public static void encode() {
        String s = BinaryStdIn.readString();
        int N = s.length();
        mCSA = new CircularSuffixArray(s);
        int idx = -1;
        for (int i = 0; i < N; i++) {
            if (mCSA.index(i) == 0) {
                idx = i;
                break;
            }
        }
        
        assert idx != -1;
        BinaryStdOut.write(idx, 32);
        for (int j = 0; j < N; j++) {
            BinaryStdOut.write(charAt(s, mCSA.index(j), N-1), 8);
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler decoding, 
    // reading from standard input and writing to standard output
    public static void decode() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        int N = t.length();
        int[] next = new int[N];
        char[] col = t.toCharArray();
        Arrays.sort(col);
        int[] indices = new int[N];
        for (int i = 0; i < N; i++) {
            indices[i] = i;
        }
        int i = 0;
        while (i < N) {
            //go through the first col and 
            //construct the next array from 
            //the corresponding last col in order 
            char c = col[i];
            List<Integer> ids = indicesOf(t, c, indices);
            for (int j = 0; j < ids.size(); j++) {
                next[i] = ids.get(j);
                indices[next[i]] = -1;
                i++;
            }
        }
        //now decode
        int nx = first;
        for (i = 0; i < N; i++) {
            BinaryStdOut.write(col[nx], 8);
            nx = next[nx];
        }
        BinaryStdOut.close();
        
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            //System.err.println("bw encoding...");
            encode();
        }
        else if (args[0].equals("+")) {
            //System.err.println("bw decoding...");
            decode();
        }
        else throw new IllegalArgumentException("Illegal command line argument");
    }
    
    private static int charAt(String a, int sid, int d) {
        //compute char for csa string a
        //sid -> start index of the string
        //  d -> dth char starting from the sid
        int N = a.length();
        assert d >= 0 && d < N;
        //if (d == N) return -1;
        int idx = sid + d;
        if (idx >= N) {
            idx = idx - N;
        }
        return a.charAt(idx);
    }
    
    private static List<Integer> indicesOf(String s, char c, int[] indices) {
        //find the all occurence of char c in string t
        List<Integer> res = new ArrayList<Integer>();
        for (int i : indices) {
            if (i == -1 || s.charAt(i) != c) continue;
            res.add(i);
        }
        return res;
    }
}