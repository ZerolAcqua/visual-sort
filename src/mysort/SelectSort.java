
package mysort;

import javax.swing.*;

public class SelectSort extends VisibleSort implements SORT, Runnable {
    Thread thread;

    SelectSort(long[] data, JFrame UIFrame) {
        super(data, UIFrame);
        thread = new Thread(this);
        thread.start();
        // TODO Auto-generated constructor stub
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

    public void sort(long[] t) {
        long k;
        if (t.length == 0 || t.length == 1)
            return;
        int j, min, i;
        for (i = 0; i < t.length - 1; i++) {
//      int distance = t.length / 50;
            min = i;
            for (j = i + 1; j < t.length; j++) {
                if (t[j] < t[min]) {
                    min = j;
                }
            }
            if (min != i) {
                k = t[i];
                t[i] = t[min];
                t[min] = k;
            } // 交换元素
            try {
                Thread.sleep(0); // 每次重画都要有一定的延迟，为了看清！
            } catch (Exception _) {
            }
            super.repaint();
        }
    }
}
