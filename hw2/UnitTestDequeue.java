import java.util.NoSuchElementException;
import java.util.Iterator;
 
public class UnitTestDequeue {
 
       
        public static void main(String[] args) {
                test_IsEmpty();
                test_Size();
                test_frontEmptyQueue();
                test_lastEmptyQueue();
                test_firstOperations();
                test_lastOperations();
                test_iterator();
               
                printConclusion();
        }
       
        public static void test_IsEmpty(){
                System.out.println("test_isEmpty() operations:");
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
               
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addFirst(10);"); 
                s.addFirst(10);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addFirst(9);");  
                s.addFirst(9);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addFirst(8);");  
                s.addFirst(8);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                System.out.println();
                               
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
                System.out.println();
                               
                printTestStatement("s.addLast(1);");   
                s.addLast(1);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addLast(10);");  
                s.addLast(10);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addLast(9);");   
                s.addLast(9);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addLasst(8);");  
                s.addLast(8);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                System.out.println();
               
                printTestStatement("s.removeLast();"); 
                s.removeLast();
                printTestStatement("s.removeLast();");
                s.removeLast();
                printTestStatement("s.removeLast();");
                s.removeLast();
                printTestStatement("s.removeLast();");
                s.removeLast();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);    
                System.out.println();  
 
                printTestStatement("s = new Deque<Integer>()");        
                s = new Deque<Integer>();      
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addLast(1);");   
                s.addLast(2);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addFirst(0);");  
                s.addFirst(0);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.addLast(3);");   
                s.addLast(3);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                System.out.println();
                               
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.removeLast();"); 
                s.removeLast();
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.removeLast();"); 
                s.removeLast();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
                System.out.println();          
               
                printTestStatement("s = new Deque<Integer>()");        
                s = new Deque<Integer>();      
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
                printTestStatement("s.addFirst(2);");  
                s.addFirst(2);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
               
