//: annotations/Testable.java
package annotations;

import net.mindview.atunit.Test;

public class Testable {
    public void execute() {
        System.out.println("Executing..");
    }

    @Test
    void testExecute() {
        execute();
    }

    public static void main(String[] args) {
        Testable testable = new Testable();
        testable.testExecute();
    }

} ///:~
