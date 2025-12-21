public abstract class LogHandler {
    protected LogHandler next;

    public void setNext(LogHandler next) {
        this.next = next;
    }

    public void handle(LogMessage msg) {
        if (shouldHandle(msg)) {
            process(msg);
        }
        if (next != null) {
            next.handle(msg);
        }
    }

    protected abstract boolean shouldHandle(LogMessage msg);
    protected abstract void process(LogMessage msg);
}
