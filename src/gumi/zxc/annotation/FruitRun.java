package gumi.zxc.annotation;

import javax.annotation.Resource;

/**
 * @author gumi.zxc
 * @since 2017/8/30 11:15
 */
public class FruitRun {

    @Resource
    private Apple apple;
    /**
     * @param args
     */
    public static void main(String[] args) {

        FruitInfoUtil.getFruitInfo(Apple.class);
        FruitRun fruitRun = new FruitRun();
        System.out.println(fruitRun.apple);
    }
}
