package mysort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputData {

  public static void main(String[] args) {

    try {

      File f = new File("data.txt");
      BufferedReader bReader = new BufferedReader(new FileReader(f));

      // 类似于c++里的vector，就当是链表就好了
      // list是一个接口ArrayList类用来实现我们所需要的数组的功能

      List<Integer> InteList = new ArrayList<>();
      String temp;

      while ((temp = bReader.readLine()) != null) {
        // readLine一次读取一行，到达末尾返回null
        InteList.add(Integer.parseInt(temp));
      }

      bReader.close();

        for (Integer integer : InteList) {
            // toString()方法用以将Integer转为对应的字符串
            System.out.println(integer.toString() + " ");
        }

    } catch (Exception _) {
    }
  }
}