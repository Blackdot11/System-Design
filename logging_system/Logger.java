import java.util.HashMap;

public class Logger {
    private static volatile Logger instance;
    private volatile LoggerConfig config;
    private volatile LogHandler root;

    private Logger(LoggerConfig config) {
        this.config = config;
        rebuildChain();
    }

    public static Logger getInstance(LoggerConfig config) {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger(config);
                }
            }
        }
        return instance;
    }

    public void updateConfig(LoggerConfig newConfig) {
        this.config = newConfig;
        rebuildChain();
    }

    private void rebuildChain() {
        LevelFilterHandler filter = new LevelFilterHandler(config.level);
        FormattingHandler formatter = new FormattingHandler(config.formatter);
        AppenderHandler appender = new AppenderHandler(config.appender);

        filter.setNext(formatter);
        formatter.setNext(appender);

        this.root = filter;
    }

    public void log(LogLevel lvl, String msg) {
        LogMessage lm = new LogMessage(lvl, msg, new HashMap<>());
        root.handle(lm);
    }

    public void debug(String msg) { log(LogLevel.DEBUG, msg); }
    public void info(String msg) { log(LogLevel.INFO, msg); }
    public void warn(String msg) { log(LogLevel.WARNING, msg); }
    public void error(String msg) { log(LogLevel.ERROR, msg); }
    public void fatal(String msg) { log(LogLevel.FATAL, msg); }
}
