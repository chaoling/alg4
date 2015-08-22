public class TestSearch
{
    public static void main(String[] args)
    {
        Graph G = new Graph(new In(args[0]));
        StdOut.println(G.toString());
        int s = Integer.parseInt(args[1]);
        Search search = new DepthFirstSearch(G,s);
        
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                StdOut.print(v + " ");
        StdOut.println();
        
        if (search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
