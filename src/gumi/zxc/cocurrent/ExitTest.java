package gumi.zxc.cocurrent;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author xuechao
 * @since 2017/11/15 14:00
 */
public class ExitTest {
    private static final String zkPath = "/gumi_zxc_test";

    private static String getPrefix() {
        String separator = "###### ";
        String name = Thread.currentThread().getName();

        return MessageFormat.format("{0}|{1,number,#}|{2}|{3} ", separator, System.currentTimeMillis(), name, zkPath);
    }


    public static void main(String[] args) throws Exception{
        System.out.println(getPrefix() + "begin");
        Runnable r = () -> {
            System.out.println(getPrefix() + "begin");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(getPrefix() + "InterruptedException catched");
            }
            System.out.println(getPrefix() +"ends");
        };

        Thread thread = new Thread(r);
        thread.setDaemon(false);
        thread.start();
        //thread.join();
        System.out.println(getPrefix() + "ends");

    }
}
