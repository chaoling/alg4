public class Percolation {
    private WeightedQuickUnionUF mUf;
    private WeightedQuickUnionUF mUfBottomRow;
    private int mSize; //row or column size of the grid
    private boolean[][] mGrid;
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("Size must greater than 0");
        //create N+1-by-N+1 grid, with all sites blocked
        mSize = N+1;
        //2D array of total N*N sites plus 2 dummy nodes
        mUf = new WeightedQuickUnionUF(N*N+2); 
         //no virtual node at the bottom
        mUfBottomRow = new WeightedQuickUnionUF(N*N+1);
        mGrid = new boolean[N+1][N+1];
        //1 based array, first node start at (1,1)
        //initialize all sites to blocked, i.e., false.
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                mGrid[i][j] = false;
            }
        }
    }
    
    //open site (row i, column j) if it is not already opened
    public void open(int i, int j) { 
        if  (isIndexOutOfBound(i, j)) {
           throw new IndexOutOfBoundsException("Illegal subarray range");
        }
        mGrid[i][j] = true;
        // look for left,right,up,down
        // first node (0) and last node (N*N+1) are dummy nodes
        // the first row,column (1,1) is node 1;
        if (j > 1 && mGrid[i][j-1]) {
            mUf.union(xyTo1D(i, j), xyTo1D(i, j-1));
            mUfBottomRow.union(xyTo1D(i, j), xyTo1D(i, j-1));
        } //union left
        if (j < mSize-1 && mGrid[i][j+1]) {
            mUf.union(xyTo1D(i, j), xyTo1D(i, j+1));
            mUfBottomRow.union(xyTo1D(i, j), xyTo1D(i, j+1));
        } //union right
        if (i > 1 && mGrid[i-1][j]) {
            mUf.union(xyTo1D(i, j), xyTo1D(i-1, j));
            mUfBottomRow.union(xyTo1D(i, j), xyTo1D(i-1, j));
        } //union up
        if (i < mSize-1 && mGrid[i+1][j]) {
            mUf.union(xyTo1D(i, j), xyTo1D(i+1, j));
            mUfBottomRow.union(xyTo1D(i, j), xyTo1D(i+1, j));
        } //union down
        //top row:
        if (i == 1) {
             mUf.union(xyTo1D(i, j), 0); //union with top virtual node
             mUfBottomRow.union(xyTo1D(i, j), 0);
        }
        if (i == mSize-1) {
            //union with bottom virtual node
            //this may cause backwash effect 
            //since actually not all bottom row open
            //site should be united.
            mUf.union(xyTo1D(i, j), (mSize-1)*(mSize-1)+1); 
        }
     }

     public boolean isOpen(int i, int j) { //is site (i,j) open?
         if  (isIndexOutOfBound(i, j)) {
            throw new IndexOutOfBoundsException("Illegal subarray range");
         }
         return mGrid[i][j];  
     }
     
     public boolean isFull(int i, int j) { //is site (i,j) full?
         if  (isIndexOutOfBound(i, j)) {
            throw new IndexOutOfBoundsException("Illegal subarray range");
         }
         return ((isOpen(i, j) && mUfBottomRow.connected(0, xyTo1D(i, j))));   
     }
     
     public boolean percolates() { // does the system percolate?
         return mUf.connected(0, (mSize-1)*(mSize-1)+1);
     }
     
     private int xyTo1D(int x, int y) {
         return (x-1)*(mSize-1)+(y-1)+1;
     }
     
     private boolean isIndexOutOfBound(int i, int j) {
         return (i >= mSize || i < 1 || j < 1 || j >= mSize);
     }
}


           
        
        
        