public class BreadthFirstPaths implements GraphPaths
{
    private boolean[] visited;
    private int[] parent;
    private final int s; //source
    
    public BreadthFirstPaths(Graph G, int s)
    {
        visited = new boolean[G.V()];
        parent = new int[G.V()];
        this.s = s;
        bfs(G,s);
    }
    
    private void bfs(Graph G, int s)
    {
        Queue<Integer> q = new Queue<Integer>();
        visited[s] = true;
        q.enqueue(s);
        while ( !q.isEmpty() )
        {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if ( ! visited[w] ) { // For every unmarked adjacent vertex
                    visited[w] = true; // mark it because path is known
                    parent[w] = v;  // save last edge on a shortest path
                    q.enqueue(w); // and add it ot the queue
                }
            }
        }
        
    }
    
    public boolean hasPathTo(int v)
    { return visited[v]; }
    
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
