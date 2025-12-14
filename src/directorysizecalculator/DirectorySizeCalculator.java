
//Defines the package for this class.
package directorysizecalculator;

//Imports the Scanner utility for reading user input from the console.
import java.util.Scanner;
/**
 *
 * @author Ahoura Minaeian
 */

//Defines the main executable class containing the program logic.
public class DirectorySizeCalculator {
    public static void main(String[] args) {
        //Entry point for Java application
        Scanner Keyboard = new Scanner(System.in);
        //Creates a Scanner object to read input from the standard input stream (System.in).
        Directory root = createSampleFileSystem();
        //Calls the helper method to build the sample file system tree and stores the root directory.
        Directory currentDir = root;
        //Initializes the user's current working directory to the root.
        
        //Prints the welcome message and a list of available command instructions to the user.
        System.out.println("=== Directory Size Calculator ===\n");
        System.out.println("Available commands:");
        System.out.println("  cd <name>     - Change to directory");
        System.out.println("  cd ..         - Go to parent directory");
        System.out.println("  cd /          - Go to root directory");
        System.out.println("  ls            - List directory contents");
        System.out.println("  size          - Calculate total size");
        System.out.println("  exit          - Exit program\n");
        
        //Starts an infinite loop for the main CLI
        while (true) {
            System.out.print(currentDir.getFullPath() + "> ");
            //Prints the current directory path as the command prompt
            String input = Keyboard.nextLine().trim();
            //Reads the entire line of user input and removes leading/trailing whitespace.
            
            //Checks if the user entered exit; if so, it breaks the loop to end the program.
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            
            //Checks if the command starts with the change directory command.
            if (input.startsWith("cd ")) {
                String dirName = input.substring(3).trim();
                //Extracts the target directory name from the input string (everything after "cd ").
                Directory newDir = changeDirectory(currentDir, dirName, root);
                //Calls the helper method to attempt the directory change, returning the new directory or null.
                if (newDir != null) {
                    currentDir = newDir;
                    //If changeDirectory was successful, updates the currentDir to the new location.
                } else {
                    System.out.println("Directory not found: " + dirName);
                    //If the directory change failed (returned null), prints an error message.
                }
            //Checks for the ls (list contents) command.
            } else if (input.equals("ls")) {
                listDirectory(currentDir);
                //Calls the helper method to print the contents of the current directory.
            
            //Checks for the size command.    
            } else if (input.equals("size")) {
                calculateSize(currentDir);
                //Calls the helper method to print the total size of the current directory.
            
            //Handles any non-empty input that doesn't match a valid command.
            } else if (!input.isEmpty()) {
                System.out.println("Unknown command. Type 'exit' to quit.");
            }
        }
        
        //Closes the Scanner resource after the loop terminates.
        Keyboard.close();
        System.out.println("Program terminated.");
        //Prints a final message indicating the program has ended.
    }
    
    //Constructs a predefined file system structure for testing using Helper Method
    private static Directory createSampleFileSystem() {
        Directory root = new Directory("root", null);
        
        //Creates and links Directory and File objects to build a sample hierarchical tree.
        Directory documents = new Directory("documents", root);
        root.addItem(documents);
        
        File report = new File("report.pdf", 2048);
        documents.addItem(report);
        
        File notes = new File("notes.txt", 512);
        documents.addItem(notes);
        
        Directory projects = new Directory("projects", documents);
        documents.addItem(projects);
        
        File project1 = new File("project1.java", 3072);
        projects.addItem(project1);
        
        File project2 = new File("project2.java", 4096);
        projects.addItem(project2);
        
        // Create images directory
        Directory images = new Directory("images", root);
        root.addItem(images);
        
        File photo1 = new File("photo1.jpg", 10240);
        images.addItem(photo1);
        
        File photo2 = new File("photo2.png", 8192);
        images.addItem(photo2);
        
        // Add some files to root
        File readme = new File("readme.txt", 1024);
        root.addItem(readme);
        
        return root;
        //Returns the top-level Directory object.
    }
    
    //Implements the logic for handling cd commands using Helper Method.
    private static Directory changeDirectory(Directory current, String dirName, Directory root) {
        if (dirName.equals("..")) {
            //Handles the special command cd .. (go to parent).
            return current.getParent() != null ? current.getParent() : current;
            //If a parent exists, returns it; otherwise, stays in the current directory.
        }
        
        //Handles the special command cd / (go to root).
        if (dirName.equals("/")) {
            return root;
            //Returns the reference to the main root directory.
        }
        
        //Tries to find an item with the given name in the current directory.
        FileSystemItem item = current.getItem(dirName);
        if (item != null && item instanceof Directory) {
            //Checks if the found item exists and is a Directory
            return (Directory) item;
            //Casts the item to a Directory and returns it as the new current directory.
        }
        
        return null;
        //Returns null if the target is not found or is a file.
    }
    
    //Prints the contents of a given directory using Helper Method.
    private static void listDirectory(Directory dir) {
        System.out.println("Contents of " + dir.getFullPath() + ":");
        //Prints the header for the directory listing.
        
        //Checks if the directory is empty and prints a message if it is.
        if (dir.getItems().isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        
        //List directories first
        for (FileSystemItem item : dir.getItems()) {
            if (item instanceof Directory) {
                System.out.println("  [DIR]  " + item.getName());
            }
        }
        
        //Then list files
        for (FileSystemItem item : dir.getItems()) {
            if (item instanceof File file) {
                System.out.println("  [FILE] " + item.getName() + 
                                 " (" + file.getSize() + " bytes)");
            }
        }
    }
    
    //Prints the total size of a directory using Helper Method.
    private static void calculateSize(Directory dir) {
        long totalSize = dir.getTotalSize();
        //Calls the recursive getTotalSize() method on the Directory object.
        System.out.println("Total size of " + dir.getFullPath() + ": " + 
                          totalSize + " bytes");
        //Prints the full path of the directory and its total calculated size in bytes.
    }
    }
