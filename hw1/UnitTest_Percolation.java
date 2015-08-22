/**
 * Tests generated by TUnit.
 * Thu Mar 22 14:27:17 CET 2012
 */
public class UnitTest_Percolation {
 /**
  * Main method: run all tests.
  */
 public static void main(String[] args) {
  test_open();
  test_isFull();
  test_percolates();
  printConclusion();
 }
 
 /**
  * Test percolation open and isOpen
  */
 public static void test_open() {
  System.out.println("Test: open and isOpen");
  printTestStatement("Percolation testGrid = new Percolation(4);");
  Percolation testGrid = new Percolation(4);
  
  printTestStatement("testGrid.open(1,1)");
  testGrid.open(1,1);
  printTestResult("testGrid.isOpen(1,1) == true", testGrid.isOpen(1, 1), true);
  printTestResult("testGrid.isOpen(2,1) == false", testGrid.isOpen(2, 1), false);
  printTestResult("testGrid.isOpen(1,2) == false", testGrid.isOpen(1, 2), false);
  printTestStatement("testGrid.open(2,1)");
  testGrid.open(2,1);
  printTestResult("testGrid.isOpen(1,1) == true", testGrid.isOpen(1, 1), true);
  printTestResult("testGrid.isOpen(2,1) == true", testGrid.isOpen(2, 1), true);
  printTestResult("testGrid.isOpen(1,2) == false", testGrid.isOpen(1, 2), false);

  boolean thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.open(-1, 1);");
   testGrid.open(-1, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.open(1, -1);");
   testGrid.open(1, -1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isOpen(-1, 1);");
   testGrid.isOpen(-1, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.open(5, 1);");
   testGrid.open(5, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.open(1, 5);");
   testGrid.open(1, 5);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isOpen(5, 1);");
   testGrid.isOpen(5, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isOpen(1, 5);");
   testGrid.isOpen(1, 5);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  System.out.println();
 }

 public static void test_isFull(){
  System.out.println("Test: isFull");
  printTestStatement("Percolation testGrid = new Percolation(4);");
  Percolation testGrid = new Percolation(4);
  
  printTestStatement("testGrid.open(1,1)");
  testGrid.open(1,1);
  printTestResult("testGrid.isFull(1,1) == true", testGrid.isFull(1, 1), true);
  
  printTestStatement("testGrid.open(2,3)");
  testGrid.open(2,3);
  printTestResult("testGrid.isFull(2,3) == false", testGrid.isFull(2, 3), false);
  
  printTestStatement("testGrid.open(2,1)");
  testGrid.open(2,1);
  printTestResult("testGrid.isFull(2,1) == true", testGrid.isFull(2, 1), true);
  printTestStatement("testGrid.open(2,2)");
  testGrid.open(2,2);
  printTestResult("testGrid.isFull(2,2) == true", testGrid.isFull(2, 2), true);
  printTestResult("testGrid.isFull(2,3) == true", testGrid.isFull(2, 3), true);

  boolean thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isFull(-1, 1);");
   testGrid.isFull(-1, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isFull(1, -1);");
   testGrid.isFull(1, -1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isFull(-1, 1);");
   testGrid.isFull(-1, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isFull(5, 1);");
   testGrid.isFull(5, 1);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
  thrown = false;
  printTestStatement("try {");
  try {
   printTestStatement("// should throw IndexOutOfBoundsException");
   // should throw IndexOutOfBoundsException
   printTestStatement("testGrid.isFull(1, 5);");
   testGrid.isFull(1, 5);
  } catch(IndexOutOfBoundsException e) {
   printTestStatement("thrown = true;");
   thrown = true;
  }
  printTestResult("thrown == true", thrown, true);
  
 }

 public static void test_percolates(){
  System.out.println("Test: percolates");
  printTestStatement("Percolation testGrid = new Percolation(1);");
  Percolation testGrid = new Percolation(1);
  printTestStatement("testGrid.open(1,1)");
  testGrid.open(1,1);
  printTestResult("testGrid.percolates() == true", testGrid.percolates(), true);
  
  printTestStatement("Percolation testGrid = new Percolation(4);");
  testGrid = new Percolation(4);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  
  printTestStatement("testGrid.open(1,1)");
  testGrid.open(1,1);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(2,2)");
  testGrid.open(2,2);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(3,3)");
  testGrid.open(3,3);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(4,4)");
  testGrid.open(4,4);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  
  printTestStatement("Percolation testGrid = new Percolation(4);");
  testGrid = new Percolation(4);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  
  printTestStatement("testGrid.open(1,1)");
  testGrid.open(1,1);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(1,2)");
  testGrid.open(1,2);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(1,3)");
  testGrid.open(1,3);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(1,4)");
  testGrid.open(1,4);
  printTestResult("testGrid.percolates() == true", testGrid.percolates(), false);
  
  printTestStatement("Percolation testGrid = new Percolation(4);");
  testGrid = new Percolation(4);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  
  printTestStatement("testGrid.open(1,1)");
  testGrid.open(1,1);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(1,2)");
  testGrid.open(1,2);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(2,2)");
  testGrid.open(2,2);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(3,2)");
  testGrid.open(3,2);
  printTestResult("testGrid.percolates() == false", testGrid.percolates(), false);
  printTestStatement("testGrid.open(4,2)");
  testGrid.open(4,2);
  printTestResult("testGrid.percolates() == true", testGrid.percolates(), true);
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