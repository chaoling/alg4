import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;

public class WordNet { 
   //store map from synset noun to id: one to many
   private Map<String, Set<Integer>> mIdMap = new HashMap<String, Set<Integer>>(); 
   //A reverse map from id to synset nouns : one to one
   private Map<Integer, String> mSynsetsMap = new HashMap<Integer, String>();
   //A Digraph of hypernyms
   private Digraph mHypernyms;
   //SAP
   private SAP mSap;
   
   // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        
        In in = new In(synsets);
        int V = 0; //it is also the size of our digraph
        while (in.hasNextLine()) {
            //format id, synsets, gloss
            V++;
            String line = in.readLine();
            String[] synsetsLine = line.split(",");
            //Set<String> ts = new TreeSet<String>();
            int id = Integer.parseInt(synsetsLine[0]);
            //System.out.println("id is: "+id);
            //if (mSynsetsMap.get(id) == null) {
                mSynsetsMap.put(id, synsetsLine[1]);
            //}
            for (String s:synsetsLine[1].split("\\ ")) {
                //System.out.println(s);
                //mSynsetsMap.get(id).add(new String(s));
                if (mIdMap.get(s) == null) {
                    mIdMap.put(s, new TreeSet<Integer>());
                    mIdMap.get(s).add(Integer.valueOf(id));
                } else {
                    mIdMap.get(s).add(Integer.valueOf(id));
                }
            }
            
        }
        
        
        //build a digraph out of the hypernyms
        in = new In(hypernyms);
        mHypernyms = new Digraph(V);
        //int vert_count = 0;
        while (in.hasNextLine()) {
            //vert_count++;
            String line = in.readLine();
            String[] hypernymLine = line.split(",");
            int edgeCount = 0;
            int vert = -1;
            for (String s:hypernymLine) {
                if (edgeCount == 0) { //the first int is the synset id
                    vert = Integer.parseInt(s);
                } else {
                    mHypernyms.addEdge(vert, Integer.parseInt(s));
                } 
                edgeCount++;
            }
        }
      
        //create a Sap object base on the digraph
        if (!isSingleRootDAG(mHypernyms)) {
            throw new IllegalArgumentException(
              "This is not a DAG with single root!");
        }
        mSap = new SAP(mHypernyms);      
    }

   // returns all WordNet nouns
    public Iterable<String> nouns() {
        return mIdMap.keySet();
    }

   // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        return mIdMap.containsKey(word);
    }

   // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        check(nounA, nounB); //throw illegal param exception
        Iterable<Integer> v = mIdMap.get(nounA);
        Iterable<Integer> w = mIdMap.get(nounB);
        return mSap.length(v, w);
    }

    // a synset (second field of synsets.txt) 
    //that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        check(nounA, nounB);
        Iterable<Integer> v = mIdMap.get(nounA);
        Iterable<Integer> w = mIdMap.get(nounB);
        int res = mSap.ancestor(v, w);
        if (res >= 0 && res < mHypernyms.V()) {
            return mSynsetsMap.get(res);
        }
        return null;
    }

   // do unit testing of this class
    public static void main(String[] args) {
        WordNet wn = new WordNet(args[0], args[1]);
        //for (String noun : wn.nouns()) {
            System.out.println(wn.distance("b", null));
        //}
    }
    
    private boolean isSingleRootDAG(Digraph g) {
        DirectedCycle finder = new DirectedCycle(g);
        return !finder.hasCycle() && isSingleRoot(g);
    }
    
    private boolean isSingleRoot(Digraph g) {
        //root has outdegree 0
        //we need to find exactly one such root
        int rootCount = 0;
        for (int v = 0; v < g.V(); v++) {
            if (!g.adj(v).iterator().hasNext()) {
                rootCount++;
            }
        }
        return rootCount == 1;
    }
    
    private void check(String a, String b) {
       
        if (a == null || b == null) {
            throw new NullPointerException();
        }
        if (!isNoun(a) || !isNoun(b)) {
            throw new IllegalArgumentException("not wordnet noun!");
        }
    }
        
}