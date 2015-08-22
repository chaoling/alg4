public class MoveToFront {
    // apply move-to-front encoding, 
    // reading from standard input and writing to standard output
    private static final int R = 256;
    
    public static void encode() {
        char[] ascii256 = initAsciiTbl();
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar(8);
            int index = indexOf(ascii256, c);
            if (index != 0) {
               moveToFront(ascii256, index);
            }
            BinaryStdOut.write(index, 8);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, 
    // reading from standard input and writing to standard output
    public static void decode() {
        char[] ascii256 = initAsciiTbl();
        
        while (!BinaryStdIn.isEmpty()) {
            int index = BinaryStdIn.readChar(8);
            char c = ascii256[index];
            if (index != 0) {
                moveToFront(ascii256, index);   
            }
            BinaryStdOut.write(c, 8);
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            //System.err.println("move-to-front encoding...");
            encode();
        }
        else if (args[0].equals("+")) {
            //System.err.println("move-to-front decoding...");
            decode();
        }
        else throw new IllegalArgumentException("Illegal command line argument");
    }
    
    private static char[] initAsciiTbl() {
        char[] tbl = new char[R];
        for (char i = 0; i < R; i++) {
            tbl[i] = i;
        }
        return tbl;
    }
    
    private static int indexOf(char[] heystack, char needle) {
        for (int i = 0; i < heystack.length; i++) {
            if (heystack[i] == needle) {
                return i;
            }
        }
        return -1;
    }
    
    private static void moveToFront(char[] ascii, int index) {
        char c = ascii[index];
        System.arraycopy(ascii, 0, ascii, 1, index);
        ascii[0] = c;
    }
}