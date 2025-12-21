public class LevelFilterHandler extends LogHandler {
    private final LogLevel threshold;

    public LevelFilterHandler(LogLevel threshold) {
        this.threshold = threshold;
    }

    @Override
    protected boolean shouldHandle(LogMessage msg) {
        return msg.getLevel().isGreaterOrEqual(threshold);
    }

    @Override
    protected void process(LogMessage msg) {
        // Filtering only
    }
}
