//: innerclasses/Parcel10.java
package innerclasses; /* Added by Eclipse.py */
// Using "instance initialization" to perform
// construction on an anonymous inner class.

import java.util.Date;
import java.util.function.Consumer;

public class Parcel10 {
    public static void main(String[] args) {
        Parcel10 parcel10 = new Parcel10();
        Destination d1 = parcel10.destination1("Tasmania", 101.395F);

        // 以下代码由@charliezhao 添加测试
        Consumer<Destination> outBudegFunc = p -> {
            System.out.println(p.readLabel() + " out budget, " + p);
        };
        Consumer<Parcel10> underBudgeFunc = p -> {
            System.out.println(p.date + " ok, under budget, " + p);
        };

        Destination d2 = parcel10.destination2("Tailand", 99.99f, outBudegFunc, underBudgeFunc);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Parcel10() {
        date = new Date();
    }



    public Destination
    destination1(final String dest, final float price) {
        return new Destination() {
            private int cost;
            private String label = dest;

            // Instance initialization for each object:
            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            @Override
            public String readLabel() {
                return label;
            }
        };
    }


    public Destination destination2(String dest, float price, Consumer<Destination> outBudgeFunc, Consumer<Parcel10> underBudgeFunc) {
        return new Destination() {
            private String label = dest;
            private int cost;
            {
                cost = (int)price;
                if(cost > 100) {
                    outBudgeFunc.accept(this);
                    //outBudgeFunc.accept(Parcel10.this);
                } else {
                    underBudgeFunc.accept(Parcel10.this);
                }

            }
            @Override
            public String readLabel() {
                return dest;
            }
        };
    }
} /* Output:
Over budget!
*///:~
