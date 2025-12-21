public class AppenderHandler extends LogHandler {
    private final LogAppender appender;

    public AppenderHandler(LogAppender appender) {
        this.appender = appender;
    }

    @Override
    protected boolean shouldHandle(LogMessage msg) {
        return true;
    }

    @Override
    protected void process(LogMessage msg) {
        String formatted = (String) msg.getContext().get("formatted");
        appender.append(formatted);
    }
}
