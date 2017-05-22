package gumi.zxc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

        /**
         * 目标类型这个概念不仅仅适用于lambda表达式，泛型方法调用和“菱形”构造方法调用也可以从目标类型中受益，下面的代码在Java SE 7是非法的，但在Java SE 8中是合法的：
         */
        List<String> list = Collections.checkedList(new ArrayList<>(), String.class);
        list.add("xuechao");
        list.add("zhao");

        Set<Integer> set = Collections.singleton(23);
        assert set != null;
    }
}
