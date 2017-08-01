package gumi.zxc.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gumi.zxc
 * @since 2017/6/12 10:16
 */
public class ClassTest {

    public static void main(String[] args) {
        List<ClassTest> list = new ArrayList<>();
        int count = 0;

        while (true) {
            try {
                list.add(new ClassTest());
                ++count;
            } catch (Exception e) {
                System.out.println("instance count:" + count);
                break;
            }
        }

   }

}
