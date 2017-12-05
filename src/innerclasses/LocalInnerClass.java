//: innerclasses/LocalInnerClass.java
package innerclasses; /* Added by Eclipse.py */
// Holds a sequence of Objects.

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

interface Counter {
    int next();
}

public class LocalInnerClass {
    private int count = 0;

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter
            c1 = lic.getCounter("Local inner "),
            c2 = lic.getCounter2("Anonymous inner ");
        for (int i = 0; i < 5; i++) { print(c1.next()); }
        for (int i = 0; i < 5; i++) { print(c2.next()); }
        lic.func3();
    }

    private Counter getCounter(final String name) {
        /**
         * A local inner class:
         */
        class LocalCounter implements Counter {
            public LocalCounter() {
                // Local inner class can have a constructor
                print("LocalCounter()");
            }

            @Override
            public int next() {
                // Access local final
                printnb(name);
                return count++;
            }
        }
        return new LocalCounter();
    }

    /**
     * The same thing with an anonymous inner class:
     */
    private Counter getCounter2(final String name) {
        return new Counter() {
            // Anonymous inner class cannot have a named
            // constructor, only an instance initializer:
            {
                print("Counter()");
            }

            @Override
            public int next() {
                // Access local final
                printnb(name);
                return count++;
            }
        };
    }


    private void func3() {
        class FuncClass3 {
            private String name;

            private String score;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }
        }
        
        FuncClass3 func3 = new FuncClass3();
        func3.setName("xuechao");
        func3.setScore("100");
        System.out.println(func3);
    }




} /* Output:
LocalCounter()
Counter()
Local inner 0
Local inner 1
Local inner 2
Local inner 3
Local inner 4
Anonymous inner 5
Anonymous inner 6
Anonymous inner 7
Anonymous inner 8
Anonymous inner 9
*///:~
