// File class representing individual files (Leaf)
public class File extends FileSystemNode {

    private String content;
    private String extension;

    public File(String name) {
        super(name);
        this.extension = extractExtension(name);
    }

    private String extractExtension(String name) {
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex > 0) ? name.substring(dotIndex + 1) : "";
    }

    public void setContent(String content) {
        this.content = content;
        updateModifiedTime();
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "📄 " + getName());
    }
}
