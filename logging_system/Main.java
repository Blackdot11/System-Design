public class Main {
    public static void main(String[] args) {

        LoggerConfig config = new LoggerConfig(
                LogLevel.DEBUG,
                new JsonLogFormatter(),
                new ConsoleAppender()
        );

        Logger logger = Logger.getInstance(config);

        logger.info("System is starting...");
        logger.error("File not found");
        logger.debug("Debug details here...");

        logger.updateConfig(
            new LoggerConfig(
                LogLevel.ERROR,
                new SimpleLogFormatter(),
                new FileAppender("logs.txt")
            )
        );

        logger.info("WILL NOT BE LOGGED (level too low)");
        logger.error("This will be logged to file");
    }
}
