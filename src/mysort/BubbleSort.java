
package mysort;

import javax.swing.*;

public class BubbleSort extends VisibleSort implements SORT, Runnable {

    Thread thread;

    BubbleSort(long[] data, JFrame UIFrame) {
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
        int len = t.length;
        if (len == 0 || len == 1) {
            return;
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0, subLen = len - 1 - i; j < subLen; j++) {
                if (t[j + 1] < t[j]) {
                    long tmp = t[j + 1];
                    t[j + 1] = t[j];
                    t[j] = tmp;
                }
            }
            try {
                Thread.sleep(0); // 每次重画都要有一定的延迟，为了看清！
            } catch (Exception _) {
            }
            super.repaint();
        }
    }
}