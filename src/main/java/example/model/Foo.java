package example.model;

public class Foo {

    private String laowen= "laowen";

    public String m() {
        return "foo -- ";
    }

    public String staticM() {
        return "foo -- ";
    }

    public String staticM1(String name) {
        return "foo static m1 --  " + name;
    }

    public String staticM2(String name) {
        return "foo static m2 --  " + name;
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
