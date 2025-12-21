public class ConsoleAppender implements LogAppender {
    @Override
    public void append(String formatted) {
        System.out.println(formatted);
    }
}
