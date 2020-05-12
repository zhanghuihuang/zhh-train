package com.zhh.train.leetcode.day2;

import java.util.Arrays;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * @date : 2020/5/10 9:29 上午
 */
public class Question1 {
    public static void main(String[] args) {
        Arrays.stream(twoSum(new int[]{3, 2, 4}, 6)).forEach(i -> System.out.println(i));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == sub) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
