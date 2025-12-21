import java.util.Date;

public class JsonLogFormatter implements LogFormatter {
    @Override
    public String format(LogMessage msg) {
        return "{"
                + "\"timestamp\": \"" + new Date(msg.getTimestamp()) + "\","
                + "\"level\": \"" + msg.getLevel() + "\","
                + "\"message\": \"" + msg.getMessage() + "\","
                + "\"context\": \"" + msg.getContext().toString() + "\""
                + "}";
    }
}
