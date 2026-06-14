package interview.coding;

import java.util.Arrays;
import java.util.List;

/*
https://www.hackerrank.com/challenges/diagonal-difference/problem?isFullScreen=true
Given a square matrix, calculate the absolute difference between the sums of its diagonals.
For example, the square matrix  is shown below:
1 2 3
4 5 6
9 8 9
The left-to-right diagonal = 1 + 5 + 9 = 15.
The right-to-left diagonal = 3 + 5 + 9 = 17 .
Their absolute difference is |15 - 17| = 2.
 */
class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        int n = arr.size();
        if (n == 0) return 0;
        if (n == 3) return Math.abs((arr.get(0).get(0) + arr.get(2).get(2) - (arr.get(0).get(2) + arr.get(2).get(0))));
        int primaryDiagonalSum = 0;
        int secondaryDiagonalSum = 0;
        for (int i = 0; i < n; i++) {
            primaryDiagonalSum += arr.get(i).get(i);
            secondaryDiagonalSum += arr.get(i).get(n - 1 - i);
        }
        return Math.abs(primaryDiagonalSum - secondaryDiagonalSum);
        /*
        stream solution:
        int n = arr.size();

        int diff = java.util.stream.IntStream.range(0, n)
            .map(i -> arr.get(i).get(i) - arr.get(i).get(n - 1 - i))
            .sum();

        return Math.abs(diff);
         */
    }
}

public class DiagonalDifference {
    record TestCase(List<List<Integer>> matrix, int expected) {
    }

    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
                new TestCase(List.of(List.of(5)), 0),
                new TestCase(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4)), 0),
                new TestCase(Arrays.asList(Arrays.asList(-1, -2, -3), Arrays.asList(-4, -5, -6), Arrays.asList(-7, -8, -9)), 0),
                new TestCase(Arrays.asList(Arrays.asList(1, 2, 3, 4), Arrays.asList(5, 6, 7, 8), Arrays.asList(9, 10, 11, 12), Arrays.asList(13, 14, 15, 16)), 0),
                new TestCase(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), Arrays.asList(9, 8, 9)), 2)
        );

        int passed = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            int result = Result.diagonalDifference(tc.matrix);
            boolean ok = result == tc.expected;
            System.out.printf("Test %d: expected=%d got=%d => %s%n", i + 1, tc.expected, result, ok ? "PASS" : "FAIL");
            if (ok) passed++;
        }
        System.out.printf("%d/%d tests passed.%n", passed, tests.size());

        // If all tests passed, print the detailed example (test 5) with inputs and explanation
        if (passed == tests.size()) {
            TestCase example = tests.getLast();
            System.out.println();
            System.out.println("Detailed example (shown because all tests passed):");
            System.out.println("Inputs:");
            for (List<Integer> row : example.matrix) {
                System.out.println(row.stream().map(Object::toString).collect(java.util.stream.Collectors.joining(" ")));
            }
            int n = example.matrix.size();
            java.util.List<Integer> primaryElems = new java.util.ArrayList<>();
            java.util.List<Integer> secondaryElems = new java.util.ArrayList<>();
            for (int r = 0; r < n; r++) {
                primaryElems.add(example.matrix.get(r).get(r));
                secondaryElems.add(example.matrix.get(r).get(n - 1 - r));
            }
            String primaryExpr = primaryElems.stream().map(Object::toString).collect(java.util.stream.Collectors.joining(" + "));
            String secondaryExpr = secondaryElems.stream().map(Object::toString).collect(java.util.stream.Collectors.joining(" + "));
            int primarySum = primaryElems.stream().mapToInt(Integer::intValue).sum();
            int secondarySum = secondaryElems.stream().mapToInt(Integer::intValue).sum();
            int result = Result.diagonalDifference(example.matrix);

            System.out.printf("The left-to-right diagonal = %s = %d.%n", primaryExpr, primarySum);
            System.out.printf("The right-to-left diagonal = %s = %d.%n", secondaryExpr, secondarySum);
            System.out.printf("Their absolute difference is |%d - %d| = %d.%n", primarySum, secondarySum, Math.abs(primarySum - secondarySum));
            System.out.printf("Expected: %d, Result: %d => %s%n", example.expected, result, result == example.expected ? "MATCH" : "MISMATCH");
        }
    }
}
