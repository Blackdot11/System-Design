import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// Base class for File System Node (Composite Pattern)
public abstract class FileSystemNode {
    private String name;
    private Map<String, FileSystemNode> children;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void addChild(String name, FileSystemNode child) {
        this.children.put(name, child);
        this.modifiedAt = LocalDateTime.now();
    }

    public boolean hasChild(String name) {
        return this.children.containsKey(name);
    }

    public FileSystemNode getChild(String name) {
        return this.children.get(name);
    }

    public boolean removeChild(String name) {
        if (hasChild(name)) {
            children.remove(name);
            return true;
        }
        return false;
    }

    public abstract boolean isFile();
    public abstract void display(int depth);

    public String getName() {
        return name;
    }

    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    protected void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }
}
