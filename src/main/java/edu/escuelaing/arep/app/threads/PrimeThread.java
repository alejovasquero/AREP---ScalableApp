package edu.escuelaing.arep.app.threads;

import edu.escuelaing.arep.app.util.PrimeFinder;

import java.math.BigInteger;
import java.util.List;

public class PrimeThread extends Thread {

    private BigInteger inf;
    private BigInteger sup;
    private List<BigInteger> result;

    public PrimeThread(BigInteger a, BigInteger b, List<BigInteger> set){
        this.inf = a;
        this.sup = b;
        this.result = set;
        System.out.println(inf + " - "+sup);
    }

    @Override
    public void run() {
        BigInteger a=inf;
        BigInteger b=sup;
        BigInteger i=a;
        while (i.compareTo(b)<=0){
            if (PrimeFinder.isPrime(i)){
                result.add(i);
            }
            i=i.add(BigInteger.ONE);
        }
    }
}
