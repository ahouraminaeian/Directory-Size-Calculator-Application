
// Directory representation which can contain files & other directories

// Classes are kept in the same project package
package directorysizecalculator;

// Imports Java collections used to store directory contents
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ahoura Minaeian
 */

// Makes Directory a type of FileSystemItem
public class Directory extends FileSystemItem {
    private List<FileSystemItem> items;
    //Stores all files and subdirectories inside this directory.
    //Uses a list so multiple items can be stored.
    public Directory(String name) {
        this(name, null);
    }
    //Constructor for creating a directory.
    
    public Directory(String name, Directory parent) {
        super(name, parent);
        //Calls the parent constructor to set the directory name.
        this.items = new ArrayList<>();
        //Initializes the list so items can be added.
    }
    
    //Method to add a file or subdirectory.
    //Accepts the base class to allow flexibility.
    public void addItem(FileSystemItem item) {
        item.setParent(this);
        items.add(item);
        //Adds the item to the directory.
    }
    
    //Getter method to access directory contents.
    public List<FileSystemItem> getItems() {
        return new ArrayList<>(items);
    }
    
    
    public FileSystemItem getItem(String name) {
        for (FileSystemItem item : items) {
            if (item.getName().equals(name)) {
                return item;
                //Returns the list of items.
            }
        }
        return null;
    }
    
    // Recursive size calculation
    //Overrides getSize() to calculate directory size.
    @Override
    public long getSize() {
        long total = 0;
        for (FileSystemItem item : items) {
            //Loops through every file and subdirectory.
            total += item.getSize();
            //Adds the size of each item.
        }
        return total;
    }
    
    //Declares a public method that returns a long representing the total size of the directory.
    public long getTotalSize() {
        long total = 0;
        for (FileSystemItem item : items) {
         //Iterates through each FileSystemItem stored in the directory’s items collection.
            total += item.getSize();
            }
        return total;
        //Returns the final accumulated size.
    }
    
    //Declares a public method that returns the full path of the directory as a String.
    public String getFullPath() {
        if (parent == null) {
            return "/" + name;
            //Returns the root directory path using its name.
        }
        
        //Creates a StringBuilder to efficiently build the directory path.
        StringBuilder path = new StringBuilder();
        Directory current = this;
        while (current != null && current.parent != null) {
            //Continues looping upward through the directory tree until the root is reached.
            path.insert(0, "/" + current.getName());
            //Inserts the current directory name at the beginning of the path.
            current = current.parent;
            //Moves to parent directory
        }
        return path.length() > 0 ? path.toString() : "/";
        //Returns the constructed path, or / if the path is empty.
    }
    
    //Overrides the toString() method from the Object class.
    @Override
    public String toString() {
        //Declares the overridden toString() method.
        return name + "/";
        //Returns the directory name with a trailing slash to indicate it is a directory.
    }
}
