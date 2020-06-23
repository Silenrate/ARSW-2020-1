package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    private static boolean pause;

    public static void main(String[] args) {
        int maxPrim = 100;
        int numberOfThreads = 4;
        pause = false;
        PrimesResultSet prs = new PrimesResultSet("john");
        PrimeFinder[] threads = new PrimeFinder[numberOfThreads];
        AtomicInteger num = new AtomicInteger(numberOfThreads);
        int amount = maxPrim / numberOfThreads;
        int start = 0;
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new PrimeFinder(new BigInteger(Integer.toString(start)), new BigInteger(Integer.toString(start + amount)), prs, num);
            threads[i].start();
            start += amount;
        }
        while (num.get() > 0) {
            //System.out.println(num.get());
            //System.out.println(MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement());
            if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() < 10000) {
                //System.out.println("no pausa");
                pause = false;
            } else {
                //System.out.println("pausa");
                pause = true;
            }
        }
        System.out.println("Prime numbers found:");
        System.out.println(prs.getPrimes());
    }

    public static boolean isPause() {
        return pause;
    }
}


