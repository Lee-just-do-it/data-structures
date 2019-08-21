package com.java.javasources.structures;

import com.java.javasources.structures.sort.Sort;

/**
 * Random number class, using a 31-bit
 * linear congruential generator.
 * Note that java.util contains a class Random,
 * so watch out for name conflicts.
 */
@SuppressWarnings("all")
public class Random {
    /**
     * The number of witnesses queried in randomized primality test.
     */
    public static final int TRIALS = 5;
    private static final int A = 48271;
    private static final int M = 2147483647;
    private static final int Q = M / A;
    private static final int R = M % A;
    private int state;

    /**
     * Construct this Random object with
     * initial state obtained from system clock.
     */
    public Random() {
        this((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
    }

    /**
     * Construct this Random object with
     * specified initial state.
     *
     * @param initialValue the initial state.
     */
    public Random(int initialValue) {
        if (initialValue < 0) {
            initialValue += M;
        }

        state = initialValue;
        if (state == 0) {
            state = 1;
        }
    }

    /**
     * Randomly rearrange an array.
     * The random numbers used depend on the time and day.
     *
     * @param a the array.
     */
    public static void permute(Object[] a) {
        Random r = new Random();

        for (int j = 1; j < a.length; j++) {
            Sort.swapReferences(a, j, r.randomInt(0, j));
        }
    }

    // Test program
    public static void main(String[] args) {
        Random r = new Random(1);

        for (int i = 0; i < 20; i++) {
            System.out.println(r.randomInt());
        }
    }

    /**
     * Method that implements the basic primality test.
     * If witness does not return 1, n is definitely composite.
     * Do this by computing a^i (mod n) and looking for
     * nontrivial square roots of 1 along the way.
     */
    private static long witness(long a, long i, long n) {
        if (i == 0) {
            return 1;
        }

        long x = witness(a, i / 2, n);
        if (x == 0)    // If n is recursively composite, stop
        {
            return 0;
        }

        // n is not prime if we find a nontrivial square root of 1
        long y = (x * x) % n;
        if (y == 1 && x != 1 && x != n - 1) {
            return 0;
        }

        if (i % 2 != 0) {
            y = (a * y) % n;
        }

        return y;
    }

    /**
     * Randomized primality test.
     * Adjust TRIALS to increase confidence level.
     *
     * @param n the number to test.
     * @return if false, n is definitely not prime.
     * If true, n is probably prime.
     */
    public static boolean isPrime(long n) {
        Random r = new Random();

        for (int counter = 0; counter < TRIALS; counter++) {
            if (witness(r.randomLong(2, n - 2), n - 1, n) != 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * Return a pseudorandom int, and change the
     * internal state.
     *
     * @return the pseudorandom int.
     */
    public int randomInt() {
        int tmpState = A * (state % Q) - R * (state / Q);
        if (tmpState >= 0) {
            state = tmpState;
        } else {
            state = tmpState + M;
        }

        return state;
    }

    /**
     * Return a pseudorandom int, and change the
     * internal state. DOES NOT WORK.
     *
     * @return the pseudorandom int.
     */
    public int randomIntWRONG() {
        return state = (A * state) % M;
    }

    /**
     * Return a pseudorandom double in the open range 0..1
     * and change the internal state.
     *
     * @return the pseudorandom double.
     */
    public double random0_1() {
        return (double) randomInt() / M;
    }

    /**
     * Return an int in the closed range [low,high], and
     * change the internal state.
     *
     * @param low  the minimum value returned.
     * @param high the maximum value returned.
     * @return the pseudorandom int.
     */
    public int randomInt(int low, int high) {
        double partitionSize = (double) M / (high - low + 1);

        return (int) (randomInt() / partitionSize) + low;
    }

    /**
     * Return an long in the closed range [low,high], and
     * change the internal state.
     *
     * @param low  the minimum value returned.
     * @param high the maximum value returned.
     * @return the pseudorandom long.
     */
    public long randomLong(long low, long high) {
        long longVal = ((long) randomInt() << 31) + randomInt();
        long longM = ((long) M << 31) + M;

        double partitionSize = (double) longM / (high - low + 1);
        return (long) (longVal / partitionSize) + low;
    }

}
