package gumi.zxc.lambda;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author xuechao
 * @since 2017/11/10 09:56
 */
public class MapUsingReduce {
    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(), (acc, x) -> {
            // We are copying data from acc to new list instance. It is very inefficient,
            // but contract of Stream.reduce method requires that accumulator function does
            // not mutate its arguments.
            // Stream.collect method could be used to implement more efficient mutable reduction,
            // but this exercise asks to use reduce method.
            List<O> newAcc = new ArrayList<>(acc);
            newAcc.add(mapper.apply(x));
            return newAcc;
        }, (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }

    public static void readFile(String fileName) {
        long uniqueWords = 0;
        try (Stream<String> lines =
                 Files.lines(Paths.get(fileName), Charset.defaultCharset())) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        readFile("/Users/xuechao/odps/ao_application.txt");

        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5, 6);
        Function<Integer, Integer> f = i -> i*2;
        Function<Integer, Integer> f2 = i -> i*i;
        List<Integer> l = map(s, f2);
        l.forEach(System.out::println);
    }
}