                System.out.println();
        }
       
        public static void test_Size(){
                System.out.println("test_size() operations:");
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
                printTestResult("s.size() == 0)", s.size(), 0);
               
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.addFirst(2);");  
                s.addFirst(2);
                printTestResult("s.size() == 2)", s.size(), 2);
                printTestStatement("s.addFirst(3);");  
                s.addFirst(3);
                printTestResult("s.size() == 3)", s.size(), 3);
                printTestStatement("s.addFirst(4);");  
                s.addFirst(4);
                printTestResult("s.size() == 4)", s.size(), 4);
                System.out.println();
       
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.size() == 0)", s.size(), 0);
                System.out.println();
 
                printTestStatement("s.addLast(1);");   
                s.addLast(1);
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.addLast(2);");   
                s.addLast(2);
                printTestResult("s.size() == 2)", s.size(), 2);
                printTestStatement("s.addLast(3);");   
                s.addLast(3);
                printTestResult("s.size() == 3)", s.size(), 3);
                printTestStatement("s.addLast(4);");   
                s.addLast(4);
                printTestResult("s.size() == 4)", s.size(), 4);
                System.out.println();
               
                printTestStatement("s.removeLast();"); 
                s.removeLast();
                printTestStatement("s.removeLast();");
                s.removeLast();
                printTestStatement("s.removeLast();");
                s.removeLast();
                printTestStatement("s.removeLast();");
                s.removeLast();
                printTestResult("s.size() == 0)", s.size(), 0);
                System.out.println();
               
                printTestStatement("s = new Deque<Integer>()");        
                s = new Deque<Integer>();      
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.addLast(1);");   
                s.addLast(2);
                printTestResult("s.size() == 2)", s.size(), 2);
                printTestStatement("s.addFirst(0);");  
                s.addFirst(0);
                printTestResult("s.size() == 3)", s.size(), 3);
                printTestStatement("s.addLast(3);");   
                s.addLast(3);
                printTestResult("s.size() == 4)", s.size(), 4);
                System.out.println();
                               
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.size() == 3)", s.size(), 3);
                printTestStatement("s.removeLast();"); 
                s.removeLast();
                printTestResult("s.size() == 2)", s.size(), 2);
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.removeLast();"); 
                s.removeLast();
                printTestResult("s.size() == 0)", s.size(), 0);
                System.out.println();
               
                printTestStatement("s = new Deque<Integer>()");        
                s = new Deque<Integer>();      
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                printTestResult("s.size() == 0)", s.size(), 0);
                printTestStatement("s.addFirst(2);");  
                s.addFirst(2);
                printTestResult("s.size() == 1)", s.size(), 1);
               
                System.out.println();
        }
 
        public static void test_frontEmptyQueue() {
                System.out.println("Test: front, empty dequeue");
               
                // test code
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                // should throw EmptyQueueException
                printTestStatement("s.removeFirst();");
                s.removeFirst();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                System.out.println();
        }
 
        public static void test_lastEmptyQueue() {
                System.out.println("Test: last, empty dequeue");
               
                // test code
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                // should throw EmptyQueueException
                printTestStatement("s.removeLast();");
                s.removeLast();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                System.out.println();
        }
 
        public static void test_firstOperations() {
                System.out.println("Test: front operations");
               
                // test code
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NullPointerException");
                // should throw EmptyQueueException
                printTestStatement("s.removeLast();");
                s.addFirst(null);
                } catch(NullPointerException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                System.out.println();
               
                printTestStatement("s.addFirst(4);");
                s.addFirst(4);
                printTestResult("s.removeFirst() == 4", (int)s.removeFirst(), 4);
               
                printTestStatement("s.addLast(4);");
                s.addLast(4);
                printTestResult("s.removeFirst() == 4", (int)s.removeFirst(), 4);
               
                printTestStatement("s.addFirst(4);");
                s.addFirst(4);
                printTestStatement("s.addFirst(3);");
                s.addFirst(3);
                printTestStatement("s.addFirst(2);");
                s.addFirst(2);
                printTestStatement("s.addFirst(1);");
                s.addFirst(1);
                printTestResult("s.removeFirst() == 1", (int)s.removeFirst(), 1);
                printTestResult("s.removeFirst() == 2", (int)s.removeFirst(), 2);
                printTestResult("s.removeFirst() == 3", (int)s.removeFirst(), 3);
                printTestResult("s.removeFirst() == 4", (int)s.removeFirst(), 4);
                System.out.println();
               
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                s = new Deque<Integer>();      
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestStatement("s.addLast(2);");   
                s.addLast(2);
                printTestStatement("s.addFirst(0);");  
                s.addFirst(0);
                printTestStatement("s.addLast(3);");   
                s.addLast(3);
 
                printTestResult("s.removeFirst() == 0", (int)s.removeFirst(), 0);
                printTestResult("s.removeFirst() == 1", (int)s.removeFirst(), 1);
                printTestResult("s.removeFirst() == 2", (int)s.removeFirst(), 2);
                printTestResult("s.removeFirst() == 3", (int)s.removeFirst(), 3);
 
                System.out.println();
        }
 
        public static void test_lastOperations() {
                System.out.println("Test: last operations");
               
                // test code
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NullPointerException");
                // should throw EmptyQueueException
                printTestStatement("s.removeLast();");
                s.addLast(null);
                } catch(NullPointerException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                System.out.println();
               
                printTestStatement("s.addFirst(4);");
                s.addFirst(4);
                printTestResult("s.removeLast() == 4", (int)s.removeLast(), 4);
               
                printTestStatement("s.addLast(4);");
                s.addLast(4);
                printTestResult("s.removeFirst() == 4", (int)s.removeLast(), 4);
               
                printTestStatement("s.addLast(4);");
                s.addLast(4);
                printTestStatement("s.addLast(3);");
                s.addLast(3);
                printTestStatement("s.addLast(2);");
                s.addLast(2);
                printTestStatement("s.addLast(1);");
                s.addLast(1);
                printTestResult("s.removeLast() == 1", (int)s.removeLast(), 1);
                printTestResult("s.removeLast() == 2", (int)s.removeLast(), 2);
                printTestResult("s.removeLast() == 3", (int)s.removeLast(), 3);
                printTestResult("s.removeLast() == 4", (int)s.removeLast(), 4);
                System.out.println();
                               
                s = new Deque<Integer>();      
                printTestStatement("s.addFirst(1);");  
                s.addFirst(1);
                printTestStatement("s.addLast(2);");   
                s.addLast(2);
                printTestStatement("s.addFirst(0);");  
                s.addFirst(0);
                printTestStatement("s.addLast(3);");   
                s.addLast(3);
 
                printTestResult("s.removeLast() == 0", (int)s.removeLast(), 3);
                printTestResult("s.removeLast() == 1", (int)s.removeLast(), 2);
                printTestResult("s.removeLast() == 2", (int)s.removeLast(), 1);
                printTestResult("s.removeLast() == 3", (int)s.removeLast(), 0);
 
                System.out.println();
        }
       
        public static void test_iterator(){
                System.out.println("test_iterator operations:");
                printTestStatement("Deque<Integer> s = new Deque<Integer>()");         
                Deque<Integer> s = new Deque<Integer>();
               
                for(int i = 0; i < 10 ; i++){
                        String testStatement = String.format("s.addFirst(%d)",i);
                        printTestStatement(testStatement);
                        s.addFirst(i);
                }
               
                printTestStatement("Iterator<Integer> it = s.iterator()");
                Iterator<Integer> it = s.iterator();
               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw UnsupportedOperationException");
                // should throw UnsupportedOperationException
                printTestStatement("it.remove();");
                it.remove();
                } catch(UnsupportedOperationException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
                System.out.println();
                               
                printTestStatement("foreach int i in s, print");
                int j = 9;
                for(int i: s){
                        String message = String.format("i: %d == %d", i, j);
                        printTestResult(message, i , j);
                        j--;
                }
                System.out.println();
                       
                int i = 9;
                StringBuilder verificationOutput = new StringBuilder();
                while(it.hasNext()){
                        int iteratorOutput = (int)it.next();
                        printTestResult("it.next();", iteratorOutput , i);
                        i--;
                       
                        verificationOutput.append(String.format("%d, ", iteratorOutput));
                }
                System.out.format("Iterator output: %s%n", verificationOutput.toString());
               
                printTestResult("it.hasNext() == false", it.hasNext(), false);
               
                printTestStatement("boolean thrown = false;");
                thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                // should throw EmptyQueueException
                printTestStatement("it.next();");
                it.next();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                System.out.println();
               
                System.out.println();
        }
       
        private static int passed = 0;
        private static int failed = 0;
 
        /** Prints the conclusion */
        public static void printConclusion() {
                System.out.println("-------------------------------------------------");
                System.out.println("Tests run: "+(passed+failed)+", Failures: "+failed+".");
                if (failed == 0)
                        System.out.print("Well done!");
        }
 
        /** Prints the statement */
        public static void printTestStatement(String testStatement) {
                System.out.println(" "+testStatement);
        }
 
        /**
         * Compares the actual result with the expected result
         * and prints the result of this test
         */
        public static void printTestResult(String testName, int actual, int expected) {
                System.out.printf(" %-50s => ", testName);
                if (expected == actual) {
                        System.out.println("passed");
                        passed++;
                } else {
                        System.out.println(" FAILED");
                        System.out.println("  -> expected " + expected + " but was " + actual);
                        System.out.println();
                        failed++;
                }
        }
 
        /**
         * Compares the actual result with the expected result
         * and prints the result of this test
         */
        public static void printTestResult(String testName, double actual, double expected) {
                System.out.printf(" %-50s => ", testName);
                if (Math.abs(expected - actual) < 1e-10) {
                        System.out.println("passed");
                        passed++;
                } else {
                        System.out.println(" FAILED");
                        System.out.println("  -> expected " + expected + " but was " + actual);
                        System.out.println();
                        failed++;
                }
        }
 
        /**
         * Compares the actual result with the expected result
         * and prints the result of this test
         */
        public static void printTestResult(String testName, boolean actual, boolean expected) {
                System.out.printf(" %-50s => ", testName);
                if (expected == actual) {
                        System.out.println("passed");
                        passed++;
                } else {
                        System.out.println(" FAILED");
                        System.out.println("  -> expected " + expected + " but was " + actual);
                        System.out.println();
                        failed++;
                }
        }
 
        /**
         * Compares the actual result with the expected result
         * and prints the result of this test
         */
        public static void printTestResult(String testName, int[] actual, int[] expected) {
                System.out.printf(" %-50s => ", testName);
                if (java.util.Arrays.equals(expected, actual)) {
                        System.out.println("passed");
                        passed++;
                } else {
                        System.out.print(" FAILED\n -> expected:\n    ");
                        System.out.println(java.util.Arrays.toString(expected) + "\n");
                        System.out.print("    but was:\n     ");
                        System.out.println(java.util.Arrays.toString(actual));
                        System.out.println();
                        failed++;
                }
        }
 
        /**
         * Compares the actual result with the expected result
         * and prints the result of this test
         */
        public static void printTestResult(String testName, Object actual, Object expected) {
                System.out.printf(" %-50s => ", testName);
                if ((expected==null && actual==null) || (expected!=null && expected.equals(actual))) {
                        System.out.println("passed");
                        passed++;
                } else {
                        System.out.print(" FAILED\n -> expected:\n    ");
                        System.out.print(" \"" + expected + "\"\n");
                        System.out.print("    but was:\n    ");
                        System.out.print(" \"" + actual+ "\"");
                        System.out.println();
                        failed++;
                }
        }
}