public class CircularSuffixArray {
    private int N;
    private int[] indices; //index table
    
    public CircularSuffixArray(String s) {
     // circular suffix array of s
        if (s == null) throw new NullPointerException();
        this.N = s.length();
        indices = new int[N];
        for (int i = 0; i < N; i++) {
            indices[i] = i;
        }
        
        //csaRadixSort(s,indices);
        Quick3CSA.sort(s, indices);
       
        
    }
    public int length() {
        // length of s
        return N;
        
    }
    public int index(int i) {
        // returns index of ith sorted suffix
        if (i < 0 || i >= length()) throw new IndexOutOfBoundsException();
        return indices[i];
    }
    
    public static void main(String[] args) {
     // unit testing of the methods (optional)
        CircularSuffixArray csa = new CircularSuffixArray("ABRACADABRA!");
        for (int i = 0; i < csa.length(); i++) {
            System.err.print(" " + csa.index(i));
        }
//        System.err.println();
//        for (int i = 0; i < csa.length(); i++) {
//            System.err.print("  "+i);
//            csa.output(s, i);
//            System.err.print("  "+csa.index(i));
//            csa.output(s, csa.index(i));
//            System.err.println();
//        }
    }
    
    private void output(String a, int sid) {
        String st = toCSA(a, sid);
        System.err.print("   "+st);
    }  
    
    private String toCSA(String a, int sid) {
        int len = a.length();
        assert sid >= 0 && sid < len;
        int len1 = len - sid;
        int len2 = sid;
        char[] src = a.toCharArray();
        char[] dest = new char[len];
        System.arraycopy(src, sid, dest, 0, len1);
        System.arraycopy(src, 0, dest, len1, len2);
        return new String(dest);
    }
}