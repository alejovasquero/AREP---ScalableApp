package edu.escuelaing.arep.app.util;

import edu.escuelaing.arep.app.threads.PrimeThread;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrimeFinder {

    public static boolean isPrime(BigInteger number){
        boolean ans = true;
        boolean done = false;
        BigInteger two = new BigInteger("2");
        BigInteger i = new BigInteger("2");
        while(i.compareTo(number.divide(two))<=0 && !done){
            if(number.mod(i).equals(BigInteger.ZERO)){
                done = true;
                ans = false;
                System.out.println(number+" NOT PRIME");
            }

            i = i.add(BigInteger.ONE);
        }
        return ans;
    }


    private static void seekWithThreads(List<BigInteger> results, BigInteger _a, BigInteger _b, int threads) throws InterruptedException {
        List<Thread> mimics = new ArrayList<Thread>();
        BigInteger t = new BigInteger(String.valueOf(threads));
        BigInteger n = _b.add(_a.multiply(new BigInteger(String.valueOf(-1))).add(BigInteger.ONE));
        if(threads == 1){
            mimics.add(new PrimeThread(_a, _b, results));
        } else if(threads==2){
            mimics.add(new PrimeThread(_a,_a.add(n.divide(t)), results));
            mimics.add(new PrimeThread(_a.add(n.divide(t)).add(BigInteger.ONE), _b, results));
        } else {
            mimics.add(new PrimeThread(_a,_a.add(n.divide(t)), results));
            mimics.add(new PrimeThread(_a.add(n.divide(t).multiply(t.add(new BigInteger(String.valueOf(-1))))).add(BigInteger.ONE), _b, results));
            PrimeThread newer = null;
            BigInteger iBig = null;
            for (int i = 1; i<threads-1; i++){
                iBig =  new BigInteger(String.valueOf(i));
                newer = new PrimeThread(_a.add(n.divide(t).multiply(iBig)).add(BigInteger.ONE), _a.add(n.divide(t).multiply(iBig.add(BigInteger.ONE))), results);
                mimics.add(newer);
            }
        }
        for(Thread th: mimics){
            th.start();
        }

        for(Thread th: mimics){
            th.join();
        }
    }


    public static List<BigInteger> primesInRange(BigInteger inf, BigInteger sup, int threads){
        ArrayList<BigInteger>  ans  = new ArrayList<>();
        try {
            seekWithThreads(ans, inf, sup, threads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ans;
    }

}
