package gumi.zxc.generics;

import java.util.ArrayList;

/**
 * @author gumi.zxc
 * @since 2017/5/31 11:24
 */
public class CommonTest {


    public void testGenerics() {
        Number number = new Integer(1);
        ArrayList<Number> list = new ArrayList<Number>();
        list.add(1);
        list.add(2.2f);
    }

    public static void main(String[] args) {
        CommonTest commonTest = new CommonTest();
        commonTest.testGenerics();
    }



}
