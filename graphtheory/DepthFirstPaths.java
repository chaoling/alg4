public class DepthFirstPaths implements GraphPaths
{
    private boolean[] marked;
    private int[] parent; //store parent vertex that leads to this one
    private final int s; //source
    
    public DepthFirstPaths(Graph G, int s)
    {
        marked = new boolean[G.V()];
        parent = new int[G.V()];
        this.s = s;
        dfs(G,s);
    }
    
    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        for (int w : G.adj(v))
        {
            if (!marked[w])
            {
              parent[w] = v;
              dfs(G,w);
            }
        }
    }
    
    public boolean hasPathTo(int v)
    {
        return marked[v]; 
    }
    
    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for ( int x = v; x != s; x = parent[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
    