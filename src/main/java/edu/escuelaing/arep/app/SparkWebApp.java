package edu.escuelaing.arep.app;
import edu.escuelaing.arep.app.util.PrimeFinder;
import spark.Request;
import spark.Response;

import java.math.BigInteger;
import java.util.List;

import static spark.Spark.*;

/**
 * @author Alejandro Vasquez
 */
public class SparkWebApp {



    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/public");
        post("/primes", (req, res) -> getPrimes(req, res).toString());
    }

    /**
     * Cambia el puerto de respuesta, dependiendo del entorno de despliegue
     * @return Puerto a trabajar
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    private static List<BigInteger> getPrimes(Request req, Response resp){
        BigInteger a = new BigInteger(req.queryParams("inf"));
        BigInteger b = new BigInteger(req.queryParams("sup"));
        int threads ;
        if(req.queryParams("threads") == null){
            threads = 4*2;
        } else {
            threads = Integer.parseInt(req.queryParams("threads"));
        }
        return PrimeFinder.primesInRange(a, b, threads);
    }

}
