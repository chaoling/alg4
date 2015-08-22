import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Dnode<Item> mFirst;
    private Dnode<Item> mLast;
    private Dnode<Item> mDummy;
    private int mSize;
    
    public Deque() {
    // construct an empty deque, with the First and Last pointer
    // all initialized to the dummy or sentinel_node
    //lastNode.next = dummyNode
    //firstNode.prev = dummyNode
        mDummy = new Dnode<Item>();
        mDummy.prev = mDummy;
        mDummy.next = mDummy;
        mFirst = mDummy;
        mLast = mDummy;
        mSize = 0;
    }
    
    public boolean isEmpty() {             
    // is the deque empty?
        return (mSize == 0);
    }
    
    public int size() {                       
    // return the number of items on the deque
        return mSize;
    }
    
    public void addFirst(Item item)  {        
    // insert the item at the front
        if (item == null) {
            throw new NullPointerException("Item can not be null");
        }
        //insert node after the front sentinel_node
        Dnode<Item> oldFirst = mFirst;
        mFirst = new Dnode<Item>();
        mFirst.item = item;
        mFirst.prev = mDummy;
        mDummy.next = mFirst;
        oldFirst.prev = mFirst;
        mFirst.next = oldFirst;
        mSize++;
        if (mSize == 1) {
            mLast = mFirst;
        }
    }
    
    public void addLast(Item item) {
        // insert the item at the end
         if (item == null) {
            throw new NullPointerException("Item can not be null");
         }
         Dnode<Item> moldLast = mLast;
         mLast = new Dnode<Item>();
         mLast.item = item;
         mLast.next = mDummy;
         mDummy.prev = mLast;
         mLast.prev = moldLast;
         moldLast.next = mLast;
         mSize++;
         if (mSize == 1) {
             mFirst = mLast;
         }
    }
    public Item removeFirst() {
    // delete and return the item at the front
        if (mSize == 0) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item itm = mFirst.item;
        mFirst = mFirst.next;
        mFirst.prev = mDummy;
        mDummy.next = mFirst;
        if (mSize == 1) {
            mLast = mFirst;
        }
        mSize--;
        return itm;
    }
    public Item removeLast() {
    // delete and return the item at the end
        if (mSize == 0) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        Item itm = mLast.item;
        mLast = mLast.prev;
        mLast.next = mDummy;
        mDummy.prev = mLast;
        if (mSize == 1) {
            mFirst = mLast;
        }
        mSize--;
        return itm;
    }
    
    public Iterator<Item> iterator() { 
    // return an iterator over items in order from front to end
        return new DequeIterator<Item>(mDummy);    
    }
    
//      public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (Item item : this)
//            s.append(item + " ");
//        return s.toString();
//    }
    
    public static void main(String[] args) {
    // unit testing
        //empty struct test
        Deque<String> strDeque = new Deque<String>();
        System.out.println("is empty: "+strDeque.isEmpty());
        strDeque.addFirst("Hello");
        strDeque.addLast(" World!");
        System.out.println("content: "+strDeque);
        System.out.println("first element: "+strDeque.removeFirst());
        System.out.println("last element: "+strDeque.removeLast());
        System.out.println("size: "+strDeque.size());   
        strDeque.addLast("To");
        strDeque.addLast("be");
        strDeque.addLast("or");
        strDeque.addLast("not");
        strDeque.addLast("to");
        strDeque.addLast("be");
        strDeque.addFirst("helen");
        strDeque.addFirst("is");
        strDeque.addFirst("name");
        strDeque.addFirst("my");
        strDeque.addFirst("Hi");
        System.out.println(strDeque.toString());
        Iterator<String> it = strDeque.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }  
    }
    
    private class Dnode<Item> {
        Item item;
        Dnode<Item> next;
        Dnode<Item> prev;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator<Item> implements Iterator<Item> {
        private Dnode<Item> current;
        private Dnode<Item> dummy;

        public DequeIterator(Dnode<Item> dummyNode) {
            dummy = dummyNode;
            current = dummy.next;
        }

        public boolean hasNext()  { return current != dummy;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    } 
}