package edu.escuelaing.arep.app.util;

import edu.escuelaing.arep.app.threads.PrimeThread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alejandro Vasquez
 */
public class PrimeFinder {

    /**
     * Retorna si un número es primo o no
     * @param number Número a consultar
     * @return Si el número es primo
     */
    public static boolean isPrime(BigInteger number){
        boolean done = false;
        BigInteger two = new BigInteger("2");
        BigInteger i = new BigInteger("2");
        if(number.compareTo(BigInteger.ONE) < 0 ){
            return false;
        }
        while(i.compareTo(number.divide(two))<=0 && !done){
            if(number.mod(i).equals(BigInteger.ZERO)){
                done = true;
                return false;
            }

            i = i.add(BigInteger.ONE);
        }
        return true;
    }

    /**
     * Distribuye la búsqueda de números primos entre un número dado de threads
     * @param results Lista donde se va a almacenar el resultado
     * @param _a Número inferior de búsqueda
     * @param _b Número superior de búsqueda
     * @param threads Número de hilos
     * @throws InterruptedException Errores de timing entre hilos
     */
    private static void seekWithThreads(List<BigInteger> results, BigInteger _a, BigInteger _b, int threads) throws InterruptedException {
        List<Thread> mimics = new ArrayList<Thread>();
        BigInteger t = new BigInteger(String.valueOf(threads));
        BigInteger n = _b.add(_a.multiply(new BigInteger(String.valueOf(-1))).add(BigInteger.ONE));
        if(threads <= 1){
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

    /**
     * Busca números primos en un rango
     * @param inf Limite inferior
     * @param sup Limite superior
     * @param threads Número de hilos
     * @return Lista de números primos
     */
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
