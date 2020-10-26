package edu.escuelaing.arep.webserver.app;
import edu.escuelaing.arep.webserver.app.util.PrimeFinder;
import spark.Request;
import spark.Response;

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

    private static List<Long> getPrimes(Request req, Response resp){
        long a = Long.parseLong(req.queryParams("inf"));
        long b = Long.parseLong(req.queryParams("sup"));
        return PrimeFinder.primesInRange(a, b);
    }

}
