package mysort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Array {
    long[] array;

    public long[] readData(String str) {

        long start = System.currentTimeMillis();
        // long t1[]= {0};
        File file = new File(str);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            List<Integer> list = new ArrayList<>();
            // ArrayList是一个大小可改变的数组，规模是动态增加的
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
                // 向List集合中添加按行读取并整型化的数据
            }
            array = new long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = Integer.parseInt(list.get(i).toString());
                // 最终转换成整数存入
            }

            long stop = System.currentTimeMillis();
            System.out.println("Read time costs: " + (stop - start) + "ms");

        } catch (IOException _) {
        }

        return array;
    }
}