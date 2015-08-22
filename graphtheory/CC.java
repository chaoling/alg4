public interface CC
{
    boolean connected(int v, int w); //are v and w connected?
    int count();  //number of connected components
    int id(int v); //component id for v (between 0 and count() -1
}
