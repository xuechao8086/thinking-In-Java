package gumi.zxc.lambda;

import java.lang.reflect.Array;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;

/**
 * jdk8 lambda recursive exercise
 * @see "http://colobu.com/2014/09/05/java-lambdas-hacking/"
 */
public class Recursion {
    private static <E> E[] newArray(Class<E> clazz, int size) {
        return (E[]) Array.newInstance(clazz, size);
    }


    public static void main(String[] args) {
        IntToDoubleFunction[] foo = {null};
        foo[0] = x -> ( x == 0)?1:x* foo[0].applyAsDouble(x-1);

        double result = foo[0].applyAsDouble(3);
        assert result == 6.0;

        Function<Long, Long>[] funs = newArray(Function.class, 1);
        funs[0] = x -> {
            if (x == 1 || x == 2) { return 1L; } else { return funs[0].apply(x - 1) + x; }
        };
        System.out.println(funs[0].apply(10L));

        BiFunction<BiFunction, Long, Long> factHelper = (f, x) -> {
            if (x == 1 || x == 2) { return 1L; } else { return x + (long)f.apply(f, x - 1); }
        };
        Function<Long, Long> fib = x -> factHelper.apply(factHelper, x);

        System.out.println(fib.apply(3L));

    }
}
