package mysort;

import javax.swing.*;

public class ShellSort extends VisibleSort implements SORT, Runnable {

    int[] dis = {1023, 511, 255, 63, 1}; // Shell排序的步长存储数组,设定为3趟
    int t = dis.length;
    long count = 0;

    Thread thread;

    ShellSort(long[] data, JFrame UIFrame) {
        super(data, UIFrame);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            startSort();
        } catch (Exception e) {
            System.err.println("错误！");
        }
    }

    @Override
    public void startSort() {
        sort(a);
    }

    public void sort(long[] t1) {

        // 扩容，a[0]用来作为哨兵
        int k;
        for (k = 0; k < t; ++k) {
            ShellInsert(t1, dis[k]); // 逐趟排序
        }
    }

    void ShellInsert(long[] a, int dis) // 单趟Shell排序,增量为dis
    {
        int i, j;
        long tem; // 临时变量代替哨兵
        for (i = dis; i < a.length; ++i) {
            if (a[i] < a[i - dis]) {
                tem = a[i]; // 暂存在哨兵处
                for (j = i - dis; j >= 0 && (tem < a[j]); j -= dis) {
                    a[j + dis] = a[j];
                }
                a[j + dis] = tem; // 完成一趟，哨兵元素插入到恰当位置
                // 取余和count计数都是为解决sleep时间精度的问题
                if (count % 50 > 0) {
                    try {
                        Thread.sleep(0); // 每次重画都要有一定的延迟，为了看清！
                    } catch (Exception _) {
                    }
                    super.repaint();
                } else {
                    try {
                        Thread.sleep(1); // 每次重画都要有一定的延迟，为了看清！
                    } catch (Exception _) {
                    }
                    super.repaint();
                }
                count++;
            }
        }
    }
}
