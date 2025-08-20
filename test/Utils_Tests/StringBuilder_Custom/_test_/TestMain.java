package Utils_Tests.StringBuilder_Custom._test_;

import DataStructures_Implementations.Stringbuilder_Custom.Custom_Stringbuilder;

/**
 * Manual test runner for Custom_Stringbuilder class.
 * This class provides a main method to manually test various operations
 * without requiring JUnit framework.
 */
public class TestMain {

    private static int testCount = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("=== Custom_Stringbuilder Manual Test Suite ===\n");

        // Run all test categories
        testConstructorAndInitialState();
        testBasicOperations();
        testAppendOperations();
        testInsertOperations();
        testReplaceOperations();
        testDeleteOperations();
        testReverseOperations();
        testClearOperations();
        testToStringOperations();
        testErrorHandling();
        testComplexOperations();
        testEdgeCases();

        // Print final results
        printTestSummary();
    }

    private static void testConstructorAndInitialState() {
        System.out.println("--- Testing Constructor and Initial State ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();

        test("Initial length should be 0", sb.length() == 0);
        test("Initial isEmpty should be true", sb.isEmpty());
        test("Initial toString should be empty", "".equals(sb.toString()));

        System.out.println();
    }

    private static void testBasicOperations() {
        System.out.println("--- Testing Basic Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();

        // Test length and isEmpty
        test("Empty length", sb.length() == 0);
        test("Empty isEmpty", sb.isEmpty());

        sb.append("Hello");
        test("Length after append", sb.length() == 5);
        test("Not empty after append", !sb.isEmpty());

        sb.clear();
        test("Length after clear", sb.length() == 0);
        test("Empty after clear", sb.isEmpty());

        System.out.println();
    }

    private static void testAppendOperations() {
        System.out.println("--- Testing Append Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();

        // Test append string
        sb.append("Hello");
        test("Append string", "Hello".equals(sb.toString()));

        sb.append(" World");
        test("Append second string", "Hello World".equals(sb.toString()));

        // Test append char
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append('H');
        sb2.append('i');
        test("Append characters", "Hi".equals(sb2.toString()));

        // Test append empty string
        sb.append("");
        test("Append empty string", "Hello World".equals(sb.toString()));

        // Test large append (triggers resize)
        Custom_Stringbuilder sb3 = new Custom_Stringbuilder();
        String longString = "This is a very long string that exceeds the default capacity";
        sb3.append(longString);
        test("Append long string (resize)", longString.equals(sb3.toString()));

        System.out.println();
    }

    private static void testInsertOperations() {
        System.out.println("--- Testing Insert Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();
        sb.append("Hello World");

        // Insert at beginning
        sb.insert(0, "Hi ");
        test("Insert at beginning", "Hi Hello World".equals(sb.toString()));

        // Insert in middle
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append("Hello");
        sb2.insert(2, "XXX");
        test("Insert in middle", "HeXXXllo".equals(sb2.toString()));

        // Insert at end
        Custom_Stringbuilder sb3 = new Custom_Stringbuilder();
        sb3.append("Hello");
        sb3.insert(sb3.length(), "!");
        test("Insert at end", "Hello!".equals(sb3.toString()));

        // Insert character
        Custom_Stringbuilder sb4 = new Custom_Stringbuilder();
        sb4.append("Hllo");
        sb4.insert(1, 'e');
        test("Insert character", "Hello".equals(sb4.toString()));

        System.out.println();
    }

    private static void testReplaceOperations() {
        System.out.println("--- Testing Replace Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();
        sb.append("Hello World");

        // Replace with string
        sb.replace(6, 11, "Java");
        test("Replace with string", "Hello Java".equals(sb.toString()));

        // Replace with character
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append("Hello");
        sb2.replace(1, 4, 'X');
        test("Replace with character", "HXo".equals(sb2.toString()));

        // Replace entire string
        Custom_Stringbuilder sb3 = new Custom_Stringbuilder();
        sb3.append("Old");
        sb3.replace(0, sb3.length(), "New");
        test("Replace entire string", "New".equals(sb3.toString()));

        System.out.println();
    }

    private static void testDeleteOperations() {
        System.out.println("--- Testing Delete Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();
        sb.append("Hello World");

        // Delete range
        sb.delete(5, 11);
        test("Delete range", "Hello".equals(sb.toString()));

        // Delete single character
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append("Hello");
        sb2.deleteCharAt(1);
        test("Delete single character", "Hllo".equals(sb2.toString()));

        // Delete from beginning
        Custom_Stringbuilder sb3 = new Custom_Stringbuilder();
        sb3.append("Hello");
        sb3.delete(0, 2);
        test("Delete from beginning", "llo".equals(sb3.toString()));

        // Delete to end
        Custom_Stringbuilder sb4 = new Custom_Stringbuilder();
        sb4.append("Hello");
        sb4.delete(2, sb4.length());
        test("Delete to end", "He".equals(sb4.toString()));

        System.out.println();
    }

    private static void testReverseOperations() {
        System.out.println("--- Testing Reverse Operations ---");

        // Reverse even length
        Custom_Stringbuilder sb1 = new Custom_Stringbuilder();
        sb1.append("abcd");
        sb1.reverse();
        test("Reverse even length", "dcba".equals(sb1.toString()));

        // Reverse odd length
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append("abcde");
        sb2.reverse();
        test("Reverse odd length", "edcba".equals(sb2.toString()));

        // Reverse single character
        Custom_Stringbuilder sb3 = new Custom_Stringbuilder();
        sb3.append("a");
        sb3.reverse();
        test("Reverse single character", "a".equals(sb3.toString()));

        // Reverse empty
        Custom_Stringbuilder sb4 = new Custom_Stringbuilder();
        sb4.reverse();
        test("Reverse empty", "".equals(sb4.toString()));

        // Reverse palindrome
        Custom_Stringbuilder sb5 = new Custom_Stringbuilder();
        sb5.append("racecar");
        sb5.reverse();
        test("Reverse palindrome", "racecar".equals(sb5.toString()));

        System.out.println();
    }

    private static void testClearOperations() {
        System.out.println("--- Testing Clear Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();
        sb.append("Hello World");

        test("Before clear - not empty", !sb.isEmpty());
        test("Before clear - has length", sb.length() > 0);

        sb.clear();
        test("After clear - empty", sb.isEmpty());
        test("After clear - zero length", sb.length() == 0);
        test("After clear - empty string", "".equals(sb.toString()));

        // Test append after clear
        sb.append("New");
        test("Append after clear", "New".equals(sb.toString()));

        System.out.println();
    }

    private static void testToStringOperations() {
        System.out.println("--- Testing ToString Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();

        test("Empty toString", "".equals(sb.toString()));

        sb.append("Hello");
        test("Simple toString", "Hello".equals(sb.toString()));

        sb.append(" World!");
        test("Complex toString", "Hello World!".equals(sb.toString()));

        // Test with special characters
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append("Line1\nLine2\tTabbed");
        test("Special characters toString", "Line1\nLine2\tTabbed".equals(sb2.toString()));

        System.out.println();
    }

    private static void testErrorHandling() {
        System.out.println("--- Testing Error Handling ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();
        sb.append("Hello");

        // Test insert out of bounds
        boolean insertException1 = false;
        try {
            sb.insert(-1, "test");
        } catch (IndexOutOfBoundsException e) {
            insertException1 = true;
        }
        test("Insert negative index throws exception", insertException1);

        boolean insertException2 = false;
        try {
            sb.insert(10, "test");
        } catch (IndexOutOfBoundsException e) {
            insertException2 = true;
        }
        test("Insert beyond length throws exception", insertException2);

        // Test delete out of bounds
        boolean deleteException = false;
        try {
            sb.delete(-1, 2);
        } catch (IndexOutOfBoundsException e) {
            deleteException = true;
        }
        test("Delete negative index throws exception", deleteException);

        // Test deleteCharAt out of bounds
        boolean deleteCharException = false;
        try {
            sb.deleteCharAt(10);
        } catch (IndexOutOfBoundsException e) {
            deleteCharException = true;
        }
        test("DeleteCharAt beyond length throws exception", deleteCharException);

        // Test replace out of bounds
        boolean replaceException = false;
        try {
            sb.replace(-1, 2, "test");
        } catch (IndexOutOfBoundsException e) {
            replaceException = true;
        }
        test("Replace negative index throws exception", replaceException);

        System.out.println();
    }

    private static void testComplexOperations() {
        System.out.println("--- Testing Complex Operations ---");

        Custom_Stringbuilder sb = new Custom_Stringbuilder();

        // Complex sequence of operations
        sb.append("Hello");
        sb.insert(0, "Hi ");
        sb.append(" World");
        test("Complex step 1", "Hi Hello World".equals(sb.toString()));

        sb.replace(3, 8, "Java");
        test("Complex step 2", "Hi Java World".equals(sb.toString()));

        sb.delete(2, 7);
        test("Complex step 3", "Hi World".equals(sb.toString()));

        sb.reverse();
        test("Complex step 4", "dlroW iH".equals(sb.toString()));

        System.out.println();
    }

    private static void testEdgeCases() {
        System.out.println("--- Testing Edge Cases ---");

        // Empty string operations
        Custom_Stringbuilder sb1 = new Custom_Stringbuilder();
        sb1.insert(0, "First");
        test("Insert into empty", "First".equals(sb1.toString()));

        // Single character operations
        Custom_Stringbuilder sb2 = new Custom_Stringbuilder();
        sb2.append("A");
        sb2.deleteCharAt(0);
        test("Delete single character", "".equals(sb2.toString()) && sb2.isEmpty());

        // Replace empty range (should just insert)
        Custom_Stringbuilder sb3 = new Custom_Stringbuilder();
        sb3.append("Hello");
        sb3.replace(2, 2, "XXX");
        test("Replace empty range", "HeXXXllo".equals(sb3.toString()));

        // Delete empty range
        Custom_Stringbuilder sb4 = new Custom_Stringbuilder();
        sb4.append("Hello");
        sb4.delete(2, 2);
        test("Delete empty range", "Hello".equals(sb4.toString()));

        // Multiple resizes
        Custom_Stringbuilder sb5 = new Custom_Stringbuilder();
        for (int i = 0; i < 100; i++) {
            sb5.append("a");
        }
        test("Multiple resizes", sb5.length() == 100);

        System.out.println();
    }

    // Helper method to run a test
    private static void test(String description, boolean condition) {
        testCount++;
        if (condition) {
            passedTests++;
            System.out.println("✓ PASS: " + description);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + description);
        }
    }

    // Print test summary
    private static void printTestSummary() {
        System.out.println("=".repeat(50));
        System.out.println("TEST SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Total Tests: " + testCount);
        System.out.println("Passed: " + passedTests + " (" + String.format("%.1f", (passedTests * 100.0 / testCount)) + "%)");
        System.out.println("Failed: " + failedTests + " (" + String.format("%.1f", (failedTests * 100.0 / testCount)) + "%)");
        System.out.println("=".repeat(50));

        if (failedTests == 0) {
            System.out.println("🎉 ALL TESTS PASSED! 🎉");
        } else {
            System.out.println("⚠️  Some tests failed. Please review the implementation.");
        }
    }
}