package com.shuailee.study.datastructure.algorithm;


import java.util.Arrays;

public class Sortsuanfa {

    /**
     * http://www.xiongfrblog.cn/%E5%8D%81%E5%A4%A7%E6%8E%92%E5%BA%8F%E7%AE%97%E6%B3%95%E5%85%A8%E9%9D%A2%E8%A7%A3%E6%9E%90-Java%E5%AE%9E%E7%8E%B0.html
     */
    public static void main(String[] args) {

        int[] arr = {2, 8, 5, 7, 4, 6, 1};

        //  insertSort(arr);
        //  selectSort(arr);
        //  bubbleSort(arr);


        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 冒泡排序算法
     * 原理：比较两个相邻的元素，将值大的元素交换到右边，然后依次将之后的每一个元素与它后面的每一个元素进行比较；O(n的平方)
     * http://www.xiongfrblog.cn/images/sort/bubbleSort.gif
     * <p>
     * 思路：依次比较相邻的两个数，将比较小的数放在前面，比较大的数放在后面.每次外层循环结束就找出了一个最大值放在最后面
     * 第一次外层循环开始，内层循环中第一次比较结束后，较大的值在第2位，然后第2个再跟第三个比较，较大的值被移到第3个，依次比较结束,最大的值将会被移到最末尾，然后本轮内层循环结束。
     * 然后在进行第二次外层循环
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 比較的永远是相鄰的元素，不断变化的
            for (int j = 1; j < arr.length; j++) {
                // 如果前面的数大于后面的数就把两者交换位置
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


    public static int[] bubbleSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 比較的永远是相鄰的元素，不断变化的
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 如果前面的数大于后面的数就把两者交换位置
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            System.out.println("第" + i + "次循环后效果:" + Arrays.toString(arr));
        }
        return arr;
    }

    /**
     * 选择排序
     * 原理：每次循环内都由一个数去跟其他数比较，每次比较都选择最小的数来进行下一次比较，并不断更新小数的下标。
     * 这样在一次循环结束时就能得到最小数的下标，再通过一次交换将最小的数放在最前面，通过n-1次循环之后完成排序。
     * 相对于冒泡排序来说，比较的次数并没有改变，但是数据交换的次数大大减少。
     * http://www.xiongfrblog.cn/images/sort/selectSort.gif
     */
    public static int[] selectSort(int[] arr) {

        if (arr == null) {
            return null;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            //保存每次比较的最小值下标，用于交换使用
            int minNumIndex = i;
            //j控制每个元素的比较次数，每次比较结束最小值会放到最前面，下次比较时就无需在比较跳过它，所以初始值是i+1
            for (int j = i + 1; j < len; j++) {
                //每一次比较都要记录最小值下标
                if (arr[minNumIndex] > arr[j]) {
                    minNumIndex = j;
                }
            }
            //每个元素和其他元素比较完毕，如果最小值索引有发生变化则将最小值交换到最前面的位置
            if (minNumIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minNumIndex];
                arr[minNumIndex] = tmp;
            }
            System.out.println("第" + i + "次循环后效果:" + Arrays.toString(arr));
        }

        return arr;

    }


    /**
     * 插入排序
     * 原理：假设第一个元素是已经排过序的最小值，然后取下一个数与已经排好序的元素由后向前比较，如果比当前位置排序好的数小，则将排序好的数往后移一位，
     * 重复以上步骤，最终找到合适的位置终止循环并将其插入
     * <p>
     * http://www.xiongfrblog.cn/images/sort/insertSort.gif
     */
    public static int[] insertSort(int[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length < 2) {
            return arr;
        }
        int len = arr.length;

        //假设第一个为排序后的最小值，所以从1开始
        for (int i = 1; i < len; i++) {
            //记录比较元素的原始位置
            int j = i;
            //记录比较元素的原始值
            int target = arr[i];

            //拿目标元素向前逐个比较，每比较一次将大于目标元素的元素往后移一位,直到遇到小于目标元素的值就终止循环，返回索引位置
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            //更新目标值到新位置
            arr[j] = target;

            System.out.println("第" + i + "次循环后效果:" + Arrays.toString(arr));
        }
        return arr;


    }



    /**
     * 快速排序：quickSort方法通过递归的方式，实现了分而治之的思想
     *
     *   ①先从队尾开始向前扫描且当low < high时,如果a[high] > tmp,则high–,但如果a[high] < tmp,则将high的值赋值给low,即
     *      arr[low] = a[high],同时要转换数组扫描的方式,即需要从队首开始向队尾进行扫描了
     *   ②同理,当从队首开始向队尾进行扫描时,如果a[low] < tmp,则low++,但如果a[low] > tmp了,则就需要将low位置的值赋值给high位置,
     *      即arr[low] = arr[high],同时将数组扫描方式换为由队尾向队首进行扫描.
     *   ③不断重复①和②,直到low>=high时(其实是low=high),low或high的位置就是该基准数据在数组中的正确索引位置.
     *
     * */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 用分治法递归数列的两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 得到基准元素位置，初始第1个为基准元素开始
     * partition方法则实现元素的移动，让数列中的元素依据自身大小，分别移动到基准元素的左右两边
     * */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束
        while (right >= left) {
        //right指针从右向左进行比较
            while (right >= left) {

                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;//坑的位置发生改变
                    left++;
                    break;
                }
                right--;
            }
            //left指针从左向右进行比较
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    /**
     * 另外一种获取基准位置的方式
     * */
    public int index(int[] arr, int low, int high){
        // 基准数据
        int tmp = arr[low];
        while (low < high) {
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low，填原来的坑，挖新坑 high
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high  填原来的坑high，挖新坑 low
            arr[high] = arr[low];

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low] = tmp;
        return low; // 返回tmp的正确位置
    }


}
