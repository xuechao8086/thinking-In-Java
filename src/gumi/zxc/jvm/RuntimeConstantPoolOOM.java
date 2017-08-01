package gumi.zxc.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gumi.zxc
 * @since 2017/6/12 20:02
 */
public class RuntimeConstantPoolOOM {


    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();

        assert str1.intern() == str1;

        String str2 = new StringBuilder("ja").append("va").toString();
        assert str2.intern() == str2;


        List<String> list = new ArrayList<>();

        int i = 0;
        while(true) {
            list.add(String.valueOf(i).intern());
        }
    }
}
