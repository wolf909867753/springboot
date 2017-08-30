package com.test.thread;

/**
 * Created by wanglu-jf on 17/8/22.
 */
public class ThreadTest extends Thread{
    private void loop(int i){
        if(i != 100000){
            i++;
            System.out.println("i="+i);
            loop(i);
        }else{
            return;
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"正在执行....");
        loop(0);
    }

//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Thread t1 = new ThreadTest();
//        Thread t2 = new ThreadTest();
//        Thread t3 = new ThreadTest();
//        Thread t4 = new ThreadTest();
//        executorService.execute(t1);
//        executorService.execute(t2);
//        executorService.execute(t3);
//        executorService.execute(t4);
//
//        executorService.shutdown();
//        Map map = new HashMap();

//        ThreadTest t = new ThreadTest();
//        t.start();
//    }

    public static void main(String[] args) {
          Base b=null; //此处如果 换成 Sub b=null，则b.x=40;不再是20
          b=new Sub();
          System.out.println(b.x);
     }

     static class Base {

       int x =10;

       public Base(){
          this.printMessage();
          x=20;
       }

       public void printMessage(){
           System.out.println("Base.x="+x);
       }

   }

   static class Sub extends Base{

       int x =30;
       public Sub(){
           super();//此处的super()；去掉也可以，默认执行super(); 而且只能在构造函数的第一行，如果放在其他行，编译错误，
           this.printMessage();
           x=40;

       }

       public void printMessage(){
           System.out.println("Sub.x="+x);
       }
  }
}
