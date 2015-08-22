import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class BoggleSolver
{
   
     private BTrieST<Integer> mDict;
     private BTrieST.Node mBTrieNode;
     //private int mWordCount;
    
    // Initializes the data structure 
    // using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains 
    // only the uppercase letters A through Z.)
        public BoggleSolver(String[] dictionary) {
            //build the BTrieST given the dictionary
            mDict = new BTrieST<Integer>();
            for (int i = 0; i < dictionary.length; i++) {
                mDict.put(dictionary[i], i);
            }
            //Do not modify Trie, read only please
            mBTrieNode = mDict.getRoot(); //or deep copy?
        }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
        public Iterable<String> getAllValidWords(BoggleBoard board) {
            Set<String> res = new HashSet<String>();
            int M = board.rows();
            int N = board.cols();
            boolean[] visited = new boolean[M*N];
            BTrieST.Node x = mBTrieNode;
            for (int i = 0; i < M*N; i++) {
                StringBuilder prefix = new StringBuilder();
                dfs(i, visited, board, prefix, x, 0, res);
            }
            return res;
        }

    // Returns the score of the given word 
    //if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        int res = 0;
        if (mDict.get(word) == null) {
            return res;
        } else {
            int n = word.length();
            assert (n >= 0);
            switch (n) {
                case 0:
                case 1:
                case 2:
                    res = 0;
                    break;
                case 3:
                case 4:
                    res = 1;
                    break;
                case 5:
                    res = 2;
                    break;
                case 6:
                    res = 3;
                    break;
                case 7:
                    res = 5;
                    break;
                case 8:
                default:
                    res = 11;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dict = in.readAllStrings();
        BoggleSolver bs1 = new BoggleSolver(dict);
        BoggleBoard board = new BoggleBoard(args[1]);
        System.out.println(board);
        Set<String> ans = (HashSet<String>) bs1.getAllValidWords(board);
        System.out.println(ans);
    }
    
    private void dfs(int v, boolean[] visited, BoggleBoard b, 
                     StringBuilder prefix, BTrieST.Node x, 
                     int level, Set<String> res) {
         visited[v] = true;
         char ch = b.getLetter(vToRow(v, b), vToCol(v, b));
         BTrieST.Node n;
         if (ch == 'Q') {
             prefix.append("QU");
             n = mDict.getNext(x, 'Q');
             n = mDict.getNext(n, 'U');
         } else {
             prefix.append(ch);
             n = mDict.getNext(x, ch);
         }
         //System.out.println("pre: "+prefix);
         if (n != null && mDict.getVal(n) != null) {
             if (prefix.length() > 2) {
                 res.add(prefix.toString());
             }
         } 
             //StdOut.print("l "+level+" : "+word.toString());
         //Queue<String> lpo = 
         //(Queue<String>) mDict.keysWithPrefix(prefix.toString());
         //System.out.println("lpo : "+lpo);
         if (n != null) {
             for (int i:adj(v, b)) {
                 if (!visited[i]) {
                     dfs(i, visited, b, prefix, n, level+1, res);
                 }
             }
         }
         //rewind the word?
         prefix.deleteCharAt(prefix.length() - 1);
         //rewind twice for Qu
         if (ch == 'Q') {
             prefix.deleteCharAt(prefix.length() - 1);
         }  
         visited[v] = false; //clean up trace
    }
    
    private int rcToV(int row, int col, BoggleBoard b) {
        int M = b.rows();
        int N = b.cols();
        if (row >= M || row < 0) throw new IllegalArgumentException("outside board");
        if (col >= N || col < 0) throw new IllegalArgumentException("outside board");
        return row * N + col;
    }
    
    private int vToRow(int v, BoggleBoard b) {
        int M = b.rows();
        int N = b.cols();
        if (v >= M*N || v < 0) throw new IllegalArgumentException("outside board");
        return v / N;
    }
    
    private int vToCol(int v, BoggleBoard b) {
        int M = b.rows();
        int N = b.cols();
        if (v >= M*N || v < 0) throw new IllegalArgumentException("outside board");
        return v - ((v / N) * N); // v % N
    }
    
    private List<Integer> adj(int v, BoggleBoard b) {
        List<Integer> res = new ArrayList<Integer>();
        int M = b.rows();
        int N = b.cols();
        int row = vToRow(v, b);
        int col = vToCol(v, b);
        //System.out.println("now processing...("+row+","+col+")");
        
        if (row > 0) {
            res.add(rcToV(row - 1, col, b)); //up
        }
        
        if (col > 0) {
            res.add(rcToV(row, col - 1, b)); //left
        }
        
        if (col < N - 1) {
            res.add(rcToV(row, col + 1, b)); //right
        }
        
        if (row < M - 1) {
            res.add(rcToV(row + 1, col, b)); //down
        }
        
         if (col < N - 1 && row > 0) {
            res.add(rcToV(row - 1, col + 1, b)); //ur
        }
        
         if (row > 0 && col > 0) {
            res.add(rcToV(row - 1, col - 1, b)); //ul
        }
        
        if (row < M - 1 && col > 0) {
            res.add(rcToV(row + 1, col - 1, b)); //dl
        }
        
        if (row < M - 1 && col < N - 1) {
            res.add(rcToV(row + 1, col + 1, b)); //dr
        }
        
        return res;
    }
}
