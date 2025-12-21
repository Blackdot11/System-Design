import java.util.Date;

public class SimpleLogFormatter implements LogFormatter {
    @Override
    public String format(LogMessage msg) {
        return "[" + new Date(msg.getTimestamp()) + "] " 
                + msg.getLevel() + " - " + msg.getMessage();
    }
}
