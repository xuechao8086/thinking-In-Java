package gumi.zxc.cocurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gumi.zxc
 * @since 2017/6/6 20:57
 */
public class SerialNumberChecker {
    private static Set<Integer> serialNumberSet = new HashSet<Integer>();
    public static int base = 0;
    public static int THREAD_SIZE = 10;
    public static int nextNumber(){
        return ++ base;
    }
    static class SerialChecker implements Runnable{
        private SerialNumberChecker snc;
        public SerialChecker(SerialNumberChecker snc) {
            this.snc = snc;
        }
        @Override
        public void run() {
            while(true){
                //int number = snc.nextNumber();
                int number = snc.base ++;
                if(serialNumberSet.contains(number)){
                    System.out.println("存在重复值:" + number);
                    System.exit(0);
                }else{
                    serialNumberSet.add(number);
                }
            }
        }

    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        SerialNumberChecker snc = new SerialNumberChecker();
        for(int i = 0; i < THREAD_SIZE; i++){
            exec.execute(new SerialChecker(snc));
        }
    }
}
