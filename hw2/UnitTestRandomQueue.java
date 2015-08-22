import java.util.Iterator;
import java.util.NoSuchElementException;
 
 
public class UnitTestRandomQueue {
 
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                test_IsEmpty();
                test_Size();
                test_Exceptions();
                test_Output();
                test_Iterator();
               
                printConclusion();
        }
 
        public static void test_IsEmpty(){
                System.out.println("test_isEmpty() operations:");
                printTestStatement("RandomizedQueue<Integer> s = new RandomizedQueue<Integer>()");             
                RandomizedQueue<Integer> s = new RandomizedQueue<Integer>();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
               
                printTestStatement("s.enqueue(1);");   
                s.enqueue(1);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.enqueue(10);");  
                s.enqueue(10);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.enqueue(9);");   
                s.enqueue(9);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.enqueue(8);");   
                s.enqueue(8);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                System.out.println();
                               
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
                System.out.println();
 
                printTestStatement("s = new RandomizedQueue<Integer>()");              
                s = new RandomizedQueue<Integer>();    
               
                printTestStatement("s.enqueue(1);");   
                s.enqueue(1);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.isEmpty() == true)", s.isEmpty(), true);
                printTestStatement("s.addFirst(2);");  
                s.enqueue(2);
                printTestResult("s.isEmpty() == false)", s.isEmpty(), false);
               
                System.out.println();
        }
       
        public static void test_Size(){
                System.out.println("test_size() operations:");
                printTestStatement("RandomizedQueue<Integer> s = new RandomizedQueue<Integer>()");             
                RandomizedQueue<Integer> s = new RandomizedQueue<Integer>();
                printTestResult("s.size() == 0)", s.size(), 0);
               
                printTestStatement("s.enqueue(1);");   
                s.enqueue(1);
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.enqueue(2);");   
                s.enqueue(2);
                printTestResult("s.size() == 2)", s.size(), 2);
                printTestStatement("s.enqueue(3);");   
                s.enqueue(3);
                printTestResult("s.size() == 3)", s.size(), 3);
                printTestStatement("s.enqueue(4);");   
                s.enqueue(4);
                printTestResult("s.size() == 4)", s.size(), 4);
                System.out.println();
       
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.size() == 3)", s.size(), 3);
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.size() == 2)", s.size(), 2);
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.size() == 0)", s.size(), 0);
                System.out.println();
                       
                printTestStatement("s = new Deque<Integer>()");        
                s = new RandomizedQueue<Integer>();    
                printTestStatement("s.enqueue(1);");   
                s.enqueue(1);
                printTestResult("s.size() == 1)", s.size(), 1);
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestResult("s.size() == 0)", s.size(), 0);
                printTestStatement("s.enqueue(2);");   
                s.enqueue(2);
                printTestResult("s.size() == 1)", s.size(), 1);
               
                System.out.println();
        }
 
        public static void test_Exceptions(){
                System.out.println("test_Exceptions() operations:");
                printTestStatement("RandomizedQueue<Integer> s = new RandomizedQueue<Integer>()");             
                RandomizedQueue<Integer> s = new RandomizedQueue<Integer>();
                               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NullPointerException");
                printTestStatement("s.enqueue(null);");
                s.enqueue(null);
                } catch(NullPointerException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
                System.out.println();
               
                printTestStatement("boolean thrown = false;");
                thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                printTestStatement("s.dequeue();");
                s.sample();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
                System.out.println();
               
                printTestStatement("boolean thrown = false;");
                thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                printTestStatement("s.sample();");
                s.dequeue();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
                System.out.println();
               
                printTestStatement("s = new Deque<Integer>()");        
                s = new RandomizedQueue<Integer>();    
                printTestStatement("s.enqueue(1);");   
                s.enqueue(1);
                printTestStatement("s.dequeue();");
                s.dequeue();
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                printTestStatement("s.sample();");
                s.sample();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                printTestStatement("boolean thrown = false;");
                thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw NoSuchElementException");
                printTestStatement("s.dequeue();");
                s.dequeue();
                } catch(NoSuchElementException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
               
                printTestStatement("s.enqueue(2);");   
                s.enqueue(2);
                System.out.println();
        }
 
        public static void test_Output(){
                System.out.println("Test combined (de)queue() operations:");
                printTestStatement("RandomizedQueue<Integer> s = new RandomizedQueue<Integer>()");             
                RandomizedQueue<Integer> s = new RandomizedQueue<Integer>();
               
               
                StringBuilder stringBuilder = new StringBuilder();
                System.out.println("Enqueue and dequeue s");
                for(int i = 0; i  < 10; i++){
                        String testStatement = String.format("s.enqueue(%d)",i);
                        printTestStatement(testStatement);
                        s.enqueue(i);
                }
               
                for(int i = 0; i < 10; i ++){
                        printTestStatement("s.dequeue()");
                        stringBuilder.append(String.format("%d, ", s.dequeue()));
                }
                String firstTest = stringBuilder.toString();
                System.out.println();
               
                stringBuilder = new StringBuilder();
                System.out.println("Enqueue and dequeue s #2 (s is not reinitialized)");
                for(int i = 0; i  < 10; i++){
                        String testStatement = String.format("s.enqueue(%d)",i);
                        printTestStatement(testStatement);
                        s.enqueue(i);
                }
               
                for(int i = 0; i < 10; i ++){
                        printTestStatement("s.dequeue()");
                        stringBuilder.append(String.format("%d, ", s.dequeue()));
                }
                String secondTest = stringBuilder.toString();
               
                boolean isOutputEqual = secondTest.equals(firstTest);
                printTestResult("Output (#1 == #2) == false ", isOutputEqual, false);
                System.out.format(" Output #1: %s%n", firstTest);
                System.out.format(" Output #2: %s%n", secondTest);
                System.out.println();          
        }
       
        public static void test_Iterator(){
                System.out.println("test_iterator operations:");
                printTestStatement("RandomizedQueue <Integer> s = new RandomizedQueue<Integer>()");            
                RandomizedQueue<Integer> s = new RandomizedQueue<Integer>();
               
                for(int i = 0; i < 10 ; i++){
                        String testStatement = String.format("s.enqueue(%d)",i);
                        printTestStatement(testStatement);
                        s.enqueue(i);
                }
               
                printTestStatement("Iterator<Integer> firstIterator = s.iterator()");
                Iterator<Integer> firstIterator = s.iterator();
                printTestStatement("Iterator<Integer> secondIterator = s.iterator()");
                Iterator<Integer> secondIterator = s.iterator();
               
                printTestStatement("boolean thrown = false;");
                boolean thrown = false;
                printTestStatement("try {");
                try {
                printTestStatement("// should throw UnsupportedOperationException");
                // should throw UnsupportedOperationException
                printTestStatement("it.remove();");
                firstIterator.remove();
                } catch(UnsupportedOperationException e) {
                printTestStatement("thrown = true;");
                thrown = true;
                }
                printTestResult("thrown == true", thrown, true);
                System.out.println();
                               
                printTestStatement("foreach int i in s #1, print");
                for(int i: s){
                        String message = String.format("i: %d ", i);
                        printTestStatement(message);
                }
                System.out.println();
               
                printTestStatement("foreach int i in s #2, print");
                for(int i: s){
                        String message = String.format("i: %d ", i);
                        printTestStatement(message);
                }
                System.out.println();
                       
                System.out.println(" Traversing both iterators: ");
                int i = 9;
                while(firstIterator.hasNext()){
                        System.out.format(" Output #%d: %d , %d%n", i, firstIterator.next(), secondIterator.next());
                        i--;
                }
               
                printTestResult("firstIterator.hasNext() == false", firstIterator.hasNext(), false);
                printTestResult("secondIterator.hasNext() == false", secondIterator.hasNext(), false);
               
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
        }
}