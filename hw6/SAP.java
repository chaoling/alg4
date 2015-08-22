    
public class SAP {
    private static final int INFINITY = Integer.MAX_VALUE;
    private Digraph mG;
    private int mV = 0;
    private int mSap = -1; 
    private int mSapLen = -1; 
   // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        //call the copy constructor 
        //because the digraph is mutable
        //and we want our sap be immutable
        mG = new Digraph(G);  
        mV = mG.V();
    }

   // length of shortest ancestral path 
    //between v and w; -1 if no such path
    public int length(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        findSap(v, w);
        return mSapLen;
    }

   // a common ancestor of v and w 
    //that participates in a shortest ancestral path; 
    //-1 if no such path
    public int ancestor(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        findSap(v, w);
        return mSap;
    }

   // length of shortest ancestral path 
    //between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        findSapAny(v, w);
        return mSapLen;
    }

   // a common ancestor that participates 
    //in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        findSapAny(v, w);
        return mSap;
    }
    
    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int v) {
        if (v < 0 || v >= mV)
            throw new IndexOutOfBoundsException(
            "vertex " + v + " is not between 0 and " + (mV-1));
    }
    
    private void findSap(int v, int w) {
        int res = INFINITY;
        mSap = -1;
        mSapLen = -1;
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(mG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(mG, w);
        for (int ap = 0; ap < mV; ap++) {
             if (bfsV.hasPathTo(ap) && bfsW.hasPathTo(ap)) {
                int lengthV = bfsV.distTo(ap);
                int lengthW = bfsW.distTo(ap);
                if (lengthV + lengthW < res) {
                    mSap = ap;
                    res = lengthV + lengthW;
                }
            }

        }
        if (mSap != -1) {
            mSapLen = res;
        }
    }
    
    private void findSapAny(Iterable<Integer> v, Iterable<Integer> w) {
        int res = INFINITY;
        mSap = -1;
        mSapLen = -1;
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(mG, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(mG, w);
        for (int ap = 0; ap < mV; ap++) {
             if (bfsV.hasPathTo(ap) && bfsW.hasPathTo(ap)) {
                int lengthV = bfsV.distTo(ap);
                int lengthW = bfsW.distTo(ap);
                if (lengthV + lengthW < res) {
                    mSap = ap;
                    res = lengthV + lengthW;
                }
            }
        }
          if (mSap != -1) {
            mSapLen = res;
        }
    }

   // do unit testing of this class
  public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}