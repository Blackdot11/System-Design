public class LoggerConfig {
    public LogLevel level;
    public LogFormatter formatter;
    public LogAppender appender;

    public LoggerConfig(LogLevel level, LogFormatter formatter, LogAppender appender) {
        this.level = level;
        this.formatter = formatter;
        this.appender = appender;
    }
}
