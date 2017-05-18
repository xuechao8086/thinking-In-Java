package gumi.zxc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

/**
 * @author gumi.zxc
 * @since 2017/5/18 20:16
 */
public class TestMethodReference {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setVisible(true);

        JButton jButton1 = new JButton("点我");
        JButton jButton2 = new JButton("也点我！");
        JButton jButton3 = new JButton("也点我2！");

        jFrame.getContentPane().add(jButton1);
        jFrame.getContentPane().add(jButton2);
        jFrame.getContentPane().add(jButton3);

        jButton1.addActionListener(e -> {System.out.println("这里是lambda实现方式");});

        jButton2.addActionListener(TestMethodReference::doSomething);

        TestMethodReference testMethodReference = new TestMethodReference();
        jButton3.addActionListener(testMethodReference::doSomethingTwo);

        java.util.List<String> list = new ArrayList<>();
        list.add("xuechaozhao");
        list.add("zhao");

        /**
         * @see https://my.oschina.net/benhaile/blog/177148
         */
        list.forEach(p -> System.out.println(p));
    }

    public static void doSomething(ActionEvent actionEvent) {
        System.out.println("这个是方法引用实现");
    }

    public void doSomethingTwo(ActionEvent actionEvent) {
        System.out.println("这个是方法引用实现2");
    }
}
