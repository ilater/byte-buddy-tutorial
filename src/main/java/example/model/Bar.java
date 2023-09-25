package example.model;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class Bar {


    public String m() {
        return "Bar -- ";
    }


    public static String staticM() {
        return "static Bar -- ";
    }


    /**
     * @Argument(0) 代表第0个参数，参数以0开始算
     * @This        代表当前对象
     * @Origin      代表原始方法
     * @param obj    req
     * @param method req
     * @param name   req
     * @return 无
     */
    public static String staticM1(@This Object obj, @Origin Method method, @Argument(0) String name) {
        return "static Bar -- " + name;
    }



    /**
     * @Argument(0) 代表第0个参数，参数以0开始算
     * @This        代表当前对象
     * @Origin      代表原始方法
     * @param obj    req
     * @param method req
     * @param name   req
     * @return 无
     */
    public static String staticM2(@This Object obj, @Origin Method method, @Argument(0) String name,@SuperCall Callable<String> zuper) throws Exception {
        return "static Bar -- " + name + "  --- origin ---" + zuper.call();
    }

    @Override
    public String toString() {
        return "Foo toString   ";
    }

    // 实现冒泡排序 从小到大
    public static void bubbleSort(int[] arr) {
        int temp = 0; // 临时变量
        boolean flag = false; // 是否交换的标志
        for (int i = 0; i < arr.length - 1; i++) { // 表示趟数，一共arr.length-1次。
            for (int j = arr.length - 1; j > i; j--) {
                // 从后往前比较，将小的数交换到前面
                if (arr[j] < arr[j - 1]) {
                    flag = true; // 发生了交换
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                System.out.println("i=" + i + ",j=" + j + ",flag=" + flag + ",temp=" + temp);
            }
            if (!flag)
                break; // 没有发生交换，提前退出
            else
                flag = false; // 重置flag!!!,进行下次判断
        }
    }

}
