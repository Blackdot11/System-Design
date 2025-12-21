public class FormattingHandler extends LogHandler {
    private final LogFormatter formatter;

    public FormattingHandler(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    protected boolean shouldHandle(LogMessage msg) {
        return true;
    }

    @Override
    protected void process(LogMessage msg) {
        msg.getContext().put("formatted", formatter.format(msg));
    }
}
