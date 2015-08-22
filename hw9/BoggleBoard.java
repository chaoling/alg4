public class BoggleBoard
{
    
    // the 16 Boggle dice (1992 version)
    private static final String[] BOGGLE1992 = {
        "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS",
        "ANAEEG", "IDSYTT", "OATTOW", "MTOICU",
        "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU",
        "YLDEVR", "ZNRNHL", "NMIQHU", "OBBAOJ"
    };
    
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final double[] FREQUENCIES = {
        0.08167, 0.01492, 0.02782, 0.04253, 0.12703, 0.02228,
        0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025,
        0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
        0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150,
        0.01974, 0.00074
    };
    
    private char[][] mBoard;
    private int mRows;
    private int mCols;
    
    // Initializes a random 4-by-4 Boggle board.
    // (by rolling the Hasbro dice)
    public BoggleBoard() {
        mBoard = new char[4][4];
        mRows = 4;
        mCols = 4;
        StdRandom.shuffle(BOGGLE1992);
        mBoard = new char[mRows][mCols];
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                String letters = BOGGLE1992[mCols*i+j];
                int r = StdRandom.uniform(letters.length());
                mBoard[i][j] = letters.charAt(r);
            }
        }
    }

    // Initializes a random M-by-N Boggle board.
    // (using the frequency of letters in the English language)
    public BoggleBoard(int M, int N) {
        this.mRows = M;
        this.mCols = N;
        mBoard = new char[mRows][mCols];
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                int r = StdRandom.discrete(FREQUENCIES);
                mBoard[i][j] = ALPHABET.charAt(r);
            }
        }
    }

    // Initializes a Boggle board from the specified filename.
    public BoggleBoard(String filename) {
        In in = new In(filename);
        mRows = in.readInt();
        mCols = in.readInt();
        mBoard = new char[mRows][mCols];
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                String letter = in.readString().toUpperCase();
                if (letter.equals("QU"))
                    mBoard[i][j] = 'Q';
                else if (letter.length() != 1)
                    throw new IllegalArgumentException(
                              "invalid character: " + letter);
                else if (ALPHABET.indexOf(letter) == -1)
                    throw new IllegalArgumentException(
                              "invalid character: " + letter);
                else 
                    mBoard[i][j] = letter.charAt(0);
            }
        }
    }

    // Initializes a Boggle board from the 2d char array.
    // (with 'Q' representing the two-letter sequence "Qu")
    public BoggleBoard(char[][] a) {
        this.mRows = a.length;
        this.mCols = a[0].length;
        mBoard = new char[mRows][mCols];
        for (int i = 0; i < mRows; i++) {
            if (a[i].length != mCols)
                throw new IllegalArgumentException(
                          "char[][] array is ragged");
            for (int j = 0; j < mCols; j++) {
                if (ALPHABET.indexOf(a[i][j]) == -1)
                    throw new IllegalArgumentException(
                              "invalid character: " + a[i][j]);
                mBoard[i][j] = a[i][j];
            }
        }
    }
    // Returns the number of rows.
    public int rows() {
        return mRows;
    }

    // Returns the number of columns.
    public int cols() {
        return mCols;
    }

    // Returns the letter in row i and column j.
    // (with 'Q' representing the two-letter sequence "Qu")
    public char getLetter(int i, int j) {
        return mBoard[i][j];
    }

    // Returns a string representation of the board.
    public String toString() {
        StringBuilder sb = new StringBuilder(mRows + " " + mCols + "\n");
        for (int i = 0; i < mRows; i++) {
            for (int j = 0; j < mCols; j++) {
                sb.append(mBoard[i][j]);
                if (mBoard[i][j] == 'Q') sb.append("u ");
                else sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
    
    public static void main(String[] args) {
        // initialize a 4-by-4 board using Hasbro dice
        StdOut.println("Hasbro board:");
        BoggleBoard board1 = new BoggleBoard();
        StdOut.println(board1);
        StdOut.println();

        // initialize a 4-by-4 board using letter frequencies in English language
        StdOut.println("Random 4-by-4 board:");
        BoggleBoard board2 = new BoggleBoard(4, 4);
        StdOut.println(board2);
        StdOut.println();

        // initialize a 4-by-4 board from a 2d char array
        StdOut.println("4-by-4 board from 2D character array:");
        char[][] a =  {
            { 'D', 'O', 'T', 'Y' },
            { 'T', 'R', 'S', 'F' },
            { 'M', 'X', 'M', 'O' },
            { 'Z', 'A', 'B', 'W' }
        };
        BoggleBoard board3 = new BoggleBoard(a);
        StdOut.println(board3);
        StdOut.println();

        // initialize a 4-by-4 board from a file
        String filename = "board-q.txt";
        StdOut.println("4-by-4 board from file " + filename + ":");
        BoggleBoard board4 = new BoggleBoard(filename);
        StdOut.println(board4);
        StdOut.println();
    }
}
