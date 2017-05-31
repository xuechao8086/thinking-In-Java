package gumi.zxc.initialize;

/**
 * @author gumi.zxc
 * @since 2017/5/31 10:58
 */
class Base
{
    private int i=2;
    public Base()
    {
        System.out.println(this.getClass());
        System.out.println(this.i);
        this.display();
        //this.sub();
    }
    public void display()
    {
        System.out.println("父类的方法");
        System.out.println(i);
    }

}
class Drib extends Base
{
    private int i=22;
    public Drib()
    {
        i=222;
    }
    public void display()
    {
        System.out.println("子类的方法");
        System.out.println(i);
    }
    public void sub()
    {

    }
}
public  class Test6
{
    public static void main(String[] args)
    {
        new Drib();
    }
}
