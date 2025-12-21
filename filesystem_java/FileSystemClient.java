import java.util.Scanner;

// Client code to test the file system
public class FileSystemClient {
    public static void main(String[] args) {

        FileSystem fs = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("File System Manager - Commands:");
        System.out.println("1. create <path>");
        System.out.println("2. write <path> <content>");
        System.out.println("3. read <path>");
        System.out.println("4. delete <path>");
        System.out.println("5. display");
        System.out.println("6. exit");

        while (isRunning) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim();

            String[] parts = input.split("\\s+", 3);

            if (parts.length == 0) continue;

            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "create":
                        if (parts.length >= 2) {
                            boolean ok = fs.createPath(parts[1]);
                            System.out.println(ok ? "Path created successfully" : "Failed to create path");
                        } else {
                            System.out.println("Usage: create <path>");
                        }
                        break;

                    case "write":
                        if (parts.length >= 3) {
                            boolean ok = fs.setFileContent(parts[1], parts[2]);
                            System.out.println(ok ? "Content written successfully" : "Failed to write content");
                        } else {
                            System.out.println("Usage: write <path> <content>");
                        }
                        break;

                    case "read":
                        if (parts.length >= 2) {
                            String content = fs.getFileContent(parts[1]);
                            System.out.println(content != null ? "Content: " + content : "Failed to read content");
                        } else {
                            System.out.println("Usage: read <path>");
                        }
                        break;

                    case "delete":
                        if (parts.length >= 2) {
                            boolean ok = fs.deletePath(parts[1]);
                            System.out.println(ok ? "Path deleted successfully" : "Failed to delete path");
                        } else {
                            System.out.println("Usage: delete <path>");
                        }
                        break;

                    case "display":
                        System.out.println("\nFile System Structure:");
                        fs.display();
                        break;

                    case "exit":
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Unknown command.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
