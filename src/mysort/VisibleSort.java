package mysort;

import javax.swing.*;
import java.awt.*;

/**
 * 界面和数组的可视化 to make sort visually
 */
public class VisibleSort extends JPanel {
    JFrame jFrame;
    long[] a;

    VisibleSort(long[] data, JFrame UIFrame) {
        jFrame = UIFrame;
        a = data;
        windowFrom();
    }

    // 排序可视化
    public void paint(Graphics g) {
        // 每次画之前都要先清空画板
        g.clearRect(0, 0, 1500, 800);
        int step = 100000 / this.getWidth();
        for (int i = 0; i < a.length / step; i++) {
            g.setColor(Color.BLACK); // 设置黑色
            // 这里设置绘制矩形的参数
            g.fillRect(i, getHeight() - (int) a[i * step] * this.getHeight() / 100000,
                    1, (int) a[i * step] * this.getHeight() / 100000);
        }
    }

    // 窗口设置
    public void windowFrom() {
        jFrame.add(this);
        jFrame.setVisible(true);
    }
}
