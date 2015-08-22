import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] mq;            // array of queue elements
    private int mSize = 0;       // number of elements on queue
    private int mfirst = 0;       // index of mfirst element of queue
    private int mlast  = 0;       // index of next available slot
    
    public RandomizedQueue() {
        // construct an empty randomized queue
        mq = (Item[]) new Object[2];
    }
    public boolean isEmpty() {
        return mSize == 0;
    }
    public int size() {
        // return the number of items on the queue
        return mSize;
    }
    public void enqueue(Item item)  {
        // add the item from the end of the queue
        if (item == null) {
            throw new NullPointerException("Item can not be null");
        }
        // double size of array if necessary
        if (mSize == mq.length) {
            resize(mq.length*2);
        }  
        mq[mlast] = item;  
        mlast++;
        // add item
        if (mlast == mq.length) mlast = 0;          // wrap-around
        mSize++; 
        
    }
    public Item dequeue() {
        // delete and return an item from the front of the queue after shuffling 
         if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue underflow"); 
        }
         
        //randomize the queue by exchanging mfirst with 
        //randomly selected item in the queue 
        int index = StdRandom.uniform(0, mSize);
        Item itm = (Item) mq[(index+mfirst) % mq.length];
        mq[(index + mfirst) % mq.length] = mq[mfirst];
        mq[mfirst] = null; //avoid loistering
        mSize--;
        mfirst++;
        if (mfirst == mq.length) {
            mfirst = 0; //wrap around
        }
        //shrink array size when quarter full
        if (mq.length > 2 && mSize <= mq.length/4) {
            resize(mq.length/2);
        }
        return itm;
    }
    
    public Item sample() {
        // return (but do not delete) a random item
        if (mSize == 0) {
            throw new java.util.NoSuchElementException("Queue is empty"); 
        }
        int index = StdRandom.uniform(0, mSize);
        return (Item) mq[(index + mfirst) % mq.length];
    }
    
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomQueueIterator<Item>();
    }
    
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (Item item : this)
//            s.append(item + " ");
//        return s.toString();
//    }
    
    public static void main(String[] args) {
        // unit testing
        RandomizedQueue<String> strRandomizedQueue = new RandomizedQueue<String>();
        System.out.println("is empty: "+strRandomizedQueue.isEmpty());
        strRandomizedQueue.enqueue("Hello");
        strRandomizedQueue.enqueue("World!");
        System.out.println("content: " + strRandomizedQueue);
        System.out.println("first element: " + strRandomizedQueue.sample());
        System.out.println("last element: " + strRandomizedQueue.sample());
        System.out.println("size: "+strRandomizedQueue.size());
        for (int i = 0; i < 10; i++) {
            if (i >= StdRandom.uniform(10) || strRandomizedQueue.size() < 1) {
                strRandomizedQueue.enqueue("hello"+i);
            } else {
                System.out.println(strRandomizedQueue.dequeue());
            }
        }
        //System.out.println(strRandomizedQueue.toString());
        Iterator<String> it = strRandomizedQueue.iterator();
        Iterator<String> it2 = strRandomizedQueue.iterator();
        Iterator<String> it3 = strRandomizedQueue.iterator();
        Iterator<String> it4 = strRandomizedQueue.iterator();
        System.out.println("iteration 1");
        while (it.hasNext()) {
            System.out.println(it.next());
        }  
        System.out.println("iteration 2");
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
        System.out.println("iteration 3");
        while (it3.hasNext()) {
            System.out.println(it3.next());
        }  
        System.out.println("iteration 4");
        while (it4.hasNext()) {
            System.out.println(it4.next());
        }
    }
    
   // resize the underlying array
    private void resize(int newSize) {
        assert newSize >= mSize;
        Item[] temp = (Item[]) new Object[newSize];
        for (int i = 0; i < mSize; i++) {
            temp[i] = mq[(mfirst + i) % mq.length];
        }
        mq = temp;
        mfirst = 0;
        mlast  = mSize;
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class RandomQueueIterator<Item> implements Iterator<Item> {
        private int mcurrent = 0;
        private int [] pos;
        public RandomQueueIterator() {
            pos = new int[mSize];
            for (int i = 0; i < mSize; i++) {
               pos[i] = i; //mq[(mfirst + i) % mq.length];
            }
            StdRandom.shuffle(pos);
        }

        public boolean hasNext()  { return mcurrent < mSize;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) mq[(mfirst + pos[mcurrent]) % mq.length];
            mcurrent++;
            return item;
        }
    } 
}