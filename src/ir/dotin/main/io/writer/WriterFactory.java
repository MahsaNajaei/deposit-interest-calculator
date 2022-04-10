package ir.dotin.main.io.writer;

public class WriterFactory {
    public Writer getWriter(String writerType) {
        if (writerType != null && writerType.equalsIgnoreCase("file")) {
            return new FileWriter();
        }
        return null;
    }
}
