package cn.ply.cloud.algorithmsvr.leetcode;

import java.util.HashMap;

/**
 * @Author:ply
 * @Description: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * @Date: created in 2019/4/15
 * @Modified By:
 */
public class TwoSum {
    /**
     * 暴力迭代法求解
     *
     * @param numList
     * @param target
     * @return
     */
    public static int[] violence(int[] numList, int target) {
        for (int i = 0, l = numList.length; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (numList[i] + numList[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("没有找到符合要求的数！");
    }


    /**
     * 一遍hash法
     *
     * @param numList
     * @param target
     * @return
     */
    public static int[] hash(int[] numList, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(numList.length);
        for (int i = 0, l = numList.length; i < l; i++) {
            Integer integer = hashMap.get(target - numList[i]);
            if (integer != null) {
                return new int[]{integer, i};
            }
            hashMap.put(numList[i], i);
        }
        throw new IllegalArgumentException("没有找到符合要求的数！");
    }
}
