package mysort;

import javax.swing.*;

public class QuickSort extends VisibleSort implements SORT, Runnable {

    Thread thread;
    int count = 0;

    QuickSort(long[] data, JFrame UIFrame) {
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
        int low = 0;
        int high = a.length - 1;
        QS(a, low, high);
    }

    public int Partition(long[] arr, int low, int high) {
        int left, right;
        long pivotkey;
        left = low;
        right = high;
        pivotkey = arr[low]; // 取第一个元素为枢轴
        while (left < right) {
            while (left < right && arr[right] >= pivotkey) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
                left++;
            }

            while (left < right && arr[left] < pivotkey) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
                right--;
            }
        }
        arr[left] = pivotkey;

        return left; // 返回枢轴位置
    }

    public void QS(long[] a, int low, int high) {
        int pivotLoc; // 枢轴位置
        if (low < high) {
            pivotLoc = Partition(a, low, high);
            // 取余和count计数都是为解决sleep时间精度的问题
            if (count % 25 > 0) {
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
            QS(a, low, pivotLoc - 1); // 递归地对左边的子序列进行快速排序
            QS(a, pivotLoc + 1, high); // 递归地对右边的子序列进行快速排序
            count++;
        }
    }
}