//: typeinfo/SweetShop.java
package typeinfo; /* Added by Eclipse.py */
// Examination of the way the class loader works.

import java.lang.reflect.Method;

import static net.mindview.util.Print.print;

class Candy {
    static {
        print("Loading Candy");
    }

    public void output() {
        System.out.println("Candy.out called");
    }
    public void output2() {
        System.out.println("Candy.out2 called");
    }

    private static final String name = "gumi.zxc";
    public String name() {
        return name;
    }

    public Candy() {
        System.out.println("Candy constructor");
    }
}

class Gum {
    static {
        print("Loading Gum");
    }
}

class Cookie {
    static {
        print("Loading Cookie");
    }
}

public class SweetShop {
    public static void main(String[] args) {
        Class<Boolean> c = Boolean.TYPE;
        String[] names = new String[] {"xuechaozhoa", "gumi.zcx"};

        try {
            Class cls = Class.forName("typeinfo.Candy");
            Object object = cls.newInstance();
            if(object instanceof Candy) {
//                ((Candy) object).output();
//                ((Candy) object).output2();
//                String name = ((Candy) object).name();
//                System.out.println(name);

                Method method = cls.getMethod("output2", null);
                method.invoke(object, null);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        print("After Class.forName(\"Gum\")");
//        new Cookie();
//        print("After creating Cookie");
    }
} /* Output:
inside main
Loading Candy
After creating Candy
Loading Gum
After Class.forName("Gum")
Loading Cookie
After creating Cookie
*///:~
