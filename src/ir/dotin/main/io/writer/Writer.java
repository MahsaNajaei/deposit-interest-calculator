package ir.dotin.main.io.writer;

import java.math.BigDecimal;
import java.util.Map;

public interface Writer {
    void write(Map<Integer, BigDecimal> output);
}
