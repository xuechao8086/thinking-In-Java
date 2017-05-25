//: containers/StringHashCode.java
package containers; /* Added by Eclipse.py */

public class StringHashCode {
    public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());

        String hello3 = "Hello";
        String hello4 = "Hello";
        System.out.println(hello3.hashCode());
        System.out.println(hello4.hashCode());
        System.out.println(hello3 == hello4);

        boolean flag2 = "Hello" == hello3 ? true : false;
        System.out.println(flag2);
    }
} /* Output: (Sample)
69609650
69609650
*///:~
