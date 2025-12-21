import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements LogAppender {
    private final String file;

    public FileAppender(String file) {
        this.file = file;
    }

    @Override
    public synchronized void append(String formatted) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(formatted + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
