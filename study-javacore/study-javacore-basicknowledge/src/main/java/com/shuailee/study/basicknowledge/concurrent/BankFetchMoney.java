package com.shuailee.study.basicknowledge.concurrent;

public class BankFetchMoney {
    public static void main(String[] args) {
        Bank bank=new Bank();
        //模拟两个用户同时取钱
        Thread t1=new MoneyThread(bank);
        Thread t2=new MoneyThread(bank);
        t1.start();
        t2.start();
    }
}

class Bank{
    //银行存款数
    private int money = 1000;
    /**
    * 取款
    * number 取款数
    * synchronized关键词：多线程的同步机制对资源进行加锁，使得在同一个时间，只有一个线程可以进行操作，同步用以解决多个线程同时访问时可能出现的问题。
    * 同步机制可以使用synchronized关键字实现。当synchronized关键字修饰一个方法的时候，该方法叫做同步方法。当synchronized方法执行完或发生异常时，会自动释放锁。
    * 不加改关键词，在并发情况下刚方法会出错（业务错误）
    */
    public synchronized int fetch(int number){
        if(number<0){
            return -1;
        }
        else if(number>money){
            return  -2;
        } else if (money < 0) {
            return -3;
        }else {
            //在未加synchronized关键词的时候，每次计算前线程休眠1秒，这样能确保两个线程同时处在else体内，在第一个线程休眠的过程中，
            // 第二个线程也成功进入了这个else语句块（因为存款的钱还没有取走），当两个线程结束休眠后，
            // 不再进行逻辑判断而是直接将钱取走，所以两个线程都取到了800元钱，此时money为-600
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            money -= number;
            System.out.println("银行剩余 Money: " + money);
            return number;
        }
    }
}

//模拟取款
class MoneyThread extends Thread{
    private Bank bank;
    public MoneyThread(Bank bank){
        this.bank=bank;
    }
    @Override
    public void run() {
        //执行取款，取 800
        System.out.println("取出"+bank.fetch(800));
    }
}
