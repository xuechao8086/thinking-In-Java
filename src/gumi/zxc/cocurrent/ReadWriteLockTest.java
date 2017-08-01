package gumi.zxc.cocurrent;


import java.util.Random;
import java.util.concurrent.locks.*;
/**
 * @author gumi.zxc
 * @since 2017/8/1 19:58
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        final TheData myData=new TheData(); // 这是各线程的共享数据
        for(int i=0;i<3;i++){ // 开启3 个写线程
            Runnable runnable = () -> {while (true) { myData.put(new Random().nextInt(100000));} };
            runnable.run();
        }

        for(int i=0;i<3;i++){ // 开启3 个读线程
            Runnable runnable = () -> {while (true) { myData.get(); }};
            runnable.run();
        }

    }
}
class TheData{
    private volatile Object data=null;
    private ReadWriteLock rwl=new ReentrantReadWriteLock();
    public void get(){
        rwl.readLock().lock(); // 读锁开启，读线程均可进入
        try { // 用try finally 来防止因异常而造成的死锁
            System.out.println(Thread.currentThread().getName()+" is ready to read");
                Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName()+" have read date "+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            rwl.readLock().unlock(); // 读锁解锁
        }
    }
    public void put(Object data){
        rwl.writeLock().lock(); // 写锁开启，这时只有一个写线程进入
        try {
            System.out.println(Thread.currentThread().getName()+" is ready to write");
                Thread.sleep(new Random().nextInt(100));
            this.data=data;
            System.out.println(Thread.currentThread().getName()+" have write date"+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            rwl.writeLock().unlock(); // 写锁解锁
        }
    }
}

