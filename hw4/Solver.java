import java.util.Comparator;

public class Solver {
    private MinPQ<SearchNode> mPQ;
    private SearchNode mRootNode;
    private int mNumOfMinMoves = -1;
    private Stack<Board> mSolution = null;
    
    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        mPQ = new MinPQ<SearchNode>(2, new ManhattanComparator());
        mRootNode = new SearchNode(initial, 0);
        mPQ.insert(mRootNode);
        mPQ.insert(new SearchNode(initial.twin(), 0));
    }
    
    public boolean isSolvable() {
        // is the initial board solvable?
        if (solution() == null) {
            return false;
        }
        return true;
    }
    
    public int moves() {
        if (!isSolvable()) {
            return -1;
        } else {
            return mNumOfMinMoves;
        }
    }
    
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if no solution
        if (mSolution != null) {
            if (mSolution.peek().equals(mRootNode.getBoard())) {
                return mSolution;
            } else {
                return null;
            }
        }
        boolean findSolution = false;
        SearchNode curNode = null;
        boolean isPrevNode = false;
        while (!mPQ.isEmpty()) {
            curNode = mPQ.delMin();
            if (curNode.getBoard().isGoal()) {
                findSolution = true;
                mNumOfMinMoves = curNode.getNumberOfSteps();
                break;
            }
            for (Board bd : curNode.getBoard().neighbors()) {
                SearchNode parentNode = curNode.getParentNode();
                SearchNode nextNode = new SearchNode();
                nextNode.setBoard(bd);
                nextNode.setParentNode(curNode);
                nextNode.setNumberOfSteps(curNode.getNumberOfSteps()+1);
                //nextNode.setIsTwin(parentNode.isTwin());
                if (!nextNode.equals(parentNode)) {
                    mPQ.insert(nextNode);
                }
            }
        }
        if (findSolution) {
            while (curNode != null) {
                if (mSolution == null) mSolution = new Stack<Board>();
                mSolution.push(curNode.getBoard());
                curNode = curNode.getParentNode();
            }
        }
        if (mSolution.peek().equals(mRootNode.getBoard())) {
            return mSolution;
        } 
        return null; 
    }
    
    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        } 
    }
    
    private class SearchNode {
        private Board mBoard;
        private int mSteps;
        private SearchNode mPreNode = null;
        //private boolean mIsTwin = false;
        
        public SearchNode(Board bd, int steps) {
            mBoard = bd;
            mSteps = steps;
        }
        
        public SearchNode() { }
        
        public Board getBoard() {
            return mBoard;
        }
        
        public int getNumberOfSteps() {
            return mSteps;
        }
        
        public SearchNode getParentNode() {
            return mPreNode;
        }
        
//        public boolean isTwin() {
//            return mIsTwin();
//        }
        
        public void setParentNode(SearchNode n) {
            mPreNode = n;
        }
        
        public void setNumberOfSteps(int steps) {
            mSteps = steps;
        }
        
        public void setBoard(Board bd) {
            mBoard = bd;
        }
//        
//        public void setIsTwin(boolean istwin) {
//            mIsTwin = istwin;
//        }
        
        public boolean equals(Object y) {
            // does this board equal y?
            if (y == this) return true;
            if (y == null || y.getClass() != this.getClass()) return false;
            SearchNode that = (SearchNode) y;
            if (that.getBoard().equals(this.getBoard())) {
                return true; //initialize to true
            }
            return false;
        }
    }
    
    private class ManhattanComparator implements Comparator<SearchNode> {
        
        @Override
        public int compare(SearchNode x, SearchNode y) {
            assert (x != null && y != null);
            return x.getBoard().manhattan() + x.getNumberOfSteps() 
                - (y.getBoard().manhattan() + y.getNumberOfSteps());
        }
    }
    
    private class HammingComparator implements Comparator<SearchNode> {
        
        @Override
        public int compare(SearchNode x, SearchNode y) {
            assert (x != null && y != null);
            return x.getBoard().hamming() + x.getNumberOfSteps() 
                - (y.getBoard().hamming() + y.getNumberOfSteps());
        }
    }
}