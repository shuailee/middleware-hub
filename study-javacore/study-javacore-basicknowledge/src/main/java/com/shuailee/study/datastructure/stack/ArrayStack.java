package com.shuailee.study.datastructure.stack;

/**
 * @program: study-javacore
 * @description: 实现一个栈 后入先出
 * @create: 2019-10-24 22:32
 **/
public class ArrayStack {
    /**
     * 栈容器
     */
    private String[] items;
    /**
     * 当前栈中元素的个数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int size;


    /**
     * 初始化栈
     */
    public ArrayStack(int size) {
        this.items = new String[size];
        this.size = size;
        this.count = 0;
    }

    public boolean push(String item) {
        if (size <= count) {
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        String tmp = items[count-1];
        --count;
        return tmp;
    }

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");

        while (true){
            if(!stack.isEmpty()){
                System.out.println(stack.pop());
            }
        }

    }
}
