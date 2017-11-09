package gumi.zxc.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import org.omg.CORBA.INTERNAL;

/**
 * @see <a href="http://www.cnblogs.com/figure9/archive/2014/10/24/4048421.html">深入理解Java 8 Lambda（语言篇——lambda，方法引用，目标类型和默认方法）</a>
 *
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


    private Runnable runnableOne = () -> System.out.println(this);
    private Runnable runnableTwo = () -> System.out.println(toString());

    @Override
    public String toString() {
        return "Hello, World!";
    }


    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x+y;
        BiFunction<Integer, Integer, Integer> biFunction1 = biFunction.andThen(z -> z*z);
        Integer z1 = biFunction1.apply(1, 2);
        System.out.println("Integer z1 = " + z1);

        Comparator<Integer> comparator = (a, b) -> a - b;
        Integer z2 = BinaryOperator.minBy(comparator).apply(5, 4);
        System.out.println("Interger z2 = " + z2);

        BinaryOperator<Integer> binaryOperator = (x, y) -> x+y;
        BiFunction<Integer, Integer, Integer> biFunction2 = binaryOperator.andThen(z -> z*2);


        //System.exit(0);

        TestLambda.runThreadUseInnerClass();
        TestLambda.runThreadUseInnerClass();
        TestLambda.runThreadUseLambda();
        TestLambda.runThreadUseLambda();

        /**
         * 相对于内部类，lambda表达式的语义就十分简单：它不会从超类（supertype）中继承任何变量名，也不会引入一个新的作用域。
         * lambda表达式基于词法作用域，也就是说lambda表达式函数体里面的变量和它外部环境的变量具有相同的语义（也包括lambda表达式的形式参数）。
         * 此外，'this'关键字及其引用在lambda表达式内部和外部也拥有相同的语义。
         */
        new TestLambda().runnableOne.run();
        new TestLambda().runnableTwo.run();


        /**
         * 目标类型这个概念不仅仅适用于lambda表达式，泛型方法调用和“菱形”构造方法调用也可以从目标类型中受益，下面的代码在Java SE 7是非法的，但在Java SE 8中是合法的：
         */
        List<String> list = Collections.checkedList(new ArrayList<>(), String.class);
        list.add("xuechao");
        list.add("zhao");

        Set<Integer> set = Collections.singleton(23);
        assert set != null;

        /**
         *
         */
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);

        final int[] sum = {0};
        integerList.forEach( integer -> sum[0] += integer);

        assert sum[0] == 45;

        /**
         * 这里的隐式lambda表达式（即String::toUpperCase实例方法引用）有一个String参数，这个参数会被toUpperCase方法使用。
         */
        Function<String, String> upperfier = String::toUpperCase;

    }
}
