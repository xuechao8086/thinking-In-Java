//: concurrency/EvenChecker.java
package concurrency; /* Added by Eclipse.py */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EvenChecker implements Runnable {
    private final int id;
    private IntGenerator generator;

    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    // Test any type of IntGenerator:
    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Control-C to exit");
        //ExecutorService exec = Executors.newCachedThreadPool();
        ExecutorService exec = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(16), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(gp, i));
        }
        exec.shutdown();
    }

    // Default value for count:
    public static void test(IntGenerator gp) {
        test(gp, 10);
    }

    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                generator.cancel(); // Cancels all EvenCheckers
            }
        }
    }
} ///:~
