package com.shuailee.study.datastructure.quene;

/**
 * @program: study-javacore
 * @description: 先入先出
 * @create: 2019-11-05 21:48
 **/
public class ArrayQuene {
    private String[] items;
    private int size = 0;
    //指向队头
    private int head = 0;
    //指向队尾
    private int tail = 0;

    public ArrayQuene(int size) {
        items = new String[size];
        this.size = size;
    }

    /**
     * 入队
     * 入队时head始终指向队头0，tail始终指向队尾（不断累加）
     * 这个方法：随着不停地进行入队、出队操作，head 和 tail 都会持续往后移动。
     * 当 tail 移动到最右边，即使数组中还有空闲空间，也无法继续往队列中添加数据了
     */
    public boolean enquene(String item) {
        //表示队尾没有空间了
        if(tail==size){
            return false;
        }
        items[tail]=item;
        ++tail;
        return true;
    }

    /**
     * 解决上面那个问题需要在入队时进行数据搬移
     * 当队列的 tail 指针移动到数组的最右边后，如果有新的数据入队，我们可以将 head 到 tail 之间的数据，
     * 整体搬移到数组中 0 到 tail-head 的位置
     * */
    public boolean enqueneV2(String item) {
        //表示队尾没有空间了
        if(tail==size){
            // tail ==n && head==0，表示整个队列都占满了
            if(head==0) {
                return false;
            }
            // 执行数据搬移
            for (int i = head; i < tail; i++) {
                items[i-head]=items[i];
            }
            // 搬移完之后重新更新head和tail
            tail = tail-head;
            head = 0;
        }

        items[tail]=item;
        ++tail;
        return true;
    }


    /**
     * 出队
     * 出队时，head不断累加指向队头0+，tail始终指向队尾
     */
    public String dequene() {
        if(head==tail){
            return null;
        }
        String item = items[head];
        ++head;
        return item;
    }


    public static void main(String[] args) {
        ArrayQuene quene=new ArrayQuene(5);
        quene.enquene("a");
        quene.enquene("b");
        quene.enquene("c");
        quene.enquene("d");
        quene.enquene("e");
        quene.enquene("f");

        for (int i = 0; i < 7; i++) {
            System.out.println(quene.dequene());
        }

    }






}
