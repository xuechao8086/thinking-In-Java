//: innerclasses/DotThis.java
package innerclasses; /* Added by Eclipse.py */
// Qualifying access to the outer-class object.

public class DotThis {
    public static void main(String[] args) {
        DotThis dt = new DotThis();

        Inner inner1 = dt.inner();
        inner1.outer().f();

        DotThis dotThis = new DotThis();
        Inner inner2 = dotThis.new Inner();
        inner2.outer();

        // 必须是由实例来引用的
        //Inner inner3 = DotThis.new Inner();
    }

    void f() {
        System.out.println("DotThis.f()");
    }

    public Inner inner() {
        return new Inner();
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;
            // A plain "this" would be Inner's "this"
        }
    }
} /* Output:
DotThis.f()
*///:~
