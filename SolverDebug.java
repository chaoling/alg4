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
    }
    
    public boolean isSolvable() {
        // is the initial board solvable?
        if (this.solution() == null || ((Stack<Board>)this.solution()).size() == 0) {
            return false;
        } 
        return true;
    }
    
    public int moves() {
        // min number of moves to solve initial board; -1 if no solution
//        if (mNumOfMinMoves == -100) {
//            if (this.solution() == null || ((Stack<Board>)this.solution()).size() == 0) {
//                mNumOfMinMoves = -1;
//            }
//        }
        if (!isSolvable()) {
            return -1;
        } else {
            return mNumOfMinMoves;
        }
    }
    
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if no solution
        if (mSolution != null) {
            return mSolution;
        }
        boolean findSolution = false;
        SearchNode curNode = null;
        int curSteps = 0;
        int curPiority = 0;
        int curMove = 0;
        int curDistance = 0;
        boolean isPrevNode = false;
        while (!mPQ.isEmpty()) {
            curNode = mPQ.delMin();
            //if (curNode != null) {
                curSteps = curNode.getNumberOfSteps();
                curDistance = curNode.getBoard().manhattan();
                curPiority = curSteps + curDistance;
                StringBuilder st = new StringBuilder();
                st.append("Step: "+curSteps+"  piority =  "+ curPiority+"\n");
                st.append("        moves = "+curSteps+"\n");
                st.append("        manhattan = "+curDistance+"\n");
                st.append("                    "+curNode.getBoard().toString());
                StdOut.print(st.toString());
                if (curNode.getBoard().isGoal()) {
                    findSolution = true;
                    mNumOfMinMoves = curNode.getNumberOfSteps();
                    //System.out.println("goal reached! "+curNode.getBoard());
                    break;
                }
//                System.out.println(curNode.getBoard()+" neighbors: ");
//                StringBuilder st = new StringBuilder();
//                for (Board bd : curNode.getBoard().neighbors()) {
//                    st.append(bd.toString());
//                }
//                System.out.println(st.toString());
                 for (Board bd : curNode.getBoard().neighbors()) {
                     SearchNode parentNode = curNode.getParentNode();
//                     System.out.println("currentNode: "+curNode.getBoard().toString()+
//                                        " parent Node: "+ (parentNode == null?
//                                            "null":parentNode.getBoard().toString()));
//                    if (parentNode != null) {
//                        if (bd.equals(parentNode.getBoard())) {
//                            isPrevNode = true;
//                            //System.out.println(bd+"is equal to previous node"+
//                                               //parentNode.getBoard());
//                        }
//                    } else {
//                        isPrevNode = false;
//                    }
                    //if (!isPrevNode) {
                        //System.out.println("enqueue: "+bd);
                        SearchNode nextNode = new SearchNode();
                        nextNode.setBoard(bd);
                        nextNode.setParentNode(curNode);
                        nextNode.setNumberOfSteps(curNode.getNumberOfSteps()+1);
                        if (! nextNode.equals(parentNode)){
                            mPQ.insert(nextNode);
                        }
                    //}
                }
           // }
        }
        if (findSolution) {
            while (curNode != null) {
                if (mSolution == null) mSolution = new Stack<Board>();
                //System.out.println(curNode.getBoard());
                mSolution.push(curNode.getBoard());
                curNode = curNode.getParentNode();
            }
        }
        return mSolution; 
    }
    
    public static void main(String[] args) {
        // solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        //StdOut.println("Initial boald: "+initial);
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
        
        public void setParentNode(SearchNode n) {
            mPreNode = n;
        }
        
        public void setNumberOfSteps(int steps) {
            mSteps = steps;
        }
        
        public void setBoard(Board bd) {
            mBoard = bd;
        }
        
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
//            if (x.getBoard().manhattan() + x.getNumberOfSteps() 
//                    < y.getBoard().manhattan() + y.getNumberOfSteps()) {
//                return 1;
//            }
//            if (x.getBoard().manhattan() + x.getNumberOfSteps() 
//                    > y.getBoard().manhattan() + y.getNumberOfSteps()) {
//                return -1;
//            }
//            return 0;
            return x.getBoard().manhattan() + x.getNumberOfSteps() - (y.getBoard().manhattan() + y.getNumberOfSteps());
        }
    }
    
    private class HammingComparator implements Comparator<SearchNode> {
        
        @Override
        public int compare(SearchNode x, SearchNode y) {
            assert (x != null && y != null);
//            if (x.getBoard().hamming() + x.getNumberOfSteps() 
//                    < y.getBoard().hamming() + y.getNumberOfSteps()) {
//                return 1;
//            }
//            if (x.getBoard().hamming() + x.getNumberOfSteps() 
//                    > y.getBoard().hamming() + y.getNumberOfSteps()) {
//                return -1;
//            }
//            return 0;
            return x.getBoard().hamming() + x.getNumberOfSteps() - (y.getBoard().hamming() + y.getNumberOfSteps());
        }
    }
}