
//Defines the package for the class
package directorysizecalculator;

/**
 *
 * @author Ahoura Minaeian
 */

//Defines the public class File, inheriting properties/methods from FileSystemItem.
public class File extends FileSystemItem {
    private long size;
    //private field to store file's size in bytes
    
    //Public constructor taking a String name (for the file's name) and a long size (for the file's size).
    public File(String name, long size) {
        this(name, size, null);
        //Calls the main constructor; setting the parent directory to null.
    }
    
    //Main public constructor. It takes the file's name (String), its size (long), and a reference to its containing directory, parent (Directory).
    public File(String name, long size, Directory parent) {
        super(name, parent);
        //Calls the superclass constructor to initialize name and parent.
        this.size = size;
    }
    
    //Overrides the getSize() method inherited from FileSystemItem.
    @Override
    public long getSize() {
        return size;
        //Returnrs file's size using Getter Method.
    }
    
    //Allows changing the file's size after creation using Setter Method.
    public void setSize(long size) {
        this.size = size;
        //Sets the new value for the size field.
    }
    
    //Overrides the toString() method from the Object class.
    @Override
    public String toString() {
        return name + " (" + size + " bytes)";
        //Returns the file's name and size in a readable format.
    }
}
