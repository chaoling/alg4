public class GraphCC implements CC
{
    private boolean[] m_bVisited;
    private int[] m_id;
    private int m_iNumOfConnectedComponent; //number of connectd components
    
    public GraphCC(Graph G)
    {
       m_bVisited = new boolean[G.V()]; 
       m_id = new int[G.V()];
       m_iNumOfConnectedComponent = 0;
       for (int s = 0; s < G.V(); s++) {
           if ( ! m_bVisited[s]) {
               dfs(G,s);
               m_iNumOfConnectedComponent++;
           }
       }
    }
    
    private void dfs(Graph G, int v)
    {
        m_bVisited[v] = true;
        m_id[v] = m_iNumOfConnectedComponent;
        for (int w : G.adj(v) ) {
            if ( ! m_bVisited[w] ) {
                dfs(G,w);
            }
        }
    }
    
    public boolean connected(int v, int w)
    { return m_id[v] == m_id[w]; }
    
    public int id(int v)
    {   return m_id[v]; }
    
    public int count()
    {
        return m_iNumOfConnectedComponent;
    }
}
    