public class MSD
{      
    int N = a.length;
    int R = 256;
    
    public static void sort(String[] a)
    { //Sort a[] using MSD similar to quicksort
        aux = new String[N];
        sort(a, aux, 0, a.length, 0);
    }
    
    private static void sort(String[] a, String[] aux, int lo, int hi, int d)
    {
        if (hi <= lo) return;
        int[] count = new int[R+2];
        //counting sort
        for (int i= lo; i <= hi; i++)
            count[charAt(a[i],d)+2]++;
        for (int r=0; r <R+1; r++)
            count[r+1] += count[r];
        for (int i = lo; i <= hi; i++)
            aux[count[charAt(a[i],d)+1]++] = a[i];
        for (int i= lo; i <= hi; i++)
            a[i] = aux[i-lo];
        //sort R subarrays recursively
        
        for (int r=0; r <R; r++)
            sort(a,aux,lo+count[r], lo+count[r+1]-1, d+1);
          
    }
}
//MSD