package com.shu.alogrithm;

/**
 * @Author shuxibing
 * @Date 2020/4/25 16:01
 * @Uint d9lab_2019
 * @Description: 回溯算法经典问题
 *
 *1 边界条件
 *2 选择
 *
 * 套路：
 * result = []
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 */
public class BackTrace {

}
