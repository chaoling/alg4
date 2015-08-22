public class EagerPrimMST
{
    private boolean[] visited; //MST vertices that is in the mst
    private Queue<Edge> mst; //MST edges
    private MinPQ<Edge> pq; //PQ of edges
    
    public LazyPrimMst(WeightedGraph G)
    {
        pq = new MinPQ<Edge>();
        mst = new Queue<Edge>();
        visited = new boolean[G.V()];
        visit(G,0);
        
        while (!pq.isEmpty())
        {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (visited[v] && visited[w]) continue;
            mst.enqueue(e);
            if (!visited[v]) visit(G,v);
            if (!visited[w]) visit(G,w);
        }
    }
    
    private void visit(WeightedGraph G, int v)
    {
        visited[v] = true;
        for (Edge e: G.adj[v]) {
            if (!visited[e.other(v)])
                pq.insert(e);
        }
    }
    
    public Iterable<Edge> mst()
    {
        return mst;
    }
}