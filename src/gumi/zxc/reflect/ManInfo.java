package gumi.zxc.reflect;

/**
 * @author xuechao
 * @since 2017/11/13 16:43
 */
public class ManInfo extends PersonInfo{
    private int height;
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static void main(String[] args) {
        PersonInfo personInfo = new ManInfo();
        System.out.println(personInfo.getClass().getName());
        System.out.println(PersonInfo.class.getName());
    }
}
