public class Count
{
    public static void main(String[] args)
    {
        String alpha = new String("ABCDR");
        StdOut.println("Alphabet is:" + alpha);
        int R = alpha.length(); 
        int[] count = new int[R];
        String s = StdIn.readAll();
        int N = s.length();
        char ch;
        for (int i = 0; i<N; i++) {
            ch = s.charAt(i);
            StdOut.println(ch);
            if (alpha.contains(Character.toString(ch))) {
                count[alpha.indexOf(ch)]++;
            }
        }
        for ( int c=0; c<R; c++) {
            if (count[c] >0)
               StdOut.println(alpha.charAt(c)+" : "+ count[c]);
        }
    }
}