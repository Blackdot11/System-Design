import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LogMessage {
    private final LogLevel level;
    private final String message;
    private final long timestamp;
    private final Map<String, Object> context;

    public LogMessage(LogLevel level, String message, Map<String, Object> context) {
        this.level = level;
        this.message = message;
        this.context = context != null ? context : new HashMap<>();
        this.timestamp = System.currentTimeMillis();
    }

    public LogLevel getLevel() { 
        return level; 
    }
    public String getMessage() { 
        return message; 
    }
    public long getTimestamp() { 
        return timestamp; 
    }
    public Map<String, Object> getContext() { 
        return context; 
    }
}
