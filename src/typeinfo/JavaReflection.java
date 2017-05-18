package typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author gumi.zxc
 * @date 2017/01/20
 * @since 2017/1/20 14:53
 */

class MyClass {

    public static int count;

    public MyClass(int start) {
        count = start;
    }

    public static void setCount(int count) {
        MyClass.count = count;
    }

    public static int getCount() {
        return count;
    }

    public void increase(int step) {
        count += step;
    }
}
/**
 * @author gumi.zxc
 * @date 2017/01/20
 * @since 2017/1/20 14:53
 */
public class JavaReflection {
    public static void main(String[] args) {
        MyClass myClass = new MyClass(0); //一般做法
        myClass.increase(2);
        System.out.println("Normal -> " + myClass.count);
        try {
            Constructor constructor = MyClass.class.getConstructor(int.class); //获取构造方法
            MyClass myClassReflect = (MyClass)constructor.newInstance(10); //创建对象
            Method method = MyClass.class.getMethod("increase", int.class);  //获取方法
            method.invoke(myClassReflect, 5); //调用方法
            Field field = MyClass.class.getField("count"); //获取域
            System.out.println("Reflect -> " + field.getInt(myClassReflect)); //获取域的值
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=================================================================================");
        try {
            Class<MyClass> myClassClass = MyClass.class;
            MyClass myClassReflect = myClassClass.getConstructor(int.class).newInstance(10);
            Method method = myClassClass.getMethod("setCount", int.class);
            method.invoke(myClassReflect, 20);
            Field field = myClassClass.getField("count");
            System.out.println("result = " + field.getInt(myClassReflect));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
