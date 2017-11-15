package gumi.zxc.cocurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


/**
 * @author xuechao
 * @since 2017/10/31 17:07
 */
public class BasicMain2 {
    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("threadName:" + Thread.currentThread().getName() + ",threadId:" + Thread.currentThread().getId());
        System.out.println("begin to start compute");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }
    public static void main(String[] args) throws Exception {
        System.out.println("threadName:" + Thread.currentThread().getName() + ",threadId:" + Thread.currentThread().getId());
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(BasicMain2::getMoreData);
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println("whenComplete threadName:" + Thread.currentThread().getName() + ",threadId:" + Thread.currentThread().getId());
            System.out.println(v);
            System.out.println(e);
        });
        System.out.println(f.get());
        //System.in.read();
    }
}
