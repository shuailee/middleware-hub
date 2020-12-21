package com.shuailee.study.basicknowledge.threadlocale;

/**
 * @program: study-javacore
 * @description:
 * @create: 2019-10-28 10:10
 **/
public class ThreadLocalDemo {


    public static void main(String[] args) {

        new ThreadLocalDemo().demo1();
    }

    public void demo1()
    {
        //在某一线程声明了ABC三种类型的ThreadLocal
       ThreadLocal<Integer> sThreadLocalA = new ThreadLocal<Integer>();
       /* ThreadLocal<Integer> sThreadLocalB = new ThreadLocal<Integer>();
        ThreadLocal<Integer> sThreadLocalC = new ThreadLocal<Integer>();*/

        for (int i = 0; i < 30; i++) {
            //ThreadLocal<Integer> sThreadLocalA = new ThreadLocal<Integer>();
            if(i==19){
                System.out.println("...");
            }
            sThreadLocalA.set(i);
        }

        System.out.println(sThreadLocalA.get());
    }
}
