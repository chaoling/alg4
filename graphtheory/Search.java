public interface Search
{
    
    boolean marked(int v);   //is v connected to s
    
    int count(); //how many vertices are connected to s
}