package com.shuailee.study.datastructure.algorithm;

import java.util.Arrays;

public class MergeArr {


    public static void main(String[] args) {

        // 合并两个有序数组
        int a[] = {0, 1, 5, 6, 7, 9, 14};
        int b[] = {2, 4, 8, 10, 13};
        int c[] = merge(a, b);
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(merge2(a, b)));
    }

    /**
     * 1 计算两个数组长度，然后循环，对比两个数组的相同位置的大小放入新数组
     * 2 直到短的数组遍历完毕
     * 3 通过标识字段判断哪个数组没有读取完毕，循环读取继续放入新数组
     * */
    static int[] merge2(int[] aArr, int[] bArr) {

        int i = 0;
        int j = 0;
        int k = 0;
        int c[] = new int[aArr.length + bArr.length];
        // 前提是两个数组是有序的
        // 通过第一次循环将两个数组按照从小到大放到新的数组中，直到短的数组获取完毕
        for (k = 0; k < c.length; k++) {
            if (i >= aArr.length || j >= bArr.length) {
                break;
            }
            if (aArr[i] < bArr[j]) {
                c[k] = aArr[i];
                i++;
            } else {
                c[k] = bArr[j];
                j++;
            }
        }

        // 通过标识字段判断哪个数组没有读取完毕，循环读取继续放入新数组
        while (i < aArr.length) {
            c[k] = aArr[i];
            i++;
            k++;
        }
        while (j < bArr.length) {
            c[k++] = bArr[j++];
        }
        return c;
    }


    /**
     * 先合并数组，在排序
     * */
    static int[] merge(int[] aArr, int[] bArr) {
        // 将两个数组合成一个数组
        int length = aArr.length + bArr.length;
        int[] cArr = new int[length];
        for (int i = 0; i < length; i++) {
            if (i < aArr.length) {
                cArr[i] = aArr[i];
            } else {
                cArr[i] = bArr[i - aArr.length];
            }
        }
        // 排序
        return bubbleSort(cArr);

    }

    /**
     * 冒泡
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < arr.length; j++) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
            System.out.println("第" + i + "次循环后效果:" + Arrays.toString(arr));
        }
        return arr;
    }


}
