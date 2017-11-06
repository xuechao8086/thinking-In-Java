package gumi.zxc.object;

/**
 * <p>
 * ClassName: StaticClass
 * </p>
 * <p>
 * Description: TODO
 * </p>
 * <p>
 * Author: mengfanyuan
 * Email:kalvenmeng@163.com
 * </p>
 * <p>
 * Date: 2014年9月9日
 * </p>
 */
public class StaticClass {

    /**
     * <p>
     * Field str1: 非静态成员变量
     * </p>
     */
    private String str1 = "";
    /**
     * <p>
     * Field str2: 静态成员变量
     * </p>
     */
    private static String str2 = "";

    /**
     * <p>
     * Field ic03: 成员变量实例化静态内部类
     * </p>
     */
    private static InnerClass1 ic03 = new InnerClass1();

    /**
     * <p>
     * Field ic04: 成员变量实例化非静态内部类
     * </p>
     */
    //	private static InnerClass2 ic04 = new InnerClass2(); //编译不通过

    /**
     * <p>
     * Description: 静态成员方法
     * </p>
     */
    public static void t1() {
        InnerClass1 ic1 = new InnerClass1();
        //InnerClass2 ic2 = new InnerClass2();	//编译不通过
    }

    /**
     * <p>
     * Description: 非静态成员方法
     * </p>
     */
    public void t2() {
        InnerClass1 ic1 = new InnerClass1();
        InnerClass2 ic2 = new InnerClass2();
    }

    /**
     * <p>
     * ClassName: InnerClass1
     * </p>
     * <p>
     * Description: 静态内部类
     * </p>
     * <p>
     * Author: kalvenmeng
     * </p>
     * <p>
     * Date: 2014年9月9日
     * </p>
     */
    public static class InnerClass1 {

        /**
         * <p>
         * Field s1: 引用宿主非静态成员变量
         * </p>
         */
        //		String s1 = str1; 	//编译不通过
        /**
         * <p>
         * Field s2: 引用宿主静态成员变量
         * </p>
         */
        String s2 = str2;

        public static String dafaultName = "kalvel";
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public void sayHello1() {
            System.out.println(name + "says \"hello\" to you !!!!");
        }

        public static void defaultSayHello() {
            System.out.println(dafaultName + "says \"hello\" to you !!!!");
        }

    }

    /**
     * <p>
     * ClassName: InnerClass2
     * </p>
     * <p>
     * Description: 非静态内部类
     * </p>
     * <p>
     * Author: kalvenmeng
     * </p>
     * <p>
     * Date: 2014年9月9日
     * </p>
     */
    public class InnerClass2 {
        //		public static String dafaultName = "kalvel";	//编译不通过
        public final static String dafaultName = "kalvel";
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public void sayHello1() {
            System.out.println(name + "says \"hello\" to you !!!!");
        }
        //编译不通过
        /*public static void defaultSayHello() {
			System.out.println(dafaultName + "says \"hello\" to you !!!!");
		}*/
    }

    public static void main(String[] args) {
        InnerClass1 ic1 = new StaticClass.InnerClass1();
        //		InnerClass2 ic2 = new InnerClass2();	//编译不通过；
        StaticClass sc = new StaticClass();
        InnerClass2 ic2 = sc.new InnerClass2();

    }

}