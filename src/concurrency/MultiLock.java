//: concurrency/MultiLock.java
package concurrency; /* Added by Eclipse.py */
// One thread can reacquire the same lock.

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

public class MultiLock {
    public static void main(String[] args) throws Exception {
        final MultiLock multiLock = new MultiLock();
        new Thread() {
            public void run() {
                multiLock.f1(10);
            }
        }.start();
        new Thread() {
            public void run() {
                multiLock.f1(20);
            }
        }.start();
    }

    public synchronized void f1(int count) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (count-- > 0) {
            print("f1() calling f2() with count " + count);
            Thread.yield();
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            print("f2() calling f1() with count " + count);
            Thread.yield();
            f1(count);
        }
    }
} /* Output:
f1() calling f2() with count 9
f2() calling f1() with count 8
f1() calling f2() with count 7
f2() calling f1() with count 6
f1() calling f2() with count 5
f2() calling f1() with count 4
f1() calling f2() with count 3
f2() calling f1() with count 2
f1() calling f2() with count 1
f2() calling f1() with count 0
*///:~
