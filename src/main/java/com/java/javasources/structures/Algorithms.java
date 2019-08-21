package com.java.javasources.structures;

@SuppressWarnings("all")
public class Algorithms {
    public static final long INFINITY = Long.MAX_VALUE;
    public static final int NOT_A_VERTEX = -1;

    /**
     * Compute Fibonacci numbers as described in Chapter 1.
     */
    public static int fib(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * Compute Fibonacci numbers as described in Chapter 1.
     */
    public static int fibonacci(int n) {
        if (n <= 1) {
            return 1;
        }

        int last = 1;
        int nextToLast = 1;
        int answer = 1;
        for (int i = 2; i <= n; i++) {
            answer = last + nextToLast;
            nextToLast = last;
            last = answer;
        }
        return answer;
    }

    public static double eval(int n) {
        if (n == 0) {
            return 1.0;
        } else {
            double sum = 0.0;
            for (int i = 0; i < n; i++) {
                sum += eval(i);
            }
            return 2.0 * sum / n + n;
        }
    }

    public static double eval2(int n) {
        double[] c = new double[n + 1];

        c[0] = 1.0;
        for (int i = 1; i <= n; i++) {
            double sum = 0.0;
            for (int j = 0; j < i; j++) {
                sum += c[j];
            }
            c[i] = 2.0 * sum / i + i;
        }

        return c[n];
    }

    /**
     * Compute optimal ordering of matrix multiplication.
     * c contains the number of columns for each of the n matrices.
     * c[ 0 ] is the number of rows in matrix 1.
     * The minimum number of multiplications is left in m[ 1 ][ n ].
     * Actual ordering is computed via another procedure using lastChange.
     * m and lastChange are indexed starting at 1, instead of 0.
     * Note: Entries below main diagonals of m and lastChange
     * are meaningless and uninitialized.
     */
    public static void optMatrix(int[] c, long[][] m, int[][] lastChange) {
        int n = c.length - 1;

        for (int left = 1; left <= n; left++) {
            m[left][left] = 0;
        }
        for (int k = 1; k < n; k++) {
            for (int left = 1; left <= n - k; left++) {
                // For each position
                int right = left + k;
                m[left][right] = INFINITY;
                for (int i = left; i < right; i++) {
                    long thisCost = m[left][i] + m[i + 1][right]
                            + c[left - 1] * c[i] * c[right];
                    if (thisCost < m[left][right])  // Update min
                    {
                        m[left][right] = thisCost;
                        lastChange[left][right] = i;
                    }
                }
            }
        }
    }

    /**
     * Compute all-shortest paths.
     * a[ ][ ] contains the adjacency matrix with
     * a[ i ][ i ] presumed to be zero.
     * d[ ] contains the values of the shortest path.
     * Vertices are numbered starting at 0; all arrays
     * have equal dimension. A negative cycle exists if
     * d[ i ][ i ] is set to a negative value.
     * Actual path can be computed using path[ ][ ].
     * NOT_A_VERTEX is -1
     */
    public static void allPairs(int[][] a, int[][] d, int[][] path) {
        int n = a.length;

        // Initialize d and path
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = a[i][j];
                path[i][j] = NOT_A_VERTEX;
            }
        }

        for (int k = 0; k < n; k++)
        // Consider each vertex as an intermediate
        {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][k] + d[k][j] < d[i][j]) {
                        // Update shortest path
                        d[i][j] = d[i][k] + d[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }
}
