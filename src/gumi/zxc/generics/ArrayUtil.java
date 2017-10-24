package gumi.zxc.generics;

/**
 * @author xuechao
 * @since 2017/10/24 15:35
 */
public class ArrayUtil {
    public static <T extends Comparable<T>> T max(T[] array) {
        if (array == null || 0 == array.length) { return null ;}
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max.compareTo(array[i]) < 0) {max = array[i];}
        }
        return max;
    }
}
