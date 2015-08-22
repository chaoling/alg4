public class CycleDetection
{
    private boolean[] m_bVisited;
    private boolean m_bHasCycle;
    
    public CycleDetection(Graph G)
    {
        m_bVisited = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (! m_bVisited[s] ) {
                dfs(G,s,s);
            }
        }
    }
    
    private void dfs(Graph G, int v, int u)
    {
        m_bVisited[v] = true;
        for (int w : G.adj(v)) {
            if ( ! m_bVisited[w] ) {
                dfs(G,w,v);
            }
            else if (w != u) m_bHasCycle = true; // w is visited at least once and w is not the parent vertex, we have a cycle
        }
    }
    
    public boolean hasCycle()
    {   return m_bHasCycle; }
}

    
    