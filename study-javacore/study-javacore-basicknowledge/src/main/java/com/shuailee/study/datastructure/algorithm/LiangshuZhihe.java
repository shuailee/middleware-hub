package com.shuailee.study.datastructure.algorithm;

import java.util.HashMap;

public class LiangshuZhihe {
    /**
     * 求解一个数组中两数之和是一个指定值
     * */

    public static void main(String[] args) {
        int [] nums={2,5,5,11};
        int [] result=twoSum(nums,10);
        System.out.println(String.format("{ %d,%d }",result[0],result[1]));
    }
    /**
     * 暴力求解
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * */
    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target-nums[i]==nums[j]){
                    return new int [] {i,j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    /**
     * 两遍哈希法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 在第一次迭代中，我们将每个元素的值和它的索引添加到表中。然后，在第二次迭代中，
     * 我们将检查每个元素所对应的目标元素（target - nums[i]target−nums[i]）是否存在于表中。
     * 注意，该目标元素不能是 nums[i]nums[i] 本身！
     *
     * */
    public static int[] twoSumByHashfor(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int result= target-nums[i];
            if(map.containsKey(result)&&map.get(result)!=i){
                return new int [] {map.get(result),i};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 一遍哈希法
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 我们可以一次完成。在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回
     *
     * */
    public static int[] twoSumByHash(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++) {
            int result = target - nums[i];
            if(map.containsKey(result)){
                return new int [] {map.get(result),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
