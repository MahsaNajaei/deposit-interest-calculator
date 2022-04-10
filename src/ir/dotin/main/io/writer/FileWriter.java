package ir.dotin.main.io.writer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class FileWriter implements Writer {
    public static String filePath;

    public void write(Map<Integer, BigDecimal> output) {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(filePath)) {
            for (Map.Entry entry : output.entrySet()) {
                fileWriter.write(entry.getKey() + "#" + entry.getValue());
                fileWriter.write("\n");
            }
        } catch (IOException e) {
              e.printStackTrace();
        }
    }
}
