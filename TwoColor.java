public class TwoColor
{
    private boolean[] m_bVisited;
    private boolean[] m_bColor;
    private boolean m_bTwoColorable = true;
    
    public TwoColor(Graph G)
    {
        m_bVisited = new boolean[G.V()];
        m_bColor = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (! m_bVisited[s] ) {
                dfs(G,s);
            }
        }
    }
    
    private void dfs(Graph G, int v)
    {
        m_bVisited[v] = true;
        for (int w : G.adj(v)) {
            if ( ! m_bVisited[w] ) {
                m_bColor[w] = !m_bColor[v];
                dfs(G,w);
            }
            else if (m_bColor[w] == m_bColor[v]) m_bTwoColorable = false; // w is the same color as parent v
        }
    }
    
    public boolean isBipartite()
    {   return m_bTwoColorable; }
    
}