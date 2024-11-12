package mysort;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class OutputData {

  public void Output(long[] a) throws IOException {
    File file = new File("array.txt");
    FileWriter out = new FileWriter(file);
      for (long l : a) {
          out.write(l + "\n");
      }
    out.close();
  }
}
