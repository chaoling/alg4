public class Board {
    private short[][] mBlks;
    private int mDim; //board dimension
    private int manhattandist = -1; //cache it
    private int hammingdist = -1; //cache it
    public Board(short[][] blocks) {        
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        assert blocks != null;
        int N = blocks.length;
        mDim = blocks[0].length;
        assert N >= 2 && mDim != 0 && N == mDim * mDim; //square blocks
        mBlks = new short[mDim][mDim];
        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                mBlks[i][j] = blocks[i][j];
            }
        }
    }
    
    public int dimension() {
        // board dimension N
        return mDim;
    }
    
    public int hamming() {
        // number of blocks out of place
        int res = 0;
        if (hammingdist == -1) {
            int N = mDim * mDim;
            for (int i = 0; i < mDim; i++) {
                for (int j = 0; j < mDim; j++) {
                    if (!(mBlks[i][j] == 0 
                              || mBlks[i][j] == (i * mDim + j + 1) % N)) {
                        res = res + 1;
                    }
                }
            }
        } else {
            res = hammingdist;
        }
        return res;  
    }
    
    public int manhattan() {               
        // sum of Manhattan distances between blocks and goal
        int res = 0;
        if (manhattandist == -1) {
            int N = mDim * mDim;
            for (int i = 0; i < mDim; i++) {
                for (int j = 0; j < mDim; j++) {
                    if (!(mBlks[i][j] == 0 
                              || mBlks[i][j] == (i * mDim + j + 1) % N)) {
                        res = res + mandist(i, j, mBlks[i][j]);
                    }
                }
            }
        } else {
            res = manhattandist;
        }
        return res;
    }
    
    public boolean isGoal() { 
        // is this board the goal board?
        int N = mDim * mDim;
        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                if (!(mBlks[i][j] == (i * mDim + j + 1) % N)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Board twin() {
        // a board obtained by exchanging two adjacent blocks in the same row
        short[][] twinBlks = new short[mDim][mDim];
        boolean swapDone = false;
        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                twinBlks[i][j] = mBlks[i][j];
                if (!swapDone && j > 0 
                        && twinBlks[i][j] != 0 
                        && twinBlks[i][j-1] != 0) {
                    swap(twinBlks, i, j, i, j-1);
                    swapDone = true; //do not need swap again
                }
            }
        }
        return new Board(twinBlks);
    }
    
    public boolean equals(Object y) {
        // does this board equal y?
        if (y == this) return true;
        if (y == null || y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.mDim == this.mDim) {
            for (int i = 0; i < mDim; i++) {
                for (int j = 0; j < mDim; j++) {
                    if (that.mBlks[i][j] != this.mBlks[i][j]) {
                        return false;
                    }
                }
            }
            return true; //initialize to true
        }
        return false;
    }
    
    public Iterable<Board> neighbors() {
        // all neighboring boards
        Stack<Board> res = new Stack<Board>();
        short[][] nextBlks = new short[mDim][mDim];
        int x0 = 0; //x pos of blank block
        int y0 = 0; //y pos of blank block
        //copy this board array to a new one and permulate
        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                nextBlks[i][j] = mBlks[i][j];
                if (nextBlks[i][j] == 0) {
                    //find the zero's location
                    x0 = i;
                    y0 = j;
                }
            }
        }
        if (x0 < mDim - 1) {
            //move to the right
            swap(nextBlks, x0, y0, x0 + 1, y0);
            res.push(new Board(nextBlks));
            swap(nextBlks, x0 + 1, y0, x0, y0);
        }
        if (x0 > 0) {
            //move to the left
            swap(nextBlks, x0, y0, x0 - 1, y0);
            res.push(new Board(nextBlks));
            swap(nextBlks, x0 - 1, y0, x0, y0);
        }
        if (y0 > 0) {
            //move up
            swap(nextBlks, x0, y0, x0, y0 - 1);
            res.push(new Board(nextBlks));
            swap(nextBlks, x0, y0 - 1, x0, y0);
        }
        if (y0 < mDim - 1) {
            //move down
            swap(nextBlks, x0, y0, x0, y0 + 1);
            res.push(new Board(nextBlks));
            swap(nextBlks, x0, y0 + 1, x0, y0);
        }
        return res;  
    }
    
    public String toString() {
        // string representation of the board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(mDim + "\n");
        for (int i = 0; i < mDim; i++) {
            for (int j = 0; j < mDim; j++) {
                sb.append(String.format("%2d ", mBlks[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private int mandist(int x, int y, short val) {
        //calculate the mantatten distance between
        //point P(x,y) and its goal
        int res = 0;
        int row = (val - 1) / mDim;
        int col = (val - 1) % mDim; //0 based index
        res = Math.abs(x - row) + Math.abs(y - col);
        return res;
    }
    
    private void swap(short[][] tiles, int x0, int y0, int x1, int y1) {
        short tmp = tiles[x0][y0];
        tiles[x0][y0] = tiles[x1][y1];
        tiles[x1][y1] = tmp;
    }
    
    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        short[][] blocks = new short[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = (short) in.readInt();
        Board initial = new Board(blocks);
        StdOut.println(initial);
        System.out.println("Mantatan distance is :"+initial.manhattan());
        System.out.println("Hamming distance is :"+initial.hamming());
        Board twin = initial.twin();
        System.out.println("Twin is: "+twin);
        System.out.println("Mantatan distance is :"+twin.manhattan());
        System.out.println("Hamming distance is :"+twin.hamming());
        // solve the puzzle
    }
        
        
    }