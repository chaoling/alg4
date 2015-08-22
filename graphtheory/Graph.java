public class Graph
{
    private final int V; // number of vertices
    private int E; //number of edges
    private Bag<Integer>[] adj; // adjacency lists
    
    public Graph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }
    
    public Graph(In in)
    {
        this(in.readInt()); //Read V and construct this graph
        int E = in.readInt(); // Read E.
        for (int i = 0; i < E; i++)
        { //Add an egde
            int v = in.readInt(); //Read a vertex
            int w = in.readInt(); // read another vertex,
            addEdge(v,w); //and add egde connecting them.
        }
    }
    
    public int V() { return V; }
    public int E() { return E; }
    
    public void addEdge( int v, int w )
    {
        adj[v].add(w); //add w to v's list.
        adj[w].add(v); //add v to w's list.
        E++;
    }
    
    public Iterable<Integer> adj(int v)
    {  return adj[v]; }
    
    public static int degree (Graph G, int v)
    {
        int degree = 0;
        for (int w : G.adj(v)) degree ++;
        return degree;
    }
    
    public static int maxDegree(Graph G)
    {
        int max = 0;
        for (int v = 0; v < G.V(); v++) 
        {
            int deg = degree(G,v);
            if (deg > max) {
                max = deg; }
        }
        
        return max;
    }
    
    public static int avgDegree(Graph G)
    {
        return  2 * G.E() /G.V(); }
    
    public static int numberOfSelfLoops(Graph G)
    {
        int count = 0;
        for (int v = 0; v < G.V(); v++)
        {
            for (int w : G.adj(v)) {
                if ( v == w ) count ++;
            }
        }
        return count>>1; //each edge counted twice
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder("").append(V).append(" vertices, ").append(E).append(" edges\n") ;
        for (int v = 0; v < V; v++)
        {
            sb.append(v).append(": ");
            for (int w : this.adj(v)) {
                sb.append(w).append(" "); }
            sb.append("\n");
        }
        return sb.toString();
    }
}
