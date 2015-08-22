public class TestPaths
{
    public static void main(String[] args)
    {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        GraphPaths searchdfs = new DepthFirstPaths(G,s);
        GraphPaths searchbfs = new BreadthFirstPaths(G,s);
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
        
        for (int v = 0; v < G.V(); v++)
        {
            StdOut.print("bfs from: "+ s + " to " + v + ": ");
            if (searchbfs.hasPathTo(v))
            for (int x : searchbfs.pathTo(v)) {
                if ( x == s ) StdOut.print(x);
                else StdOut.print("-" + x);
            }
            
            StdOut.println();
        }
    }
}
