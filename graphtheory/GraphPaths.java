public interface GraphPaths
{
    boolean hasPathTo(int v); //is there a path from s to v?
    Iterable<Integer> pathTo(int v); //path from s to v; null if no such path
}