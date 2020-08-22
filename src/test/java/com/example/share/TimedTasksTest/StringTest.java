package com.example.share.TimedTasksTest;

public class StringTest {

    public static void main(String[] args) {
        String name = "1233";
//        System.out.println(Integer.parseInt(name));
        int[] arrys = new int[]{1,2,3,4,5,2,3,1,2,4,5,6,7};
        findMajorityElement(arrys);
    }

    /**
     * 找出长用的数字：
     * 给出一个长度为n的数组，其中一个数字出现的次数至少为n/2，找出这个数字。
     */
    public static void findMajorityElement(int[] arrys){
        // 构建一个静态的栈
        int[]  stack = new int[arrys.length];
        // 定义栈的指针
        int front = -1;
        //遍历给出的数组
        for (int i = 0; i < arrys.length; i++) {
            // 判断元素为空，那么直接将元素入栈
            if (front == -1){
                stack[++front] = arrys[i];
            }else if (stack[front] == arrys[i]){ // 该元素是否与栈的元素一致-->继续入栈
                stack[++front] = arrys[i];
            }else {
                front--;
            }
        }
        System.out.println(stack[0]);
    }
}
