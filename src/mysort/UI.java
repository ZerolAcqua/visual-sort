package mysort;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;


public class UI extends JFrame implements ActionListener, ItemListener {

  // 存储
  long[] data;
  long[] order_data;
  int length = 0;
  boolean finish = false;

  // 菜单
  private final JMenuItem fileExit = new JMenuItem("退出");
  private final JMenuItem fileOpen = new JMenuItem("打开");

  // 按钮
  private final JButton start = new JButton("开始");
  private final JButton quit = new JButton("退出");
  private final WindowCloser listener = new WindowCloser();
  private final JButton search = new JButton("查找");
  private final JButton output = new JButton("输出结果");

  // 单选按钮
  private int mode = 0;

  private final ButtonGroup sort_group = new ButtonGroup();
  private final JRadioButton bubble_sort = new JRadioButton("冒泡排序", true);
  private final JRadioButton quick_sort = new JRadioButton("快速排序");
  private final JRadioButton shell_sort = new JRadioButton("希尔排序");
  private final JRadioButton select_sort = new JRadioButton("选择排序");
  private final JRadioButton heap_sort = new JRadioButton("堆排序");

  // 复选框
  JCheckBox hide = new JCheckBox("隐藏", true);

  // 文本栏
  private final JTextField text = new JTextField(10);
  private final JTextField file_text = new JTextField(10);
  private final JTextField result_text = new JTextField(10);
  private final JTextField out_text = new JTextField(10);

  // 标签
  JLabel enter = new JLabel("输入要查找的数字");
  JLabel file_name = new JLabel("输入文件路径：");
  JLabel search_result = new JLabel("查找结果：");
  JLabel out_result = new JLabel("输出文件路径：");

  // 文件
  File file;

  // 构造函数
  public UI() {
    super("排序程序");
    this.setIconImage(new ImageIcon("../assets/logo.jpg").getImage());
    // 菜单项
    JMenu file = new JMenu("操作");
    file.add(fileOpen);
    fileOpen.setEnabled(true);
    fileOpen.addActionListener(this);

    file.add(fileExit);
    fileExit.setEnabled(true);

    JMenuBar bar = new JMenuBar();
    bar.add(file);
    setJMenuBar(bar);
    fileExit.addActionListener(this);

    // 按钮
    pack();
    setVisible(true);
    quit.addActionListener(this);
    start.addActionListener(this);
    search.addActionListener(this);
    output.addActionListener(this);

    // 复选框
    hide.addItemListener(this);
    if (hide.isSelected()) {
      bubble_sort.setText("****");
      quick_sort.setText("****");
      shell_sort.setText("****");
      select_sort.setText("***");
      heap_sort.setText("****");
    }

    // 单选按钮
    bubble_sort.addItemListener(this);
    quick_sort.addItemListener(this);
    shell_sort.addItemListener(this);
    select_sort.addItemListener(this);
    heap_sort.addItemListener(this);

    // 文本栏
    text.setEditable(true);
    out_text.setEditable(false);
    out_text.setText("d:\\array.txt");
    result_text.setEditable(false);
    file_text.setEditable(false); // 打开文件的路径

    // 主界面
    setup();
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  }

  // 从exit或quit退出时的确认对话框
  // 使用了showConfirmDialog
  public void actionPerformed(ActionEvent e) {
    // 退出
    if (e.getSource() == fileExit || e.getSource() == quit) {
      int n = JOptionPane.showConfirmDialog(null, "确认退出吗?", "确认",
                                            JOptionPane.YES_NO_OPTION);
      if (n == JOptionPane.YES_OPTION)
        System.exit(0);
    }
    // 打开文件
    else if (e.getSource() == fileOpen) {
      JFileChooser chooser = new JFileChooser();

      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

      chooser.showDialog(new JLabel(), "选择");

      file = chooser.getSelectedFile();
      if (file != null) {
        file_text.setText(file.getAbsoluteFile().toString());
        // 读取数据
        Array a = new Array();
        data = a.readData(file.getAbsoluteFile().toString());
        length = data.length;
        order_data = new long[length];
      }
    }
    // 开始排序
    else if (e.getSource() == start && file != null) {
      Sort();
    }
    // 查找
    else if (e.getSource() == search && finish) {
      int number = Integer.parseInt(text.getText());
      FindNumber(number);
    } else if (e.getSource() == output && file != null) {
      OutputData out = new OutputData();
      try {
        out.Output(order_data);
      } catch (IOException _) {
      }
    }
  }

