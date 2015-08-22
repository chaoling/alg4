import java.util.Iterator;
public class Subset {
    public static void main(String[] args) {
        int k = 0;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (k < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            int i = 0;
               while (!StdIn.isEmpty()) {
                   String str = StdIn.readString();
                   if (i < k) {
                    rq.enqueue(str);
                   } else {
                       if (StdRandom.uniform(i+1) < k) {
                           rq.dequeue();
                           rq.enqueue(str);
                       }
                   }
                   i++;
               }
        }
        Iterator<String> it = rq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}