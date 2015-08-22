class TestGraph
{
    public static void main(String[] args)
    {
        Graph G = new Graph(new In(args[0]));
        CycleDetection cd = new CycleDetection(G);
        TwoColor tc = new TwoColor(G);
        int s = 0;
        GraphPaths searchdfs = new DepthFirstPaths(G,s);
        CC cc = new GraphCC(G);
        Search search = new DepthFirstSearch(G,s);
        
        for (int v = 0; v < G.V(); v++)
        {
            StdOut.print("dfs from: " + s + " to " + v + ": ");
            if (searchdfs.hasPathTo(v))
            for (int x : searchdfs.pathTo(v)) {
                if ( x == s ) StdOut.print(x);
                else StdOut.print("-" + x);
            }
            
            StdOut.println();
        }
        StdOut.println( cd.hasCycle() ? "This Graph has Cycle! " : "This Graph is acyclic" );
        StdOut.println( tc.isBipartite() ? "This Graph is Bipartite!" : "This Graph is NOT Bipartite!");
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
            StdOut.print(v + " ");
        StdOut.println();
        
        StdOut.println();
        StdOut.print(" This Graph is ");
        if (search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
        
        int M = cc.count();
        StdOut.println(M + " components");
        Bag<Integer> [] components;
        components = (Bag<Integer>[]) 
            new Bag[M];
        for (int i = 0; i < M; i++)
            components[i] = new Bag<Integer>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);
        for (int i = 0; i < M; i++)
        {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
        StdOut.println(G.toString());
    }
}