package com.shuailee.study.datastructure.linked;

/**
 * 单向链表
 */
public class Node {
    // 节点数据
    int data;
    // 下一个节点的指针
    Node next;

    public Node(int data) {
        this.data = data;
        this.next=null;
    }
}
