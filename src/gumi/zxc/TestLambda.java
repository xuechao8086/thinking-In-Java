package gumi.zxc;

import containers.Test;

/**
 * @author gumi.zxc
 * @since 2017/5/18 19:22
 */
public class TestLambda {
    public static void runThreadUseLambda() {
        Thread thread = new Thread(() -> {System.out.println("new thread starting by lambda");});
        thread.start();
    }

    public static void runThreadUseInnerClass() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new thread starting by inner class");
            }
        });
        thread.run();
    }


    public static void main(String[] args) {
        TestLambda.runThreadUseInnerClass();
        TestLambda.runThreadUseInnerClass();
        TestLambda.runThreadUseLambda();
        TestLambda.runThreadUseLambda();

    }
}
