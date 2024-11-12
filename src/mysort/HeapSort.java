
package mysort;

import javax.swing.*;

public class HeapSort extends VisibleSort implements SORT, Runnable {

    Thread thread;
    long count = 0;

    HeapSort(long[] data, JFrame UIFrame) {
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

    public void sort(long[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);
            adjustHeap(arr, 0, j);
            // 取余和count计数都是为解决sleep时间精度的问题
            if (count % 50 > 0) {
                try {
                    Thread.sleep(0); // 每次重画都要有一定的延迟，为了看清！
                } catch (Exception _) {
                }
            } else {
                try {
                    Thread.sleep(1); // 每次重画都要有一定的延迟，为了看清！
                } catch (Exception _) {
                }
            }
            count++;
        }
    }

    public void adjustHeap(long[] arr, int i, int length) {
        long temp = arr[i];

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
                try {
                    Thread.sleep(0); // 每次重画都要有一定的延迟，为了看清！
                } catch (Exception _) {
                }
                super.repaint();
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    public static void swap(long[] arr, int a, int b) {
        long temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
