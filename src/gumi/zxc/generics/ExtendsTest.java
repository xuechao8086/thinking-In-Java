package gumi.zxc.generics;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xuechao
 * @since 2017/10/23 11:48
 */
class Fruit {
    @Override
    public String toString() {
        return "it is Fruit instance";
    }

}

class Apple extends Fruit {
    @Override
    public String toString() {
        return "it is Apple instance";
    }
}

class Orange extends Fruit {
    @Override
    public String toString() {
        return "it is Orange instance";
    }
}


public class ExtendsTest {
    public static void main(String[] args) {

        List<? super Fruit> list = new ArrayList<>(3);
        list.add(new Fruit());
        list.add(new Apple());
        list.add(new Orange());

        list.forEach(System.out::println);
        assert list.size() > 0;
    }

    private  void process(Object object) {
        System.out.println("processObject called");
    }
    private static void process(Fruit fruit) {
        System.out.println("processFruit called");
    }

    private static void process(Apple apple) {
        System.out.println("processApple called");
    }

    private static void process(Orange orange) {
        System.out.println("processOrange called");
    }
}
