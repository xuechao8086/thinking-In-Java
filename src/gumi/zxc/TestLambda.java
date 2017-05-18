package gumi.zxc;

/**
 * @author gumi.zxc
 * @since 2017/5/18 19:22
 */
public class TestLambda {
    public static void runThreadUseLambda() {
        Thread thread = new Thread(() -> {System.out.println("new thread starting");});

    }


    public static void main(String[] args) {

    }
}
