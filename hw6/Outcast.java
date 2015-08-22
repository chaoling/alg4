public class Outcast {
    private WordNet mWn;
    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object
        mWn = wordnet; //no deepcopy needed since wordnet is itself immutable
    }
    
    public String outcast(String[] nouns)  {
        // given an array of WordNet nouns, return an outcast
        int sumDistMax = -1;
        String oc = null;
        int size = nouns.length;
        for (int i = 0; i < size; i++) {
            int sumdist = 0;
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    sumdist += mWn.distance(nouns[i], nouns[j]); 
                }
            }
            assert (sumdist >= 0);
            //System.out.println(nouns[i]+" is "+sumdist);
            if (sumDistMax < sumdist) {
                sumDistMax = sumdist;
                oc = nouns[i];
            }
        }
        //System.out.println("max distance of "+oc+" is "+sumDistMax); 
        return oc;
    }
    
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}