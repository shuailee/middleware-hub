package com.shuailee.study.datastructure.linked;


/**
 * 实现一个链表
 */
public class LinkedListDemo {
    // 头结点
    Node head;
    // 下一个节点
    Node current;

    public void add(int data) {
        if (head == null) {
            head = new Node(data);
            current = head;
        } else {
            current.next = new Node(data);
            //把链表的当前索引向后移动一位
            current = current.next;
        }
    }

    /**
     * 遍历链表
     */
    public void print(Node node) {
        if (node == null) {
            return;
        }
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    /**
     * 单链表反转
     */
    public Node reverseList(Node node) {
        if (node == null) {
            return null;
        }
        // 声明一个前置节点，用来临时存储当前节点
        Node pre = null;
        // 声明一个next节点，用来临时存储当前节点的下一个节点，在新的链表中为下一个遍历的节点
        Node next = null;
        while (node != null) {
            next = node.next; // 临时存储当前节点下一个节点
            node.next = pre; // 将当前节点下个节点设置为它前面的节点
            pre = node;       // 临时存储当前节点，在下次循环时做为下个节点的指向
            node = next;  // 将下个节点赋值给当前节点，继续遍历
        }
        return pre;
    }



    public static void main(String[] args) {
        LinkedListDemo linkedListDemo = new LinkedListDemo();
        linkedListDemo.add(1);
        linkedListDemo.add(2);
        linkedListDemo.add(3);
        linkedListDemo.print(linkedListDemo.head);
        Node reverseNode = linkedListDemo.reverseList(linkedListDemo.head);
        linkedListDemo.print(reverseNode);

    }
}
