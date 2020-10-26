package edu.escuelaing.arep.webserver.app.util;

import java.util.ArrayList;
import java.util.List;

public class PrimeFinder {

    public static boolean isPrime(long number){
        boolean ans = true;
        boolean done = false;
        for(long i=2; i<=number/2 && !done; i++){
            if(number%i == 0){
                done = true;
                ans = false;
            }
        }
        return ans;
    }


    public static List<Long> primesInRange(long inf, long sup){
        ArrayList<Long>  ans  = new ArrayList<Long>();
        for (long i=inf; i<= sup; i++){
            if(isPrime(i)){
                ans.add(i);
            }
        }
        return ans;
    }

}
