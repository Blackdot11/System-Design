// Directory class representing directories (Composite)
public class Directory extends FileSystemNode {

    public Directory(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "📁 " + getName() + " (" + getChildren().size() + " items)");

        for (FileSystemNode child : getChildren()) {
            child.display(depth + 1);
        }
    }
}