  // 单选按钮、复选框listener
  public void itemStateChanged(ItemEvent e) {
    if (e.getSource() == bubble_sort) {
      mode = 0;
    } else if (e.getSource() == select_sort) {
      mode = 1;
    } else if (e.getSource() == shell_sort) {
      mode = 2;
    } else if (e.getSource() == quick_sort) {
      mode = 3;
    } else if (e.getSource() == heap_sort) {
      mode = 4;
    } else if (e.getSource() == hide) {
      if (hide.isSelected()) {
        bubble_sort.setText("****");
        quick_sort.setText("****");
        shell_sort.setText("****");
        select_sort.setText("***");
        heap_sort.setText("****");
      } else {
        bubble_sort.setText("冒泡排序");
        quick_sort.setText("快速排序");
        shell_sort.setText("希尔排序");
        select_sort.setText("选择排序");
        heap_sort.setText("堆排序");
      }
    }
  }

  // 布局
  private void setup() {

    // 输入界面区域
    JPanel inputArea = new JPanel();
    inputArea.setLayout(new FlowLayout());
    inputArea.add(file_name);
    inputArea.add(file_text);
    inputArea.add(enter);
    inputArea.add(text);
    inputArea.add(search);

    // 输出界面区域
    JPanel outputArea = new JPanel();
    JPanel outArea = new JPanel();
    outArea.add(out_result);
    outArea.add(out_text);
    JPanel searchArea = new JPanel();
    searchArea.add(search_result);
    searchArea.add(result_text);
    outputArea.setLayout(new GridLayout(1, 2));
    outputArea.add(outArea);
    outputArea.add(searchArea);

    // 开始和退出区域
    JPanel Start_QuitArea = new JPanel();
    Start_QuitArea.setLayout(new GridLayout(1, 3));
    Start_QuitArea.add(start);
    Start_QuitArea.add(output);
    Start_QuitArea.add(quit);

    // 排序方法选择
    sort_group.add(bubble_sort);
    sort_group.add(quick_sort);
    sort_group.add(shell_sort);
    sort_group.add(select_sort);
    sort_group.add(heap_sort);

    JPanel Sort_Area = new JPanel();
    Sort_Area.add(bubble_sort);
    Sort_Area.add(select_sort);
    Sort_Area.add(shell_sort);
    Sort_Area.add(quick_sort);
    Sort_Area.add(heap_sort);
    Sort_Area.add(hide);
    Sort_Area.setBorder(BorderFactory.createTitledBorder(
        "排序方法(请不要在排序过程中切换排序!)"));

    // 上方的GridLayout布局
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(2, 1));
    p1.add(inputArea);
    p1.add(outputArea);

    // 下方的GridLayout布局
    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(2, 1));
    p2.add(Sort_Area);
    p2.add(Start_QuitArea);

    // 总的布局
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add("North", p1);
    getContentPane().add("South", p2);

    setSize(500, 500);
    setLocationRelativeTo(null); // 居中显示
    setVisible(true);
    addWindowListener(listener);
  }

  // 排序
  void Sort() {
    System.arraycopy(data, 0, order_data, 0, length);

    // 排序算法
    switch (mode) {
    case 0:
        new BubbleSort(order_data, this);
        break;
    case 1:
        new SelectSort(order_data, this);
        break;
    case 2:
        new ShellSort(order_data, this);
        break;
    case 3:
        new QuickSort(order_data, this);
        break;
    case 4:
        new HeapSort(order_data, this);
        break;
    }
    finish = true;
  }

  // 查找
  void FindNumber(int number) {
    int i;
    for (i = 0; i < order_data.length; ++i) {
      if (order_data[i] == number) {
        result_text.setText("数字位于第" + (i + 1) + "位");
        return;
      }
    }
    result_text.setText("没有此数字!");
  }

  public static void main(String[] args) {
      new UI();
  }
}

// 点击叉退出时的确认框
// 使用了showConfirmDialog
class WindowCloser extends WindowAdapter {
  public void windowClosing(WindowEvent we) {
    int n = JOptionPane.showConfirmDialog(null, "确认退出吗?", "确认",
                                          JOptionPane.YES_NO_OPTION);
    if (n == JOptionPane.YES_OPTION)
      System.exit(0);
  }
}

interface SORT {
  void startSort();
}
