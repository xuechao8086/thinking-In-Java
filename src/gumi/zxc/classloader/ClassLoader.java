package gumi.zxc.classloader;

import java.net.URL;

/**
 * @author gumi.zxc
 * @since 2017/5/23 19:11
 */
public class ClassLoader {

    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
}
