package interview.coding;

import java.util.List;

/*
Given an array of integers, calculate the ratios of its elements that are
positive, negative, and zero. Print the decimal value of each fraction on a
new line with 6 places after the decimal.

Note: This challenge introduces precision problems. The test cases are
scaled to six decimal places, though answers with absolute error of up to
10^-4 are acceptable.

Example

arr = [1, 1, 0, -1, -1]

There are n = 5 elements: two positive, two negative and one zero. Their
ratios are 2/5 = 0.400000, 2/5 = 0.400000 and 1/5 = 0.200000. Results are
printed as:

0.400000
0.400000
0.200000
*/

class Result1 {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
        // Write your code here
        double positive = 0;
        double negative = 0;
        double neutral = 0;
        for (Integer integer : arr) {
            if (integer > 0)
                positive++;
            if (integer < 0)
                negative++;
            if (integer == 0)
                neutral++;
        }
        System.out.printf("%.6f%n", (positive / arr.size()));
        System.out.printf("%.6f%n", (negative / arr.size()));
        System.out.printf("%.6f%n", (neutral / arr.size()));
    }
}

public class PlusMinus {
    record TestCase(List<Integer> arr, String[] expected) {
    }

    public static void main(String[] args) {
        List<TestCase> tests = java.util.Arrays.asList(
                // Test 1: Example from problem statement
                new TestCase(java.util.Arrays.asList(1, 1, 0, -1, -1), new String[]{"0.400000", "0.400000", "0.200000"}),
                // Test 2: All positive numbers
                new TestCase(java.util.Arrays.asList(1, 2, 3, 4, 5), new String[]{"1.000000", "0.000000", "0.000000"}),
                // Test 3: All negative numbers
                new TestCase(java.util.Arrays.asList(-1, -2, -3, -4, -5), new String[]{"0.000000", "1.000000", "0.000000"}),
                // Test 4: All zeros
                new TestCase(java.util.Arrays.asList(0, 0, 0), new String[]{"0.000000", "0.000000", "1.000000"}),
                // Test 5: Mixed distribution
                new TestCase(java.util.Arrays.asList(1, 0, -1), new String[]{"0.333333", "0.333333", "0.333333"})
        );

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);

            // Capture output
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.PrintStream oldOut = System.out;
            System.setOut(new java.io.PrintStream(baos));

            Result1.plusMinus(tc.arr);

            System.setOut(oldOut);
            String[] output = baos.toString().trim().split("\n");

            // Check if output matches expected
            boolean ok = true;
            if (output.length != 3) {
                ok = false;
            } else {
                for (int j = 0; j < 3; j++) {
                    if (!output[j].trim().equals(tc.expected[j])) {
                        ok = false;
                        break;
                    }
                }
            }

            System.out.printf("Test %d: %s => %s%n", i + 1, tc.arr, ok ? "PASS" : "FAIL");
            if (ok) passed++;
        }
        System.out.printf("%d/%d tests passed.%n", passed, tests.size());

        // If all tests passed, print the detailed example (test 1) with inputs and explanation
        if (passed == tests.size()) {
            TestCase example = tests.getFirst();
            System.out.println();
            System.out.println("Detailed example (shown because all tests passed):");
            System.out.println("Input: " + example.arr);

            // Calculate counts
            long positiveCount = example.arr.stream().filter(x -> x > 0).count();
            long negativeCount = example.arr.stream().filter(x -> x < 0).count();
            long zeroCount = example.arr.stream().filter(x -> x == 0).count();
            int n = example.arr.size();

            System.out.printf("Array has %d elements: %d positive, %d negative, %d zero.%n", n, positiveCount, negativeCount, zeroCount);
            System.out.printf("Positive ratio: %d/%d = %.6f%n", positiveCount, n, (double) positiveCount / n);
            System.out.printf("Negative ratio: %d/%d = %.6f%n", negativeCount, n, (double) negativeCount / n);
            System.out.printf("Zero ratio: %d/%d = %.6f%n", zeroCount, n, (double) zeroCount / n);

            System.out.println();
            System.out.println("Output:");
            Result1.plusMinus(example.arr);
        }
    }
}
