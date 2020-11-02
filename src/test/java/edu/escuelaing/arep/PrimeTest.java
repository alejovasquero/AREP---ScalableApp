package edu.escuelaing.arep;

import edu.escuelaing.arep.app.util.PrimeFinder;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class PrimeTest {

    /**
     * Checks for numbers that should not be primes
     */
    @Test
    public void shouldNotBePrime(){
        boolean ans = PrimeFinder.isPrime(new BigInteger("-1"));
        assertFalse(ans);

        ans = PrimeFinder.isPrime(new BigInteger("4"));
        assertFalse(ans);

        ans = PrimeFinder.isPrime(new BigInteger("6"));
        assertFalse(ans);

        ans = PrimeFinder.isPrime(new BigInteger("8"));
        assertFalse(ans);
    }

    /**
     * Check for a fixed set of numbers that should be primes
     */
    @Test
    public void shouldBePrime(){
        boolean ans = PrimeFinder.isPrime(new BigInteger("1"));
        assertTrue(ans);

        ans = PrimeFinder.isPrime(new BigInteger("2"));
        assertTrue(ans);;

        ans = PrimeFinder.isPrime(new BigInteger("3"));
        assertTrue(ans);

        ans = PrimeFinder.isPrime(new BigInteger("5"));
        assertTrue(ans);

        ans = PrimeFinder.isPrime(new BigInteger("7"));
        assertTrue(ans);

        ans = PrimeFinder.isPrime(new BigInteger("11"));
        assertTrue(ans);
    }

    /**
     * Checks for a empty list of primes in a range of compound numbers
     */
    @Test
    public void shouldNotFindPrimes(){
        List<BigInteger>  ans = PrimeFinder.primesInRange(new BigInteger("-5"), new BigInteger("-1"), 3);
        assertEquals(ans, new ArrayList<>());

        ans = PrimeFinder.primesInRange(new BigInteger("-1000"), new BigInteger("-10000000"), 3);
        assertEquals(ans, new ArrayList<>());

        ans = PrimeFinder.primesInRange(new BigInteger("20"), new BigInteger("22"), 3);
        assertEquals(ans, new ArrayList<>());
    }

    /**
     * Checks for a non empty list of primes in a range of a non compound numbers
     */
    @Test
    public void shouldFindPrimes(){
        List<BigInteger>  ans = PrimeFinder.primesInRange(new BigInteger("1"), new BigInteger("5"), 3);
        ans.sort(new Comparator<BigInteger>() {
            @Override
            public int compare(BigInteger o1, BigInteger o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(ans);
        assertEquals(ans, new ArrayList<>(Arrays.asList(new BigInteger("1"),
        new BigInteger("2"), new BigInteger("3"), new BigInteger("5"))));


        ans = PrimeFinder.primesInRange(new BigInteger("23"), new BigInteger("30"), 3);
        ans.sort(new Comparator<BigInteger>() {
            @Override
            public int compare(BigInteger o1, BigInteger o2) {
                return o1.compareTo(o2);
            }
        });
        assertEquals(ans, new ArrayList<>(Arrays.asList(new BigInteger("23"),
                 new BigInteger("29"))));

    }
}
