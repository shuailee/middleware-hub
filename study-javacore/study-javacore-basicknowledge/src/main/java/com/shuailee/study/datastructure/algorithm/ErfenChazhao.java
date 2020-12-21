package com.shuailee.study.datastructure.algorithm;

import java.util.Scanner;

public class ErfenChazhao {

    /*
    *
    * 1.二分查找作为一种常见的查找算法，将原本的线性时间提升到了对数的范围，大大缩短了搜索的时间，但是有一个前提，
    * 那就是必须是在有序的数据中进行查找。

　　2.算法思想：二分查找又叫做折半查找。每次取出中间未知的值与待查的关键字进行比较，如果中间位置的关键字比待查关键字大，
    则在前半部分循环这个查找的过程，如果中间关键字比待查关键字小，那么就在后半部分循环查找过程。知道查找到了为止，
    否则序列中没有待查关键字
    * */


    public static void main(String[] args) {
        //下面作为用力输入进行测试
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个有序数组容量:");
        int a = sc.nextInt();
        System.out.println("请输入一个有序数组:");
        int[] b = new int[a];
        for (int i = 0 ; i < a ; i++){
            b[i]=sc.nextInt();
        }
        System.out.println("请输入要进行查找的值：");
        int target = sc.nextInt();
        System.out.println("搜索结果："+search(b,target));

    }
    public static  int search(int[] A,int target){
        int low = 0;
        int high=A.length-1;
        int mid;
        while (low<=high){
            mid = (low+high) /2;
            if(A[mid]==target){
                return mid;
            }else if(A[mid]>target){
                high=mid-1;
            }else {
                low = mid +1;
            }
        }
        return -1;
    }


    public static int search1(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        int mid;
        while(start<=end){
            //mid=(start+end)/2 有可能溢出
            mid=start+(end-start)/2;
            if(nums[mid]==target){
                return mid;
            }
            else if(nums[mid]<target){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return -1;

    }
}
