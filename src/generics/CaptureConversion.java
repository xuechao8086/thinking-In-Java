//: generics/CaptureConversion.java
package generics; /* Added by Eclipse.py */

public class CaptureConversion {
    private static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }

    private static void f2(Holder<?> holder) {
        f1(holder); // Call with captured type
    }

    //@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder<Integer> raw = new Holder<>(1);
        f1(raw);
        f2(raw);
        Holder<Object> rawBasic = new Holder<>();
        rawBasic.set(new Object());
        f2(rawBasic);
        Holder<?> wildcarded = new Holder<>(1.0);
        f2(wildcarded);
    }
} /* Output:
Integer
Object
Double
*///:~
