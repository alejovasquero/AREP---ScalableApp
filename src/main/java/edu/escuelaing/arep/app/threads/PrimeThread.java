package edu.escuelaing.arep.app.threads;

import edu.escuelaing.arep.app.util.PrimeFinder;

import java.math.BigInteger;
import java.util.List;

public class PrimeThread extends Thread {

    private BigInteger inf;
    private BigInteger sup;
    private List<BigInteger> result;

    /**
     * Constructor de hilos de búsqueda de números primos
     * @param a Limite inferior de búsqueda
     * @param b Limite superior de búsqueda
     * @param set Estructura donde se van a guardar las respuestas encontradas
     */
    public PrimeThread(BigInteger a, BigInteger b, List<BigInteger> set){
        this.inf = a;
        this.sup = b;
        this.result = set;
        System.out.println(inf + " - "+sup);
    }

    /**
     * Búsqueda constante de números primos en un rango dado por el constructor
     */
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
